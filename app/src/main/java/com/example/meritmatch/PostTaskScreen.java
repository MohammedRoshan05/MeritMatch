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

public class PostTaskScreen extends AppCompatActivity {
    EditText Usernamebox,Titlebox,Descriptionbox,Karmabox;
    String User_name,Title,Description,Karma;
    Button PostTask,PostTasktoHome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_post_task_screen);

        Usernamebox = findViewById(R.id.usernameTextpostscreen);
        Titlebox = findViewById(R.id.titleBoxpsotscreen);
        Descriptionbox = findViewById(R.id.Descriptionboxpostscreen);
        Karmabox = findViewById(R.id.Karmaboxpostscreen);

        Intent intent = getIntent();
        User_name = intent.getStringExtra("Username");
        Usernamebox.setText(User_name);

        PostTask = findViewById(R.id.postTask);
        PostTasktoHome = findViewById(R.id.postTasktoHome);
        PostTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new APICall().postTask(Usernamebox.getText().toString(), Titlebox.getText().toString(),
                        Descriptionbox.getText().toString(), Integer.parseInt(Karmabox.getText().toString()),
                        new APICall.postTaskCallback() {
                            @Override
                            public void onResponse(ClassTask classTask) {
                                if (classTask != null) {
                                    Toast.makeText(PostTaskScreen.this,
                                            "Task successfully posted", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(PostTaskScreen.this,
                                            "Task couldnt be posted failed. Please try again.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
        PostTasktoHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HomePage.passUsername(PostTaskScreen.this, HomePage.class,User_name);
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}