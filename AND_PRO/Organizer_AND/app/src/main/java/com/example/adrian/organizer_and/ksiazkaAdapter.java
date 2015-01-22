package com.example.adrian.organizer_and;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.adrian.sqllite.Zadanie;

import java.util.List;

/**
 * Created by Adrian on 2015-01-22.
 */
public class ksiazkaAdapter extends ArrayAdapter<Kontakt> {

    private Activity context;
    private List<Kontakt> listaKontaktow;

    public ksiazkaAdapter(Activity context, List<Kontakt> listaKontaktow)
    {
        super(context, R.layout.layout_kontakty, listaKontaktow);
        this.context = context;
        this.listaKontaktow = listaKontaktow;
    }

    static class ViewHolder {
        public TextView tvNazwa;
        public TextView tvTelefon;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        View rowView = convertView;
        if(rowView == null) {
            LayoutInflater layoutInflater = context.getLayoutInflater();
            rowView = layoutInflater.inflate(R.layout.layout_kontakty, null, true);
            viewHolder = new ViewHolder();
            viewHolder.tvNazwa = (TextView) rowView.findViewById(R.id.tvNazwa);
            viewHolder.tvTelefon = (TextView) rowView.findViewById(R.id.tvTelefon);
            rowView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) rowView.getTag();
        }
        Kontakt kontakt = listaKontaktow.get(position);
        viewHolder.tvNazwa.setText(viewHolder.tvNazwa.getText().toString() + kontakt.getNazwa());
        viewHolder.tvTelefon.setText(viewHolder.tvTelefon.getText().toString() + kontakt.getTelefon());


        return rowView;
    }

}
