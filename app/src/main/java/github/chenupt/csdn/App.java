package github.chenupt.csdn;

import android.app.Application;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.memory.impl.FIFOLimitedMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.utils.StorageUtils;

import org.androidannotations.annotations.EApplication;

import java.io.File;

/**
 * Created by chenupt@gmail.com on 2014/9/22.
 * Description CSDN Application
 */
@EApplication
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        initImageLoader();
    }

    private void initImageLoader() {
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                // .showImageOnLoading(R.drawable.default_flipper)
                // .showImageOnFail(R.drawable.default_flipper)
                .cacheInMemory(true).delayBeforeLoading(0)
                        // default
                .cacheOnDisk(true)
                        // default
                .build();

        File cacheDir = StorageUtils.getCacheDirectory(this);
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                getApplicationContext()).defaultDisplayImageOptions(options)
                .memoryCache(new FIFOLimitedMemoryCache(100))
                .denyCacheImageMultipleSizesInMemory()
                .memoryCacheSize(2 * 1024 * 1024).memoryCacheSizePercentage(13)
                        // default
                .diskCache(new UnlimitedDiscCache(cacheDir))
                        // default
                .diskCacheSize(50 * 1024 * 1024).diskCacheFileCount(100)
                .build();

        ImageLoader.getInstance().init(config);
    }
}
