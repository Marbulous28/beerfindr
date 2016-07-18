package com.epicodus.beerfindr;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;

public class BeersActivity extends AppCompatActivity {
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
}
