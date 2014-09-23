package github.chenupt.csdn.base;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;

import com.hannesdorfmann.swipeback.SwipeBack;

import github.chenupt.csdn.R;


public class BaseActivity extends ActionBarActivity {

    public final static String CHANGE_DATA＿ACTION = "change_data_action";

	protected boolean isActive;
	protected boolean isDataChanged;
	private OnSyncDataListener onSyncDataListener;
	
	private Intent intentReceived;

    private boolean enableSwipeBack = true;

	protected BroadcastReceiver syncDataReceiver = new BroadcastReceiver() {

		@Override
		public void onReceive(Context context, Intent intent) {
			if (onSyncDataListener != null) {
				if(isActive){
					onSyncDataListener.onReceive(context, intent);
				}else{
					intentReceived = intent;
					isDataChanged = true;
				}
			}
		}

	};

	@Override
	protected void onResume() {
		isActive = true;
		if(isDataChanged && onSyncDataListener != null){
			onSyncDataListener.onReceive(this, intentReceived);
			isDataChanged = false;
		}
		super.onResume();
	}

	@Override
	protected void onPause() {
		isActive = false;
		super.onPause();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		IntentFilter intentFilter = new IntentFilter();
		intentFilter.addAction(CHANGE_DATA＿ACTION);
		registerReceiver(syncDataReceiver, intentFilter);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if(enableSwipeBack){
            SwipeBack.attach(this)
                    .setDrawOverlay(true)
                    .setDividerEnabled(true)
                    .setUpDefaultSwipeBackView();
        }
	}

	@Override
	protected void onDestroy() {
		unregisterReceiver(syncDataReceiver);
		super.onDestroy();
	}

	protected void setOnSyncDataListener(OnSyncDataListener i) {
		this.onSyncDataListener = i;
	}

	protected interface OnSyncDataListener {
		public void onReceive(Context context, Intent intent);
	}

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }



    private Drawable oldBackground = null;
    protected void changeColor(int newColor) {

        Drawable colorDrawable = new ColorDrawable(newColor);
        Drawable bottomDrawable = getResources().getDrawable(R.drawable.umeng_actionbar_bottom);
        LayerDrawable ld = new LayerDrawable(new Drawable[]{colorDrawable, bottomDrawable});

        if (oldBackground == null) {
            getSupportActionBar().setBackgroundDrawable(ld);
        } else {
            TransitionDrawable td = new TransitionDrawable(new Drawable[]{oldBackground, ld});
            getSupportActionBar().setBackgroundDrawable(td);
            td.startTransition(200);
        }

        oldBackground = ld;

        // http://stackoverflow.com/questions/11002691/actionbar-setbackgrounddrawable-nulling-background-from-thread-handler
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
    }

    public void setEnableSwipeBack(boolean enableSwipeBack) {
        this.enableSwipeBack = enableSwipeBack;
    }
}
