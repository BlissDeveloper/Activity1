package com.example.activity1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Toolbar mToolbar;
    private Button buttonInchToMeter, buttonInchToCentimeter, buttonInchToFoot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar = findViewById(R.id.toolbarMain);
        buttonInchToMeter = findViewById(R.id.buttonInchToMeter);
        buttonInchToCentimeter = findViewById(R.id.buttonInchToCentimeter);
        buttonInchToFoot = findViewById(R.id.buttonInchToFoot);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Unit Conversion");

        buttonInchToMeter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, InputActivity.class);
                intent.putExtra("flag", 0);
                startActivity(intent);
            }
        });

        buttonInchToCentimeter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, InputActivity.class);
                intent.putExtra("flag", 1);
                startActivity(intent);
            }
        });

        buttonInchToFoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, InputActivity.class);
                intent.putExtra("flag", 2);
                startActivity(intent);
            }
        });
    }
}
