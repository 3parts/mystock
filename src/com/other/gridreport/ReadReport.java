package com.other.gridreport;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ReadReport {

	/**
	 * 打开文件
	 * */
	public static void Read(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 打开文件准备读取数据
		String FileName = request.getRealPath("grf") + "\\"
				+ request.getSession().getAttribute("table") + "\\"
				+ request.getParameter("report"); // 注意参数名称中字母的大小写，要与URL中保持一致
		FileInputStream fis = new FileInputStream(FileName);

		// 清除已经在缓冲区中的数据，因为这里的响应数据只要文件数据
		// response.resetBuffer();

		ServletOutputStream sos = response.getOutputStream();

		byte[] DataBuf = new byte[1024 * 5];

		// 读文件数据，并写入响应流中
		int ReadBytes = fis.read(DataBuf);
		while (ReadBytes > 0) {
			sos.write(DataBuf, 0, ReadBytes);
			ReadBytes = fis.read(DataBuf);
		}
		fis.close();
		sos.flush();
	}

	/**
	 * 保存记录
	 * */
	public static void Save(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		int DataLen = request.getContentLength();
		if (DataLen > 0) {
			final int BufSize = 1024; // 一次读写数据的缓冲区大小

			// 打开写入文件
			String FileName = request.getRealPath("grf") + "\\"
					+ request.getSession().getAttribute("table") + "\\"
					+ request.getParameter("report");
			FileOutputStream fos = new FileOutputStream(FileName);

			// 注意：要分批读写，不然在某些条件下对大数据(>8K)模板保存不成功
			// 读出客户端发送的数据，并写入文件流
			byte[] DataBuf = new byte[BufSize];
			ServletInputStream sif = request.getInputStream();
			int TotalReadedLen = 0;
			while (DataLen > TotalReadedLen) {
				int TheReadedlen = sif.read(DataBuf, 0, BufSize);
				if (TheReadedlen <= 0)
					break;

				fos.write(DataBuf, 0, TheReadedlen);

				TotalReadedLen += TheReadedlen;
			}
			fos.close();
		}

	}
}
