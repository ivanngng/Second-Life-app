package com.ivanng.secondlifeapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUp extends AppCompatActivity {

    private Button btnLogin, btnSignup;
    private Button confirmSignUp;
    private EditText sign_email, sign_password, sign_cpassword;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        btnLogin = findViewById(R.id.btnLogin);
        btnSignup = findViewById(R.id.btnSignup);

        confirmSignUp = findViewById(R.id.confirmsignup);
        sign_email = findViewById(R.id.signup_email);
        sign_password = findViewById(R.id.signup_password);
        sign_cpassword = findViewById(R.id.signup_cpassword);

        firebaseAuth = FirebaseAuth.getInstance();


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUp.this, Login.class);
                startActivity(intent);
            }
        });

        confirmSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = sign_email.getText().toString().trim();
                String password = sign_password.getText().toString().trim();
                String confirmpassword = sign_cpassword.getText().toString().trim();


                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(SignUp.this, "Please Enter Email", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(SignUp.this, "Please Enter Password", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(confirmpassword)) {
                    Toast.makeText(SignUp.this, "Please Enter Password Again", Toast.LENGTH_SHORT).show();
                    return;
                }



                if (password.length()<6) {
                    Toast.makeText(SignUp.this, "Password too short", Toast.LENGTH_SHORT).show();
                }

                if (password.equals(confirmpassword)) {
                    firebaseAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(SignUp.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {


                                    if (task.isSuccessful()) {

                                        startActivity(new Intent(getApplicationContext(), Login.class));
                                        Toast.makeText(SignUp.this, "Registeration Complete", Toast.LENGTH_SHORT).show();

                                    } else {

                                        Toast.makeText(SignUp.this, "Authentication Failed", Toast.LENGTH_SHORT).show();

                                    }

                                    // ...
                                }
                            });
                }



            }
        });
    }
}
