package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ViewProductActivity extends AppCompatActivity implements ProductAdapter.ItemClickListener {

    private RecyclerView mRecyclerView;
    private ProductAdapter mAdapter;
    private DatabaseReference mDatabaseRef;
    private List<Product> mProducts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_product);

        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mProducts = new ArrayList<>();
        mAdapter = new ProductAdapter(this, mProducts);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(this);

        mDatabaseRef = FirebaseDatabase.getInstance().getReference("products");
        mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mProducts.clear();
                for (DataSnapshot productSnapshot : dataSnapshot.getChildren()) {
                    Product product = productSnapshot.getValue(Product.class);
                    mProducts.add(product);
                }
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(ViewProductActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        registerForContextMenu(mRecyclerView);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu, menu);
    }


    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int position = info.position;
        Product selectedProduct = mProducts.get(position);

        switch (item.getItemId()) {
            case R.id.update:
                Intent intent = new Intent(this, UpdateProductActivity.class);
                intent.putExtra("productId", selectedProduct.getId());
                startActivity(intent);
                return true;

            case R.id.delete:
                deleteProduct(selectedProduct);
                return true;

            default:
                return super.onContextItemSelected(item);
        }
    }

    public void deleteProduct(Product product) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("products").child(product.getId());
        databaseReference.removeValue();
        Toast.makeText(this, "Product deleted successfully", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemClick(int position) {
        // no action needed
    }
}