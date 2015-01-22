package com.example.adrian.organizer_and;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.example.adrian.sqllite.AdapterDB;

import java.util.ArrayList;
import java.util.List;


public class ksiazka extends ActionBarActivity {

    private ListView lv_kontakty;
    private List<Kontakt> listaKontaktow;
    private ksiazkaAdapter adapterListy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ksiazka);
        lv_kontakty = (ListView)findViewById(R.id.lv_kontakty);
        listaKontaktow = new ArrayList<Kontakt>();
        fillListViewData();
    }

    private void fillListViewData()
    {
        fetchContacts();
        adapterListy = new ksiazkaAdapter(this, listaKontaktow);
        lv_kontakty.setAdapter(adapterListy);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ksiazka, menu);
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

    public void noweZadanie(View v)
    {
        Intent intent = new Intent(this, zadanie.class);
        startActivity(intent);
    }

    public void fetchContacts() {

        Uri CONTENT_URI = ContactsContract.Contacts.CONTENT_URI;
        String _ID = ContactsContract.Contacts._ID;
        String DISPLAY_NAME = ContactsContract.Contacts.DISPLAY_NAME;
        String HAS_PHONE_NUMBER = ContactsContract.Contacts.HAS_PHONE_NUMBER;

        Uri PhoneCONTENT_URI = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        String Phone_CONTACT_ID = ContactsContract.CommonDataKinds.Phone.CONTACT_ID;
        String NUMBER = ContactsContract.CommonDataKinds.Phone.NUMBER;

        Uri EmailCONTENT_URI =  ContactsContract.CommonDataKinds.Email.CONTENT_URI;
        String EmailCONTACT_ID = ContactsContract.CommonDataKinds.Email.CONTACT_ID;
        String DATA = ContactsContract.CommonDataKinds.Email.DATA;

        ContentResolver contentResolver = getContentResolver();

        Cursor cursor = contentResolver.query(CONTENT_URI, null,null, null, null);

        if (cursor.getCount() > 0) {

            while (cursor.moveToNext()) {

                String contact_id = cursor.getString(cursor.getColumnIndex( _ID ));

                int hasPhoneNumber = Integer.parseInt(cursor.getString(cursor.getColumnIndex( HAS_PHONE_NUMBER )));

                if (hasPhoneNumber > 0) {
                    Cursor phoneCursor = contentResolver.query(PhoneCONTENT_URI, null, Phone_CONTACT_ID + " = ?", new String[] { contact_id }, null);
                    String nazwa = cursor.getString(cursor.getColumnIndex( DISPLAY_NAME ));
                    String telefon = phoneCursor.getString(phoneCursor.getColumnIndex(NUMBER));
                    listaKontaktow.add(new Kontakt(nazwa, telefon));
                    phoneCursor.close();
                }
            }
        }
    }

}
