package github.chenupt.csdn.fragments;

import android.app.Activity;
import android.util.Log;
import android.view.View;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import github.chenupt.csdn.R;
import github.chenupt.csdn.base.BaseFragment;
import uk.co.senab.actionbarpulltorefresh.library.ActionBarPullToRefresh;
import uk.co.senab.actionbarpulltorefresh.library.PullToRefreshLayout;
import uk.co.senab.actionbarpulltorefresh.library.listeners.OnRefreshListener;

/**
 * Created by chenupt@gmail.com on 2014/9/21.
 * Description 信息列表界面
 */
@EFragment(R.layout.fragment_news)
public class NewsListFragment extends BaseFragment {

    public static final String TAG = "NewsFragment";

    @ViewById(R.id.ptr_layout)
    PullToRefreshLayout pullToRefreshLayout;
    int i = 0;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        i ++;
        Log.d(TAG, "onAttach" + i);
    }

    @AfterViews
    void afterViews(){
        // Now setup the PullToRefreshLayout
        ActionBarPullToRefresh.from(getActivity())
                .allChildrenArePullable()
                .listener(onRefreshListener)
                .setup(pullToRefreshLayout);
    }

    private OnRefreshListener onRefreshListener = new OnRefreshListener() {
        @Override
        public void onRefreshStarted(View view) {
            delayNetGetComment();
        }

    };

    @UiThread(delay = 1000)
    void delayNetGetComment(){
        pullToRefreshLayout.setRefreshComplete();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(TAG, "onDetach");
    }
}
