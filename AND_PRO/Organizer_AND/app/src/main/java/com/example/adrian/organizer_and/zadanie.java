package com.example.adrian.organizer_and;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.example.adrian.sqllite.AdapterDB;


public class zadanie extends ActionBarActivity {

    private EditText imie;
    private EditText telefon;
    private EditText data;
    private EditText miejsce;
    private AdapterDB adapterDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zadanie);
        imie = (EditText)findViewById(R.id.imie);
        telefon = (EditText)findViewById(R.id.telefon);
        data = (EditText)findViewById(R.id.data);
        miejsce = (EditText)findViewById(R.id.miejsce);
        adapterDB = new AdapterDB(getApplicationContext());
        adapterDB.open();
    }

    public void dodajNowe(View v)
    {
        String nazwa = imie.getText().toString();
        String telefon = this.telefon.getText().toString();
        String data = this.data.getText().toString();
        String miejsce = this.miejsce.getText().toString();
        adapterDB.insertZadanie(nazwa, telefon, data, miejsce);
    }

    private void saveNewZadanie(String nazwa, String telefon, String data, String miejsce)
    {
        if(nazwa.equals(""))
        {

        }else
        {
            adapterDB.insertZadanie(nazwa, telefon, data, miejsce);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_zadanie, menu);
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

    public void doZadan(View v)
    {
        Intent intent = new Intent(this, zadania.class);
        startActivity(intent);
    }

    public void doMenu(View v)
    {
        Intent intent = new Intent(this, Main.class);
        startActivity(intent);
    }

    public void doKsiazki(View v)
    {
        Intent intent = new Intent(this, ksiazka.class);
        startActivity(intent);
    }
}
