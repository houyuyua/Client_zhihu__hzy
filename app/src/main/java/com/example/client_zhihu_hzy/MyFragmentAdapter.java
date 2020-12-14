package com.example.client_zhihu_hzy;

import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class MyFragmentAdapter extends FragmentPagerAdapter {
    public final static int TAB_COUNT = 4;
    public MyFragmentAdapter(FragmentManager fm){ super(fm);
    }


    @Override
    public Fragment getItem(int position) {
        switch(position){
            case MyFirstActivityDl.TAB_HOME:
                HomeFragment homeFragment = new HomeFragment();
                return homeFragment;

            case MyFirstActivityDl.TAB_FIND:
                FindFragment findFragment = new FindFragment();
                return findFragment;

            case MyFirstActivityDl.TAB_MESS:
                MessFragment messFragment = new MessFragment();
                return messFragment;

            case MyFirstActivityDl.TAB_MINE:
                MineFragment mineFragment = new MineFragment();
                return mineFragment;
        }
        return null;
    }

    @Override
    public int getCount() {
        return TAB_COUNT;
    }
}
