package com.example.risalat.slidingtabsusingviewpager;

import android.app.Fragment;
import android.media.MediaExtractor;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Risalat on 3/20/2018.
 */

public class PagerAdapter extends FragmentPagerAdapter {

    int mNumberOfTabs;
    public PagerAdapter(FragmentManager fm, int NumberOfTabs) {
        super(fm);
        this.mNumberOfTabs=NumberOfTabs;
    }

    @Override
    public android.support.v4.app.Fragment getItem(int position) {
        switch (position){
            case 0:
                Tab1 tab1=new Tab1();
                return tab1;

            case 1:
                Tab2 tab2=new Tab2();
                return tab2;

            case 2:
                Tab3 tab3=new Tab3();
                return tab3;

                default:
                    return null;
        }



    }

    @Override
    public int getCount() {
        return mNumberOfTabs;
    }
}
