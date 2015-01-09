package com.example.adrian.test;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import java.sql.SQLException;

/**
 * Created by Adrian on 2015-01-09.
 */
public class DbAdapter {

    private static final String DEBUG_TAG = "SqLiteTodoManager";

    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "zadania.db";
    private static final String DB_TODO_TABLE = "zad";

    public static final String KEY_ID = "idZadania";
    public static final String ID_OPTIONS = "INTEGER PRIMARY KEY AUTOINCREMENT";
    public static final int ID_COLUMN = 0;
    public static final String KEY_NAZWA = "Nazwa";
    public static final String NAZWA_OPTIONS = "TEXT NOT NULL";
    public static final int NAZWA_COLUMN = 1;
    public static final String KEY_ADRES = "Adres";
    public static final String ADRES_OPTIONS = "TEXT NOT NULL";
    public static final int ADRES_COLUMN = 2;
    public static final String KEY_DATA = "Data";
    public static final String DATA_OPTIONS = "TEXT NOT NULL";
    public static final int DATA_COLUMN = 3;

    private static final String DB_CREATE_TODO_TABLE =
            "CREATE TABLE " + DB_TODO_TABLE + "( " +
                    KEY_ID + " " + ID_OPTIONS + ", " +
                    KEY_NAZWA + " " + NAZWA_OPTIONS + ", " +
                    KEY_ADRES + " " + ADRES_OPTIONS + ", " +
                    KEY_DATA + " " + DATA_OPTIONS +
                    ");";
    private static final String DROP_TODO_TABLE =
            "DROP TABLE IF EXISTS " + DB_TODO_TABLE;

    private SQLiteDatabase db;
    private Context context;
    private DatabaseHelper dbHelper;

    public DbAdapter(Context context) {
        this.context = context;
    }

    public DbAdapter open() throws SQLException{
        dbHelper = new DatabaseHelper(context, DB_NAME, null, DB_VERSION);
            db = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    public long insertTodo(String nazwa, String adres, String data) {
        ContentValues newTodoValues = new ContentValues();
        newTodoValues.put(KEY_NAZWA, nazwa);
        newTodoValues.put(KEY_ADRES, adres);
        newTodoValues.put(KEY_DATA, data);
        return db.insert(DB_TODO_TABLE, null, newTodoValues);
    }

    public boolean updateTodo(Task task) {
        long id = task.getId();
        String nazwa = task.getNazwa();
        String adres = task.getAdres();
        String data = task.getData();
        return updateTodo(id, nazwa,adres, data);
    }

    public boolean updateTodo(long id, String nazwa, String adres, String data) {
        String where = KEY_ID + "=" + id;
        ContentValues updateTodoValues = new ContentValues();
        updateTodoValues.put(KEY_NAZWA, nazwa);
        updateTodoValues.put(KEY_NAZWA, adres);
        updateTodoValues.put(KEY_NAZWA, data);
        return db.update(DB_TODO_TABLE, updateTodoValues, where, null) > 0;
    }

    public boolean deleteTodo(long id){
        String where = KEY_ID + "=" + id;
        return db.delete(DB_TODO_TABLE, where, null) > 0;
    }

    public Cursor getAllTodos() {
        String[] columns = {KEY_ID, KEY_NAZWA, KEY_ADRES, KEY_DATA};
        return db.query(DB_TODO_TABLE, columns, null, null, null, null, null);
    }

    public Task getTodo(long id) {
        String[] columns = {KEY_ID, KEY_NAZWA, KEY_ADRES, KEY_DATA};
        String where = KEY_ID + "=" + id;
        Cursor cursor = db.query(DB_TODO_TABLE, columns, where, null, null, null, null);
        Task task = null;
        if(cursor != null && cursor.moveToFirst()) {
            String nazwa = cursor.getString(NAZWA_COLUMN);
            String adres = cursor.getString(ADRES_COLUMN);
            String data = cursor.getString(DATA_COLUMN);
            task = new Task(id, nazwa, adres, data);
        }
        return task;
    }

    private static class DatabaseHelper extends SQLiteOpenHelper {
        public DatabaseHelper(Context context, String name,
                              SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(DB_CREATE_TODO_TABLE);

            Log.d(DEBUG_TAG, "Database creating...");
            Log.d(DEBUG_TAG, "Table " + DB_TODO_TABLE + " ver." + DB_VERSION + " created");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL(DROP_TODO_TABLE);

            Log.d(DEBUG_TAG, "Database updating...");
            Log.d(DEBUG_TAG, "Table " + DB_TODO_TABLE + " updated from ver." + oldVersion + " to ver." + newVersion);
            Log.d(DEBUG_TAG, "All data is lost.");

            onCreate(db);
        }
    }



}
