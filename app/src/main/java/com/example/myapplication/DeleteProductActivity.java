package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DeleteProductActivity extends AppCompatActivity {

    private String productId;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_product);

        Intent intent = getIntent();
        productId = intent.getStringExtra("productId");

        databaseReference = FirebaseDatabase.getInstance().getReference("products").child(productId);

        Button deleteButton = findViewById(R.id.delete_button);

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteProduct();
            }
        });
    }

    private void deleteProduct() {
        databaseReference.removeValue();
        Toast.makeText(this, "Product deleted", Toast.LENGTH_SHORT).show();
        finish();
    }
}