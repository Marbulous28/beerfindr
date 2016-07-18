package com.epicodus.beerfindr;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class BeersActivity extends AppCompatActivity {
    public static final String TAG = BeersActivity.class.getSimpleName();
    private String[] beers = new String[] {"beer1", "beer2",
            "beer3", "beer4"};
    @Bind (R.id.listView) ListView mListView;
    @Bind(R.id.beerTextView) TextView mBeerTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beers);

       ButterKnife.bind(this);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, beers);
        mListView.setAdapter(adapter);
        getBeers();

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String beer = ((TextView)view).getText().toString();
                Toast.makeText(BeersActivity.this, beer, Toast.LENGTH_LONG).show();
            }
        });

        Intent intent = getIntent();
        String beer = intent.getStringExtra("beer");
        mBeerTextView.setText("Beers: " + beer);
    }

    private void getBeers() {
        final BeerService beerService = new BeerService();
        beerService.findBeers(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    String jsonData = response.body().string();
                    Log.v(TAG, jsonData);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
