package com.other.gridreport;

public class Grcommon {

	// ///////////////////////////////////////////////////////////////////////////////////////////////////////
	// 判断是否包含特殊字符
	public static boolean HasSpecialChar(String text) {
		if (text == null)
			return false;

		boolean ret = false;
		int len = text.length();
		for (int i = 0; i < len; ++i) {
			char c = text.charAt(i);
			if (c == '&' || c == '<' || c == '>' || c == '"') {
				ret = true;
				break;
			}
		}

		return ret;
	}

	// ///////////////////////////////////////////////////////////////////////////////////////////////////////
	// 对数据中的特殊字符进行编码
	public static String HTMLEncode(String text) {
		int len = text.length();
		StringBuffer results = new StringBuffer(len + 20);
		char[] orig = text.toCharArray();

		int beg = 0;
		for (int i = 0; i < len; ++i) {
			char c = text.charAt(i);
			switch (c) {
			case '&':
				if (i > beg)
					results.append(orig, beg, i - beg);
				beg = i + 1;
				results.append("&amp;");
				break;
			case '<':
				if (i > beg)
					results.append(orig, beg, i - beg);
				beg = i + 1;
				results.append("&lt;");
				break;
			case '>':
				if (i > beg)
					results.append(orig, beg, i - beg);
				beg = i + 1;
				results.append("&gt;");
				break;
			case '"':
				if (i > beg)
					results.append(orig, beg, i - beg);
				beg = i + 1;
				results.append("&quot;");
				break;
			}
		}

		results.append(orig, beg, len - beg);

		return results.toString();
	}

	// ///////////////////////////////////////////////////////////////////////////////////////////////////////
	// 将二进制数据编码为BASE64
	/*
	 * public static String encodeBASE64(byte[] a) { final char[] codec_table =
	 * { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N',
	 * 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b',
	 * 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p',
	 * 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3',
	 * '4', '5', '6', '7', '8', '9', '+', '/' };
	 * 
	 * int totalBits = a.length * 8; int nn = totalBits % 6; int curPos = 0;//
	 * process bits StringBuffer toReturn = new StringBuffer(); while (curPos <
	 * totalBits) { int bytePos = curPos / 8; switch (curPos % 8) { case 0:
	 * toReturn.append(codec_table[(a[bytePos] & 0xfc) >> 2]); break; case 2:
	 * 
	 * toReturn.append(codec_table[(a[bytePos] & 0x3f)]); break; case 4: if
	 * (bytePos == a.length - 1) { toReturn .append(codec_table[((a[bytePos] &
	 * 0x0f) << 2) & 0x3f]); } else { int pos = (((a[bytePos] & 0x0f) << 2) |
	 * ((a[bytePos + 1] & 0xc0) >> 6)) & 0x3f;
	 * toReturn.append(codec_table[pos]); } break; case 6: if (bytePos ==
	 * a.length - 1) { toReturn .append(codec_table[((a[bytePos] & 0x03) << 4) &
	 * 0x3f]); } else { int pos = (((a[bytePos] & 0x03) << 4) | ((a[bytePos + 1]
	 * & 0xf0) >> 4)) & 0x3f; toReturn.append(codec_table[pos]); } break;
	 * default: //never hanppen break; } curPos+=6; } if(nn==2) {
	 * toReturn.append("=="); } else if(nn==4) { toReturn.append("="); } return
	 * toReturn.toString(); }
	 */

}
