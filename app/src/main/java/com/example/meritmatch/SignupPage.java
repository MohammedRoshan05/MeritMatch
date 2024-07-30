package com.example.meritmatch;

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

public class SignupPage extends AppCompatActivity {

    Button signup;
    EditText usernameInput,passwordInput;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_signup_page);

        signup = findViewById(R.id.signup_button);
        usernameInput = findViewById(R.id.usernameText);
        passwordInput = findViewById(R.id.passwordText);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameInput.getText().toString();
                String password = passwordInput.getText().toString();
                new APICall().signupUser(username, password, new APICall.SignupCallback() {
                    @Override
                    public void onResponse(User user) {
                        if (user != null) {
                            signup.setText(user.getUser_name());
                            Toast.makeText(SignupPage.this,
                                    "Welcome to Merit Match " + user.getUser_name(), Toast.LENGTH_SHORT).show();
                            gotoHomePage();
                        } else {
                            Toast.makeText(SignupPage.this, "Signup failed. Please try again.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
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
}