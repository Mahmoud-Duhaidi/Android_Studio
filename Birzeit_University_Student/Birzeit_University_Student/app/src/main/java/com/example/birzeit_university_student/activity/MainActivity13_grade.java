package com.example.birzeit_university_student.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.birzeit_university_student.R;

public class MainActivity13_grade extends AppCompatActivity {

    private EditText[] editTexts;
    private Spinner[] spinners;
    private Button buttonSubmit;
    private TextView textViewResult;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activityb13_grade);
        Intent intent = getIntent();
        id = (int)intent.getExtras().get("stud_id");

        in();

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateAverage();
            }
        });
    }

    private void in() {
        editTexts = new EditText[]{
                findViewById(R.id.editText1),
                findViewById(R.id.editText2),
                findViewById(R.id.editText3),
                findViewById(R.id.editText4),
                findViewById(R.id.editText5),
                findViewById(R.id.editText6),
                findViewById(R.id.editText7),
                findViewById(R.id.editText8)
        };

        spinners = new Spinner[]{
                findViewById(R.id.spinner1),
                findViewById(R.id.spinner2),
                findViewById(R.id.spinner3),
                findViewById(R.id.spinner4),
                findViewById(R.id.spinner5),
                findViewById(R.id.spinner6),
                findViewById(R.id.spinner7),
                findViewById(R.id.spinner8)
        };

        buttonSubmit = findViewById(R.id.buttonSubmit);
        textViewResult = findViewById(R.id.textViewSubmit);
    }

    private void calculateAverage() {
        double sumGrades = 0;
        int sumSpinners = 0;
        int filledFields = 0;
        boolean hasHighGrade = false;
        boolean hasFailed = false;
        int counter = 0;

        for (int i = 0; i < editTexts.length; i++) {
            EditText editText = editTexts[i];
            Spinner spinner = spinners[i];

            String gradeText = editText.getText().toString().trim();
            if (!gradeText.isEmpty()) {
                double grade = Double.parseDouble(gradeText);
                if (grade >= 1 && grade <= 100) {
                    String spinnerValue = spinner.getSelectedItem().toString();
                    int spinnerNumber = Integer.parseInt(spinnerValue);

                    if (spinnerNumber == 0) {
                        TextView spinnerTextView = (TextView) spinner.getSelectedView();
                        spinnerTextView.setError("You must fill the hour");
                        return;
                    }

                    sumGrades += grade * spinnerNumber;
                    sumSpinners += spinnerNumber;
                    filledFields++;

                    if (grade >= 80) {
                        hasHighGrade = true;
                    }

                    if (grade < 60) {
                        hasFailed = true;
                        counter++;
                    }
                } else {
                    editText.setError("Grade must be between 1 and 100");
                }
            }
        }

        if (filledFields == 0) {
            textViewResult.setText("No valid data entered.");
            return;
        }

        double average = sumGrades / sumSpinners;

        if (hasFailed) {
            if (average >= 60)
                textViewResult.setText("The average is: " + String.format("%.2f", average) + "/100. And you have failed in " + counter + " course.");
            else {
                textViewResult.setText("The average is: " + String.format("%.2f", average) + "  you fail. And you have failed in " + counter + " course.");
            }
        } else if (hasHighGrade && average >= 85) {
            textViewResult.setText("The average is: " + String.format("%.2f", average) + "/100. You get an Honor.");
        } else {
            textViewResult.setText("The average is: " + String.format("%.2f", average) + "/100");
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(MainActivity13_grade.this, MainActivity5_studentHome.class);
        intent.putExtra("stud_id", id);
        startActivity(intent);
        finish();
    }


}
