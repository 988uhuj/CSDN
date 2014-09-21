package github.chenupt.csdn.fragments;

import android.widget.Button;
import android.widget.Toast;

import com.umeng.fb.FeedbackAgent;
import com.umeng.update.UmengUpdateAgent;
import com.umeng.update.UmengUpdateListener;
import com.umeng.update.UpdateResponse;
import com.umeng.update.UpdateStatus;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import github.chenupt.csdn.R;
import github.chenupt.csdn.base.BaseFragment;


/**
 * Created by chenupt@gmail.com on 2014/7/7.
 * Description : 关于界面
 */
@EFragment(R.layout.fragment_about)
public class AboutFragment extends BaseFragment {

    @ViewById(R.id.feed_btn)
    Button feedBackBtn;
    @ViewById(R.id.update_btn)
    Button updateBtn;


    @AfterViews
    void afterViews(){

    }

    @Click(R.id.feed_btn)
    void feedClick(){
        FeedbackAgent agent = new FeedbackAgent(getActivity());
        agent.startFeedbackActivity();
    }

    @Click(R.id.update_btn)
    void updateClick(){
        UmengUpdateAgent.forceUpdate(getActivity());
        UmengUpdateAgent.setUpdateListener(new UmengUpdateListener() {
            @Override
            public void onUpdateReturned(int i, UpdateResponse updateResponse) {
                if (i == UpdateStatus.No) {
                    Toast.makeText(getActivity(), getActivity().getText(R.string.new_one), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }





}
