package com.example.adrian.organizer_and;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.example.adrian.sqllite.AdapterDB;
import com.example.adrian.sqllite.Zadanie;

import java.util.ArrayList;
import java.util.List;


public class zadania extends ActionBarActivity {

    private ListView lvZadania;

    private AdapterDB adapterDB;
    private Cursor zadaniaCursor;
    private List<Zadanie> listaZadan;
    private ZadaniaAdpater adapterListy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zadania);
        initUIElements();
        initListView();
    }

    private void initUIElements()
    {
        lvZadania = (ListView)findViewById(R.id.lv_Zadania);
    }

    private void initListView()
    {
        fillListViewData();
    }

    private void fillListViewData()
    {
        adapterDB = new AdapterDB(getApplicationContext());
        adapterDB.open();
        getAllZadanie();
        adapterListy = new ZadaniaAdpater(this, listaZadan);
        lvZadania.setAdapter(adapterListy);
    }

    private void getAllZadanie()
    {
        listaZadan = new ArrayList<Zadanie>();
        zadaniaCursor = getAllEntriesFromDB();
        updateTaskList();
    }

    @SuppressWarnings("deprecation")
    private Cursor getAllEntriesFromDB()
    {
        zadaniaCursor = adapterDB.getAllZadanie();
        if(zadaniaCursor != null)
        {
            startManagingCursor(zadaniaCursor);
            zadaniaCursor.moveToFirst();
        }
        return zadaniaCursor;
    }



    private void updateTaskList()
    {
        if(zadaniaCursor != null && zadaniaCursor.moveToFirst())
        {
            do
            {
                long id = zadaniaCursor.getLong(AdapterDB.id_kolumna);
                String nazwa = zadaniaCursor.getString(AdapterDB.nazwa_kolumna);
                String telefon = zadaniaCursor.getString(AdapterDB.telefon_kolumna);
                String data = zadaniaCursor.getString(AdapterDB.data_kolumna);
                String miejsce = zadaniaCursor.getString(AdapterDB.miejsce_kolumna);
                listaZadan.add(new Zadanie(id, nazwa, telefon, data, miejsce));
            }while(zadaniaCursor.moveToNext());
        }
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



    public void doMenu(View v)
    {
        Intent intent = new Intent(this, Main.class);
        startActivity(intent);
    }

    public void doNowegoZadania(View v)
    {
        Intent intent = new Intent(this, zadanie.class);
        startActivity(intent);
    }
}
