package github.chenupt.csdn.customitemview;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import github.chenupt.common.listhelper.BaseItemModel;
import github.chenupt.csdn.R;
import github.chenupt.csdn.entity.NewsItem;

/**
 * Created by chenupt@gmail.com on 2014/9/22.
 * Description TODO
 */
@EViewGroup(R.layout.item_view_news_item)
public class NewsItemView extends BaseItemModel<NewsItem>{

    @ViewById(R.id.title)
    TextView titleTextView;
    @ViewById(R.id.content)
    TextView contentTextView;
    @ViewById(R.id.image)
    ImageView imageView;

    public NewsItemView(Context context) {
        super(context);
    }

    @Override
    public void bindView() {
        titleTextView.setText(model.getContent().getTitle());
        contentTextView.setText(model.getContent().getContent());
//        Picasso.with(getContext()).load(model.getContent().getImgLink()).into(imageView);
        ImageLoader.getInstance().displayImage(model.getContent().getImgLink(), imageView);
    }
}
