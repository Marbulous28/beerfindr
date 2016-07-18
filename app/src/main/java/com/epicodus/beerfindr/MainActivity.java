package com.epicodus.beerfindr;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.findBeersButton) Button mFindBeersButton;
    @Bind(R.id.beerEditText) EditText mBeerEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        mFindBeersButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == mFindBeersButton) {
            String beer = mBeerEditText.getText().toString();
            Intent intent = new Intent(MainActivity.this, BeersActivity.class);
            intent.putExtra("beer", beer);
            startActivity(intent);
        }
    }
}
