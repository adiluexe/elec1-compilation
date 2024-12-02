package com.example.elec1compilation.machine_problems;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.elec1compilation.MainActivity;
import com.example.elec1compilation.R;

public class SemestralGradeComputation extends AppCompatActivity {

    private EditText studentName, prelimGrade, midtermGrade, finalGrade;
    private TextView resultStudentName, resultSemGrade, resultPtEquiv, resultRemarks;
    private Button btnNewEntry, btnCompute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.semestral_grade_computation);

        studentName = findViewById(R.id.studentName);
        prelimGrade = findViewById(R.id.prelimGrade);
        midtermGrade = findViewById(R.id.midtermGrade);
        finalGrade = findViewById(R.id.finalGrade);
        resultStudentName = findViewById(R.id.resultStudentName);
        resultSemGrade = findViewById(R.id.resultSemGrade);
        resultPtEquiv = findViewById(R.id.resultPtEquiv);
        resultRemarks = findViewById(R.id.resultRemarks);
        btnNewEntry = findViewById(R.id.btnNewEntry);
        btnCompute = findViewById(R.id.btnCompute);

        btnNewEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(SemestralGradeComputation.this)
                        .setTitle("WARNING MESSAGE")
                        .setMessage("Are you sure?")
                        .setPositiveButton("Yes", (dialog, which) -> clearEntries())
                        .setNegativeButton("No", null)
                        .show();
            }
        });

        btnCompute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(SemestralGradeComputation.this)
                        .setTitle("WARNING MESSAGE")
                        .setMessage("All Entries Correct?")
                        .setPositiveButton("Yes", (dialog, which) -> computeGrade())
                        .setNegativeButton("No", null)
                        .show();
            }
        });
    }

    private void clearEntries() {
        studentName.setText("");
        prelimGrade.setText("");
        midtermGrade.setText("");
        finalGrade.setText("");
        resultStudentName.setText("Student Name: ");
        resultSemGrade.setText("Semestral Grade: ");
        resultPtEquiv.setText("Pt. Equivalent: ");
        resultRemarks.setText("Remarks: ");
    }

    private void computeGrade() {
        String nameStr = studentName.getText().toString();
        String prelimStr = prelimGrade.getText().toString();
        String midtermStr = midtermGrade.getText().toString();
        String finalStr = finalGrade.getText().toString();

        if (nameStr.isEmpty() || prelimStr.isEmpty() || midtermStr.isEmpty() || finalStr.isEmpty()) {
            Toast.makeText(this, "Please provide an input on the missing fields", Toast.LENGTH_SHORT).show();
            return;
        }

        double prelim = Double.parseDouble(prelimStr);
        double midterm = Double.parseDouble(midtermStr);
        double finalGrade = Double.parseDouble(finalStr);

        double semGrade = (0.25 * prelim) + (0.25 * midterm) + (0.50 * finalGrade);
        double ptEquivalent = getPtEquivalent(semGrade);
        String remarks = semGrade >= 75 ? "PASSED" : "FAILED";

        resultStudentName.setText("Student Name: " + nameStr);
        resultSemGrade.setText("Semestral Grade: " + String.format("%.2f", semGrade));
        resultPtEquiv.setText("Pt. Equivalent: " + String.format("%.2f", ptEquivalent));
        resultRemarks.setText("Remarks: " + remarks);

        if (remarks.equals("PASSED")) {
            resultRemarks.setTextColor(getResources().getColor(android.R.color.holo_blue_dark));
        } else {
            resultRemarks.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
        }
    }

    private double getPtEquivalent(double semGrade) {
        if (semGrade >= 95) return 1.50;
        else if (semGrade >= 90) return 2.00;
        else if (semGrade >= 85) return 2.50;
        else if (semGrade >= 80) return 3.00;
        else if (semGrade >= 75) return 3.50;
        else return 5.00;
    }
}

