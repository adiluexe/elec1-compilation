package com.example.elec1compilation.machine_problems;


import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.elec1compilation.R;

import java.util.ArrayList;
import java.util.Locale;

public class EmployeePayrollComputation extends AppCompatActivity {
    private Spinner employeeIdSpinner, positionCodeSpinner, daysWorkedSpinner;
    private TextView employeeNameText;
    private RadioGroup civilStatusContainer;
    private Button computeButton, clearButton;
    private ArrayList<Employee> employees;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mp4_main);

        dbHelper = new DatabaseHelper(this);

        initializeViews();
        setupEmployeeData();
        setupSpinners();
        setupListeners();
    }

    private void inspectDatabase() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
    }

    private void initializeViews() {
        employeeIdSpinner = findViewById(R.id.employeeIdSpinner);
        positionCodeSpinner = findViewById(R.id.positionCodeSpinner);
        daysWorkedSpinner = findViewById(R.id.daysWorkedSpinner);
        employeeNameText = findViewById(R.id.employeeNameText);
        civilStatusContainer = findViewById(R.id.civilStatusContainer);
        computeButton = findViewById(R.id.computeButton);
        clearButton = findViewById(R.id.clearButton);
    }

    private void setupEmployeeData() {
        employees = new ArrayList<>(dbHelper.getAllEmployees());
    }

    private void setupSpinners() {
        // Setup Employee ID Spinner
        ArrayList<String> employeeIds = new ArrayList<>();
        for(Employee emp : employees) {
            employeeIds.add(emp.getEmployeeId());
        }
        ArrayAdapter<String> employeeAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, employeeIds);
        employeeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        employeeIdSpinner.setAdapter(employeeAdapter);

        // Setup Position Code Spinner
        String[] positionCodes = {"A", "B", "C"};
        ArrayAdapter<String> positionAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, positionCodes);
        positionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        positionCodeSpinner.setAdapter(positionAdapter);

        // Setup Days Worked Spinner
        Integer[] daysWorked = {7, 14, 21, 30};
        ArrayAdapter<Integer> daysAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, daysWorked);
        daysAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        daysWorkedSpinner.setAdapter(daysAdapter);
    }

    private void setupListeners() {
        employeeIdSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedId = (String) parent.getItemAtPosition(position);
                for(Employee emp : employees) {
                    if(emp.getEmployeeId().equals(selectedId)) {
                        employeeNameText.setText("Employee Name: " + emp.getName());
                        break;
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        computeButton.setOnClickListener(v -> computeSalary());
        clearButton.setOnClickListener(v -> clearFields());
    }

    private void computeSalary() {
        try {
            String selectedId = employeeIdSpinner.getSelectedItem().toString();
            Employee currentEmployee = null;

            for(Employee emp : employees) {
                if(emp.getEmployeeId().equals(selectedId)) {
                    currentEmployee = emp;
                    break;
                }
            }

            if(currentEmployee == null) return;

            currentEmployee.setPositionCode(positionCodeSpinner.getSelectedItem().toString());

            // Get civil status
            int selectedRadioId = civilStatusContainer.getCheckedRadioButtonId();
            if (selectedRadioId == -1) {
                Toast.makeText(this, "Please select civil status", Toast.LENGTH_SHORT).show();
                return;
            }
            RadioButton selectedRadio = findViewById(selectedRadioId);
            currentEmployee.setCivilStatus(selectedRadio.getText().toString());

            // Calculate pay
            int daysWorked = (Integer) daysWorkedSpinner.getSelectedItem();
            double basicPay = daysWorked * currentEmployee.getRatePerDay();
            double sssRate = currentEmployee.calculateSSSRate(basicPay);
            double sssContribution = basicPay * sssRate;
            double withholdingTax = basicPay * currentEmployee.getTaxRate();
            double netPay = basicPay - (sssContribution + withholdingTax);

            // Save to database after calculations
            dbHelper.saveEmployeePayroll(
                    currentEmployee.getEmployeeId(),
                    currentEmployee.getPositionCode(),
                    currentEmployee.getCivilStatus(),
                    daysWorked,
                    basicPay,
                    sssContribution,
                    withholdingTax,
                    netPay
            );

            // Create summary text and launch summary activity
            String summaryText = String.format(Locale.getDefault(),
                    "Employee ID: %s\n\n" +
                            "Name: %s\n\n" +
                            "Position Code: %s\n\n" +
                            "Civil Status: %s\n\n" +
                            "No. of Days Worked: %d\n\n" +
                            "Basic Pay: Php %.2f\n\n" +
                            "SSS Contribution: Php %.2f\n\n" +
                            "Withholding Tax: Php %.2f\n\n" +
                            "Net Pay: Php %.2f",
                    currentEmployee.getEmployeeId(),
                    currentEmployee.getName(),
                    currentEmployee.getPositionCode(),
                    currentEmployee.getCivilStatus(),
                    daysWorked,
                    basicPay,
                    sssContribution,
                    withholdingTax,
                    netPay);

            Intent intent = new Intent(this, SummaryActivity.class);
            intent.putExtra("SUMMARY_TEXT", summaryText);
            startActivity(intent);

        } catch (Exception e) {
            Toast.makeText(this, "Please fill all fields correctly",
                    Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    private void clearFields() {
        employeeIdSpinner.setSelection(0);
        positionCodeSpinner.setSelection(0);
        daysWorkedSpinner.setSelection(0);
        civilStatusContainer.clearCheck();
    }
}
