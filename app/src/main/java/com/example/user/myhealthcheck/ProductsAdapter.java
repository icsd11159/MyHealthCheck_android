package com.example.user.myhealthcheck;

/**
 * Created by user on 28/12/2017.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by Belal on 10/18/2017.
 */

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ProductViewHolder> {


    private Context mCtx;
    private List<Product> productList;

    public ProductsAdapter(Context mCtx, List<Product> productList) {
        this.mCtx = mCtx;
        this.productList = productList;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.product_list, null);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        Product product = productList.get(position);

        //loading the image
       // Glide.with(mCtx)
               // .load(product.getImage())
               // .into(holder.imageView);

        holder.textViewTitle.setText(product.getAmka());
        holder.textViewShortDesc.setText(product.getId_d());
        holder.textViewRating.setText(String.valueOf(product.getName_exam()));
        holder.textViewPrice.setText(String.valueOf(product.getType()));
        holder.textViewPrice.setText(String.valueOf(product.getResult()));
        holder.textViewPrice.setText(String.valueOf(product.getDate()));
        holder.textViewPrice.setText(String.valueOf(product.getComments()));
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView textViewTitle, textViewShortDesc, textViewRating, textViewPrice;
        ImageView imageView;

        public ProductViewHolder(View itemView) {
            super(itemView);
            textViewTitle = (TextView) itemView.findViewById(R.id.textViewAmka);
            textViewTitle = (TextView) itemView.findViewById(R.id.textViewId_d);
            textViewShortDesc = (TextView) itemView.findViewById(R.id.textViewType);
            textViewRating = (TextView) itemView.findViewById(R.id.textViewName_exam);
            textViewPrice = (TextView) itemView.findViewById(R.id.textViewResult);
            textViewPrice = (TextView) itemView.findViewById(R.id.textViewDate);
            textViewPrice = (TextView) itemView.findViewById(R.id.textViewComments);

        }
    }
}
