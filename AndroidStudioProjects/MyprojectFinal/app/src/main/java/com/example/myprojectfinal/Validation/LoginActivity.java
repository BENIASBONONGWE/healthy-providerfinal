package com.example.myprojectfinal.Validation;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.example.myprojectfinal.Databases.Database;
import com.example.myprojectfinal.Homedetails.HomeActivity;
import com.example.myprojectfinal.R;

public class LoginActivity extends AppCompatActivity {
   EditText password, username,registerButton;
            Button Btn;
            TextView tv,regButton;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        password =findViewById(R.id.passwordEditText);
        username =findViewById(R.id.usernameEditText);
        Btn=findViewById(R.id.loginButton);
         regButton=findViewById(R.id.registerTextView);

        Btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String username = LoginActivity.this.username.getText().toString();
                String password = LoginActivity.this.password.getText().toString();
                Database db= new Database(getApplicationContext(),"healthcare",null,1);
                if(username.length()==0 || password.length()==0 ){
                    Toast.makeText(getApplicationContext(), "fill all details", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if(db.login(username,password)==true){

                        Toast.makeText(getApplicationContext(), "login success", Toast.LENGTH_SHORT).show();
                        SharedPreferences sharedPreferences =getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor =sharedPreferences.edit();
                        editor.putString("username",username);
                        startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                    } else{
                        Toast.makeText(getApplicationContext(), "invalid Username and password", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });
        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });


    }
}