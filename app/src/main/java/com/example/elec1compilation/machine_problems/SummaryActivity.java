package com.example.elec1compilation.machine_problems;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.elec1compilation.R;

public class SummaryActivity extends AppCompatActivity {
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mp4_summary);

        dbHelper = new DatabaseHelper(this);
        TextView summaryDetails = findViewById(R.id.summaryDetails);
        Button backButton = findViewById(R.id.backButton);

        // Get data from intent
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String summaryText = extras.getString("SUMMARY_TEXT");
            summaryDetails.setText(summaryText);
        }

        backButton.setOnClickListener(v -> finish());
    }
}
