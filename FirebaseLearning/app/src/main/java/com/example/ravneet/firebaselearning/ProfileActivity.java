package com.example.ravneet.firebaselearning;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfileActivity extends AppCompatActivity {

    TextView tv_username;
    Button btn_logout;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        tv_username = (TextView) findViewById(R.id.tv_useremail);
        btn_logout = (Button) findViewById(R.id.btn_logout);

        firebaseAuth = FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser() == null){
            startActivity(new Intent(this,LoginActivity.class));
            finish();
        }

        FirebaseUser user = firebaseAuth.getCurrentUser();
        tv_username.setText("Welcome...... "+user.getEmail());

        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.signOut();
                finish();
                startActivity(new Intent(ProfileActivity.this,LoginActivity.class));
            }
        });


    }
}
