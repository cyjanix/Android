package com.example.adrian.organizer_and;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.app.Activity;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.adrian.sqllite.Zadanie;

import java.util.List;

/**
 * Created by Adrian on 2015-01-21.
 */
public class ZadaniaAdpater extends ArrayAdapter<Zadanie>{

    private Activity context;
    private List<Zadanie> listaZadan;

    public ZadaniaAdpater(Activity context, List<Zadanie> listaZadan)
    {
        super(context, R.layout.layout_zadania, listaZadan);
        this.context = context;
        this.listaZadan = listaZadan;
    }

    static class ViewHolder {
        public TextView tvNazwa;
        public TextView tvTelefon;
        public TextView tvData;
        public TextView tvMiejsce;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        View rowView = convertView;
        if(rowView == null) {
            LayoutInflater layoutInflater = context.getLayoutInflater();
            rowView = layoutInflater.inflate(R.layout.layout_zadania, null, true);
            viewHolder = new ViewHolder();
            viewHolder.tvNazwa = (TextView) rowView.findViewById(R.id.tvNazwa);
            viewHolder.tvTelefon = (TextView) rowView.findViewById(R.id.tvTelefon);
            viewHolder.tvData = (TextView) rowView.findViewById(R.id.tvData);
            viewHolder.tvMiejsce = (TextView) rowView.findViewById(R.id.tvMiejsce);
            rowView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) rowView.getTag();
        }
        Zadanie zadanie = listaZadan.get(position);
        viewHolder.tvNazwa.setText(viewHolder.tvNazwa.getText().toString() + zadanie.getNazwa());
        viewHolder.tvTelefon.setText(viewHolder.tvTelefon.getText().toString() + zadanie.getTelefon());
        viewHolder.tvData.setText(viewHolder.tvData.getText().toString() + zadanie.getData());
        viewHolder.tvMiejsce.setText(viewHolder.tvMiejsce.getText().toString() + zadanie.getMiejsce());

        return rowView;
    }
}
