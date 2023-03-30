package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    private Context context;
    private List<Product> productList;
    private ItemClickListener mItemClickListener;

    public interface ItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(ItemClickListener listener) {
        mItemClickListener = listener;
    }

    public ProductAdapter(Context context, List<Product> productList) {
        this.context = context;
        this.productList = productList;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.product_item, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = productList.get(position);
        holder.nameTextView.setText(product.getName());
        holder.priceTextView.setText("Price: " + product.getPrice());
        Glide.with(context).load(product.getImageUrl()).into(holder.productImageView);
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public void addProduct(Product product) {
        productList.add(product);
        notifyItemInserted(productList.size() - 1);
    }

    public void updateProduct(Product product) {
        int index = productList.indexOf(product);
        if (index != -1) {
            productList.set(index, product);
            notifyItemChanged(index);
        }
    }

    public void removeProduct(Product product) {
        int index = productList.indexOf(product);
        if (index != -1) {
            productList.remove(product);
            notifyItemRemoved(index);
        }
    }

    public void clearProducts() {
        int size = productList.size();
        if (size > 0) {
            productList = new ArrayList<>();
            notifyItemRangeRemoved(0, size);
        }
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView nameTextView, priceTextView;
        public ImageView productImageView;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);

            nameTextView = itemView.findViewById(R.id.name_text_view);
            priceTextView = itemView.findViewById(R.id.price_text_view);
            productImageView = itemView.findViewById(R.id.product_image_view);

            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        showDeleteDialog(productList.get(position));
                        return true;
                    }
                    return false;
                }
            });
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION) {
                Intent intent = new Intent(context, UpdateProductActivity.class);
                intent.putExtra("product", (CharSequence) productList.get(position));
                context.startActivity(intent);
            }
        }

        private void showDeleteDialog(final Product product) {
            DeleteProductDialog dialog = new DeleteProductDialog(context);
            dialog.setOnDeleteClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (context instanceof ViewProductActivity) {
                        ((ViewProductActivity) context).deleteProduct(product);
                    }
                }
            });
            dialog.show();
        }
    }
}
