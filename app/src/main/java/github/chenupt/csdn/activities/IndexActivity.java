package github.chenupt.csdn.activities;

import android.os.Bundle;

import github.chenupt.csdn.base.BaseActivity;

/**
 * Created by chenupt@gmail.com on 2014/9/22.
 * Description TODO
 */
public class IndexActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        SplashActivity_.intent(this).start();
        MainActivity_.intent(this).start();
        finish();
    }
}
