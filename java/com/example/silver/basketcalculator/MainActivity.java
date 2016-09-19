package com.example.silver.basketcalculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
        textResult = (TextView) findViewById(R.id.textResult);
        btnDone = (Button) findViewById(R.id.btnDone);
        textSubtractor.setText(display);
        textResult.setText(result);

        btnDone.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                if(display == "") return;

                num1 = Double.valueOf(textSubtractor.getText().toString());
                num2 = Double.valueOf(textSubtractee.getText().toString());
                sub = num1 - num2;
                textResult.setText(new DecimalFormat("##.##").format(sub));

            }
        });
    }

    public void onClickNumber(View v) {
        if (result != "") {
            clear();
            updateScreen();
        }

        Button b = (Button) v;
        display += b.getText();
        updateScreen();
    }

    public void onClickClear(View v) {

        clear();
        updateScreen();
    }

    private void clear() {
        display = "";
        result = "";
        textResult.setText("0");
    }

    private void updateScreen() {
        textSubtractor.setText(display);
    }
}
