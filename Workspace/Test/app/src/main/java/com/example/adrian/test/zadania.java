package com.example.adrian.test;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class zadania extends ActionBarActivity {

    private ListView lv_zadania;

    private DbAdapter todoDbAdapter;
    private Cursor todoCursor;
    private List<Task> tasks;
    private TasksAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zadania);
        initUIElements();
        initListView();
        saveNewTask();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_zadania, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onClickMenu(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void initUIElements() {
        lv_zadania = (ListView) findViewById(R.id.lv_zadania);
    }

    private void initListView() {
        fillListViewData();
    }

    private void fillListViewData() {
        todoDbAdapter = new DbAdapter(getApplicationContext());
        try {
            todoDbAdapter.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        getAllTasks();
        listAdapter = new TasksAdapter(this, tasks);
        lv_zadania.setAdapter(listAdapter);
    }

    private void getAllTasks() {
        tasks = new ArrayList<Task>();
        todoCursor = getAllEntriesFromDb();
        updateTaskList();
    }

    private Cursor getAllEntriesFromDb() {
        todoCursor = todoDbAdapter.getAllTodos();
        if(todoCursor != null) {
            startManagingCursor(todoCursor);
            todoCursor.moveToFirst();
        }
        return todoCursor;
    }

    private void updateTaskList() {
        if(todoCursor != null && todoCursor.moveToFirst()) {
            do {
                long id = todoCursor.getLong(DbAdapter.ID_COLUMN);
                String nazwa = todoCursor.getString(DbAdapter.NAZWA_COLUMN);
                String adres = todoCursor.getString(DbAdapter.ADRES_COLUMN);
                String data = todoCursor.getString(DbAdapter.DATA_COLUMN);
                tasks.add(new Task(id, nazwa, adres, data));
            } while(todoCursor.moveToNext());
        }
    }

    private void saveNewTask(){
        String nazwa = "Adrian";
        String adres = "asd";
        String data = "asd";
        todoDbAdapter.insertTodo(nazwa, adres, data);
        updateListViewData();
    }

    private void updateListViewData() {
        todoCursor.requery();
        tasks.clear();
        updateTaskList();
        listAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onDestroy() {
        if(todoDbAdapter != null)
            todoDbAdapter.close();
        super.onDestroy();
    }
}
