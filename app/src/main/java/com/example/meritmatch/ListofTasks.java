package com.example.meritmatch;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ListofTasks extends AppCompatActivity {
    String User_name;
    private RecyclerView recyclerView;
    private TaskAdapter adapter;
    private List<Task_database> tasks = new ArrayList<>();
    Button Home,Reserve;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_listof_tasks);

        tasks = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        new APICall().getTasks(new APICall.getTasksCallback() {
            @Override
            public void onResponse(List<Task_database> tasks) {
                adapter = new TaskAdapter(tasks);
                recyclerView.setAdapter(adapter);
            }
        });

        Home = findViewById(R.id.listtaskstoHome);
        Reserve = findViewById(R.id.reserve);
        Intent intent = getIntent();
        User_name = intent.getStringExtra("Username");
        Home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HomePage.passUsername(ListofTasks.this, HomePage.class,User_name);
            }
        });

        Reserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}