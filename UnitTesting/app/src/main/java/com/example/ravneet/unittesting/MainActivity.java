package com.example.ravneet.unittesting;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText et_Distance,et_Time;
    Button btn_Calculate;
    TextView tv_Result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_Distance = (EditText) findViewById(R.id.et_Distabce);
        et_Time = (EditText) findViewById(R.id.et_Time);
        tv_Result = (TextView) findViewById(R.id.textView);
        btn_Calculate = (Button) findViewById(R.id.btn_Calculate);

        btn_Calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int distance = Integer.valueOf(et_Distance.getText().toString());
                int time = Integer.valueOf(et_Distance.getText().toString());

                tv_Result.setText("Fare is :"+calcFare(distance,time));
            }
        });
    }

    static int calcFare (int distance, int time){
        int price = 0;
        if(distance <=2 && time <=15){
            price = 25;
        }else if(distance > 2 && time <= 15){
            price = 25 +((distance-2)*8);
        }else if(distance > 2 && time > 15){
            price = (25 +((distance-2)*8))+((time - 15)*1);
        }else if(distance<=2 && time >15){
            price = 25 + ((time -15)*1);
        }

        return price;
    }
}
