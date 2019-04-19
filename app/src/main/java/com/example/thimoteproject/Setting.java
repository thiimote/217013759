package com.example.thimoteproject;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Setting extends AppCompatActivity {

   // SQLiteOpenHelper openHelper;
    //SQLiteDatabase db;
    Button changepass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
       // getSupportActionBar().setTitle("Setting");

       // openHelper = new DatabaseHelper(this);
        changepass = (Button)findViewById(R.id.submitchangepassword);
        changepass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Error! Check Internet connection", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
