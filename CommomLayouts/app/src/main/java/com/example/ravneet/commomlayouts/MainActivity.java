package com.example.ravneet.commomlayouts;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == R.id.menu_add){
            Toast.makeText(getApplicationContext(),"ADD Menu Selected",Toast.LENGTH_SHORT).show();
        }
        else if(item.getItemId() == R.id.menu_settings){
            Toast.makeText(getApplicationContext(),"Settings Button Selected",Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }
}
