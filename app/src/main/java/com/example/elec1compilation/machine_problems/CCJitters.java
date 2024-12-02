package com.example.elec1compilation.machine_problems;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.elec1compilation.R;

public class CCJitters extends AppCompatActivity {

    TextView subTotal, discount, netAmount;
    Button compute, clearAll;
    CheckBox CVF, GTCF, SF, MF;
    RadioButton fivePrcnt, tenPrcnt, fifteenPrcnt, noPercent;
    Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ccjitters);

        // Correct placement of findViewById
        subTotal = findViewById(R.id.tvSubtotal);
        discount = findViewById(R.id.tvDiscount);
        netAmount = findViewById(R.id.tvNetAmount);
        compute = findViewById(R.id.btnCompute);
        clearAll = findViewById(R.id.btnClearAll);
        CVF = findViewById(R.id.cbCVF);
        GTCF = findViewById(R.id.cbGTCF);
        SF = findViewById(R.id.cbSF);
        MF = findViewById(R.id.cbMF);
        fivePrcnt = findViewById(R.id.rb5Disc);
        tenPrcnt = findViewById(R.id.rb10Disc);
        fifteenPrcnt = findViewById(R.id.rb15Disc);
        noPercent = findViewById(R.id.rbNoDisc);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        CVF.setOnClickListener(v -> {
            if (CVF.isChecked()) {
                toast = Toast.makeText(getApplicationContext(), "You Selected Cappuccino Vanilla Frappuccino: ₱150", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
        GTCF.setOnClickListener(v -> {
            if (GTCF.isChecked()) {
                toast = Toast.makeText(getApplicationContext(), "You Selected Green Tea Creme Frappuccino: ₱190", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
        SF.setOnClickListener(v -> {
            if (SF.isChecked()) {
                toast = Toast.makeText(getApplicationContext(), "You Selected S'mores Frappuccino: ₱199", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
        MF.setOnClickListener(v -> {
            if (MF.isChecked()) {
                toast = Toast.makeText(getApplicationContext(), "You selected Mocha Frappuccino: ₱130", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
        fivePrcnt.setOnClickListener(v -> {
            if (fivePrcnt.isChecked()) {
                toast = Toast.makeText(getApplicationContext(), "You selected 5% Discount", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
        tenPrcnt.setOnClickListener(v -> {
            if (tenPrcnt.isChecked()) {
                toast = Toast.makeText(getApplicationContext(), "You selected 10% Discount", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
        fifteenPrcnt.setOnClickListener(v -> {
            if (fifteenPrcnt.isChecked()) {
                toast = Toast.makeText(getApplicationContext(), "You selected 15% Discount", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
        noPercent.setOnClickListener(v -> {
            if (noPercent.isChecked()) {
                toast = Toast.makeText(getApplicationContext(), "You selected No Discount", Toast.LENGTH_SHORT);
                toast.show();
            }
        });

        compute.setOnClickListener(v -> {
            double total = 0;
            if (CVF.isChecked()) {
                total += 150;
            }
            if (GTCF.isChecked()) {
                total += 190;
            }
            if (SF.isChecked()) {
                total += 199;
            }
            if (MF.isChecked()) {
                total += 130;
            }
            subTotal.setText(String.valueOf(total));

            double discount = 0;
            if (fivePrcnt.isChecked()) {
                discount = total * 0.05;
            } else if (tenPrcnt.isChecked()) {
                discount = total * 0.10;
            } else if (fifteenPrcnt.isChecked()) {
                discount = total * 0.15;
            } else if (noPercent.isChecked()) {
                discount = 0;
            }
            this.discount.setText(String.valueOf(discount));
            netAmount.setText(String.valueOf(total - discount));
        });

        clearAll.setOnClickListener(v -> {
            subTotal.setText("");
            discount.setText("");
            netAmount.setText("");
            CVF.setChecked(false);
            GTCF.setChecked(false);
            SF.setChecked(false);
            MF.setChecked(false);
            fivePrcnt.setChecked(false);
            tenPrcnt.setChecked(false);
            fifteenPrcnt.setChecked(false);
            noPercent.setChecked(false);
            toast = Toast.makeText(getApplicationContext(), "All fields cleared", Toast.LENGTH_SHORT);
            toast.show();
        });
    }
}