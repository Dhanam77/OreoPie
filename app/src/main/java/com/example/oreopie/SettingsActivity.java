package com.example.oreopie;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

public class SettingsActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private TextView Currency,Legal,AboutUS,AppVersion;
    private ImageView settingsLogo;
    private Spinner currencySpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        InitializeFields();
        String[] currencyTypes = {"INR", "USD", "GBP"};

        currencySpinner.setOnItemSelectedListener(this);
        ArrayAdapter currencyAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,currencyTypes);
        currencyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        currencySpinner.setAdapter(currencyAdapter);

        Legal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingsActivity.this, LegalActivity.class);
                startActivity(intent);
            }
        });

        AboutUS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingsActivity.this, AboutUsActivity.class);
                startActivity(intent);
            }
        });

    }

    private void InitializeFields() {
        currencySpinner =  (Spinner)findViewById(R.id.currency_spinner);
        Currency = (TextView)findViewById(R.id.currency);
        Legal = (TextView)findViewById(R.id.legal);
        AboutUS = (TextView)findViewById(R.id.aboutUs);
        AppVersion = (TextView)findViewById(R.id.version);
        settingsLogo = (ImageView)findViewById(R.id.settings_logo);

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(SettingsActivity.this, MainActivity.class);
        super.onBackPressed();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
