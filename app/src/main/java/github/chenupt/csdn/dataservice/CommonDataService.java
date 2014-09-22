package github.chenupt.csdn.dataservice;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import github.chenupt.csdn.entity.Comment;
import github.chenupt.csdn.entity.News;
import github.chenupt.csdn.entity.NewsItem;
import github.chenupt.csdn.utils.Constants;


public class CommonDataService {
	public static boolean contentFirstPage = true;
	public static boolean contentLastPage = true;
	public static boolean multiPages = false;
	
	public static void resetPages(){
		contentFirstPage = true;
		contentLastPage = true;
		multiPages = false;
	}
	

	public static List<News> getContent(String url, String str){
		List<News> list = new ArrayList<News>();

		Document doc = Jsoup.parse(str);
		Elements pages = doc.getElementsByClass("next");
		if(pages.size() > 0){
			if(url.equals(pages.get(0).attr("href"))){
				contentLastPage = true;
			}else{
                // TODO
//				NewsContentActivity.URL = pages.get(0).attr("href");
				contentLastPage = false;
				multiPages = true;
			}
		}else{
			contentFirstPage = true;
			contentLastPage = true;
			multiPages = false;
		}
		
		Element detail = doc.getElementsByClass("detail").get(0);
		detail.select("script").remove();
	//	detail.select("br").remove();
		
		
		if(contentFirstPage){
			Element title = detail.getElementsByClass("title").get(0);
			News titleNews = new News();
			titleNews.setState(Constants.DEF_NEWS_ITEM_TYPE.TITLE);
			titleNews.setContent(ToDBC(title.text()));
			list.add(titleNews);
			
			Element summary = detail.getElementsByClass("summary").get(0);
			News summaryNews = new News();
			summaryNews.setState(Constants.DEF_NEWS_ITEM_TYPE.SUMMARY);
			summaryNews.setContent(ToDBC(summary.text()));
			list.add(summaryNews);		
		}
		

		Element content = detail.select("div.con.news_content").get(0);

		Elements as = detail.getElementsByTag("a");
		for(int b = 0; b < as.size(); b++){
			Element blockquote = as.get(b);
			blockquote.tagName("bold");
		}
		
		Elements ps = detail.getElementsByTag("p");
		for(int b = 0; b < ps.size(); b++){
			Element blockquote = ps.get(b);
			blockquote.tagName("body");
		}
		
		Elements blockquotes = detail.getElementsByTag("blockquote");
		for(int b = 0; b < blockquotes.size(); b++){
			Element blockquote = blockquotes.get(b);
			blockquote.tagName("body");
		}
		
		Elements uls = detail.getElementsByTag("ul");
		for(int b = 0; b < uls.size(); b++){
			Element blockquote = uls.get(b);
			blockquote.tagName("body");
		}
		
		Elements bs = detail.getElementsByTag("b");
		for(int b = 0; b < bs.size(); b++){
			Element bold = bs.get(b);
			bold.tagName("bold");
		}
		
		
		for (int j = 0; j < content.children().size(); j++) {
			Element c = content.child(j);
			
			if (c.select("img").size() > 0) {
				Elements imgs = c.getElementsByTag("img");
				for (Element img : imgs) {
					if(!img.attr("src").equals("")){
						News imgNews = new News();
						if(!img.parent().attr("href").equals("")){
							imgNews.setImgLink(img.parent().attr("href"));
							img.parent().remove();
						}
						imgNews.setContent(img.attr("src"));
						imgNews.setState(Constants.DEF_NEWS_ITEM_TYPE.IMG);
						list.add(imgNews);
					}
				}
			}
			c.select("img").remove();
			
			
			News contentNews = new News();
			contentNews.setState(Constants.DEF_NEWS_ITEM_TYPE.CONTENT);
			
			
			if (c.text().equals("")) {
				continue;
			} else if (c.children().size() == 1) {
				if (c.child(0).tagName().equals("bold")
						|| c.child(0).tagName().equals("span")) {
					if (c.ownText().equals("")) {
						contentNews.setState(Constants.DEF_NEWS_ITEM_TYPE.BOLD_TITLE);
					}
				}
			}
			
			contentNews.setContent(ToDBC(c.outerHtml()));
			list.add(contentNews);
		}

		contentFirstPage = false;
		return list;
	}
	/*public static List<NewsItem> getNewsItemList(String str){
		List<NewsItem> list = new ArrayList<NewsItem>();
		Document doc = Jsoup.parse(str);
		Element newList = doc.getElementById("news_list");
		
		Elements dls = newList.select("dl");
		for(Element dl : dls){
			String date = dl.select("dt").text();
			Elements links = dl.select("a");
			for(Element link : links){
				NewsItem item = new NewsItem();
				item.setTitle(link.text());
				item.setLink(link.attr("href"));
				item.setDate(date);
				list.add(item);
			}
		}
		
		return list;
	}*/
	
	public static List<NewsItem> getNewsItemList(int newsType, String str){
		List<NewsItem> list = new ArrayList<NewsItem>();
		Document doc = Jsoup.parse(str);
		Elements newsList = doc.getElementsByClass("unit");
		
		for(Element newsItem : newsList){
			NewsItem news = new NewsItem();
			
			String title = newsItem.select("h1").text();
			String msg = newsItem.select("h4").text();
			String content = newsItem.select("dd").text();
			String link = newsItem.select("a").attr("href");
			
			String date = newsItem.getElementsByClass("ago").get(0).text();
			
			news.setTitle(title);
			news.setMsg(msg);
			news.setContent(content);
			news.setLink(link);
			news.setDate(date);
			news.setType(newsType);
			
			Elements imgs = newsItem.select("img");
			for(Element img : imgs){
				String imgLink = img.attr("src");
				news.setImgLink(imgLink);
			}
			list.add(news);
		}
		return list;
	}
	public static List<Comment> getComment(String str){
		List<Comment> list = new ArrayList<Comment>();
		try {
			JSONObject jsonObject = new JSONObject(str);
			String count = jsonObject.getString("count");
            // TODO
//			NewsCommentActivity.commentCount = count;
			
			JSONArray data = jsonObject.getJSONArray("data");
			for(int i = 0; i < data.length(); i ++){
				JSONObject item = data.getJSONObject(i);
				String userName = item.getString("username");
				String date = item.getString("create_time");
				String content = item.getString("body");
				String pic = item.getString("avatar");
				String replyCount = item.getString("reply_count");
				
				Comment comment = new Comment();
				comment.setName(userName);
				comment.setDate(date);
				comment.setContent(content);
				comment.setPic(pic);
				if(replyCount.equals("0")){
					comment.setReplyCount("");
				}else{
					comment.setReplyCount("�ظ�:" + replyCount);
				}
				comment.setType(Constants.DEF_COMMENT_TYPE.PARENT);
				list.add(comment);
				
				if(!item.has("children")){
					continue;
				}
				JSONArray children = item.getJSONArray("children");
				for(int j = 0; j < children.length(); j ++){
					JSONObject child = children.getJSONObject(j);
					String userName2 = child.getString("username");
					String date2 = child.getString("create_time");
					String content2 = child.getString("body");
					String pic2 = child.getString("avatar");
					String replyCount2 = child.getString("reply_count");
					
					Comment comment2 = new Comment();
					comment2.setName(userName2);
					comment2.setDate(date2);
					comment2.setContent(content2);
					comment2.setPic(pic2);
					comment2.setReplyCount(replyCount2);
					comment2.setType(Constants.DEF_COMMENT_TYPE.CHILD);
					
					list.add(comment2);
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return list;
	}
    public static String ToDBC(String input) {
        char[] c = input.toCharArray();   
        for (int i = 0; i < c.length; i++) {   
            if (c[i] == 12288) {   
                c[i] = (char) 32;   
                continue;   
            }   
            if (c[i] > 65280 && c[i] < 65375)   
                c[i] = (char) (c[i] - 65248);   
        }   
        return new String(c);   
    }  
    
    public static String getDate(String str){
    	Date date = new Date(Long.parseLong(str));
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
    	return sdf.format(date);
    }
}
