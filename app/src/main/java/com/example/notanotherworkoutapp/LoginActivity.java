package com.example.notanotherworkoutapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.notanotherworkoutapp.database.DataBaseHelper;
import com.example.notanotherworkoutapp.entity.UserModel;

public class LoginActivity extends AppCompatActivity {

    EditText username, password;
    Button register, loginButton;
    DataBaseHelper dataBaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = (EditText) findViewById(R.id.edittext_username);
        password = (EditText) findViewById(R.id.edittext_password);
        //register = (Button) findViewById(R.id.textview_register);
        loginButton = (Button) findViewById(R.id.button_login);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser(username.getText().toString(), password.getText().toString());

            }
        });




    }
    private void loginUser(String username, String password){
        validateUsername();
        validatePassword();



        if (validateUsername() && validatePassword()){

            UserModel userExists = DataBaseHelper.(username,password);
            if (userExists != null){
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            }else{
                System.out.println("wrong username/password");
            }
        }
    }
    private Boolean validateUsername(){
        String value = username.getText().toString().trim();


        if (value.isEmpty()){
            return false;
        }else{
            return true;
        }
    }
    private Boolean validatePassword(){
        String value = password.getText().toString().trim();


        if (value.isEmpty()){
            return false;
        }else{
            return true;
        }
    }

}
