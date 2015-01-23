package com.example.adrian.class_activity;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.adrian.class_help.tasksAdpater;
import com.example.adrian.sqllite.dbAdapter;

import java.util.ArrayList;
import java.util.List;


public class Tasks extends ActionBarActivity {

    private ListView lvTasks;

    private dbAdapter dbAdapter;
    private Cursor tasksCursor;
    private List<com.example.adrian.class_help.Task> tasksList;
    private tasksAdpater listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zadania);
        initUIElements();
        initListView();
        lvTasks.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                long l_id = listAdapter.getItem(position).getId();
                dbAdapter.deleteZadanie(l_id);
                initListView();
                Toast.makeText(getApplicationContext(), "Usunięto zadanie!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initUIElements()
    {
        lvTasks = (ListView)findViewById(R.id.lv_Zadania);
    }

    private void initListView()
    {
        fillListViewData();
    }

    private void fillListViewData() {
        dbAdapter = new dbAdapter(getApplicationContext());
        dbAdapter.open();
        getAllTasks();
        listAdapter = new tasksAdpater(this, tasksList);
        lvTasks.setAdapter(listAdapter);
    }

    private void getAllTasks()
    {
        tasksList = new ArrayList<com.example.adrian.class_help.Task>();

        tasksCursor = getAllEntriesFromDB();
        updateTaskList();
    }

    @SuppressWarnings("deprecation")
    private Cursor getAllEntriesFromDB()
    {
        tasksCursor = dbAdapter.getAllZadanie();
        if(tasksCursor != null)
        {
            startManagingCursor(tasksCursor);
            tasksCursor.moveToFirst();
        }
        return tasksCursor;
    }



    private void updateTaskList()
    {
        if(tasksCursor != null && tasksCursor.moveToFirst())
        {
            do
            {
                long id = tasksCursor.getLong(dbAdapter.id_kolumna);
                String s_name = tasksCursor.getString(dbAdapter.nazwa_kolumna);
                String s_number = tasksCursor.getString(dbAdapter.telefon_kolumna);
                String s_date = tasksCursor.getString(dbAdapter.data_kolumna);
                String s_place = tasksCursor.getString(dbAdapter.miejsce_kolumna);
                tasksList.add(new com.example.adrian.class_help.Task(id, s_name, s_number, s_date, s_place));
            }while(tasksCursor.moveToNext());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_zadania, menu);
        return true;
    }

    public void toMenu(View v)
    {
        Intent intent = new Intent(this, Main.class);
        startActivity(intent);
    }

    public void toTask(View v)
    {
        Intent intent = new Intent(this, Task.class);
        startActivity(intent);
    }
}
