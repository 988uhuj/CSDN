package github.chenupt.csdn.net;

import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.util.Map;

import github.chenupt.common.util.JsonUtil;
import github.chenupt.csdn.Config;

/**
 * Created by chenupt@gmail.com on 2014/9/7.
 * Description TODO
 */
public class HttpClient {

    public static final String TAG = "HttpClient";

    private static AsyncHttpClient client;
    static{
        client = new AsyncHttpClient();
        client.setTimeout(Config.TIME_OUT);
        client.setUserAgent(Config.USER_AGENT);
    }

    public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.get(Config.getAbsoluteUrl(url), params, responseHandler);
    }

    public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.post(Config.getAbsoluteUrl(url), params, responseHandler);
    }

    public static void getUrl(String url, AsyncHttpResponseHandler responseHandler) {
        client.get(url, responseHandler);
    }


    public static void postService(String url, Map<String, Object> params) {
        Log.d(TAG, "post url:" + Config.getAbsoluteUrl(url));
        RequestParams requestParams = new RequestParams();
        requestParams.add(Config.POST_PARAM, JsonUtil.fromObjectHasDateToJson(params));
        client.post(Config.getAbsoluteUrl(url), requestParams, null);
    }

}
