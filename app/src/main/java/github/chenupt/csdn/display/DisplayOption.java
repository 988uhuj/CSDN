package github.chenupt.csdn.display;

import com.nostra13.universalimageloader.core.DisplayImageOptions;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.EBean.Scope;

import github.chenupt.csdn.R;

@EBean(scope = Scope.Singleton)
public class DisplayOption {

    /**
     * 话题
     */
    public DisplayImageOptions newsItemImageOptions = new DisplayImageOptions.Builder()
            .cacheOnDisc(true).cacheInMemory(true)
            .showImageOnFail(R.drawable.default_flipper)
            .showImageOnLoading(R.drawable.default_flipper)
            .showImageForEmptyUri(R.drawable.default_flipper).build();
}
