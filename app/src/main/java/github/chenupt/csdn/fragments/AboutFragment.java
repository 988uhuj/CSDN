package github.chenupt.csdn.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.umeng.fb.FeedbackAgent;
import com.umeng.update.UmengUpdateAgent;
import com.umeng.update.UmengUpdateListener;
import com.umeng.update.UpdateResponse;
import com.umeng.update.UpdateStatus;

import github.chenupt.csdn.R;
import github.chenupt.csdn.base.BaseFragment;


/**
 * Created by chenupt@gmail.com on 2014/7/7.
 * Description : TODO
 */

public class AboutFragment extends BaseFragment {

    private Button feedBackBtn;
    private Button updateBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_about, null);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        findViewById();
        initData();
        initView();

        action();
    }

    private void findViewById(){
        feedBackBtn = (Button) getView().findViewById(R.id.feed_btn);
        updateBtn = (Button) getView().findViewById(R.id.update_btn);
    }

    private void initData(){
    }

    private void initView(){
        feedBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FeedbackAgent agent = new FeedbackAgent(getActivity());
                agent.startFeedbackActivity();
            }
        });
        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
        });
    }

    private void action(){
    }




}
