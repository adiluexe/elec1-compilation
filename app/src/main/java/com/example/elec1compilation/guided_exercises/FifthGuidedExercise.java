package com.example.elec1compilation.guided_exercises;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.elec1compilation.R;


public class FifthGuidedExercise extends AppCompatActivity {

    RadioButton red, blue, yellow, green;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ge5);

        red = findViewById(R.id.rbRed);
        blue = findViewById(R.id.rbBlue);
        yellow = findViewById(R.id.rbYellow);
        green = findViewById(R.id.rbGreen);
    }

    public void showSelectedColor(){
        if(red.isChecked()){
            Toast.makeText(getApplicationContext(),"Color: RED", Toast.LENGTH_SHORT).show();
        }
        if(blue.isChecked()){
            Toast.makeText(getApplicationContext(),"Color: BLUE", Toast.LENGTH_SHORT).show();
        }
        if(yellow.isChecked()){
            Toast.makeText(getApplicationContext(),"Color: YELLOW", Toast.LENGTH_SHORT).show();
        }
        if(green.isChecked()){
            Toast.makeText(getApplicationContext(),"Color: GREEN", Toast.LENGTH_SHORT).show();
        }
    }
    // call this method on onClick property of our four radio button
    public void changeBackgroundColor(View view){
        // this will set into true if one of the radion button is checked
        boolean checked = ((RadioButton) view).isChecked();

        if (view.getId() == R.id.rbRed && checked) {
            getWindow().getDecorView().setBackgroundColor(Color.RED);
            showSelectedColor();
        } else if (view.getId() == R.id.rbBlue && checked) {
            getWindow().getDecorView().setBackgroundColor(Color.BLUE);
            showSelectedColor();
        } else if (view.getId() == R.id.rbYellow && checked) {
            getWindow().getDecorView().setBackgroundColor(Color.YELLOW);
            showSelectedColor();
        } else if (view.getId() == R.id.rbGreen && checked) {
            getWindow().getDecorView().setBackgroundColor(Color.GREEN);
            showSelectedColor();
        }

    }
}

