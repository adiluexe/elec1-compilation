package com.example.elec1compilation.machine_problems;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.elec1compilation.R;

public class Calculator extends AppCompatActivity {

    TextView firstnum,secondnum,result;
    Button add,diff,prod,quo;
    Double FirstNum;
    Double SecondNum;
    Double Result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculatormp);

        result = findViewById(R.id.tvResult);
        firstnum = findViewById(R.id.etFirstNumber);
        secondnum = findViewById(R.id.etSecondNumber);
        add = findViewById(R.id.btnAdd);
        diff = findViewById(R.id.btnDiff);
        prod = findViewById(R.id.btnProd);
        quo = findViewById(R.id.btnQuo);

        computeTotal();
    }

    public void computeTotal(){
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirstNum = Double.parseDouble(firstnum.getText().toString());
                SecondNum = Double.parseDouble(secondnum.getText().toString());
                Result = FirstNum + SecondNum;
                result.setText("The total sum is " + Result);
                if (((double)Result) % 2 != 0){
                    result.setTextColor(Color.RED);
                }else {
                    result.setTextColor(Color.BLUE);
                }

            }
        });
        diff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirstNum = Double.parseDouble(firstnum.getText().toString());
                SecondNum = Double.parseDouble(secondnum.getText().toString());
                Result = FirstNum - SecondNum;
                result.setText("The difference is " + Result);
                if (Result % 2 != 0){
                    result.setTextColor(Color.RED);
                }else {
                    result.setTextColor(Color.BLUE);
                }

            }
        });
        prod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirstNum = Double.parseDouble(firstnum.getText().toString());
                SecondNum = Double.parseDouble(secondnum.getText().toString());
                Result = FirstNum * SecondNum;
                result.setText("The product is " + Result);
                if (Result % 2 != 0){
                    result.setTextColor(Color.RED);
                }else {
                    result.setTextColor(Color.BLUE);
                }

            }
        });
        quo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirstNum = Double.parseDouble(firstnum.getText().toString());
                SecondNum = Double.parseDouble(secondnum.getText().toString());
                Result = FirstNum / SecondNum;
                result.setText("The total quotient is " + Result);
                if (Result % 2 != 0){
                    result.setTextColor(Color.RED);
                }else {
                    result.setTextColor(Color.BLUE  );
                }

            }
        });
    }

}