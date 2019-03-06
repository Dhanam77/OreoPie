package com.example.oreopie;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class ConfigureActivity extends AppCompatActivity {

    EditText eneed, ewant, esav,esal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configure);
    }

    public void aSalary(View view) {
        esal = findViewById(R.id.annualIncome);
        eneed = findViewById(R.id.need);
        ewant = findViewById(R.id.want);
        esav = findViewById(R.id.savings);
        int d = Integer.parseInt(esal.getText().toString());
        eneed.setText((int) (d*0.5));
        ewant.setText((int) (0.5 * (d)));
        esav.setText((int) (0.5 * (d)));
    }
}
