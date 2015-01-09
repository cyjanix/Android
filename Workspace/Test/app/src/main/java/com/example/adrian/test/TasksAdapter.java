package com.example.adrian.test;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;

/**
 * Created by Adrian on 2015-01-09.
 */
public class TasksAdapter extends ArrayAdapter<Task> {

    private Activity context;
    private List<Task> tasks;

    public TasksAdapter(Activity context, List<Task> tasks) {
        super(context, R.layout.activity_zadania, tasks);
        this.context = context;
        this.tasks = tasks;
    }

    static class ViewHolder {
        //todo
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        View rowView = convertView;
        if(rowView == null) {
            LayoutInflater layoutInflater = context.getLayoutInflater();
            rowView = layoutInflater.inflate(R.layout.activity_zadania, null, true);
            viewHolder = new ViewHolder();
            //viewHolder.tvTodoDescription = (TextView) rowView.findViewById(R.id.tvTodoDescription);
            rowView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) rowView.getTag();
        }
        Task task = tasks.get(position);
        //viewHolder.tvTodoDescription.setText(task.getDescription());
        return rowView;
    }
}
