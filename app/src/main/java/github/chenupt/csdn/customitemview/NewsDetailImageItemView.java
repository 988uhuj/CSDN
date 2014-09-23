package github.chenupt.csdn.customitemview;

import android.content.Context;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import github.chenupt.common.listhelper.BaseItemModel;
import github.chenupt.csdn.R;
import github.chenupt.csdn.display.DisplayOption;
import github.chenupt.csdn.entity.NewsDetail;

/**
 * Created by chenupt@gmail.com on 2014/9/22.
 * Description TODO
 */
@EViewGroup(R.layout.item_view_news_detail_img)
public class NewsDetailImageItemView extends BaseItemModel<NewsDetail>{

    @ViewById(R.id.image_view)
    ImageView imageView;

    @Bean
    DisplayOption displayOption;

    public NewsDetailImageItemView(Context context) {
        super(context);
    }

    @Override
    public void bindView() {
        ImageLoader.getInstance().displayImage(model.getContent().getContent(), imageView, displayOption.newsItemImageOptions);
    }
}
