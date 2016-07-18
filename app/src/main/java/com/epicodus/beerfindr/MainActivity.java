package com.epicodus.beerfindr;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
        private Button mFindBeersButton;
        private EditText mBeerEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBeerEditText = (EditText) findViewById(R.id.beerEditText);
        mFindBeersButton = (Button) findViewById(R.id.findBeersButton);
        mFindBeersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String beer = mBeerEditText.getText().toString();
                Intent intent = new Intent(MainActivity.this, BeersActivity.class);
                intent.putExtra("beer", beer);
                startActivity(intent);
            }
        });
    }
}
