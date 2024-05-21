package com.example.birzeit_university_student.activity_tools;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import com.example.birzeit_university_student.R;
import com.example.birzeit_university_student.classes.Task;

import java.util.ArrayList;


public class TaskAdapter extends ArrayAdapter<Task> {

    public TaskAdapter(Context context, ArrayList<Task> tasks) {
        super(context, 0, tasks);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Task task = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_task, parent, false);
        }

        TextView taskTitle = convertView.findViewById(R.id.TaskTitle);
        TextView taskDate = convertView.findViewById(R.id.TaskDate);
        TextView taskTime = convertView.findViewById(R.id.TaskTime);
        TextView taskStatus = convertView.findViewById(R.id.TaskStatus);

        if (task != null) {
            taskTitle.setText(task.getTitle());
            taskDate.setText(task.getDate());
            taskTime.setText(task.getTime());
            taskStatus.setText(task.getStatus());
        }

        return convertView;
    }
}