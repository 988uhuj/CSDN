package github.chenupt.csdn.fragments;

import android.support.v4.view.ViewPager;

import com.astuetz.PagerSlidingTabStrip;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.res.StringArrayRes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import github.chenupt.csdn.R;
import github.chenupt.csdn.adapter.ModelFactory;
import github.chenupt.csdn.adapter.SimplePagerAdapter;
import github.chenupt.csdn.base.BaseFragment;

/**
 * Created by chenupt@gmail.com on 2014/9/21.
 * Description TODO
 */
@EFragment(R.layout.fragment_pager_news)
public class NewsPagerFragment extends BaseFragment{

    @ViewById(R.id.view_pager)
    ViewPager viewPager;

    @StringArrayRes(R.array.news_menu)
    String[] newsMenuArray;

    @ViewById
    PagerSlidingTabStrip tabs;

    @AfterViews
    void afterViews(){
        ModelFactory modelFactory = new ModelFactory();
        modelFactory.addCommonFragment(NewsFragment_.class, getListData(), Arrays.asList(newsMenuArray));
        SimplePagerAdapter adapter = new SimplePagerAdapter(getFragmentManager(), modelFactory);
        viewPager.setAdapter(adapter);

        // Bind the tabs to the ViewPager
        tabs.setViewPager(viewPager);
        // continued from above
//        tabs.setOnPageChangeListener(mPageChangeListener);
    }

    private List<String> getListData(){
        String testUrl = "";
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < newsMenuArray.length; i++) {
            list.add(testUrl);
        }
        return list;
    }
}
