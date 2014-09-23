package github.chenupt.csdn.activities;

import android.util.Log;
import android.view.View;

import com.loopj.android.http.TextHttpResponseHandler;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;
import org.apache.http.Header;

import java.util.List;

import github.chenupt.common.listhelper.SimpleItemEntity;
import github.chenupt.common.listhelper.SimpleModelAdapter;
import github.chenupt.common.view.loadlistview.LoadListView;
import github.chenupt.csdn.R;
import github.chenupt.csdn.base.BaseActivity;
import github.chenupt.csdn.dataservice.CommonDataService;
import github.chenupt.csdn.dataservice.NewsDetailService;
import github.chenupt.csdn.entity.NewsDetail;
import github.chenupt.csdn.net.HttpClient;
import github.chenupt.csdn.utils.Page;
import uk.co.senab.actionbarpulltorefresh.library.ActionBarPullToRefresh;
import uk.co.senab.actionbarpulltorefresh.library.PullToRefreshLayout;
import uk.co.senab.actionbarpulltorefresh.library.listeners.OnRefreshListener;

/**
 * Created by chenupt@gmail.com on 2014/9/22.
 * Description TODO
 */
@EActivity(R.layout.activity_news_detail)
public class NewsDetailActivity extends BaseActivity {

    public static final String TAG = "NewsDetailActivity";

    @ViewById(R.id.ptr_layout)
    PullToRefreshLayout pullToRefreshLayout;
    @ViewById(R.id.list_view)
    LoadListView loadListView;

    @Bean
    NewsDetailService newsDetailService;
    @Bean
    CommonDataService commonDataService;
    @Bean
    Page page;

    SimpleModelAdapter adapter;

    @Extra
    String url;

    @AfterViews
    void afterViews(){
        adapter = new SimpleModelAdapter(this, newsDetailService.getFactory());
        loadListView.setAdapter(adapter);

        // Now setup the PullToRefreshLayout
        ActionBarPullToRefresh.from(this)
                .allChildrenArePullable()
                .listener(onRefreshListener)
                .setup(pullToRefreshLayout);

        loadListView.setOnLoadListener(new LoadListView.OnLoadListener() {
            @Override
            public void onLoad() {
                netGetData(false);
            }
        });

        netGetData(true);
    }

    private void netGetData(final boolean isRefresh){
        Log.d(TAG, "url:" + url);
        HttpClient.getUrl(url, new TextHttpResponseHandler() {
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

    private void handleData(boolean isRefresh, String result){
        List<NewsDetail> dataList =  commonDataService.getContent(url, result);
        Log.d(TAG, "size:" + dataList.size());
        List<SimpleItemEntity> list = newsDetailService.getWrapperList(dataList);
        if (isRefresh) {
            page.resetPage();
            adapter.clearList();
        }
        adapter.addList(list);
        page.increasePage();
    }

    private void loadFinish() {
        adapter.notifyDataSetChanged();
        pullToRefreshLayout.setRefreshComplete();
        loadListView.setLoadComplete();
    }

    private OnRefreshListener onRefreshListener = new OnRefreshListener() {
        @Override
        public void onRefreshStarted(View view) {
            netGetData(true);
        }
    };

}
