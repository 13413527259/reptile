package com.example.demo.urlUtil;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.example.demo.bean.Seller;

public class UrlConnection {

	public final static String head = "http://www.zhishubao.com/";
	
	public static String getURL(String statu,Integer page) {
		return String.format("%srank.php?d=state&state=%s&page=%d",head, statu,page);
	}

	public static String getContent(String urlStr) throws Exception {
		URL url = new URL(urlStr);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
		StringBuffer content = new StringBuffer();
		String line = "";
		while ((line = br.readLine()) != null) {
			content.append(line);
		}
		br.close();
		return content.toString();
	}

	public static boolean isGetNext(String content, int nextPage) {
		for (String a : UrlConnection.getA(content)) {
			if (a.contains("page=" + nextPage)) {
				return true;
			}
		}
		return false;
	}

	public static List<String> getA(String content) {
		List<String> list = new ArrayList<>();
		Matcher mc = Pattern.compile("<a[^>]+>[^<]*</a>").matcher(content);
		while (mc.find()) {
			list.add(mc.group());
		}
		return list;
	}

	public static List<String> getTr(String content) {
		List<String> trs = new ArrayList<>();
		Matcher mc = Pattern.compile("<tr[^>]+>.+?</tr>").matcher(content);
		while (mc.find()) {
			trs.add(mc.group());
		}
		return trs;
	}

	public static List<Seller> trToSeller(List<String> trs) {
		List<Seller> sellers = new ArrayList<>();
		for (String td : trs) {
			Matcher mc = Pattern.compile("<td>(.+?)</td>").matcher(td);
			int attr = 6;
			Seller seller=new Seller();
			while (mc.find()) {
				switch (attr % 6) {
				case 0:
					//1
					seller.setTop(Integer.valueOf(mc.group(1)));
					break;
				case 1:
					//<a href="/rank/气质淑女.html" target="_blank" title="气质淑女的销售额/销量分析">气质淑女</a>
					seller.setUrl(head+getAttrToTag(mc.group(1), "href=\"(.+?)\""));
					break;
				case 2:
					//★气质淑女★日韩瑞丽欧美OL原创平价女装专卖店
					seller.setName(mc.group(1));
					break;
				case 3:
					//<img src="http://www.zhishubao.com/img/grade/18.gif">
					seller.setLv(getAttrToTag(mc.group(1), "src=\"(.+?)\""));
					break;
				case 4:
					//广州 广东
					seller.setAddress(mc.group(1));
					break;
				case 5:
					//t恤 半身裙 气质淑女 长袖 大码 短袖 韩版 宽松 拼接 气质 时尚 淑女女装 夏季 夏款 夏装 新款 休闲 修身 
					seller.setInfo(mc.group(1));
					break;
				}
				attr++;
			}
			sellers.add(seller);
		}
		return sellers;
	}
	
	public static String getAttrToTag(String tag,String regex) {
		Matcher mc=Pattern.compile(regex).matcher(tag);
		if (mc.find()) {
			return mc.group(1);
		}
		return null;
	}

}
