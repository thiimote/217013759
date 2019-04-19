package com.example.thimoteproject;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {

    private TextInputLayout txtfirstname;
    private TextInputLayout txtlastname;
    private TextInputLayout txtusername;
    private TextInputLayout txtpassword;


    SQLiteOpenHelper openHelper;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
            openHelper = new DatabaseHelper(this);

            txtfirstname = findViewById(R.id.firstname);
            txtlastname = findViewById(R.id.lastname);
            txtusername = findViewById(R.id.username);
            txtpassword = findViewById(R.id.password);
       /*
        reg = (Button)findViewById(R.id.submit);
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db = openHelper.getWritableDatabase();
                String firstname = firstname.getText().toString();
                String lastname = lastname.getText().toString();
                String username = username.getText().toString();
                String password = password.getText().toString();

                if (firstname.equals("") || lastname.equals("") || username.equals("") || password.equals("")){
                    Toast.makeText(getApplicationContext(),"All fields are required" , Toast.LENGTH_SHORT).show();
                }
                db = openHelper.getReadableDatabase();
              Cursor  cursor = db.rawQuery(" SELECT  * FROM " + DatabaseHelper.TABLE_NAME + " WHERE " + DatabaseHelper.COL_4 + " =? AND " + DatabaseHelper.COL_5 + " =? " , new String[]{username,password});
                if (cursor != null){
                    if (cursor.getCount() > 0){
                        Toast.makeText(getApplicationContext(), firstname + "All ready exists" , Toast.LENGTH_SHORT).show();
                    }
                }
                if (cursor.getCount() == 0){
                    insertdata(firstname,lastname,username,password);
                    Toast.makeText(getApplicationContext(),"registration successful" , Toast.LENGTH_SHORT).show();
                   Intent intent = new Intent(Register.this, MainActivity.class);
                   startActivity(intent);
                }
            }
        });   */
    }
 private boolean validatedata(){
       String fname = txtfirstname.getEditText().getText().toString().trim();

       if (fname.isEmpty()){
           txtfirstname.setError("field can't be empty");
           return false;
       }else if (fname.length() > 15){
           txtfirstname.setError("firstname too long");

           return false;
       }else {
           txtfirstname.setError(null);

           return true;
       }
 }
 private boolean validatelast(){
     String lname = txtlastname.getEditText().getText().toString().trim();
     if (lname.isEmpty()){
         txtlastname.setError("field can't be empty");
         return false;
     }else if (lname.length()> 15){
         txtlastname.setError("lastname too long");
         return false;
     }else {
         txtlastname.setError(null);
         return true;
     }
 }
 private boolean validateusername(){
        String user = txtusername.getEditText().getText().toString().trim();
        if (user.isEmpty()){
            txtusername.setError("field can't be empty");
            return false;
        } else {
            txtusername.setError(null);
            return true;
        }
 }
 private boolean validatepass(){
        String pass = txtpassword.getEditText().getText().toString().trim();
        if (pass.isEmpty()){
            txtpassword.setError("field can't be empty");
            return false;
        }else {
            txtpassword.setError(null);
            return true;
        }
 }

 public void submit(View view){
        if ( !validatedata() | !validatepass() | !validateusername() | !validatelast()){
            return;
        }
        db = openHelper.getWritableDatabase();
        String firs_name = txtfirstname.getEditText().getText().toString().trim();
     String last_name = txtlastname.getEditText().getText().toString().trim();
     String user_name = txtusername.getEditText().getText().toString().trim();
     String pass_word = txtpassword.getEditText().getText().toString().trim();
     Cursor c = db.rawQuery(" SELECT * FROM " + DatabaseHelper.TABLE_NAME + " WHERE " + DatabaseHelper.COL_4 + " =? AND " + DatabaseHelper.COL_5 + " =? ", new String[]{user_name,pass_word});
        if (c != null){
            if (c.getCount() > 0){
                Toast.makeText(getApplicationContext(), user_name + "  " +"allready exist", Toast.LENGTH_SHORT).show();
            }
            else if (c.getCount() == 0){
                ContentValues contentValues = new ContentValues();
                contentValues.put(DatabaseHelper.COL_2,firs_name);
                contentValues.put(DatabaseHelper.COL_3,last_name);
                contentValues.put(DatabaseHelper.COL_4,user_name);
                contentValues.put(DatabaseHelper.COL_5,pass_word);
                db.insert(DatabaseHelper.TABLE_NAME , null,contentValues);
                Toast.makeText(getApplicationContext(), "registration successful", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Register.this,MainActivity.class);
                startActivity(intent);
            }
        }
    }
/*
    public void insertdata(String ft_name ,String lt_name , String user_name , String pass_word ){

        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.COL_2,ft_name);
        contentValues.put(DatabaseHelper.COL_3,lt_name);
        contentValues.put(DatabaseHelper.COL_4,user_name);
        contentValues.put(DatabaseHelper.COL_5,pass_word);
        long result = db.insert(DatabaseHelper.TABLE_NAME , null ,contentValues);
    }
*/
}
