package com.example.oreopie;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddIncome extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    private DatabaseReference Ref;
    private TextView AddIncome;
    private EditText etIncome;
    private Button  submit;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_income);
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        Ref = FirebaseDatabase.getInstance().getReference();
        AddIncome = (TextView)findViewById(R.id.add_income);
        etIncome = (EditText)findViewById(R.id.annualIncome);
        submit = (Button)findViewById(R.id.submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Income = etIncome.getText().toString();
                if(TextUtils.isEmpty(Income))
                {
                    Toast.makeText(AddIncome.this, "Please fill all details", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    String currentUserID = mAuth.getCurrentUser().getUid();
                    Ref.child("Income").child(currentUserID).setValue(Income);
                }
            }
        });
    }
}
