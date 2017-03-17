package com.cxstock.action.print;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.cxstock.action.BaseAction;
import com.cxstock.biz.print.PrintBiz;
import com.cxstock.pojo.Tbtemplate;
import com.other.myclass.PublicClass;

public class PrintAction extends BaseAction {

	private PrintBiz printBiz;

	private Integer id;
	private Integer itype;
	private String vcNo;
	private String vcName;
	private String vcTable;
	private String vccode;
	private String vcRemark;
	private Integer companyId;

	private String key;

	private File upload;// 实际上传文件
	private String uploadContentType; // 文件的内容类型
	private String uploadFileName; // 上传文件名

	/**
	 * 获得记录
	 * */
	public void getInfo() {
		try {
			String sql = "select t.* from tbtemplate t";
			sql += " where " + PublicClass.getRightStr("t.companyId");
			if (key != null && key.length() > 0) {
				sql += " and (t.vcNo like '%" + key + "%' or t.vcName like '%"
						+ key + "%')";
			}
			this.outHashMapString(printBiz.getInfo(sql));
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}

	}

	/**
	 * 获得单个记录
	 * */
	public void getSingInfo() {
		try {
			String code = printBiz.getSingInfo(key); /* 信封打印关联销售单据* */
			this.outString(code);
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
	}

	/**
	 * @描述：导出grf
	 * @作者：RC
	 * @日期：2015-07-14
	 * */
	public void downloadFile() {
		try {
			String strPath = ServletActionContext.getServletContext()
					.getRealPath(
							"/grf/" + this.getSession().getAttribute("table"));
			String path = strPath + "/" + vccode;
			File file = new File(path);
			String filename = vcName;
			InputStream fis;
			fis = new BufferedInputStream(new FileInputStream(path));
			byte[] buffer = new byte[fis.available()];
			fis.read(buffer);
			fis.close();
			HttpServletResponse response = this.getResponse();
			response.reset();
			// 先去掉文件名称中的空格,然后转换编码格式为utf-8,保证不出现乱码,这个文件名称用于浏览器的下载框中自动显示的文件名
			response.addHeader("Content-Disposition", "attachment;filename="
					+ filename + ".grf");
			response.addHeader("Content-Length", "" + file.length());
			OutputStream os = new BufferedOutputStream(response
					.getOutputStream());
			response.setContentType("application/octet-stream");
			os.write(buffer);/* 输出文件 */
			os.flush();
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
	}

	/**
	 * @描述：导入模板grf
	 * @作者：RC
	 * @日期：2015-07-14
	 * */
	public void uploadFile() {
		try {
			if (upload == null) {
				this.outString("{success:true,msg:'参数错误'}");
				return;
			}
			String strPath = ServletActionContext.getServletContext()
					.getRealPath(
							"/grf/" + this.getSession().getAttribute("table"));
			/* 生成文件名 */
			SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
			String strFielName = df.format(new Date());
			/* 得到文件类型 */
			String str = uploadFileName.substring(uploadFileName.length() - 3);
			if (!str.equals("grf")) {
				this.outString("{success:true,msg:'格式不正确,请选择.grf格式'}");
				return;
			}
			File target = new File(strPath, strFielName + "." + str);
			try {
				FileUtils.copyFile(upload, target);
				/* 保存名称 */
				Tbtemplate t = new Tbtemplate();
				t.setVccode(strFielName + "." + str);
				t.setId(id);
				printBiz.saveInfo(t);
				this.outString("{success:true,msg:'ok'}");
			} catch (IOException e) {
				e.printStackTrace();
				this.outError();
			}

		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getItype() {
		return itype;
	}

	public void setItype(Integer itype) {
		this.itype = itype;
	}

	public String getVcNo() {
		return vcNo;
	}

	public void setVcNo(String vcNo) {
		this.vcNo = vcNo;
	}

	public String getVcName() {
		return vcName;
	}

	public void setVcName(String vcName) {
		this.vcName = vcName;
	}

	public String getVcTable() {
		return vcTable;
	}

	public void setVcTable(String vcTable) {
		this.vcTable = vcTable;
	}

	public String getVccode() {
		return vccode;
	}

	public void setVccode(String vccode) {
		this.vccode = vccode;
	}

	public String getVcRemark() {
		return vcRemark;
	}

	public void setVcRemark(String vcRemark) {
		this.vcRemark = vcRemark;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public void setPrintBiz(PrintBiz printBiz) {
		this.printBiz = printBiz;
	}

	public PrintBiz getPrintBiz() {
		return printBiz;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public File getUpload() {
		return upload;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public String getUploadContentType() {
		return uploadContentType;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

}
