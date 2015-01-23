package com.example.adrian.class_activity;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.adrian.sqllite.dbAdapter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Task extends ActionBarActivity {

    private EditText et_name;
    private EditText et_number;
    private EditText et_date;
    private EditText et_place;
    private dbAdapter dbAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zadanie);
        et_name = (EditText)findViewById(R.id.imie_zad);
        et_number = (EditText)findViewById(R.id.telefon_zad);
        et_date = (EditText)findViewById(R.id.data);
        et_place = (EditText)findViewById(R.id.miejsce);
        et_name.setEnabled(false);
        et_number.setEnabled(false);
        dbAdapter = new dbAdapter(getApplicationContext());
        dbAdapter.open();
        Intent intent = getIntent();
        String message_name = intent.getStringExtra(Book.NAME_MESSAGE);
        String message_number = intent.getStringExtra(Book.NUMBER_MESSAGE);
        et_name.setText(message_name);
        et_number.setText(message_number);
    }

    public void dodajNowe(View v)
    {
        if(!et_name.getText().toString().isEmpty() && !et_number.getText().toString().isEmpty())
        {
            String s_name = et_name.getText().toString();
            String s_number = this.et_number.getText().toString();
            Pattern p = Pattern.compile("(0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[012])/((19|20)\\d\\d)");
            Matcher m = p.matcher(this.et_date.getText().toString());
            if(m.matches())
            {
                String s_date = this.et_date.getText().toString();
                if(!this.et_place.getText().toString().isEmpty())
                {
                    String s_place = this.et_place.getText().toString();
                    dbAdapter.insertZadanie(s_name, s_number, s_date, s_place);
                    et_name.setText(null);
                    et_number.setText(null);
                    et_date.setText(null);
                    et_place.setText(null);
                    Toast.makeText(this, "Dodano zadanie.", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(this, Tasks.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(this, "Wprowadź miejsce!", Toast.LENGTH_SHORT).show();
                }
            }
            else
            {
               Toast.makeText(this, "Niepoprawna data! \n DD/MM/YYYY", Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            Toast.makeText(this, "Wybierz dane z książki.", Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_zadanie, menu);
        return true;
    }

    public void toBook(View v)
    {
        Intent intent = new Intent(this, Book.class);
        startActivity(intent);
    }

    public void toTasks(View v)
    {
        Intent intent = new Intent(this, Tasks.class);
        startActivity(intent);
    }

}
