package com.example.todoproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;

import com.example.todoproject.Adapter.ToDoAdapter;
import com.example.todoproject.Model.ToDoModel;
import com.example.todoproject.Utils.DatabaseHandler;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity implements DialogCloseListener {

    private RecyclerView taskRecyclerView;
    private ToDoAdapter adapter;
    private FloatingActionButton fab;

    private List<ToDoModel> taskList;
    private DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        db = new DatabaseHandler(this);
        db.openDatabase();

        taskRecyclerView = findViewById(R.id.taskRecyclerView);
        taskRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ToDoAdapter(db, this);
        taskRecyclerView.setAdapter(adapter);

        fab = findViewById(R.id.addTaskFab);

        taskList = db.getAllTasks();
        Collections.reverse(taskList);
        adapter.setTasks(taskList);

        fab.setOnClickListener(view -> {
            AddNewTask.newInstance().show(getSupportFragmentManager(), AddNewTask.TAG);
        });
    }

    @Override
    public void handleDialogClose(DialogInterface dialog) {
        taskList = db.getAllTasks();
        Collections.reverse(taskList);
        adapter.setTasks(taskList);
        adapter.notifyDataSetChanged();
    }
}