package com.example.hangukkid.foodie.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;



public class PagerAdapter extends FragmentPagerAdapter {
    private static final int NUM_PAGES = 3;

    private String[] titles = {"Main"};

    public PagerAdapter(FragmentManager fm){
            super(fm);
        }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            // make screen swiping
//            case 0:
//                return .newInstance();
            default:
                return null;
        }
    }


    @Override
    public int getCount() {
        return NUM_PAGES;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }


}
