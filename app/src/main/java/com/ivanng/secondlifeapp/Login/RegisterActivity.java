package com.ivanng.secondlifeapp.Login;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ivanng.secondlifeapp.Home.HomeActivity;
import com.ivanng.secondlifeapp.R;
import com.ivanng.secondlifeapp.Utils.FirebaseMethods;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {
    private static final String TAG = "RegisterActivity";

    private String email, username, password;
    private EditText mEmail, mPassword, mUsername;
    private Button btnLogin, confirmsignup;
    private FirebaseAuth mAuth;
    private DatabaseReference reference;
    private FirebaseMethods firebaseMethods;
    private Context mContext;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mContext = RegisterActivity.this;
        firebaseMethods = new FirebaseMethods(mContext);

        Log.d(TAG, "onCreate: started.");

        mEmail = findViewById(R.id.signup_email);
        mPassword = findViewById(R.id.signup_password);
        mUsername = findViewById(R.id.signup_fullname);
        btnLogin = findViewById(R.id.btnLogin);
        confirmsignup = findViewById(R.id.confirmsignup);

        mAuth = FirebaseAuth.getInstance();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });


        confirmsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txt_username = mUsername.getText().toString();
                String txt_email = mEmail.getText().toString();
                String txt_password = mPassword.getText().toString();


                if (TextUtils.isEmpty(txt_username) || TextUtils.isEmpty(txt_email) || TextUtils.isEmpty(txt_password)) {
                    Toast.makeText(RegisterActivity.this, "All fileds are required", Toast.LENGTH_SHORT).show();
                } else if (txt_password.length() < 6 ) {
                    Toast.makeText(RegisterActivity.this,"password must be at least 6 character",Toast.LENGTH_SHORT).show();
                } else {
                    register(txt_username,txt_email,txt_password);
                }


            }
        });
    }
    private void register(final String username, final String email, String password){

        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            firebaseMethods.addNewUser(email, username, "", "", "");

                            FirebaseUser firebaseUser = mAuth.getCurrentUser();
                            assert firebaseUser  != null;
                            String userid = firebaseUser.getUid();

                            reference = FirebaseDatabase.getInstance().getReference("Users").child(userid);

                            HashMap<String, String> hashMap = new HashMap<>();
                            hashMap.put("id", userid);
                            hashMap.put("username",username);
                            hashMap.put("imageURL", "default");
                            hashMap.put("status","offline");
                            hashMap.put("search", username.toLowerCase());


                            Toast.makeText(RegisterActivity.this, "Register success", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent();
                            intent.setClass(RegisterActivity.this,LoginActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                            finish();

                        }else {
                            Toast.makeText(RegisterActivity.this, "You can't register with this email or password", Toast.LENGTH_SHORT).show();
                        }



                    }
                });



    }
}