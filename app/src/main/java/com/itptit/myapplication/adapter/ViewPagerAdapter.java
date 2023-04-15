package com.itptit.myapplication.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.itptit.myapplication.fragment.InforFragment;
import com.itptit.myapplication.fragment.ListviewFragment;
import com.itptit.myapplication.fragment.SearchFragment;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    public ViewPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment frag = null;
        switch (position) {
            case 0:
                frag = new ListviewFragment();
                break;
            case 1:
                frag = new InforFragment();
                break;
            case 2:
                frag = new SearchFragment();
                break;
        }
        return frag;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        switch (position) {
            case 0:
                title = "Home";
                break;
            case 1:
                title = "My info";
                break;
            case 2:
                title = "Search";
                break;
        }
        return title;
    }
}

