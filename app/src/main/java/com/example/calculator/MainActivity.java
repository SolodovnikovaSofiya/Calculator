package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView textViewResult, textViewLastResult;
    private StringBuilder currentInput = new StringBuilder();
    private double lastResult = 0;
    private char lastOperator = ' ';

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewResult = findViewById(R.id.textViewResult);
        textViewLastResult = findViewById(R.id.textViewLastResult);

        setupDigitButtons();

        setupOperationButtons();
    }

    private void setupDigitButtons() {
        for (int i = 0; i <= 9; i++) {
            String buttonID = "button" + i;
            int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
            Button button = findViewById(resID);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    currentInput.append(((Button) v).getText());
                    updateResult();
                }
            });
        }
    }

    private void setupOperationButtons() {
        String[] operators = {"buttonAdd", "buttonSubtract", "buttonMultiply", "buttonDivide", "buttonPower"};
        for (String operator : operators) {
            int resID = getResources().getIdentifier(operator, "id", getPackageName());
            Button button = findViewById(resID);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    handleOperation((String) button.getText());
                }
            });
        }

        Button buttonEquals = findViewById(R.id.buttonEquals);
        buttonEquals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateResult();
            }
        });

        Button buttonClear = findViewById(R.id.buttonClear);
        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear();
            }
        });
    }

    private void handleOperation(String operator) {
        if (currentInput.length() > 0) {
            calculateResult();
        }
        lastOperator = operator.charAt(0);
        currentInput.setLength(0);
    }

    private void calculateResult() {
        if (currentInput.length() == 0) return;

        double num = Double.parseDouble(currentInput.toString());
        double result = 0;

        switch (lastOperator) {
            case '+':
                result = lastResult + num;
                break;
            case '-':
                result = lastResult - num;
                break;
            case '*':
                result = lastResult * num;
                break;
            case '/':
                if (num != 0) {
                    result = lastResult / num;
                } else {
                    textViewResult.setText("Error: Division by zero");
                    return;
                }
                break;
            case '^':
                result = Math.pow(lastResult, num);
                break;
            default:
                result = num;
                break;
        }

        lastResult = result;
        textViewResult.setText("Result: " + result);
        textViewLastResult.setText("Last Result: " + lastResult);
        currentInput.setLength(0);
    }

    private void updateResult() {
        textViewResult.setText(currentInput.toString());
    }

    private void clear() {
        currentInput.setLength(0);
        lastResult = 0;
        lastOperator = ' ';
        textViewResult.setText("");
        textViewLastResult.setText("Last Result: ");
    }
}