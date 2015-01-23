package com.example.adrian.class_activity;

import android.content.Intent;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.adrian.class_help.Contact;
import com.example.adrian.class_help.bookAdapter;

import java.util.ArrayList;
import java.util.List;


public class Book extends ActionBarActivity{

    public final static String NAME_MESSAGE = "com.example.adrian.MESSAGE_IM";
    public final static String NUMBER_MESSAGE = "com.example.adrian.MESSAGE_TEL";
    private ListView lv_contacts;
    private List<Contact> contactsList;
    private bookAdapter listAdapter;
    private Task task = new Task();
    private TextView tv_Name;
    private TextView tv_Number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ksiazka);
        lv_contacts = (ListView)findViewById(R.id.lv_kontakty);
        lv_contacts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String name = listAdapter.getItem(position).getName();
                String number = listAdapter.getItem(position).getNumber();
                Intent intent = new Intent(getApplicationContext(), Task.class);
                intent.putExtra(NAME_MESSAGE, name);
                intent.putExtra(NUMBER_MESSAGE, number);
                startActivity(intent);
            }
        });
        contactsList = new ArrayList<Contact>();
        fillListViewData();
    }

    private void fillListViewData()
    {
        readContacs();
        listAdapter = new bookAdapter(this, contactsList);
        lv_contacts.setAdapter(listAdapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ksiazka, menu);
        return true;
    }

    public void toTasks(View v)
    {
        Intent intent = new Intent(this, Tasks.class);
        startActivity(intent);
    }

    public void readContacs()
    {
            String[] PROJECTION=new String[] {ContactsContract.Contacts._ID,
                    ContactsContract.Contacts.DISPLAY_NAME,
                    ContactsContract.CommonDataKinds.Phone.NUMBER
            };

            Cursor c=managedQuery(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                    PROJECTION, null, null, null);
            if (c.moveToFirst()) {
                String name = null;
                String number = null;

                do
                {
                    name = c.getString(c.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                    number = c.getString(c.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

                    number.replaceAll("\\D", "");
                    name=name.replaceAll("&", "");
                    name.replace("|", "");
                    contactsList.add(new Contact(name, number));

                }while(c.moveToNext());

            }
    }




}
