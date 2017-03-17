package com.other.myclass;

import com.cxstock.action.BaseAction;
import com.cxstock.biz.power.dto.UserDTO;
import com.cxstock.dao.OrderDAO;
import com.cxstock.utils.pubutil.Page;
import com.cxstock.utils.system.Constants;

/**
 * 框架公用的类
 * */
public class PublicClass extends BaseAction {

	// /**
	// * 当前用户所能操作的企业ID串
	// * */
	// public static String strUserUnitRights = "";
	//
	// /**
	// * 登录部门的ID
	// * */
	// public static Integer iLoginCompanyId = 0;
	// public static String strLoginCompanyName = "";
	//
	// /**
	// * 当前登录人的ID
	// * */
	// public static Integer iLoginId = 0;
	// public static String strLoginName = "";
	// public static Integer iLoginRole = 0;

	/**
	 * 获取汉字的拼音码
	 * 
	 * @param 传入的字符
	 * @return 返回 拼音码
	 * */
	public static String getPyCode(String strVaule) {
		// 汉字区位码
		int li_SecPosValue[] = { 1601, 1637, 1833, 2078, 2274, 2302, 2433,
				2594, 2787, 3106, 3212, 3472, 3635, 3722, 3730, 3858, 4027,
				4086, 4390, 4558, 4684, 4925, 5249, 5590 };
		// 存放国标一级汉字不同读音的起始区位码对应读音
		char lc_FirstLetter[] = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J',
				'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'W', 'X',
				'Y', 'Z' };
		// 二级字库偏移量
		int ioffset = 0;
		// //存放所有国标二级汉字读音
		java.lang.String ls_SecondSecTable = "CJWGNSPGCGNE[Y[BTYYZDXYKYGT[JNNJQMBSGZSCYJSYY"
				+ "[PGKBZGY[YWJKGKLJYWKPJQHY[W[DZLSGMRYPYWWCCKZNKYYGTTNJJNYKKZYTCJNMCYLQLYPYQFQRPZSLWBTGKJFYXJWZLTBNCXJJJJTXDTTSQZYCDXXHGCK"
				+ "[PHFFSS[YBGXLPPBYLL[HLXS[ZM[JHSOJNGHDZQYKLGJHSGQZHXQGKEZZWYSCSCJXYEYXADZPMDSSMZJZQJYZC[J"
				+ "[WQJBYZPXGZNZCPWHKXHQKMWFBPBYDTJZZKQHYLYGXFPTYJYYZPSZLFCHMQSHGMXXSXJ["
				+ "[DCSBBQBEFSJYHXWGZKPYLQBGLDLCCTNMAYDDKSSNGYCSGXLYZAYBNPTSDKDYLHGYMYLCXPY"
				+ "[JNDQJWXQXFYYFJLEJPZRXCCQWQQSBNKYMGPLBMJRQCFLNYMYQMSQYRBCJTHZTQFRXQHXMJJCJLXQGJMSHZKBSWYEMYLTXFSYDSWLYCJQXSJNQBSCTYHBFTDCYZDJWY"
				+ "GHQFRXWCKQKXEBPTLPXJZSRMEBWHJLBJSLYYSMDXLCLQKXLHXJRZJMFQHXHWYWSBHTRXXGLHQHFNM[YKLDYXZPYLGG[MTCFPAJJZYLJTYANJGBJPLQGDZYQY"
				+ "AXBKYSECJSZNSLYZHSXLZCGHPXZHZNYTDSBCJKDLZAYFMYDLEBBGQYZKXGLDNDNYSKJSHDLYXBCGHXYPKDJMMZNGMMCLGWZSZXZJFZNMLZZTHCSYDBDLLSCDD"
				+ "NLKJYKJSYCJLKWHQASDKNHCSGANHDAASHTCPLCPQYBSDMPJLPZJOQLCDHJJYSPRCHN[NNLHLYYQYHWZPTCZGWWMZFFJQQQQYXACLBHKDJXDGMMYDJXZLLSYGX"
				+ "GKJRYWZWYCLZMSSJZLDBYD[FCXYHLXCHYZJQ[[QAGMNYXPFRKSSBJLYXYSYGLNSCMHZWWMNZJJLXXHCHSY[[TTXRYCYXBYHCSMXJSZNPWGPXXTAYBGAJCXLY"
				+ "[DCCWZOCWKCCSBNHCPDYZNFCYYTYCKXKYBSQKKYTQQXFCWCHCYKELZQBSQYJQCCLMTHSYWHMKTLKJLYCXWHEQQHTQH[PQ"
				+ "[QSCFYMNDMGBWHWLGSLLYSDLMLXPTHMJHWLJZYHZJXHTXJLHXRSWLWZJCBXMHZQXSDZPMGFCSGLSXYMJSHXPJXWMYQKSMYPLRTHBXFTPMHYXLCHLHLZY"
				+ "LXGSSSSTCLSLDCLRPBHZHXYYFHB[GDMYCNQQWLQHJJ[YWJZYEJJDHPBLQXTQKWHLCHQXAGTLXLJXMSL[HTZKZJECXJCJNMFBY[SFYWYBJZGNYSDZSQYRSLJ"
				+ "PCLPWXSDWEJBJCBCNAYTWGMPAPCLYQPCLZXSBNMSGGFNZJJBZSFZYNDXHPLQKZCZWALSBCCJX[YZGWKYPSGXFZFCDKHJGXDLQFSGDSLQWZKXTMHSBGZMJZRGLYJ"
				+ "BPMLMSXLZJQQHZYJCZYDJWBMYKLDDPMJEGXYHYLXHLQYQHKYCWCJMYYXNATJHYCCXZPCQLBZWWYTWBQCMLPMYRJCCCXFPZNZZLJPLXXYZTZLGDLDCKLYRZZGQTG"
				+ "JHHGJLJAXFGFJZSLCFDQZLCLGJDJCSNZLLJPJQDCCLCJXMYZFTSXGCGSBRZXJQQCTZHGYQTJQQLZXJYLYLBCYAMCSTYLPDJBYREGKLZYZHLYSZQLZNWCZCLLWJQ"
				+ "JJJKDGJZOLBBZPPGLGHTGZXYGHZMYCNQSYCYHBHGXKAMTXYXNBSKYZZGJZLQJDFCJXDYGJQJJPMGWGJJJPKQSBGBMMCJSSCLPQPDXCDYYKY[CJDDYYGYWRHJRTGZ"
				+ "NYQLDKLJSZZGZQZJGDYKSHPZMTLCPWNJAFYZDJCNMWESCYGLBTZCGMSSLLYXQSXSBSJSBBSGGHFJLYPMZJNLYYWDQSHZXTYYWHMZYHYWDBXBTLMSYYYFSXJC[DXX"
				+ "LHJHF[SXZQHFZMZCZTQCXZXRTTDJHNNYZQQMNQDMMG[YDXMJGDHCDYZBFFALLZTDLTFXMXQZDNGWQDBDCZJDXBZGSQQDDJCMBKZFFXMKDMDSYYSZCMLJDSYNSBRS"
				+ "KMKMPCKLGDBQTFZSWTFGGLYPLLJZHGJ[GYPZLTCSMCNBTJBQFKTHBYZGKPBBYMTDSSXTBNPDKLEYCJNYDDYKZDDHQHSDZSCTARLLTKZLGECLLKJLQJAQNBDKKGHP"
				+ "JTZQKSECSHALQFMMGJNLYJBBTMLYZXDCJPLDLPCQDHZYCBZSCZBZMSLJFLKRZJSNFRGJHXPDHYJYBZGDLQCSEZGXLBLGYXTWMABCHECMWYJYZLLJJYHLG[DJLSLY"
				+ "GKDZPZXJYYZLWCXSZFGWYYDLYHCLJSCMBJHBLYZLYCBLYDPDQYSXQZBYTDKYXJY[CNRJMPDJGKLCLJBCTBJDDBBLBLCZQRPPXJCJLZCSHLTOLJNMDDDLNGKAQHQH"
				+ "JGYKHEZNMSHRP[QQJCHGMFPRXHJGDYCHGHLYRZQLCYQJNZSQTKQJYMSZSWLCFQQQXYFGGYPTQWLMCRNFKKFSYYLQBMQAMMMYXCTPSHCPTXXZZSMPHPSHMCLMLDQF"
				+ "YQXSZYYDYJZZHQPDSZGLSTJBCKBXYQZJSGPSXQZQZRQTBDKYXZKHHGFLBCSMDLDGDZDBLZYYCXNNCSYBZBFGLZZXSWMSCCMQNJQSBDQSJTXXMBLTXZCLZSHZCXRQ"
				+ "JGJYLXZFJPHYMZQQYDFQJJLZZNZJCDGZYGCTXMZYSCTLKPHTXHTLBJXJLXSCDQXCBBTJFQZFSLTJBTKQBXXJJLJCHCZDBZJDCZJDCPRNPQCJPFCZLCLZXZDMXMPH"
				+ "JSGZGSZZQLYLWTJPFSYASMCJBTZKYCWMYTCSJJLJCQLWZMALBXYFBPNLSFHTGJWEJJXXGLLJSTGSHJQLZFKCGNNNSZFDEQFHBSAQTGYLBXMMYGSZLDYDQMJJRGBJ"
				+ "TKGDHGKBLQKBDMBYLXWCXYTTYBKMRTJZXQJBHLMHMJJZMQASLDCYXYQDLQCAFYWYXQHZ";

		java.lang.String sreturn = "";
		for (int j = 0; j < strVaule.length(); j++) {
			String stemp = strVaule.substring(j, j + 1);
			byte[] by = stemp.getBytes();
			if (by.length == 1) {
				sreturn = sreturn + stemp;
			} else {
				int ia = 96 + (int) by[0]; // 区码
				int ib = 96 + (int) by[1]; // 位码
				int in = ia * 100 + ib;
				if (in > 1600 && in < 5590) {
					for (int i = 0; i < 24; i++) {
						if (in < li_SecPosValue[i]) {
							sreturn = sreturn + lc_FirstLetter[i - 1];
							break;
						}
					}
				} else {
					ioffset = (ia - 56) * 94 + ib - 1;
					if (ioffset >= 0 && ioffset <= 3007) {
						sreturn = sreturn
								+ ls_SecondSecTable.substring(ioffset,
										ioffset + 1);
					}
				}
			}
			sreturn = sreturn.toLowerCase();
		}
		return sreturn;

	}

	/**
	 * 获得 控制数据隔离的字符串
	 * */
	public static String getRightStr(String strfiled) {
		PublicClass pc = new PublicClass();
		String strUserUnitRights = (String) pc.getSession().getAttribute(
				"userunitrights");
		/* 实现数据隔离 */
		if (strUserUnitRights != null && strUserUnitRights.length() > 0) {
			return strfiled + " in (" + strUserUnitRights + ") ";
		} else {
			return strfiled + " is null ";
		}
	}

	public static String getRightStr() {
		return getRightStr("companyid");
	}

	/**
	 * 自动生成编号 格式：getCodeNo("tab","vcNo","20150414");
	 * 代表获得在表tab给vcNo字段按照变量[20150414]生成编号的sql
	 * */
	// public static String getCodeNo(String tab, String field, String dateCode)
	// {
	// PublicClass pc = new PublicClass();
	// UserDTO udt = (UserDTO) pc.getSession()
	// .getAttribute(Constants.USERINFO);
	// return "select max(t." + field + ") from " + tab + " as t " + " where "
	// + " t." + field + " like '" + dateCode + "%' and t.companyId="
	// + udt.getCompanyid();
	// }
	public static String getCodeNo(OrderDAO orderDao, String tab, String key,
			String field, String dateCode) {
		PublicClass pc = new PublicClass();
		UserDTO udt = (UserDTO) pc.getSession()
				.getAttribute(Constants.USERINFO);
		String sql = "select max(t." + field + ") from " + tab + " as t "
				+ " where " + " t." + field + " like '" + key + dateCode
				+ "%' and t.companyId=" + udt.getCompanyid();
		Object obj = orderDao.getSinge(sql);
		if (obj != null) {
			String str = (obj + "").replace(key, "");
			Long l = Long.parseLong(str + "") + 1;
			return key + l + "";
		}
		return key + dateCode + "0001";
	}

	/**
	 * 根据 Page 获得带where条件的sql语句
	 * */
	public static String getPageSql(Page page) {
		String sql = "select " + page.getField() + " from " + page.getTable()
				+ " ";
		if (page.getWheres() != null && page.getWheres().length() > 0) {
			sql += " where " + page.getWheres();
		}
		return sql;
	}

	/**
	 * 根据Page 得到 单行记录的sql
	 * */
	public static String getPageCountSql(Page page) {
		return "select count(1) from (" + getPageSql(page) + ") t ";
	}

	/**
	 * 根据 Page 得到分页的sql
	 * */
	public static String getPageLimitSql(Page page) {
		return "select t.* from (" + getPageSql(page) + ") t limit "
				+ page.getStart() + "," + page.getLimit() + "";
	}

	/**
	 * 得到商品是否在盘点的sql语句
	 * */
	public static String getInventoryStockSql(Integer commodityId,
			String batch, String sn, Integer warehouseId) {
		return "select 1 from tbinventorydel t INNER JOIN tbinventory a ON t.inventoryId=a.id where t.commodityId="
				+ commodityId
				+ " and t.warehouseId="
				+ warehouseId
				+ " and t.vcBatch='"
				+ batch
				+ "' and t.vcSn='"
				+ sn
				+ "' and t.warehouseId=" + warehouseId + " and a.isOk<>1";
	}

	/**
	 * @功能：判断是否为数字
	 * @作者：RC
	 * */
	public static boolean isNumeric(String str) {
		for (int i = str.length(); --i >= 0;) {
			if (!Character.isDigit(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}
}
