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
import com.example.birzeit_university_student.classes.Student;

import java.util.ArrayList;


public class StudentAdapter extends ArrayAdapter<Student> {

    public StudentAdapter(Context context, ArrayList<Student> student) {
        super(context, 0, student);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Student stud = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_student, parent, false);
        }

        TextView stud_id = convertView.findViewById(R.id.textViewStud_Id);
        TextView stud_name = convertView.findViewById(R.id.textViewStud_name);
        TextView stud_major = convertView.findViewById(R.id.textViewStud_major);


        if (stud != null) {
            stud_id.setText(String.valueOf(stud.getStud_id()));
            stud_name.setText(stud.getStud_name());
            stud_major.setText(stud.getStud_major());
        }

        return convertView;
    }
}