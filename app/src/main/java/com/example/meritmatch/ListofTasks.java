package com.example.meritmatch;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.GridView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class ListofTasks extends AppCompatActivity {
    private GridView gridView;
    private GridAdapter gridAdapter;
    private List<Task_database> tasks = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_listof_tasks);

        tasks = new ArrayList<>();
        GridView gridView = findViewById(R.id.gridview);
        GridAdapter adapter = new GridAdapter(ListofTasks.this, tasks);
        gridView.setAdapter(adapter);

        new APICall().getTasks(new APICall.getTasksCallback() {
            @Override
            public void onResponse(List<Task_database> tasks) {
                adapter.updateTasks(tasks);
            }
        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}