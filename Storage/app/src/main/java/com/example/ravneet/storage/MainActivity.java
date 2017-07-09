package com.example.ravneet.storage;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText data;
    Button add;
    TextView showdata;
    SharedPreferences preferences;

    public static final String PREF_KEY_TEXT = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        data = (EditText) findViewById(R.id.et_data);
        add = (Button) findViewById(R.id.btn_add);
        showdata = (TextView) findViewById(R.id.tv_show);

        preferences = getPreferences(MODE_PRIVATE);

        String savedvalue = preferences.getString(PREF_KEY_TEXT,null);

        if(savedvalue != null){
            showdata.setText(savedvalue);
        }

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showdata.setText(data.getText().toString());
            }
        });

        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(PREF_KEY_TEXT,data.getText().toString());
        editor.apply();

    }
}
