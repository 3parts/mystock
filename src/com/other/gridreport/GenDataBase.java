package com.other.gridreport;

import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import java.util.zip.DeflaterOutputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

public class GenDataBase {

	// 简要说明：
	// <!--连接数据库，产生Grid++Report需要的XML格式报表数据-->
	// <!--必须安装相应数据库的JDBC驱动-->

	// ！注意：定义 oracle 数据库访问参数，应修改为与实际一致
	public class oracle_jdbc_param {
		public final static String driver = "oracle.jdbc.driver.OracleDriver";
		public final static String url = "jdbc:oracle:thin:@localhost:1521";
		public final static String user = "hr";
		public final static String password = "hr";
	}

	// ！注意：定义 mysql 数据库访问参数，应修改为与实际一致
	public class mysql_jdbc_param {
		public final static String driver = "com.mysql.jdbc.Driver";
		public final static String url = "jdbc:mysql://localhost/gridreport?user=root&password=&useUnicode=true&characterEncoding=utf8";
		public final static String user = "root";
		public final static String password = "root";
	}

	// ！注意：定义 mssql 数据库访问参数，应修改为与实际一致
	public class mssql_jdbc_param {
		public final static String driver = "com.microsoft.jdbc.sqlserver.SQLServerDriver"; // mssql2000
		// jdbc
		public final static String url = "jdbc:microsoft:sqlserver://localhost;DatabaseName=gridreport"; // mssql2000
		// jdbc
		// 如果是应用mssql20005的jdbc驱动，应该注视掉上面两行，而去掉下面两行的注视。说明：mssql2000
		// jdbc可以连接mssql20005 数据库
		// public final static String driver =
		// "com.microsoft.sqlserver.jdbc.SQLServerDriver"; //mssql2005 jdbc
		// public final static String url =
		// "jdbc:sqlserver://localhost;DatabaseName=gridreport"; //mssql2005
		// jdbc
		public final static String user = "sa";
		public final static String password = "";
	}

	// ！注意：定义 odbc 数据库访问参数，应修改为与实际一致
	public class odbc_jdbc_param {
		public final static String driver = "sun.jdbc.odbc.JdbcOdbcDriver";
		public final static String url = "jdbc:odbc:webreport";
		public final static String user = "sa";
		public final static String password = "";
	}

	// ！注意：如果是 oracle 数据库，请将 extends 后的类名改为 oracle_jdbc_param，并将
	// oracle_jdbc_param 中的参数改为与实际一致
	// ！注意：如果是 mysql 数据库，请将 extends 后的类名改为 mysql_jdbc_param，并将 mysql_jdbc_param
	// 中的参数改为与实际一致
	// ！注意：如果是 mssql 数据库，请将 extends 后的类名改为 mssql_jdbc_param，并将 mssql_jdbc_param
	// 中的参数改为与实际一致
	// ！注意：如果是 odbc 数据源，请将 extends 后的类名改为 odbc_jdbc_param，并将 odbc_jdbc_param
	// 中的参数改为与实际一致
	// public class jdbc_param extends oracle_jdbc_param
	public class jdbc_param extends mysql_jdbc_param
	// public class jdbc_param extends mssql_jdbc_param
	// public class jdbc_param extends odbc_jdbc_param
	{
	}

	// ///////////////////////////////////////////////////////////////////////////////////////////////////////
	// 以下枚举指定报表数据的格式类型
	public enum ResponseDataType {
		PlainText, // 报表数据为XML或JSON文本，在调试时可以查看报表数据。数据未经压缩，大数据量报表采用此种方式不合适
		ZipBinary, // 报表数据为XML或JSON文本经过压缩得到的二进制数据。此种方式数据量最小(约为原始数据的1/10)，但用Ajax方式加载报表数据时不能为此种方式
		ZipBase64, // 报表数据为将 ZipBinary 方式得到的数据再进行 BASE64
		// 编码的数据。此种方式适合用Ajax方式加载报表数据
	};

	// 指定报表的默认数据类型，便于统一定义整个报表系统的数据类型
	// 在报表开发调试阶段，通常指定为 ResponseDataType.PlainText, 以便在浏览器中查看响应的源文件时能看到可读的文本数据
	// 在项目部署时，通常指定为 ResponseDataType.ZipBinary 或
	// ResponseDataType.ZipBase64，这样可以极大减少数据量，提供报表响应速度
	public static ResponseDataType DefaultReportDataType = ResponseDataType.PlainText; // PlainText

	// ZipBinary
	// ZipBase64

	// ///////////////////////////////////////////////////////////////////////////////////////////////////////
	// ReportQueryItem
	public class ReportQueryItem {
		String QuerySQL;
		String RecordsetName;

		public ReportQueryItem(String AQuerySQL, String ARecordsetName) {
			QuerySQL = AQuerySQL;
			RecordsetName = ARecordsetName;
		}
	}

	// ///////////////////////////////////////////////////////////////////////////////////////////////////////
	// 将产生的报表文本数据(XML文本 或 JSON文本)发送给客户端，可以对数据进行压缩
	public static void ResponseText(HttpServletResponse response,
			String DataText, ResponseDataType DataType) throws Exception {
		response.resetBuffer();

		if (DataType == ResponseDataType.PlainText) {
			PrintWriter pw = response.getWriter();
			pw.print(DataText);
			pw.close(); // 终止后续不必要内容输出
		} else {
			byte[] RawData = DataText.getBytes("UTF-8"); // byte[] RawData =
			// DataText.getBytes();

			// 写入特有的压缩头部信息，以便报表客户端插件能识别数据
			response.addHeader("gr_zip_type", "deflate"); // 指定压缩方法
			response.addIntHeader("gr_zip_size", RawData.length); // 指定数据的原始长度
			response
					.addHeader("gr_zip_encode", response.getCharacterEncoding()); // 指定数据的编码方式
			// utf-8
			// utf-16
			// ...

			if (DataType == ResponseDataType.ZipBinary) {
				// 压缩数据并输出
				ServletOutputStream bos = response.getOutputStream();
				DeflaterOutputStream zos = new DeflaterOutputStream(bos);
				zos.write(RawData);
				zos.close();
				bos.flush();
			} else {
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				DeflaterOutputStream zos = new DeflaterOutputStream(baos);
				zos.write(RawData);
				zos.close();
				baos.close();

				PrintWriter pw = response.getWriter();
				pw.print((new sun.misc.BASE64Encoder()).encode(baos
						.toByteArray()));
				// pw.print( encodeBASE64( baos.toByteArray() ) );
				pw.close(); // 终止后续不必要内容输出
			}
		}
	}

}
