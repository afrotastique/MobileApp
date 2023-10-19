package com.example.assignment1;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.MenuItem;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    private EditText editTextHoursWorked;
    private EditText editTextHourlyRate;
    private TextView textViewPay;
    private TextView textViewOvertimePay;
    private TextView textViewTotalPay;
    private TextView textViewTax;
    private Button buttonCalculate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Find views by their IDs
        editTextHoursWorked = findViewById(R.id.editTextHoursWorked);
        editTextHourlyRate = findViewById(R.id.editTextHourlyRate);
        textViewPay = findViewById(R.id.textViewPay);
        textViewOvertimePay = findViewById(R.id.textViewOvertimePay);
        textViewTotalPay = findViewById(R.id.textViewTotalPay);
        textViewTax = findViewById(R.id.textViewTax);
        buttonCalculate = findViewById(R.id.buttonCalculate);

        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculatePay();
            }
        });
    }

    private void calculatePay() {
        double hoursWorked = Double.parseDouble(editTextHoursWorked.getText().toString());
        double hourlyRate = Double.parseDouble(editTextHourlyRate.getText().toString());

        double pay;
        double overtimePay;
        double totalPay;
        double tax;

        if (hoursWorked <= 40) {
            pay = hoursWorked * hourlyRate;
            overtimePay = 0;
        } else {
            pay = (hoursWorked - 40) * hourlyRate * 1.5 + 40 * hourlyRate;
            overtimePay = (hoursWorked - 40) * hourlyRate * 1.5;
        }

        tax = pay * 0.18;
        totalPay = pay - tax;

        // Display the results
        textViewPay.setText("Pay: " + pay);
        textViewOvertimePay.setText("Overtime Pay: " + overtimePay);
        textViewTotalPay.setText("Total Pay: " + totalPay);
        textViewTax.setText("Tax: " + tax);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.d("MainActivity", "onCreateOptionsMenu is called");
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_about) {
            // Launch AboutActivity here
            Intent intent = new Intent(this, AboutActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}