package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;


import javax.xml.transform.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;


public class QRScannerActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    private ZXingScannerView scannerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrscanner);
        scannerView = findViewById(R.id.scannerView);
    }

    @Override
    public void onResume() {
        super.onResume();
        scannerView.setResultHandler(this);
        scannerView.startCamera();
    }



    @Override
    public void onPause() {
        super.onPause();
        scannerView.stopCamera();
    }

    public static final int QR_SCAN_REQUEST_CODE = 123;

    @Override
    public void handleResult(com.google.zxing.Result result) {
        String resultString = result.getText();
        Intent data = new Intent();
        data.putExtra("result", resultString);
        setResult(RESULT_OK, data);
        finish();
    }
}