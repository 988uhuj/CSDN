package github.chenupt.csdn.dataservice;

import org.androidannotations.annotations.EBean;

import java.util.ArrayList;
import java.util.List;

import github.chenupt.common.listhelper.ItemEntityWrapper;
import github.chenupt.common.listhelper.ModelFactory;
import github.chenupt.common.listhelper.SimpleItemEntity;
import github.chenupt.csdn.customitemview.NewsDetailContentItemView;
import github.chenupt.csdn.customitemview.NewsDetailImageItemView;
import github.chenupt.csdn.customitemview.NewsDetailSummaryItemView;
import github.chenupt.csdn.customitemview.NewsDetailTitleItemView;
import github.chenupt.csdn.entity.NewsDetail;
import github.chenupt.csdn.utils.Constants;

/**
 * Created by chenupt@gmail.com on 2014/9/22.
 * Description TODO
 */
@EBean
public class NewsDetailService {

    public ModelFactory getFactory(){
        ModelFactory modelFactory = new ModelFactory.Builder()
                .addModel(String.valueOf(Constants.DEF_NEWS_DETAIL_TYPE.TITLE), NewsDetailTitleItemView.class)
                .addModel(String.valueOf(Constants.DEF_NEWS_DETAIL_TYPE.CONTENT), NewsDetailContentItemView.class)
                .addModel(String.valueOf(Constants.DEF_NEWS_DETAIL_TYPE.IMG), NewsDetailImageItemView.class)
                .addModel(String.valueOf(Constants.DEF_NEWS_DETAIL_TYPE.SUMMARY), NewsDetailSummaryItemView.class)
                .build();
        return modelFactory;
    }

    public List<SimpleItemEntity> getWrapperList(List<NewsDetail> dataList){
        List<SimpleItemEntity> list = new ArrayList<SimpleItemEntity>();
        for (int i = 0; i < dataList.size(); i++) {
            ItemEntityWrapper.wrap(dataList.get(i)).setModelType(String.valueOf(dataList.get(i).getState())).attach(list);
        }
        return list;
    }

}
