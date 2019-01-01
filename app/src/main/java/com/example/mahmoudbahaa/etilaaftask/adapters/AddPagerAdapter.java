package com.example.mahmoudbahaa.etilaaftask.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.mahmoudbahaa.etilaaftask.utilities.ui.photo.PhotosFragment;
import com.example.mahmoudbahaa.etilaaftask.utilities.ui.story.StoriesFragment;

/**
 * Created by MahmoudBahaa on 31/12/2018.
 */

public class AddPagerAdapter extends FragmentPagerAdapter {


    private int numOfTabs;

    public AddPagerAdapter(FragmentManager fm, int numOfTabs){
        super(fm);
        this.numOfTabs = numOfTabs;

    }

    @Override
    public Fragment getItem(int position) {
        switch (position)
        {

            case 0:
                return new StoriesFragment();
            case 1:
                return new PhotosFragment();

            default:
                return null;


        }

    }

    @Override
    public int getCount() {
        return numOfTabs;
    }

}
