package com.example.activity1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {
    private int flag = 0;
    private double result = 0.0;
    private double inches = 0.0;

    private Toolbar mToolbar;
    private TextView textViewResultUnit;
    private TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        mToolbar = findViewById(R.id.toolbarResult);
        textViewResultUnit = findViewById(R.id.textViewResultUnit);
        textViewResult = findViewById(R.id.textViewResult);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void onStart() {
        super.onStart();

        flag = Integer.parseInt(getIntent().getExtras().get("flag").toString());
        result = Double.parseDouble(getIntent().getExtras().get("result").toString());
        inches = Double.parseDouble(getIntent().getExtras().get("inches").toString());

        if (flag == 0) {
            textViewResultUnit.setText(R.string.meter);
            textViewResult.setText(inches + " in " + "= " + result + " meters");
        } else if (flag == 1) {
            textViewResultUnit.setText(R.string.centi);
            textViewResult.setText(inches + " in " + "= " + result + " centimeters");
        } else if (flag == 2) {
            textViewResultUnit.setText(R.string.foot);
            textViewResult.setText(inches + " in " + "= " + result + " feet");
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent intent = new Intent();
        intent.putExtra("flag", flag);
        setResult(26, intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(ResultActivity.this, InputActivity.class);
                intent.putExtra("flag", flag);
                setResult(26, intent);
                startActivity(intent);

                Log.d("Avery", "Back pressed");
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }
}
