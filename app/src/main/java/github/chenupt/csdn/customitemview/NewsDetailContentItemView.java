package github.chenupt.csdn.customitemview;

import android.content.Context;
import android.text.Html;
import android.widget.TextView;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import github.chenupt.common.listhelper.BaseItemModel;
import github.chenupt.csdn.R;
import github.chenupt.csdn.entity.NewsDetail;
import github.chenupt.csdn.utils.NewsTagHandler;

/**
 * Created by chenupt@gmail.com on 2014/9/22.
 * Description TODO
 */
@EViewGroup(R.layout.item_view_news_detail_content)
public class NewsDetailContentItemView extends BaseItemModel<NewsDetail>{

    @ViewById(R.id.text)
    TextView textView;

    public NewsDetailContentItemView(Context context) {
        super(context);
    }

    @Override
    public void bindView() {
        textView.setText(Html.fromHtml(model.getContent().getContent(), null, new NewsTagHandler()));
    }
}
