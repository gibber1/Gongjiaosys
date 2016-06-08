package l_wl.cn.gongjiaosys.news;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import l_wl.cn.gongjiaosys.R;
import l_wl.cn.gongjiaosys.ui.BaseMainFragment;
import l_wl.cn.gongjiaosys.ui.fragment.HomeFragment;

/**
 * Created by l_wl on 2016/6/5.
 */
public class NewsFragment extends BaseMainFragment{
    public static final int NEWS_TYPE_NEWS = 0;
    public static final int NEWS_TYPE_Tech= 1;
    public static final int NEWS_TYPE_Notice = 2;

    private TabLayout mTablayout;
    private ViewPager mViewPager;
    private Toolbar mtoolbar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, null);
        mTablayout = (TabLayout) view.findViewById(R.id.tab_layout);
        mViewPager = (ViewPager) view.findViewById(R.id.viewPager);
        mViewPager.setOffscreenPageLimit(3);
        mtoolbar= (Toolbar) view.findViewById(R.id.toolbar);
        mtoolbar.setTitle("新闻");
        initToolbarNav(mtoolbar, false);

        mTablayout.addTab(mTablayout.newTab().setText(R.string.top));
        mTablayout.addTab(mTablayout.newTab().setText(R.string.tech));
        setupViewPager(mViewPager);
        mTablayout.setupWithViewPager(mViewPager);
        return view;
    }

    private void setupViewPager(ViewPager mViewPager) {
        //Fragment中嵌套使用Fragment一定要使用getChildFragmentManager(),否则会有问题
        MyPagerAdapter adapter = new MyPagerAdapter(getChildFragmentManager());
        adapter.addFragment(NewsListFragment.newInstance(NEWS_TYPE_NEWS), getString(R.string.top));
        adapter.addFragment(NewsListFragment.newInstance(NEWS_TYPE_Tech), getString(R.string.tech));
        adapter.addFragment(NewsListFragment.newInstance(NEWS_TYPE_Notice), getString(R.string.notice));
        mViewPager.setAdapter(adapter);
    }

    public static class MyPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragments = new ArrayList<>();
        private final List<String> mFragmentTitles = new ArrayList<>();

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment, String title) {
            mFragments.add(fragment);
            mFragmentTitles.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitles.get(position);
        }
    }
}
