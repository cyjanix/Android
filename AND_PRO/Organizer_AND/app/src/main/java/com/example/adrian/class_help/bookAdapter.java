package com.example.adrian.class_help;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.adrian.class_activity.R;

import java.util.List;

/**
 * Created by Adrian on 2015-01-22.
 */
public class bookAdapter extends ArrayAdapter<Contact> {

    private Activity context;
    private List<Contact> contactsList;

    public bookAdapter(Activity context, List<Contact> contactsList)
    {
        super(context, R.layout.element_kontakty, contactsList);
        this.context = context;
        this.contactsList = contactsList;
    }

    static class ViewHolder {
        public TextView tv_Name;
        public TextView tv_Number;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        View rowView = convertView;
        if(rowView == null) {
            LayoutInflater layoutInflater = context.getLayoutInflater();
            rowView = layoutInflater.inflate(R.layout.element_kontakty, null, true);
            viewHolder = new ViewHolder();
            viewHolder.tv_Name = (TextView) rowView.findViewById(R.id.imie_kon);
            viewHolder.tv_Number = (TextView) rowView.findViewById(R.id.telefon_kon);
            rowView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) rowView.getTag();
        }
        Contact contact = contactsList.get(position);
        viewHolder.tv_Name.setText(viewHolder.tv_Name.getText().toString() + contact.getName());
        viewHolder.tv_Number.setText(viewHolder.tv_Number.getText().toString() + contact.getNumber());


        return rowView;
    }

}
