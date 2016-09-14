package org.lenve.meizi.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import org.lenve.meizi.bean.ClassfyBean;

import java.util.List;

/**
 * Created by 王松 on 2016/9/13.
 */
public class FgAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragments;
    private List<ClassfyBean.TngouBean> classfyBeen;

    public FgAdapter(FragmentManager fm, List<ClassfyBean.TngouBean> classfyBeen, List<Fragment> fragments) {
        super(fm);
        this.classfyBeen = classfyBeen;
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return classfyBeen.get(position).getTitle();
    }
}
