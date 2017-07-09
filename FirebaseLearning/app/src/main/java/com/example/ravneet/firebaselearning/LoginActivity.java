package com.example.ravneet.firebaselearning;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ViewUtils;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tv_registration;
    EditText et_email,et_password;
    Button btn_login;

    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuth = FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(this);

        if(firebaseAuth.getCurrentUser() != null){
            //profile Activity starts as User is already Logged In
            startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
            finish();
        }

        tv_registration = (TextView) findViewById(R.id.tv_register);
        btn_login = (Button) findViewById(R.id.btn_login);
        et_email = (EditText) findViewById(R.id.et_email);
        et_password = (EditText) findViewById(R.id.et_password);

        btn_login.setOnClickListener(this);
        tv_registration.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        if(view == tv_registration){

            Intent i = new Intent(LoginActivity.this,MainActivity.class);
            startActivity(i);

        }else if(view == btn_login){
            UserLogin();
        }

    }

    private void UserLogin() {
        String email = et_email.getText().toString().trim();
        String password = et_password.getText().toString().trim();

        if(TextUtils.isEmpty(email)){
            // Email Field is Empty
            Toast.makeText(LoginActivity.this,"Please Enter Email",Toast.LENGTH_SHORT).show();
            // Stop Further excution function
            return;

        }else if(TextUtils.isEmpty(password)) {
            //Password Field is Empty
            Toast.makeText(LoginActivity.this, "Please Enter Password", Toast.LENGTH_SHORT).show();
            // Stop futher excution functions
            return;
        }

        progressDialog.setMessage("Regestring User.....");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    progressDialog.dismiss();
                    //Start Profile Activity
                    startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                    finish();
                }else {

                }
            }
        });

    }
}
