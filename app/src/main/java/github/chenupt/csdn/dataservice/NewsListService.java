package github.chenupt.csdn.dataservice;

import org.androidannotations.annotations.EBean;

import java.util.ArrayList;
import java.util.List;

import github.chenupt.common.listhelper.ItemEntityWrapper;
import github.chenupt.common.listhelper.ModelFactory;
import github.chenupt.common.listhelper.SimpleItemEntity;
import github.chenupt.csdn.customitemview.NewsItemView_;
import github.chenupt.csdn.entity.NewsItem;

/**
 * Created by chenupt@gmail.com on 2014/9/22.
 * Description TODO
 */
@EBean
public class NewsListService {

    public ModelFactory getFactory(){
        ModelFactory modelFactory = new ModelFactory.Builder()
                .addModel(NewsItemView_.class)
                .build();
        return modelFactory;
    }

    public List<SimpleItemEntity> getWrapperList(List<NewsItem> dataList){
        List<SimpleItemEntity> list = new ArrayList<SimpleItemEntity>();
        for (int i = 0; i < dataList.size(); i++) {
            ItemEntityWrapper.wrap(dataList.get(i)).setModelView(NewsItemView_.class).attach(list);
        }
        return list;
    }

}
