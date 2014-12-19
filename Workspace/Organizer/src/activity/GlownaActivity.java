package activity;

import java.io.File;
import java.io.FileNotFoundException;

import robocze.Zapis;

import com.example.organizer.R;
import com.example.organizer.R.id;
import com.example.organizer.R.layout;
import com.example.organizer.R.menu;

import android.support.v7.app.ActionBarActivity;
import android.content.ContentResolver;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

public class GlownaActivity extends ActionBarActivity {

	private ContentResolver cr = getContentResolver();
	EditText edImieNazwisko = null;
	EditText edData = null;
	EditText edMiejsce = null;
	EditText edCel = null;
	File file = getFileStreamPath("zadania.txt");
	Zapis zapis;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.glowne);

		edImieNazwisko = (EditText) findViewById(R.id.idImie);
		edData = (EditText) findViewById(R.id.idData);
		edMiejsce = (EditText) findViewById(R.id.idMiejsce);
		edCel = (EditText) findViewById(R.id.idCel);

	}

	private void Zapisz() throws FileNotFoundException {
		zapis = new Zapis(file);

		StringBuffer linia = null;

		linia.append(edImieNazwisko.getText());
		linia.append(" ");
		linia.append(edData.getText());
		linia.append(" ");
		linia.append(edMiejsce.getText());
		linia.append(" ");
		linia.append(edCel.getText());
		linia.append(" ");

		zapis.ZapisPliku(linia);
	}

	private void doListy() {
		Intent intent = new Intent(getBaseContext(), ListaActivity.class);
		startActivity(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public ContentResolver getCr() {
		return cr;
	}

}
