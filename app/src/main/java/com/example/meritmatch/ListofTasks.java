package com.example.meritmatch;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ListofTasks extends AppCompatActivity implements TaskAdapter.OnItemClickListener {
    String postedBy,reserver;
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

        refreshTasks();

        Home = findViewById(R.id.listtaskstoHome);
        Reserve = findViewById(R.id.reserve);
        Intent intent = getIntent();
        reserver = intent.getStringExtra("Username");
        Home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HomePage.passUsername(ListofTasks.this, HomePage.class,reserver);
            }
        });

        Reserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new APICall().reserve(postedBy,reserver, ListofTasks.this, new APICall.getTaskStatusCallback() {
                    @Override
                    public void onResponse(Status taskStatus) {
                        refreshTasks();
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
    public void onItemClick(Task_database task) {
        postedBy = task.getPostedBy(); // Example: Assign the reserved part to variable

    }
    public void refreshTasks(){
        new APICall().getTasks(new APICall.getTasksCallback() {
            @Override
            public void onResponse(List<Task_database> tasks) {
                adapter = new TaskAdapter(tasks, ListofTasks.this);
                recyclerView.setAdapter(adapter);
            }
        });
    }
}