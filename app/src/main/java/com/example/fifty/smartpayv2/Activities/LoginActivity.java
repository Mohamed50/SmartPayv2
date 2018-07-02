package com.example.fifty.smartpayv2.Activities;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fifty.smartpayv2.DBA.DBA;
import com.example.fifty.smartpayv2.DBA.LocalDBA;
import com.example.fifty.smartpayv2.R;

public class LoginActivity extends Activity {
    static String MY_PREFERENCE = "user_session";
    static String USERNAME = "username";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        /*LocalDBA localDBA = new LocalDBA(this);
        Cursor result =localDBA.autoLogin();
        if(result!=null){
            DBA dba = new DBA();
            dba.updateData();
            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);
        }*/
        SharedPreferences sharedPreferences = getSharedPreferences(MY_PREFERENCE,MODE_PRIVATE);
        if(sharedPreferences != null && sharedPreferences.getString(USERNAME,null) != null){
            Intent intent = new Intent(getBaseContext(),MainActivity.class);
            startActivity(intent);
        }

    }
    public void login(View view){
        EditText username = (EditText) findViewById(R.id.login_username);
        EditText password = (EditText) findViewById(R.id.login_password);
        LocalDBA localDBA = new LocalDBA(this);
        DBA dba = new DBA();
        int result = dba.login(username.getText().toString(),password.getText().toString());
        if (result!=-1){
           // localDBA.insertAccount(username.getText().toString(),password.getText().toString());
            SharedPreferences sharedPreferences = getSharedPreferences(MY_PREFERENCE,MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(USERNAME,username.getText().toString());
            editor.putInt("user_id",result);
            editor.commit();
            Toast.makeText(this,"Login Successfully",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);
        }
        else{
            Toast.makeText(this,"sorry username or password is wrong",Toast.LENGTH_LONG).show();
        }
    }
    public void switchToRegister(View view){

        Intent intent = new Intent(this,RegisterActivity.class);
        startActivity(intent);
    }
}
