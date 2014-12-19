package kontakty;

import java.util.ArrayList;

import activity.GlownaActivity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;

public class Kontakt {
	
	private
	String phoneNumber = null;
	String email = null;
	String name = null;
	ArrayList<Osoba> listaOsob = new ArrayList<Osoba>();
	

	public ArrayList<Osoba> zwrocKontakty() {

		Uri CONTENT_URI = ContactsContract.Contacts.CONTENT_URI;
		String _ID = ContactsContract.Contacts._ID;
		String DISPLAY_NAME = ContactsContract.Contacts.DISPLAY_NAME;

		Uri PhoneCONTENT_URI = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
		String Phone_CONTACT_ID = ContactsContract.CommonDataKinds.Phone.CONTACT_ID;
		String NUMBER = ContactsContract.CommonDataKinds.Phone.NUMBER;

		Uri EmailCONTENT_URI = ContactsContract.CommonDataKinds.Email.CONTENT_URI;
		String EmailCONTACT_ID = ContactsContract.CommonDataKinds.Email.CONTACT_ID;
		String DATA = ContactsContract.CommonDataKinds.Email.DATA;

		GlownaActivity GA = new GlownaActivity();
		
		ContentResolver contentResolver = GA.getCr();

		Cursor cursor = contentResolver.query(CONTENT_URI, null, null, null,
				null);

		// Loop for every contact in the phone
		if (cursor.getCount() > 0) {

			while (cursor.moveToNext()) {
				String contact_id = cursor
						.getString(cursor.getColumnIndex(_ID));
				Cursor phoneCursor = contentResolver.query(PhoneCONTENT_URI,
						null, Phone_CONTACT_ID + " = ?",
						new String[] { contact_id }, null);
				Cursor emailCursor = contentResolver.query(EmailCONTENT_URI,
						null, EmailCONTACT_ID + " = ?",
						new String[] { contact_id }, null);

				name = cursor.getString(cursor.getColumnIndex(DISPLAY_NAME));
				phoneNumber = phoneCursor.getString(phoneCursor
						.getColumnIndex(NUMBER));
				email = emailCursor.getString(emailCursor.getColumnIndex(DATA));
				listaOsob.add(new Osoba(name, phoneNumber, email));
			}
		}
		return listaOsob;
	}
	
	
}