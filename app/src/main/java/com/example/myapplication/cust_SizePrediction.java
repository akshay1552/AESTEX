package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

public class cust_SizePrediction extends AppCompatActivity {

    private EditText chestEditText;
    private EditText waistEditText;
    private EditText sleeveLengthEditText;
    private EditText neckEditText;
    private TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cust_size_prediction);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        getSupportActionBar().hide();

        chestEditText = findViewById(R.id.edit_text_chest);
        waistEditText = findViewById(R.id.edit_text_waist);
        sleeveLengthEditText = findViewById(R.id.edit_text_sleeve_length);
        neckEditText = findViewById(R.id.edit_text_neck);
        resultTextView = findViewById(R.id.text_view_result);

        findViewById(R.id.button_predict).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double chest = Double.parseDouble(chestEditText.getText().toString());
                double waist = Double.parseDouble(waistEditText.getText().toString());
                double sleeveLength = Double.parseDouble(sleeveLengthEditText.getText().toString());
                double neck = Double.parseDouble(neckEditText.getText().toString());

                String size = calculateShirtSize(chest, waist, sleeveLength, neck);

                resultTextView.setText(size);
            }
        });
    }

    public static String calculateShirtSize(double chest, double waist, double sleeveLength, double neck) {
        String size;

        if (chest <= 36 && waist <= 28 && sleeveLength <= 32.5 && neck <= 14) {
            size = "Based on your measurements, we recommend the XXXS size for you.";
        } else if (chest <= 38 && waist <= 30 && sleeveLength <= 33 && neck <= 14.5) {
            size = "Based on your measurements, we recommend the XXS size for you.";
        } else if (chest <= 40 && waist <= 32 && sleeveLength <= 33.5 && neck <= 15) {
            size = "Based on your measurements, we recommend the XS size for you.";
        } else if (chest <= 42 && waist <= 34 && sleeveLength <= 34 && neck <= 15.5) {
            size = "Based on your measurements, we recommend the S size for you.";
        } else if (chest <= 44 && waist <= 36 && sleeveLength <= 34.5 && neck <= 16) {
            size = "Based on your measurements, we recommend the M size for you.";
        } else if (chest <= 46 && waist <= 38 && sleeveLength <= 35 && neck <= 17) {
            size = "Based on your measurements, we recommend the L size for you.";
        } else if (chest <= 48 && waist <= 40 && sleeveLength <= 35.5 && neck <= 17.5) {
            size = "Based on your measurements, we recommend the XL size for you.";
        } else if (chest <= 50 && waist <= 42 && sleeveLength <= 36 && neck <= 18.5) {
            size = "Based on your measurements, we recommend the XXL size for you.";
        } else {
            size = "Based on your measurements, we recommend the XXXL size for you.";
        }

        return size;
    }
}
