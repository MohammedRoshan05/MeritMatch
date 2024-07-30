package com.example.meritmatch;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    String User_name;
    int Karma;
    Button login,signup;
    EditText usernameInput,passwordInput;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        login = findViewById(R.id.login_button);
        usernameInput = findViewById(R.id.usernameTextMain);
        passwordInput = findViewById(R.id.passwordTextMain);
        signup = findViewById(R.id.signup);

        new APICall().getUser("Roshan",this, new APICall.UserCallback() {
            @Override
            public void onResponse(User user) {
                User_name = user.getUser_name();
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new APICall().loginUser(usernameInput.getText().toString(),
                        passwordInput.getText().toString(), new APICall.LoginCallback() {
                    @Override
                    public void onResponse(LoginResponse response) {
                        if (response.getMessage().equals("Login successful")) {
                            Toast.makeText(MainActivity.this,
                                    response.getMessage().toString(),Toast.LENGTH_SHORT).show();
                            passUsername(MainActivity.this, HomePage.class,
                                    usernameInput.getText().toString());
                        } else {
                            Toast.makeText(MainActivity.this,
                                    response.getMessage().toString(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoSignupPage();
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    public void gotoHomePage(){
        Intent intent = new Intent(this,HomePage.class);
        startActivity(intent);
    }
    public void gotoSignupPage(){
        Intent intent = new Intent(this,SignupPage.class);
        startActivity(intent);
    }
    public void passUsername(Context Screen1, Class<?> screen2,String User_name){
        Intent intent = new Intent(Screen1,screen2);
        intent.putExtra("Username",User_name);
        startActivity(intent);
    }
}