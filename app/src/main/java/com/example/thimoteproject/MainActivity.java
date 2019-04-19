package com.example.thimoteproject;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText _username ,_password;
    Button  _register ,_login;

  SQLiteDatabase db;
  SQLiteOpenHelper openHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       openHelper = new DatabaseHelper(this);

        _username = (EditText)findViewById(R.id.username);
        _password = (EditText)findViewById(R.id.password);
        _login = (Button)findViewById(R.id.t_logins);
        _register = (Button)findViewById(R.id.register);

        _register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
         Intent intent = new Intent(MainActivity.this,Register.class);
          startActivity(intent);
            }
        });

        _login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = _username.getText().toString().trim();
                String password = _password.getText().toString().trim();
// empty
                if (username.isEmpty() | password.isEmpty()){
                    Toast.makeText(getApplicationContext(), "field can't be empty !" ,Toast.LENGTH_SHORT).show();
                }
               db = openHelper.getReadableDatabase();
              //  cursor = db.rawQuery("SELECT * FROM " + DatabaseHelper.TABLE_NAME + " WHERE " + DatabaseHelper.COL_4 + " =? AND " + DatabaseHelper.COL_5 + " =? " , new String[]{username,password});
             Cursor  cursor = db.rawQuery(" SELECT  * FROM " + DatabaseHelper.TABLE_NAME + " WHERE " + DatabaseHelper.COL_4 + " =? AND " + DatabaseHelper.COL_5 + " =? " , new String[]{username,password});
                if (cursor != null){
                      if (cursor.getCount() > 0){

                              Toast.makeText(getApplicationContext(), "login successful", Toast.LENGTH_LONG).show();
                              Intent intent = new Intent(MainActivity.this, Navigation.class);
                              startActivity(intent);

                      }
                  }
                  if (cursor.getCount()==0) {

                          Toast.makeText(getApplicationContext(), "Invalid Username or Password", Toast.LENGTH_LONG).show();

                      }
            }
        });


    }
}
