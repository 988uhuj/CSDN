package github.chenupt.csdn.fragments;

import android.app.Activity;
import android.util.Log;
import android.view.View;

import com.loopj.android.http.TextHttpResponseHandler;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.apache.http.Header;

import java.util.List;

import github.chenupt.common.view.loadlistview.LoadListView;
import github.chenupt.csdn.R;
import github.chenupt.csdn.base.BaseFragment;
import github.chenupt.csdn.dataservice.CommonDataService;
import github.chenupt.csdn.entity.NewsItem;
import github.chenupt.csdn.utils.Page;
import github.chenupt.csdn.net.HttpClient;
import github.chenupt.csdn.utils.Constants;
import github.chenupt.csdn.utils.URLUtil;
import uk.co.senab.actionbarpulltorefresh.library.ActionBarPullToRefresh;
import uk.co.senab.actionbarpulltorefresh.library.PullToRefreshLayout;
import uk.co.senab.actionbarpulltorefresh.library.listeners.OnRefreshListener;

/**
 * Created by chenupt@gmail.com on 2014/9/21.
 * Description 信息列表界面
 */
@EFragment(R.layout.fragment_news)
public class NewsListFragment extends BaseFragment {

    public static final String TAG = "NewsListFragment";

    @ViewById(R.id.ptr_layout)
    PullToRefreshLayout pullToRefreshLayout;
    @ViewById(R.id.list_view)
    LoadListView loadListView;

    @Bean
    CommonDataService commonDataService;
    @Bean
    Page page;

    int i = 0;
    int newsType;


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        i ++;
        Log.d(TAG, "onAttach" + i);
    }

    @AfterViews
    void afterViews(){
        if (getArguments() != null) {
            newsType = getArguments().getInt("data");
        }

        // Now setup the PullToRefreshLayout
        ActionBarPullToRefresh.from(getActivity())
                .allChildrenArePullable()
                .listener(onRefreshListener)
                .setup(pullToRefreshLayout);

        loadListView.setOnLoadListener(new LoadListView.OnLoadListener() {
            @Override
            public void onLoad() {
                netGetData(false);
            }
        });

        action();
    }

    private void action() {
        netGetData(true);
    }

    private void netGetData(final boolean isRefresh) {
        Log.d(TAG, URLUtil.getNewsListURL(newsType,isRefresh ? "1" : page.getCurrentPage()));
        HttpClient.getUrl(URLUtil.getNewsListURL(newsType,
                isRefresh ? "1" : page.getCurrentPage()), new TextHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                Log.d(TAG, "result: " + responseString);
                handleData(isRefresh, responseString);
                loadFinish();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Log.d(TAG, "onFailure: " + statusCode);
                loadFinish();
            }
        });
    }

    private void handleData(boolean isRefresh, String content) {
        if (isRefresh) {

        }
        List<NewsItem> dataList =  commonDataService.getNewsItemList(Constants.DEF_NEWS_TYPE.YEJIE, content);
        Log.d(TAG, "size" + dataList.size());
    }

    private void loadFinish() {
        delayNetGetComment();
    }


    private OnRefreshListener onRefreshListener = new OnRefreshListener() {
        @Override
        public void onRefreshStarted(View view) {
            netGetData(true);
        }

    };

    @UiThread(delay = 3000)
    void delayNetGetComment(){
        pullToRefreshLayout.setRefreshComplete();
        loadListView.setLoadComplete();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(TAG, "onDetach");
    }
}
