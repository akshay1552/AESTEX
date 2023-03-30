package com.example.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class UpdateProductActivity extends AppCompatActivity {

    private EditText barcodeEditText, nameEditText, priceEditText, descriptionEditText, quantityEditText;
    private ImageView productImageView;
    private Button updateButton;
    private String productId;
    private DatabaseReference databaseReference;
    private StorageReference storageReference;
    private Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_product);

        barcodeEditText = findViewById(R.id.barcode_edit_text);
        nameEditText = findViewById(R.id.name_edit_text);
        priceEditText = findViewById(R.id.price_edit_text);
        descriptionEditText = findViewById(R.id.description_edit_text);
        quantityEditText = findViewById(R.id.quantity_edit_text);
        productImageView = findViewById(R.id.product_image_view);
        updateButton = findViewById(R.id.update_button);

        Intent intent = getIntent();
        productId = intent.getStringExtra("productId");

        databaseReference = FirebaseDatabase.getInstance().getReference("products").child(productId);
        storageReference = FirebaseStorage.getInstance().getReference("products");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Product product = dataSnapshot.getValue(Product.class);
                barcodeEditText.setText(product.getBarcode());
                nameEditText.setText(product.getName());
                priceEditText.setText(product.getPrice());
                descriptionEditText.setText(product.getDescription());
                quantityEditText.setText(String.valueOf(product.getQuantity()));
                Glide.with(UpdateProductActivity.this).load(product.getImageUrl()).into(productImageView);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(UpdateProductActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        productImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openImageChooser();
            }
        });

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateProduct();
            }
        });
    }

    private void openImageChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imageUri = data.getData();
            Glide.with(this).load(imageUri).into(productImageView);
        }
    }

    private void updateProduct() {
        final String barcode = barcodeEditText.getText().toString().trim();
        final String name = nameEditText.getText().toString().trim();
        final String price = priceEditText.getText().toString().trim();
        final String description = descriptionEditText.getText().toString().trim();
        String quantityString = quantityEditText.getText().toString().trim();

        if (TextUtils.isEmpty(barcode)) {
            Toast.makeText(this, "Please enter barcode", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "Please enter name", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(price)) {
            Toast.makeText(this, "Please enter price", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(description)) {
            Toast.makeText(this, "Please enter description", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(quantityString)) {
            Toast.makeText(this, "Please enter quantity", Toast.LENGTH_SHORT).show();
            return;
        }

        final int quantity = Integer.parseInt(quantityString);

        if (imageUri != null) {
            final StorageReference fileReference = storageReference.child(System.currentTimeMillis() + "." + getFileExtension(imageUri));

            UploadTask uploadTask = fileReference.putFile(imageUri);
            uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                @Override
                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                    if (!task.isSuccessful()) {
                        throw task.getException();
                    }

                    return fileReference.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {
                    if (task.isSuccessful()) {
                        Uri downloadUri = task.getResult();

                        Product product = new Product(productId, barcode, name, price, description, downloadUri.toString(), quantity);

                        databaseReference.setValue(product);

                        Toast.makeText(UpdateProductActivity.this, "Product updated successfully", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(UpdateProductActivity.this, ViewProductActivity.class);
                        intent.putExtra("productId", productId);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(UpdateProductActivity.this, "Failed to upload image", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } else {
            databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    Product product = dataSnapshot.getValue(Product.class);

                    product.setBarcode(barcode);
                    product.setName(name);
                    product.setPrice(price);
                    product.setDescription(description);
                    product.setQuantity(quantity);

                    databaseReference.setValue(product);

                    Toast.makeText(UpdateProductActivity.this, "Product updated successfully", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(UpdateProductActivity.this, ViewProductActivity.class);
                    intent.putExtra("productId", productId);
                    startActivity(intent);
                    finish();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(UpdateProductActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private String getFileExtension(Uri uri) {
        return MimeTypeMap.getSingleton().getExtensionFromMimeType(getContentResolver().getType(uri));
    }
}
