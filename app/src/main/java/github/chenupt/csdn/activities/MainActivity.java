package github.chenupt.csdn.activities;

import android.view.Menu;
import android.view.MenuItem;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;

import github.chenupt.csdn.R;
import github.chenupt.csdn.base.BaseActivity;

@EActivity(R.layout.activity_main)
public class MainActivity extends BaseActivity {


    @AfterViews
    void afterViews(){
        setEnableSwipeBack(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }else if(id == R.id.action_about){
            AboutActivity_.intent(this).start();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
