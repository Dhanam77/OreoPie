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

import static android.widget.Toast.LENGTH_SHORT;

public class SignUpActivity extends AppCompatActivity {

    private EditText userName, userEmail, userPassword;
    private Button SignUpButton;
    private ImageView SignUpLogo;
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    String Email, Password, Name;
    private ProgressBar loadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        InitializeFields();

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
        Name = userName.getText().toString();


        if (TextUtils.isEmpty(Email) && TextUtils.isEmpty(Password) && TextUtils.isEmpty(Name)) {
            Toast.makeText(this, "Please fill all details", Toast.LENGTH_SHORT).show();
        } else {
            loadingBar.setVisibility(View.VISIBLE);
            mAuth.createUserWithEmailAndPassword(Email, Password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {

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
        currentUser = mAuth.getCurrentUser();
        userName = (EditText) findViewById(R.id.full_name);
        userEmail = (EditText) findViewById(R.id.user_email);
        userPassword = (EditText) findViewById(R.id.user_password);
        SignUpButton = (Button) findViewById(R.id.signup_button);
        SignUpLogo = (ImageView) findViewById(R.id.signUp_logo);
        loadingBar = (ProgressBar)findViewById(R.id.signup_progressBar);
    }
}


