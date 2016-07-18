package com.epicodus.beerfindr.UI;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

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

public class BeersActivity extends AppCompatActivity {
    public static final String TAG = BeersActivity.class.getSimpleName();

    @Bind (R.id.listView) ListView mListView;
    @Bind(R.id.beerTextView) TextView mBeerTextView;

    public ArrayList<Beer> mBeers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beers);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        mBeerTextView.setText("Here are some random beers:");
            getBeers();
    }

    private void getBeers() {
        final BeerService beerService = new BeerService();

        beerService.findBeers(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) {
                mBeers = beerService.processResults(response);;

                BeersActivity.this.runOnUiThread(new Runnable() {

                    @Override
                    public void run() {

                        String[] beerNames = new String[mBeers.size()];
                        for (int i = 0; i < beerNames.length; i++) {
                            beerNames[i] = mBeers.get(i).getName();
                        }

                        ArrayAdapter adapter = new ArrayAdapter(BeersActivity.this,
                                android.R.layout.simple_list_item_1, beerNames);
                        mListView.setAdapter(adapter);

                        for (Beer beer : mBeers) {
                            Log.d(TAG, "Name: " + beer.getName());
                            Log.d(TAG, "Description: " + beer.getDescription());
                            Log.d(TAG, "ABV: " + beer.getABV());
                            Log.d(TAG, "IBU: " + beer.getIBU());
                        }
                    }
                });
            }
        });
    }
}