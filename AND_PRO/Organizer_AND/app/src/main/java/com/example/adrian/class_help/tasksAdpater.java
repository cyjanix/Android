package com.example.adrian.class_help;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.view.LayoutInflater;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.adrian.class_activity.R;

import java.util.List;


public class tasksAdpater extends ArrayAdapter<Task>{

    private Activity context;
    private List<Task> tasksList;


    public tasksAdpater(Activity context, List<Task> tasksList)
    {
        super(context, R.layout.element_zadania, tasksList);
        this.context = context;
        this.tasksList = tasksList;
    }

    static class ViewHolder {
        public TextView tv_Name;
        public TextView tv_Number;
        public TextView tv_Date;
        public TextView tv_Place;
        public ImageButton b_Delete;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        View rowView = convertView;
        if(rowView == null) {
            LayoutInflater layoutInflater = context.getLayoutInflater();
            rowView = layoutInflater.inflate(R.layout.element_zadania, null, true);
            viewHolder = new ViewHolder();
            viewHolder.tv_Name = (TextView) rowView.findViewById(R.id.tvNazwa);
            viewHolder.tv_Number = (TextView) rowView.findViewById(R.id.tvTelefon);
            viewHolder.tv_Date = (TextView) rowView.findViewById(R.id.tvData);
            viewHolder.tv_Place = (TextView) rowView.findViewById(R.id.tvMiejsce);
            rowView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) rowView.getTag();
        }
        Task task = tasksList.get(position);
        viewHolder.tv_Name.setText(task.getName());
        viewHolder.tv_Number.setText(task.getNumber());
        viewHolder.tv_Date.setText(task.getDate());
        viewHolder.tv_Place.setText(task.getPlace());
        return rowView;
    }
}
