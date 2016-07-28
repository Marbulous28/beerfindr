package com.epicodus.beerfindr.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.epicodus.beerfindr.models.Beer;
import com.epicodus.beerfindr.ui.BeerDetailFragment;

import java.util.ArrayList;

/**
 * Created by Peter on 7/20/16.
 */
public class BeerPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<Beer> mBeers;

    public BeerPagerAdapter(FragmentManager fm, ArrayList<Beer> beers) {
        super(fm);
        mBeers = beers;
    }

    @Override
    public Fragment getItem(int position) {
        return BeerDetailFragment.newInstance(mBeers.get(position));
    }

    @Override
    public int getCount() {
        return mBeers.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {

        return mBeers.get(position).getName();
    }
}