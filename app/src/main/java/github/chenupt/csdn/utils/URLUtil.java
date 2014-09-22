package github.chenupt.csdn.utils;
/**
 * URL������
 * @author Dave
 *
 */
public class URLUtil {
	public static String NEWS_LIST_URL = "http://www.csdn.net/headlines.html";
	public static String NEWS_LIST_URL_YIDONG = "http://mobile.csdn.net/mobile";
	public static String NEWS_LIST_URL_YANFA = "http://sd.csdn.net/sd";
	public static String NEWS_LIST_URL_YUNJISUAN = "http://cloud.csdn.net/cloud";
	public static String NEWS_LIST_URL_ZAZHI = "http://programmer.csdn.net/programmer";
	public static String NEWS_LIST_URL_YEJIE = "http://news.csdn.net/news";
	
	
	public static String getNewsListURL(String page){
		return NEWS_LIST_URL + "/" + page;
	}
	public static String getRefreshNewsURl(){
		return NEWS_LIST_URL;
	}
	
	public static String getNewsListURLYiDong(String page){
		return NEWS_LIST_URL_YIDONG + "/" + page;
	}
	public static String getRefreshNewsURLYiDong(){
		return NEWS_LIST_URL_YIDONG + "/1";
	}
	
	public static String getNewsListURLYanFa(String page){
		return NEWS_LIST_URL_YANFA + "/" + page;
	}
	public static String getRefreshNewsURLYanFa(){
		return NEWS_LIST_URL_YANFA + "/1";
	}
	
	
	public static String getNewsListURL(int newsType, String page){
		String url = "";
		switch(newsType){
		case Constants.DEF_NEWS_TYPE.YEJIE:
			url = NEWS_LIST_URL_YEJIE;
			break;
		case Constants.DEF_NEWS_TYPE.YIDONG:
			url = NEWS_LIST_URL_YIDONG;
			break;
		case Constants.DEF_NEWS_TYPE.YANFA:
			url = NEWS_LIST_URL_YANFA;
			break;
		case Constants.DEF_NEWS_TYPE.YUNJISUAN:
			url = NEWS_LIST_URL_YUNJISUAN;
			break;
		case Constants.DEF_NEWS_TYPE.ZAZHI:
			url = NEWS_LIST_URL_ZAZHI;
			break;
		default:
			break;
		}
		url = url + "/" + page;
		return url;
	}
	public static String getRefreshNewsListURL(int newsType){
		String url = "";
		switch(newsType){
		case Constants.DEF_NEWS_TYPE.YIDONG:
			url = NEWS_LIST_URL_YIDONG;
			break;
		case Constants.DEF_NEWS_TYPE.YANFA:
			url = NEWS_LIST_URL_YANFA;
			break;
		case Constants.DEF_NEWS_TYPE.YUNJISUAN:
			url = NEWS_LIST_URL_YUNJISUAN;
			break;
		case Constants.DEF_NEWS_TYPE.ZAZHI:
			url = NEWS_LIST_URL_ZAZHI;
			break;
		case Constants.DEF_NEWS_TYPE.YEJIE:
			url = NEWS_LIST_URL_YEJIE;
			break;
		default:
			break;
		}
		url = url + "/1";
		return url;
	}
	
	public static String getCommnetListURL(String url, String page){
		return "http://ptcms.csdn.net/comment/comment/newest?url=" + url + "&pageno=" + page + "&pagesize=50";
	}
}
