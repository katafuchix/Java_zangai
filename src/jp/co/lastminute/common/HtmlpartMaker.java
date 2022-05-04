package jp.co.lastminute.common;

import java.io.*;
import jp.co.yobrain.util.ParseFormat;

/**
 * @author user
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public final class HtmlpartMaker {
	public static String getCartPriceView(String str, String ft, String bk) {
		ParseFormat pf = null;
		try {
			String temp = pf.ToPriceFormat(str);
			if ((temp.length() != 0)&&(!temp.equals("0")) ){
				return ft + temp + bk;
			}
		} catch (Exception ex) {
		}
		return "";
	}
	public static String getCartPriceView(int num, String ft, String bk) {
		try {
			String str = "" + num + "";
			return getCartPriceView(str, ft, bk);
		} catch (Exception ex) {
		}
		return "";
	}

	public static String getNumOptionString(
		String target,
		int start,
		int end) {
		int targ = start;
		try {
			targ = Integer.parseInt(target);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return getNumOptionString(targ, start, end);
	}
	public static String getNumOptionString(int target, int start, int end) {
		int targ = start;
		String reStr = "";
		for (int i = start; i < end; i++) {
			if (i == targ) {
				reStr += "<option selected>" + i + "</option>\n";
			} else {
				reStr += "<option>" + i + "</option>\n";
			}
		}
		return reStr;
	}
	public static String getLinkList(
		String url,
		String param,
		String opval,
		String val) {
		if (val.equals(param)) {
			return opval;
		}
		return "<a href='" + url + param + "'>" + opval + "</a>";
	}
	public static String getCheckBoxChecked(String base, String target) {
		if (base.trim().equals(target.trim())) {
			return " checked='checked'";
		}
		return "";
	}
	public static String getArrayfromvalue(String[] strarray, String target) {
		String reStr = strarray[0];
		try {
			int postion = Integer.parseInt(target);
			if (postion < strarray.length) {
				reStr = strarray[postion];
			}
		} catch (Exception ex) {
		}
		return reStr;
	}
	public static String getPagingString(
		String nextstr,
		String targetscatid,
		String position,
		int totalpagecounter) {
		try {
			int positionnum = Integer.parseInt(position);
			return getPagingString(
				nextstr,
				targetscatid,
				positionnum,
				totalpagecounter);
		} catch (Exception ex) {
		}
		return "";
	}

	public static String getPagingString(
		String nextstr,
		String targetscatid,
		int position,
		int totalpagecounter) {
		String reStr = "";
		if (totalpagecounter > 0) {
			if (position > 0) {
				reStr += "<a HREF='"
					+ nextstr
					+ "?scatid="
					+ targetscatid
					+ "&pages="
					+ (position - 1)
					+ "'>&lt;&lt;back</a>&nbsp;";
			}
			for (int i = 0; i <= totalpagecounter; i++) {
				if (position != i) {
					reStr += "<a href='"
						+ nextstr
						+ "?scatid="
						+ targetscatid
						+ "&pages="
						+ i
						+ "'>"
						+ "<span CLASS='b'>"
						+ (i + 1)
						+ "</span></a>&nbsp;";
				} else {
					reStr += "<span CLASS='b'>" + (i + 1) + "</span>&nbsp;";
				}
			}
			if (position < totalpagecounter) {
				reStr += "<a HREF='"
					+ nextstr
					+ "?scatid="
					+ targetscatid
					+ "&pages="
					+ (position + 1)
					+ "'>next&gt;&gt;</a>&nbsp;";
			}
		}
		return reStr;
	}
}
