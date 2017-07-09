package com.example.ravneet.firebaselearning;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
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

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText tv_email,tv_password;
    Button btn_register;
    TextView tv_login;

    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressDialog = new ProgressDialog(this);
        firebaseAuth = FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser() != null){
            startActivity(new Intent(this,ProfileActivity.class));
            finish();
        }

        tv_email = (EditText) findViewById(R.id.et_email);
        tv_password = (EditText) findViewById(R.id.et_password);
        btn_register = (Button)findViewById(R.id.btn_register);
        tv_login = (TextView) findViewById(R.id.tv_login);

        //Attaching on ClickListner

        btn_register.setOnClickListener(this);
        tv_login.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        if(view == btn_register){
            registerUser();
        }else if(view == tv_login){
            // Open Login Activity
            startActivity(new Intent(this,LoginActivity.class));
        }

    }

    private void registerUser() {
        String email = tv_email.getText().toString().trim();
        String password = tv_password.getText().toString().trim();

        if(TextUtils.isEmpty(email)){
            // Email Field is Empty
            Toast.makeText(MainActivity.this,"Please Enter Email",Toast.LENGTH_SHORT).show();
            // Stop Further excution function
            return;

        }else if(TextUtils.isEmpty(password)) {
            //Password Field is Empty
            Toast.makeText(MainActivity.this, "Please Enter Password", Toast.LENGTH_SHORT).show();
            // Stop futher excution functions
            return;
        }

        progressDialog.setMessage("Regestring User.....");
        progressDialog.show();
        // This will take 2 Arguments Email and Password to Register our User
        firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    progressDialog.dismiss();
                    //User is Successfully registered and LoggedIn and Now Start Profile Activity
                    Toast.makeText(MainActivity.this,"Regestered Successfully",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(),ProfileActivity.class));
                    finish();
                }else {
                    Toast.makeText(MainActivity.this,"Failed to Register",Toast.LENGTH_SHORT).show();
                }
        }
        });
    }
}
