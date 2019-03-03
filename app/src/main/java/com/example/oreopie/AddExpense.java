package com.example.oreopie;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class AddExpense extends AppCompatActivity {
    private ImageView logo;
    private TextView addExpenses, Loan, Medical, Food, Clothing,Transport,Leisure,Bills, Education;
    private EditText etLoan, etMedical, etFood, etClothing, etTransport,etLeisure, etBills, etEducation;
    private Button submit;
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    private DatabaseReference Ref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);
        InitializeFields();
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        final String UID = mAuth.getCurrentUser().getUid();
        Ref = FirebaseDatabase.getInstance().getReference();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Loan = etLoan.getText().toString();
                String Food = etFood.getText().toString();
                String Medical = etMedical.getText().toString();
                String Clothing = etClothing.getText().toString();
                String Transport = etTransport.getText().toString();
                String Leisure = etLeisure.getText().toString();
                String Bills = etBills.getText().toString();
                String Education = etEducation.getText().toString();

                if(!TextUtils.isEmpty(Loan) && !TextUtils.isEmpty(Food) && !TextUtils.isEmpty(Medical) && !TextUtils.isEmpty(Clothing) && !TextUtils.isEmpty(Transport) && !TextUtils.isEmpty(Leisure) && !TextUtils.isEmpty(Bills) && !TextUtils.isEmpty(Education)) {
                    Toast.makeText(AddExpense.this, "Please fill all details", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    HashMap<String, String>ExpenseMap = new HashMap<>();
                    ExpenseMap.put("loan",Loan);
                    ExpenseMap.put("food",Food);
                    ExpenseMap.put("Medical",Medical);
                    ExpenseMap.put("education",Education);
                    ExpenseMap.put("leisure",Leisure);
                    ExpenseMap.put("clothing",Clothing);
                    ExpenseMap.put("bills",Bills);
                    ExpenseMap.put("transport",Transport);

                    Ref.child("Users").child(UID).setValue(ExpenseMap);

                    Toast.makeText(AddExpense.this, "Expenses Added Successfully", Toast.LENGTH_SHORT).show();


                }

            }
        });


    }

    private void InitializeFields() {
        logo = (ImageView)findViewById(R.id.addexpenselogo);
        Loan =(TextView)findViewById(R.id.LoanView);
        addExpenses =(TextView)findViewById(R.id.addExpenses);
        Medical =(TextView)findViewById(R.id.addExpenses);
        Food =(TextView)findViewById(R.id.addExpenses);
        Clothing =(TextView)findViewById(R.id.addExpenses);
        Transport =(TextView)findViewById(R.id.addExpenses);
        Leisure =(TextView)findViewById(R.id.addExpenses);
        Bills =(TextView)findViewById(R.id.addExpenses);
        Education =(TextView)findViewById(R.id.addExpenses);
        etLoan = (EditText)findViewById(R.id.loanText);
        etMedical = (EditText)findViewById(R.id.medicalText);
        etFood = (EditText)findViewById(R.id.foodText);
        etClothing = (EditText)findViewById(R.id.clothingText);
        etTransport = (EditText)findViewById(R.id.transportText);
        etLeisure = (EditText)findViewById(R.id.leisureText);
        etBills = (EditText)findViewById(R.id.billsText);
        etEducation = (EditText)findViewById(R.id.educationText);
        submit = (Button)findViewById(R.id.submit);

    }
}
