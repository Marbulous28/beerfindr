package com.epicodus.beerfindr.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.epicodus.beerfindr.adapters.BeerListAdapter;
import com.epicodus.beerfindr.services.BeerService;
import com.epicodus.beerfindr.R;
import com.epicodus.beerfindr.models.Beer;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class BeerListActivity extends AppCompatActivity {
    public static final String TAG = BeerListActivity.class.getSimpleName();

    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;
    private BeerListAdapter mAdapter;

    public ArrayList<Beer> mBeers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beers);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String abv = intent.getStringExtra("abv");

        getBeers(abv);
    }

    private void getBeers(String abv) {
        final BeerService beerService = new BeerService();

        beerService.findBeers(abv, new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) {
                mBeers = beerService.processResults(response);;

                BeerListActivity.this.runOnUiThread(new Runnable() {


                    @Override
                    public void run() {

                        mAdapter = new BeerListAdapter(getApplicationContext(), mBeers);
                        mRecyclerView.setAdapter(mAdapter);
                        RecyclerView.LayoutManager layoutManager =
                                new LinearLayoutManager(BeerListActivity.this);
                        mRecyclerView.setLayoutManager(layoutManager);
                        mRecyclerView.setHasFixedSize(true);
                    }
                });
            }
        });
    }
}