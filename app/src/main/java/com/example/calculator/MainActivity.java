package com.example.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText etInput;
    private String currentNumber = "";
    private String operation = "";
    private double firstNumber = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etInput = findViewById(R.id.etInput);

        setupButtons();
    }

    private void setupButtons() {
        int[] buttonIds = {
                R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4,
                R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9,
                R.id.btnDot, R.id.btnAdd, R.id.btnSubtract, R.id.btnMultiply,
                R.id.btnDivide, R.id.btnClear, R.id.btnEquals
        };

        for (int id : buttonIds) {
            findViewById(id).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onButtonClick(v);
                }
            });
        }
    }

    private void onButtonClick(View view) {
        Button button = (Button) view;
        String buttonText = button.getText().toString();

        switch (buttonText) {
            case "C":
                currentNumber = "";
                operation = "";
                firstNumber = 0;
                etInput.setText("0");
                break;
            case "+":
            case "-":
            case "*":
            case "/":
                if (!currentNumber.isEmpty()) {
                    firstNumber = Double.parseDouble(currentNumber);
                    operation = buttonText;
                    currentNumber = "";
                }
                break;
            case "=":
                if (!currentNumber.isEmpty() && !operation.isEmpty()) {
                    double secondNumber = Double.parseDouble(currentNumber);
                    double result = performOperation(firstNumber, secondNumber, operation);
                    etInput.setText(String.valueOf(result));
                    currentNumber = String.valueOf(result);
                    operation = "";
                }
                break;
            default:
                currentNumber += buttonText;
                etInput.setText(currentNumber);
                break;
        }
    }

    public static double performOperation(double first, double second, String operation) {
        switch (operation) {
            case "+":
                return first + second;
            case "-":
                return first - second;
            case "*":
                return first * second;
            case "/":
                if (second != 0) {
                    return first / second;
                } else {
                    return Double.NaN; // Handle division by zero
                }
            default:
                return second;
        }
    }
}