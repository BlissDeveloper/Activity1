package com.example.activity1;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class InputActivity extends AppCompatActivity {
    private int flag = 0;
    private final double ONE_METER = 0.0254;
    private final double ONE_CENTIMETER = 2.54;

    private Toolbar toolbarInput;
    private EditText editTextInches;
    private Button buttonConvert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        editTextInches = findViewById(R.id.editTextInches);
        buttonConvert = findViewById(R.id.buttonConvert);
        toolbarInput = findViewById(R.id.toolbarInput);

        setSupportActionBar(toolbarInput);

        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if(getIntent().hasExtra("flag")) {
            flag = Integer.parseInt(getIntent().getExtras().get("flag").toString());
        }

        editTextInches.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if(s.toString().trim().length() > 0) {
                    buttonConvert.setVisibility(View.VISIBLE);
                }
                else {
                    buttonConvert.setVisibility(View.GONE);
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.toString().trim().length() > 0) {
                    buttonConvert.setVisibility(View.VISIBLE);
                }
                else {
                    buttonConvert.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        buttonConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    double inches = Double.parseDouble(editTextInches.getText().toString());

                    if(flag == 0) {
                        //In to M
                        // 1 in = 0.0254 m
                        // 1 in x (0.0254 m / 1 in)
                        double inches_to_meter = inches * ONE_METER;
                        goToResult(inches,inches_to_meter);
                    }
                    else if(flag == 1) {
                        //In to CM
                        // 1 in x (2.54 m / 1 in)
                        double inches_to_centimeter = inches * ONE_CENTIMETER;
                        goToResult(inches,inches_to_centimeter);
                    }
                    else if(flag == 2) {
                        //In to ft
                        // 1 ft = 12 in
                        // 12 in = 1 ft
                        // 1 in = 1 ft / 12
                        double inches_to_foot = inches / 12;
                        goToResult(inches,inches_to_foot);
                    }
                    else {
                    }
                }
                catch (Exception e) {
                   editTextInches.setError("Only number are accepted!");
                   editTextInches.requestFocus();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 26 && resultCode == RESULT_OK) {
            flag = Integer.parseInt(data.getExtras().get("flag").toString());
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        if(flag == 0) {
            //In to M
            Log.d("Avery", "Inches To Meter");
        }
        else if(flag == 1) {
            //In to CM
            Log.d("Avery", "Inches To Centimeter");
        }
        else if(flag == 2) {
            //In to ft
            Log.d("Avery", "Inches To Foot");
        }
        else {

        }

        buttonConvert.setVisibility(View.GONE);
    }

    public void goToResult(final double inches,final double result) {
        Intent intent = new Intent(InputActivity.this, ResultActivity.class);
        intent.putExtra("result", result);
        intent.putExtra("flag", flag);
        intent.putExtra("inches",inches );

        startActivity(intent);
    }
}
