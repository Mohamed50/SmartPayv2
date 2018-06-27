package com.example.fifty.smartpayv2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.SharedElementCallback;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fifty.smartpayv2.DBA.DBA;

public class RegisterActivity extends AppCompatActivity {
    final static String MY_PREFERENCE = "user_session";
    private static final  String FULLNAME = "full_name";
    private static final  String USER_ID = "user_id";
    private static final  String PHONE = "phone_no";
    private static final  String EMAIL = "email";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }
    public void register(View view){
        EditText username = (EditText) findViewById(R.id.et_username);
        EditText password = (EditText) findViewById(R.id.et_password);
        EditText confirmPassword = (EditText) findViewById(R.id.et_confirm_password);
        EditText fullname = (EditText) findViewById(R.id.et_fullName);
        EditText email = (EditText) findViewById(R.id.et_email);
        EditText phone = (EditText) findViewById(R.id.et_phone_no);
        DBA dba = new DBA();
        Account account = new Account();
        account.setUsername(username.getText().toString());
        account.setFullName(fullname.getText().toString());
        if(password.getText().toString().compareToIgnoreCase(confirmPassword.getText().toString())==0){
            account.setPassword(password.getText().toString());
        }
        else{
            Toast.makeText(getBaseContext(), "Password and Confirm password isn't the same", Toast.LENGTH_SHORT).show();
        }
        account.setPhone(phone.getText().toString());
        account.setEmail(email.getText().toString());
        int result = dba.SendAccountInformation(account);
        if (result != -1){
            SharedPreferences sharedPreferences = getSharedPreferences(MY_PREFERENCE,MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(FULLNAME,account.getFullName());
            editor.putString(EMAIL,account.getEmail());
            editor.putString(PHONE,account.getPhone());
            editor.putInt(USER_ID,result);
            editor.commit();
            Toast.makeText(getBaseContext(),"Registered Successfully",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(getBaseContext(),MainActivity.class);
            startActivity(intent);
        }
        else{
            Toast.makeText(getBaseContext(),"Something went Wrong try Again", Toast.LENGTH_SHORT).show();
        }
    }

}
