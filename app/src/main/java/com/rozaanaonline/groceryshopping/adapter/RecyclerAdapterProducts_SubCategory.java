package com.rozaanaonline.groceryshopping.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rozaanaonline.groceryshopping.R;
import com.rozaanaonline.groceryshopping.pojo.SubCategoryDataByProductsParser;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapterProducts_SubCategory extends
        RecyclerView.Adapter<RecyclerAdapterProducts_SubCategory.ViewHolder>{

    Context context;
    List<SubCategoryDataByProductsParser.Product> productsSubCategoryPojoList = new ArrayList<>();


    public RecyclerAdapterProducts_SubCategory(Context context,
                                               List<SubCategoryDataByProductsParser.Product>
                                                       productsSubCategoryPojoList) {
        this.context = context;
        this.productsSubCategoryPojoList = productsSubCategoryPojoList;
    }
    @NonNull
    @Override
    public RecyclerAdapterProducts_SubCategory.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                                             int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent,
                false);
        return new RecyclerAdapterProducts_SubCategory.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapterProducts_SubCategory.ViewHolder holder,
                                 int position) {

        holder.productTitle.setText(productsSubCategoryPojoList.get(position).getTitle());
        Picasso.get().load(productsSubCategoryPojoList.get(position).getImage())
                .into(holder.imageView);
        holder.actualPrice.setText("₹ "+productsSubCategoryPojoList.get(position).getPrice());
        holder.strikedPrice.setPaintFlags(holder.strikedPrice.getPaintFlags()|
                Paint.STRIKE_THRU_TEXT_FLAG);
        holder.strikedPrice.setText("₹ "+productsSubCategoryPojoList.get(position).getAmount());
        holder.qty.setText("10 KG");
    }

    @Override
    public int getItemCount() {
        return productsSubCategoryPojoList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView productTitle,qty,actualPrice,strikedPrice,discount;
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            productTitle = itemView.findViewById(R.id.title);
            qty = itemView.findViewById(R.id.qty);
            imageView = itemView.findViewById(R.id.prodImage);
            actualPrice = itemView.findViewById(R.id.actualPrice);
            strikedPrice =itemView.findViewById(R.id.strikedPrice);
            discount =itemView.findViewById(R.id.discount);
        }
    }
}
