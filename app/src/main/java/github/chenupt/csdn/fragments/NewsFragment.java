package github.chenupt.csdn.fragments;

import android.app.Activity;
import android.util.Log;

import org.androidannotations.annotations.EFragment;

import github.chenupt.csdn.R;
import github.chenupt.csdn.base.BaseFragment;

/**
 * Created by chenupt@gmail.com on 2014/9/21.
 * Description TODO
 */
@EFragment(R.layout.fragment_news)
public class NewsFragment extends BaseFragment {

    public static final String TAG = "NewsFragment";

    int i = 0;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        i ++;
        Log.d(TAG, "onAttach" + i);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(TAG, "onDetach");
    }
}
