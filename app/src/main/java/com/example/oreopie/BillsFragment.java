package com.example.oreopie;


import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class BillsFragment extends Fragment {

    private View myView;
    private TextView addBills;
    private EditText billType, companyName, Date, Amount;
    private Button Submit;


    public BillsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        myView =  inflater.inflate(R.layout.fragment_bills, container, false);
        addBills = (TextView)myView.findViewById(R.id.addBills);
        billType = (EditText)myView.findViewById(R.id.billType);
        companyName = (EditText)myView.findViewById(R.id.companyName);
        Date = (EditText)myView.findViewById(R.id.date);
        Amount = (EditText)myView.findViewById(R.id.amount);
        Submit = (Button)myView.findViewById(R.id.submit);

        Submit.setOnClickListener(new View.OnClickListener() {

            String a = billType.getText().toString();
            String b = companyName.getText().toString();
            String c = Date.getText().toString();
            String d = Amount.getText().toString();
            @Override
            public void onClick(View v) {
               if(!TextUtils.isEmpty(a) && !TextUtils.isEmpty(b) && !TextUtils.isEmpty(c) && !TextUtils.isEmpty(d))
               {

               }
            }
        });


        return myView;
    }

}
