package com.example.oreopie;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.ProgressDialog;
import android.content.Intent;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

import static android.widget.Toast.LENGTH_SHORT;

public class SignUpActivity extends AppCompatActivity {

    private EditText userName, userEmail, userPassword, COnfirmPassword,Age;
    private Button SignUpButton;
    private ImageView SignUpLogo;
     FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    String Email, Password,SecondPassword, Name, age;
    private ProgressBar loadingBar;
    private DatabaseReference Ref;
    String currentUserID;

    String UID = "blank";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        InitializeFields();
        loadingBar.setVisibility(View.INVISIBLE);



        SignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckCredentials();
            }
        });
    }

    private void CheckCredentials() {

        Email = userEmail.getText().toString();
        Password = userPassword.getText().toString();
        SecondPassword = COnfirmPassword.getText().toString();
        Name = userName.getText().toString();
        age = Age.getText().toString();


        if (TextUtils.isEmpty(Email) && TextUtils.isEmpty(Password) && TextUtils.isEmpty(Name) && TextUtils.isEmpty(SecondPassword) && TextUtils.isEmpty(age) ) {
            Toast.makeText(this, "Please fill all details", Toast.LENGTH_SHORT).show();
        }
        if(!Password.equals(SecondPassword))
        {
            Toast.makeText(SignUpActivity.this,"Passwords don't match!",LENGTH_SHORT);
        }

        else {
            loadingBar.setVisibility(View.VISIBLE);
            mAuth.createUserWithEmailAndPassword(Email, Password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                HashMap<String,String> profileMap = new HashMap<>();
                                profileMap.put("username",Name);
                                profileMap.put("password",Password);
                                profileMap.put("email",Email);
                                profileMap.put("age",age);
                                Ref.child(UID).setValue(profileMap);
                                Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                                finish();
                                loadingBar.setVisibility(View.INVISIBLE);
                            } else {
                                String message = task.getException().toString();
                                Toast.makeText(SignUpActivity.this, "Error:" + message, LENGTH_SHORT).show();
                                loadingBar.setVisibility(View.INVISIBLE);

                            }
                        }


                    });
        }

    }

    private void InitializeFields() {
        mAuth = FirebaseAuth.getInstance();

        if ( mAuth.getCurrentUser() != null) {
            currentUser = mAuth.getCurrentUser();
            UID = currentUser.getUid();
        }

        Ref = FirebaseDatabase.getInstance().getReference("Users");
        Age = (EditText)findViewById(R.id.user_age);
        userName = (EditText) findViewById(R.id.full_name);
        userEmail = (EditText) findViewById(R.id.user_email);
        userPassword = (EditText) findViewById(R.id.user_password);
        SignUpButton = (Button) findViewById(R.id.signup_button);
        SignUpLogo = (ImageView) findViewById(R.id.signUp_logo);
        loadingBar = (ProgressBar)findViewById(R.id.signup_progressBar);
        COnfirmPassword = (EditText)findViewById(R.id.user_confirmpassword);
    }
}


