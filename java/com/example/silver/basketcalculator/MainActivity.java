package com.example.silver.basketcalculator;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {


    private TextView textResult;
    private TextView textSubtractor;
    private TextView textSubtractee;
    private String display = "0";
    private String result = "0";
    private SharedPreferences mPrefs;

    Button btnDone;
    double num1, num2, sub;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textResult = (TextView) findViewById(R.id.textResult);
        textSubtractor = (TextView) findViewById(R.id.textSubtractor);

        textSubtractee = (TextView) findViewById(R.id.textSubtractee);

        btnDone = (Button) findViewById(R.id.btnDone);

        textSubtractor.setText(display);
        textResult.setText(result);

        mPrefs = getSharedPreferences("test", 0);
        if (mPrefs != null){
            display = mPrefs.getString("display", "0");
            sub = mPrefs.getInt(result, 0);
        }
        updateScreen();

        btnDone.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
            }
        });
    }

    final static int INTEGER_SIZE = 3;
    final static int DECIMAL_SIZE = 2;


    public void onClickNumber(View v) {

        if (v.getId() == R.id.btnDot && display.equals(""))
            display += "0.";
            updateScreen();

        if (v.getId() == R.id.btnDot) { // Handle dot
            if (!display.contains("."))
                display += ".";
                updateScreen();
        } else { // Only number values reach this
            if (display.equals("0")) { // Handle default zero
                clear();
                updateScreen();
            }
            if (display.contains(".")) { //If the number contains a dot, decimal length has to be checked
                String[] split = display.split("\\.");
                if (split.length == 2 && split[1].length() == DECIMAL_SIZE)
                    return; // New number is not added
            } else if (display.length() == INTEGER_SIZE) //Otherwise check the length of the integer (whole sting)
                return; // New number is not added


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


            DecimalFormat df = new DecimalFormat("##.##");
            df.setRoundingMode(RoundingMode.FLOOR);

            num1 = Double.parseDouble(display);
            textSubtractor.setText(df.format(num1));

            num2 = Double.parseDouble(textSubtractee.getText().toString());

            sub = num1 - num2;
            textResult.setText(df.format(sub));
        }
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

    protected void onPause(){
        super.onPause();

        SharedPreferences.Editor ed = mPrefs.edit();
        ed.putString("display", display);
        ed.putInt(result, 0);
        ed.apply();
    }
}
