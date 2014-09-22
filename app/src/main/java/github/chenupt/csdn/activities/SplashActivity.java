package github.chenupt.csdn.activities;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;

import github.chenupt.csdn.R;
import github.chenupt.csdn.base.BaseActivity;

/**
 * Created by chenupt@gmail.com on 2014/9/22.
 * Description TODO
 */
@EActivity(R.layout.activity_splash)
public class SplashActivity extends BaseActivity {

    @AfterViews
    void afterViews(){
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        loadMain();
    }

    @UiThread(delay = 2000)
    void loadMain(){
        MainActivity_.intent(this).start();
        finish();
    }

}
