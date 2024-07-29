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

public class ListofTasks extends AppCompatActivity {
    private GridView gridView;
    private DatabaseHelper databaseHelper;
    private GridAdapter gridAdapter;
    private ArrayList<String> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_listof_tasks);

        gridView = findViewById(R.id.gridview);
        databaseHelper = new DatabaseHelper(this);
        data = new ArrayList<>();

        // Fetch data from SQLite
        Cursor cursor = databaseHelper.getAllData();
        if (cursor.getCount() != 0) {
            while (cursor.moveToNext()) {
                data.add(cursor.getString(1)); // Adding titles
                data.add(cursor.getString(2)); // Adding data1
                data.add(cursor.getString(3)); // Adding data2
            }
        }

        gridAdapter = new GridAdapter(this, data);
        gridView.setAdapter(gridAdapter);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}