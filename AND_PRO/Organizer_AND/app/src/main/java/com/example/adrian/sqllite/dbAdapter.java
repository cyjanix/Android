package com.example.adrian.sqllite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.adrian.class_help.Task;

/**
 * Created by Adrian on 2015-01-21.
 */
public class dbAdapter {

    private static final String DEBUG_TAG = "ListaZadanManager";

    private static final int DB_VERSION = 2;
    private static final String DB_NAME = "zadania.db";
    private static final String DB_TABLE = "taski";

    public static final String key_id = "id";
    public static final String id_opcje = "INTEGER PRIMARY KEY AUTOINCREMENT";
    public static final int id_kolumna = 0;

    public static final String key_nazwa = "nazwa";
    public static final String nazwa_opcje = "TEXT NOT NULL";
    public static final int nazwa_kolumna = 1;

    public static final String key_telefon = "telefon";
    public static final String telefon_opcje = "TEXT NOT NULL";
    public static final int telefon_kolumna = 2;

    public static final String key_data = "data";
    public static final String data_opcje = "TEXT NOT NULL";
    public static final int data_kolumna = 3;

    public static final String key_miejsce = "miejsce";
    public static final String miejsce_opcje = "TEXT NOT NULL";
    public static final int miejsce_kolumna = 4;

    public static final String DB_CREATE = "CREATE TABLE " + DB_TABLE + "( " +
            key_id + " " + id_opcje + ", " +
            key_nazwa + " " + nazwa_opcje + ", " +
            key_telefon + " " + telefon_opcje + ", " +
            key_data + " " + data_opcje + ", " +
            key_miejsce + " " + miejsce_opcje +
            ");";

    public static final String DB_DROP = "DROP TABLE IF EXIST " + DB_TABLE;

    private SQLiteDatabase db;
    private Context context;
    private DatabaseHelper dbHelper;

    public dbAdapter(Context context)
    {
        this.context = context;
    }

    public dbAdapter open()
    {
        dbHelper = new DatabaseHelper(context, DB_NAME, null, DB_VERSION);
        db = dbHelper.getWritableDatabase();
        return this;
    }

    public void close()
    {
        dbHelper.close();
    }

    public long insertZadanie(String nazwa, String telefon, String data, String miejsce)
    {
        ContentValues newZadanie = new ContentValues();
        newZadanie.put(key_nazwa, nazwa);
        newZadanie.put(key_telefon, telefon);
        newZadanie.put(key_data, data);
        newZadanie.put(key_miejsce, miejsce);
        return db.insert(DB_TABLE, null, newZadanie);
    }

    public boolean updateZadanie(Task zadanie)
    {
        long id = zadanie.getId();
        String nazwa = zadanie.getName();
        String telefon = zadanie.getNumber();
        String data = zadanie.getDate();
        String miejsce = zadanie.getPlace();
        return updateZadanie(id, nazwa, telefon, data, miejsce);
    }

    public boolean updateZadanie(long id, String nazwa, String telefon, String data, String miejsce)
    {
        String where = key_id + "=" + id;
        ContentValues updateZadanie = new ContentValues();
        updateZadanie.put(key_nazwa, nazwa);
        updateZadanie.put(key_telefon, telefon);
        updateZadanie.put(key_data, data);
        updateZadanie.put(key_miejsce, miejsce);
        return db.update(DB_TABLE, updateZadanie, where, null) > 0;
    }

    public boolean deleteZadanie(long id)
    {
        String where = key_id + "=" + id;
        return db.delete(DB_TABLE, where, null) > 0;
    }

    public Cursor getAllZadanie()
    {
        String[] kolumny = {key_id, key_nazwa, key_telefon, key_data, key_miejsce};
        return db.query(DB_TABLE, kolumny, null, null, null, null, null);
    }

    public Task getZadanie(long id)
    {
        String[] kolumny = {key_id, key_nazwa, key_telefon, key_data, key_miejsce};
        String where = key_id + "=" + id;
        Cursor cursor = db.query(DB_TABLE, kolumny, null, null, null, null, null);
        Task zadanie = null;
        if(cursor != null && cursor.moveToFirst())
        {
            String nazwa = cursor.getString(nazwa_kolumna);
            String telefon = cursor.getString(telefon_kolumna);
            String data = cursor.getString(data_kolumna);
            String miejsce = cursor.getString(miejsce_kolumna);
            zadanie = new Task(id, nazwa, telefon, data, miejsce);
        }
        return zadanie;
    }

    private static class DatabaseHelper extends SQLiteOpenHelper
    {

        public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version)
        {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(DB_CREATE);

            Log.d(DEBUG_TAG, "Database creating...");
            Log.d(DEBUG_TAG, "Table " + DB_TABLE + " ver." + DB_VERSION + " created");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL(DB_DROP);
            onCreate(db);
        }
    }

}
