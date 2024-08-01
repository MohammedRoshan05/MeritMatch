package com.example.meritmatch;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class PostedTask extends AppCompatActivity {

    Button PostedTasktoHome,ApproveTask;
    String resolver;
    int points;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_posted_task);

        Intent intent = getIntent();
        String postedBy = intent.getStringExtra("Username");

        new APICall().getTask(postedBy, PostedTask.this, new APICall.TaskApprovalCallback() {
            @Override
            public void onResponse(ClassTaskApproval approval) {
                if(approval != null){
                    resolver = approval.getResolver();
                    points = approval.getPoints();
                }
            }
        });

        PostedTasktoHome = findViewById(R.id.postedTasktoHome);
        ApproveTask = findViewById(R.id.approveTask);

        PostedTasktoHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HomePage.passUsername(PostedTask.this,HomePage.class,postedBy);
            }
        });
        ApproveTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ApproveTask.setText(resolver);
                new APICall().updateKarma(postedBy, resolver, PostedTask.this, points, new APICall.getTaskStatusCallback() {
                    @Override
                    public void onResponse(ClassStatus taskClassStatus) {
                        if (taskClassStatus != null){
                            Toast.makeText(PostedTask.this,"Points have been updated",
                                    Toast.LENGTH_SHORT).show();
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
}