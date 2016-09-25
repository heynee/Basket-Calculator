package com.example.silver.basketcalculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputFilter;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private TextView textResult;
    private TextView textSubtractor;
    private TextView textSubtractee;
    private String display = "0";
    private String result = "0";

    Button btnDone;

    double num1, num2, sub;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textSubtractee = (TextView) findViewById(R.id.textSubtractee);

        textSubtractor = (TextView) findViewById(R.id.textSubtractor);
        textSubtractor.setFilters(new InputFilter[]{new InputFilter.LengthFilter(7)});

        textResult = (TextView) findViewById(R.id.textResult);
        textResult.setFilters(new InputFilter[]{new InputFilter.LengthFilter(8)});

        btnDone = (Button) findViewById(R.id.btnDone);

        textSubtractor.setText(display);
        textResult.setText(result);

        btnDone.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                if (display.equals("")){
                    return;
                }

                if (display.endsWith(".")) {
                    return;
                }

                num1 = Double.parseDouble(display);
                num2 = Double.parseDouble(textSubtractee.getText().toString());
                sub = num1 - num2;
                textResult.setText(new DecimalFormat("##.##").format(sub));
            }
        });
    }

    public void onClickNumber(View v) {

        if (result.equals("0")) {
            clear();
            updateScreen();
        }

        if (v.getId() == R.id.btnDot && (display.equals("") || display.contains("."))) {
            return;
        }

        if ((v.getId() == R.id.btn0 ||
                v.getId() == R.id.btn1 || v.getId() == R.id.btn2 ||
                v.getId() == R.id.btn3 || v.getId() == R.id.btn4 ||
                v.getId() == R.id.btn5 || v.getId() == R.id.btn6 ||
                v.getId() == R.id.btn7 || v.getId() == R.id.btn8 ||
                v.getId() == R.id.btn9) && (display.equals("0"))) {
            clear();
            updateScreen();
        }

        Button b = (Button) v;
        display += b.getText();
        updateScreen();
    }

    public void onClickClear(View v) {
        clear();
    }

    private void clear() {
        display = "";
        result = "";
        textSubtractor.setText("0");
        textResult.setText("0");
    }

    private void updateScreen() {
        textSubtractor.setText(display);
    }
    
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        final TextView textSubtractor = (TextView)findViewById(R.id.textSubtractor);
        CharSequence userText = textSubtractor.getText();
        outState.putCharSequence("savedText1", userText);

        final TextView textResult = (TextView)findViewById(R.id.textResult);
        CharSequence userText1 = textResult.getText();
        outState.putCharSequence("savedText2", userText1);

    }
    protected void onRestoreInstanceState(Bundle savedState) {
        super.onRestoreInstanceState(savedState);

        final TextView textSubtractor = (TextView)findViewById(R.id.textSubtractor);
        CharSequence userText = savedState.getCharSequence("savedText1");
        textSubtractor.setText(userText);

        final TextView textResult = (TextView)findViewById(R.id.textResult);
        CharSequence userText1 = savedState.getCharSequence("savedText2");
        textResult.setText(userText1);
    }
}
