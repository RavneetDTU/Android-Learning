package com.example.ravneet.firebaselearning;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ProfileActivity extends AppCompatActivity {

    TextView tv_username;
    Button btn_logout,btn_adddata;
    EditText et_name,et_adderss;

    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        tv_username = (TextView) findViewById(R.id.tv_useremail);
        btn_logout = (Button) findViewById(R.id.btn_logout);
        btn_adddata = (Button) findViewById(R.id.btn_addData);
        et_name = (EditText) findViewById(R.id.et_name);
        et_adderss = (EditText) findViewById(R.id.et_address);

        firebaseAuth = FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser() == null){
            startActivity(new Intent(this,LoginActivity.class));
            finish();
        }

        databaseReference = FirebaseDatabase.getInstance().getReference();

        FirebaseUser user = firebaseAuth.getCurrentUser();
        tv_username.setText("Welcome..... "+user.getEmail());

        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.signOut();
                finish();
                startActivity(new Intent(ProfileActivity.this,LoginActivity.class));
            }
        });

        btn_adddata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveuserInformation();
            }
        });


    }

    private void saveuserInformation(){
        String name = et_name.getText().toString().trim();
        String address = et_adderss.getText().toString().trim();

        UserInformation userInformation = new UserInformation(name,address);

        // Now we need Unique ID of Loggged In User
        FirebaseUser user = firebaseAuth.getCurrentUser();

        databaseReference.child(user.getUid()).setValue(userInformation);

        Toast.makeText(this,"Information Saved....",Toast.LENGTH_SHORT).show();

    }
}
