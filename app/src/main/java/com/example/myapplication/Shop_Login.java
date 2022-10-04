package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Shop_Login extends AppCompatActivity {

    ImageView imageView;
    EditText username,password;
    TextView btnlogin;

    String admin_id = "aestex";
    String admin_pw = "aestexteam1234";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_login);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        getSupportActionBar().hide();

        imageView = (ImageView) findViewById(R.id.cust_back);

        username = findViewById(R.id.adminid);
        password = findViewById(R.id.adminpw);
        btnlogin = findViewById(R.id.adlogin_btn);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Shop_Login.this,LoginActivity.class);
                startActivity(intent);
            }
        });

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //validate input

                if (TextUtils.isEmpty(username.getText().toString()) || TextUtils.isEmpty(password.getText().toString())){

                    Toast.makeText(Shop_Login.this,"Please Enter Username and Password",Toast.LENGTH_LONG).show();

                }else if (username.getText().toString().equals(admin_id)){

                    //check password
                    if (password.getText().toString().equals(admin_pw)){

                        Toast.makeText(Shop_Login.this,"Succesfully Login !!!",Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(Shop_Login.this,Shop_Dashboard.class);
                        startActivity(intent);

                    }else {

                        Toast.makeText(Shop_Login.this,"Login Failed",Toast.LENGTH_LONG).show();

                    }

                }else {

                    Toast.makeText(Shop_Login.this,"Login Failed :(",Toast.LENGTH_LONG).show();

                }

            }
        });

    }
}