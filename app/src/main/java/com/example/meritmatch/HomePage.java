package com.example.meritmatch;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.w3c.dom.Text;

public class HomePage extends AppCompatActivity {

    String User_name;
    Button posttask,goToTasks;
    TextView UserName_textbox,Points_textbox,Status_task;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home_page);

        goToTasks = findViewById(R.id.list_tasks);
        posttask = findViewById(R.id.post_taskhome);
        UserName_textbox = findViewById(R.id.usernameHome);
        Points_textbox = findViewById(R.id.points_noHome);
        Status_task = findViewById(R.id.status_postHome);

        Intent intent = getIntent();
        User_name = intent.getStringExtra("Username");
        UserName_textbox.setText(User_name);

        new APICall().getUser(User_name, HomePage.this, new APICall.UserCallback() {
            @Override
            public void onResponse(User user) {
                if (user != null) {
                    UserName_textbox.setText(user.getUser_name());
                    Points_textbox.setText("" + user.getKarma());
                }
            }
        });
        new APICall().getStatus(User_name, HomePage.this, new APICall.getTaskStatusCallback() {
            @Override
            public void onResponse(Status taskStatus) {
                if(taskStatus != null){
                    Status_task.setText(taskStatus.getStatus());
                }
            }
        });
        posttask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                passUsername(HomePage.this,PostTaskScreen.class,User_name);
            }
        });

        goToTasks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                passUsername(HomePage.this, ListofTasks.class,User_name);
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    public static void passUsername(Context Screen1, Class<?> screen2, String User_name){
        Intent intent = new Intent(Screen1,screen2);
        intent.putExtra("Username",User_name);
        Screen1.startActivity(intent);
    }
}