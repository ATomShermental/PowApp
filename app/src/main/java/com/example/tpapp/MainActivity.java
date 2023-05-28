package com.example.tpapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText baseEditText;
    private EditText exponentEditText;
    private CheckBox negativeExponentCheckBox;
    private Button calculateButton;
    private TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        baseEditText = findViewById(R.id.base);
        exponentEditText = findViewById(R.id.exponent);
        negativeExponentCheckBox = findViewById(R.id.negative_exponent);
        calculateButton = findViewById(R.id.calculate);
        resultTextView = findViewById(R.id.result);

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculatePower();
            }
        });
    }

    private void calculatePower() {
        String baseText = baseEditText.getText().toString();
        String exponentText = exponentEditText.getText().toString();

        if (baseText.isEmpty() || exponentText.isEmpty()) {
            Toast.makeText(this, "Введите основание и показатель степени", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!isValidNumber(baseText) || !isValidNumber(exponentText)) {
            Toast.makeText(this, "Введите корректные числовые значения", Toast.LENGTH_SHORT).show();
            return;
        }

        double base = Double.parseDouble(baseText);
        double exponent = Double.parseDouble(exponentText);



        if (negativeExponentCheckBox.isChecked()) {
            exponent = -exponent;
        }

        double result = Math.pow(base, exponent);

        if (Double.isInfinite(result) || Double.isNaN(result)) {
            Toast.makeText(this, "Невозможно выполнить операцию", Toast.LENGTH_SHORT).show();
            return;
        }

        resultTextView.setText(Double.toString(result));
    }

    private boolean isValidNumber(String numberString) {
        try {
            Double.parseDouble(numberString);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}

