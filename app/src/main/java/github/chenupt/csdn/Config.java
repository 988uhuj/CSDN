package github.chenupt.csdn;

/**
 * Created by chenupt@gmail.com on 2014/9/22.
 * Description TODO
 */
public class Config {
    public static final String BASE_URL = "http://192.168.88.148:8080";
    public final static String POST_PARAM = "param";
    public final static String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/34.0.1847.116 Safari/537.36";
    public final static int TIME_OUT = 15000;    // http 超时15秒

    public static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }
}
