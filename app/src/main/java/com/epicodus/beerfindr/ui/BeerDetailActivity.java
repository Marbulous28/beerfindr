package com.epicodus.beerfindr.ui;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.epicodus.beerfindr.R;
import com.epicodus.beerfindr.adapters.BeerPagerAdapter;
import com.epicodus.beerfindr.models.Beer;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class BeerDetailActivity extends AppCompatActivity {
    @Bind(R.id.viewPager) ViewPager mViewPager;
    private BeerPagerAdapter adapterViewPager;
    ArrayList<Beer> mBeers = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beer_detail);
        ButterKnife.bind(this);

        mBeers = Parcels.unwrap(getIntent().getParcelableExtra("beers"));

        int startingPosition = getIntent().getIntExtra("position", 0);

        adapterViewPager = new BeerPagerAdapter(getSupportFragmentManager(), mBeers);
        mViewPager.setAdapter(adapterViewPager);
        mViewPager.setCurrentItem(startingPosition);
    }
}