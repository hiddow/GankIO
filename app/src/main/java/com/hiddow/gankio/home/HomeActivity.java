package com.hiddow.gankio.home;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;
import android.view.ViewGroup;

import com.hiddow.gankio.R;
import com.hiddow.gankio.base.BaseActivity;
import com.hiddow.gankio.home.android.AndroidFragment;
import com.hiddow.gankio.home.ios.IOSFragment;
import com.hiddow.gankio.home.welfare.WelfareFragment;

import butterknife.BindView;

/**
 * Created by yangxiaoguang on 2016/11/4.
 */

public class HomeActivity extends BaseActivity {

    @BindView(R.id.bottom_navigation_view)
    BottomNavigationView mBottomNavigationView;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;
    private TabIndexAdapter mAdapter;
    private MenuItem prevMenuItem;

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        mAdapter = new TabIndexAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mAdapter);
        mBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.item_welfare:
                        mViewPager.setCurrentItem(0);
                        break;
                    case R.id.item_android:
                        mViewPager.setCurrentItem(1);
                        break;
                    case R.id.item_iOS:
                        mViewPager.setCurrentItem(2);
                        break;
                }
                return false;
            }
        });

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (prevMenuItem != null) {
                    prevMenuItem.setChecked(false);
                } else {
                    mBottomNavigationView.getMenu().getItem(0).setChecked(false);
                }

                mBottomNavigationView.getMenu().getItem(position).setChecked(true);
                prevMenuItem = mBottomNavigationView.getMenu().getItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mViewPager.setCurrentItem(1);

    }

    @Override
    public int getLayoutResource() {
        return R.layout.activity_home;
    }

    private class TabIndexAdapter extends FragmentPagerAdapter {

        private Class[] fragmentsClass = new Class[]{
                WelfareFragment.class,
                AndroidFragment.class,
                IOSFragment.class,
        };


        public TabIndexAdapter(FragmentManager fm) {
            super(fm);

        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            super.destroyItem(container, position, object);
        }

        @Override
        public android.support.v4.app.Fragment getItem(int position) {
            try {
                Fragment fragment = null;
                switch (position) {
                    case 0:
                        fragment = WelfareFragment.newInstance();
                        break;
                    case 1:
                        fragment = AndroidFragment.newInstance();
                        break;
                    case 2:
                        fragment = IOSFragment.newInstance();
                        break;
                }
                return fragment;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        public int getCount() {
            return fragmentsClass.length;
        }


    }

}
