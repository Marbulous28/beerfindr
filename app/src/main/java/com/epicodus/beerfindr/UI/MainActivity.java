package com.epicodus.beerfindr.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.epicodus.beerfindr.Constants;
import com.epicodus.beerfindr.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    @Bind(R.id.findBeersButton) Button mFindBeersButton;
    @Bind(R.id.beerEditText) EditText mBeerEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mSharedPreferences.edit();

        mFindBeersButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == mFindBeersButton) {
            String beer = mBeerEditText.getText().toString();
            addToSharedPreferences(beer);
            Intent intent = new Intent(MainActivity.this, BeerListActivity.class);
            intent.putExtra("beer", beer);
            startActivity(intent);
        }
    }

    private void addToSharedPreferences(String location) {
        mEditor.putString(Constants.PREFERENCES_TEST_KEY, location).apply();
    }
}
