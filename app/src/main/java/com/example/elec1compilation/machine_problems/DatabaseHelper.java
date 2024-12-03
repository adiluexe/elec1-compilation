package com.example.elec1compilation.machine_problems;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "EmployeePayroll.db";
    private static final int DATABASE_VERSION = 1;

    // Table name
    private static final String TABLE_EMPLOYEES = "employees";

    // Column names
    private static final String COLUMN_ID = "employee_id";
    private static final String COLUMN_NAME = "employee_name";
    private static final String COLUMN_POSITION_CODE = "position_code";
    private static final String COLUMN_CIVIL_STATUS = "civil_status";
    private static final String COLUMN_DAYS_WORKED = "days_worked";
    private static final String COLUMN_BASIC_PAY = "basic_pay";
    private static final String COLUMN_SSS_CONTRIBUTION = "sss_contribution";
    private static final String COLUMN_WITHHOLDING_TAX = "withholding_tax";
    private static final String COLUMN_NET_PAY = "net_pay";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_EMPLOYEES_TABLE = "CREATE TABLE " + TABLE_EMPLOYEES + "("
                + COLUMN_ID + " TEXT PRIMARY KEY,"
                + COLUMN_NAME + " TEXT,"
                + COLUMN_POSITION_CODE + " TEXT,"
                + COLUMN_CIVIL_STATUS + " TEXT,"
                + COLUMN_DAYS_WORKED + " INTEGER,"
                + COLUMN_BASIC_PAY + " REAL,"
                + COLUMN_SSS_CONTRIBUTION + " REAL,"
                + COLUMN_WITHHOLDING_TAX + " REAL,"
                + COLUMN_NET_PAY + " REAL"
                + ")";
        db.execSQL(CREATE_EMPLOYEES_TABLE);

        // Insert initial employee data
        initializeEmployeeData(db);
    }

    private void initializeEmployeeData(SQLiteDatabase db) {
        String[][] employeeData = {
                {"EMP1203", "Papsi"},
                {"EMP1204", "Michael Josh Tempra"},
                {"EMP1205", "Clement Harold David"},
                {"EMP1206", "Miguel"},
                {"EMP1207", "Wimari Baluyut"}
        };

        for (String[] employee : employeeData) {
            ContentValues values = new ContentValues();
            values.put(COLUMN_ID, employee[0]);
            values.put(COLUMN_NAME, employee[1]);
            db.insert(TABLE_EMPLOYEES, null, values);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EMPLOYEES);
        onCreate(db);
    }

    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_EMPLOYEES;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            int idIndex = cursor.getColumnIndexOrThrow(COLUMN_ID);
            int nameIndex = cursor.getColumnIndexOrThrow(COLUMN_NAME);

            do {
                try {
                    String id = cursor.getString(idIndex);
                    String name = cursor.getString(nameIndex);

                    if (id != null && name != null) {
                        Employee employee = new Employee(id, name);
                        employees.add(employee);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    // Continue to next record if there's an error
                    continue;
                }
            } while (cursor.moveToNext());
        }

        cursor.close();
        return employees;
    }

    public void saveEmployeePayroll(String employeeId, String positionCode,
                                    String civilStatus, int daysWorked,
                                    double basicPay, double sssContribution,
                                    double withholdingTax, double netPay) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_POSITION_CODE, positionCode);
        values.put(COLUMN_CIVIL_STATUS, civilStatus);
        values.put(COLUMN_DAYS_WORKED, daysWorked);
        values.put(COLUMN_BASIC_PAY, basicPay);
        values.put(COLUMN_SSS_CONTRIBUTION, sssContribution);
        values.put(COLUMN_WITHHOLDING_TAX, withholdingTax);
        values.put(COLUMN_NET_PAY, netPay);

        db.update(TABLE_EMPLOYEES, values, COLUMN_ID + " = ?",
                new String[]{employeeId});
    }

    public Cursor getEmployeePayrollData(String employeeId) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(TABLE_EMPLOYEES, null,
                COLUMN_ID + " = ?", new String[]{employeeId},
                null, null, null);
    }
}
