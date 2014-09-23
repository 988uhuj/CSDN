package github.chenupt.csdn.customitemview;

import android.content.Context;
import android.widget.TextView;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import github.chenupt.common.listhelper.BaseItemModel;
import github.chenupt.csdn.R;
import github.chenupt.csdn.entity.NewsDetail;

/**
 * Created by chenupt@gmail.com on 2014/9/22.
 * Description TODO
 */
@EViewGroup(R.layout.item_view_news_detail_title)
public class NewsDetailTitleItemView extends BaseItemModel<NewsDetail>{

    @ViewById(R.id.text)
    TextView textView;

    public NewsDetailTitleItemView(Context context) {
        super(context);
    }

    @Override
    public void bindView() {
        textView.setText(model.getContent().getContent());
    }
}
