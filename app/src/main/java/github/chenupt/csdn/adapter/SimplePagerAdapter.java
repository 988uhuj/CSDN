package github.chenupt.csdn.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by chenupt@gmail.com on 2014/8/9.
 * Description TODO
 */
public class SimplePagerAdapter extends FragmentStatePagerAdapter {

    protected ModelFactory modelFactory;

    public SimplePagerAdapter(FragmentManager fm, ModelFactory modelFactory) {
        super(fm);
        this.modelFactory = modelFactory;
    }

    @Override
    public Fragment getItem(int position) {
        return modelFactory.getItem(position);
    }

    @Override
    public int getCount() {
        return modelFactory.getFragmentCount();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if(modelFactory.hasTitles()){
            return modelFactory.getTilte(position);
        }
        return super.getPageTitle(position);
    }
}
