package com.rozaanaonline.groceryshopping.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.rozaanaonline.groceryshopping.R;
import com.rozaanaonline.groceryshopping.pojo.AddressPojoParser;
import com.rozaanaonline.groceryshopping.pojo.ProductDetailDataParser;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapterQuantity extends RecyclerView.Adapter<RecyclerAdapterQuantity.ViewHolder>{

    Context context;
    List<ProductDetailDataParser.Variant> variantList;

    public RecyclerAdapterQuantity(Context context, List<ProductDetailDataParser.Variant> variantList) {
        this.context = context;
        this.variantList = variantList;
    }

    @NonNull
    @NotNull
    @Override
    public RecyclerAdapterQuantity.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent,
                                                                 int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_quantity, parent, false);        //myorders_list
        return new RecyclerAdapterQuantity.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull RecyclerAdapterQuantity.ViewHolder holder, int position) {
        holder.quantityText.setText(variantList.get(position).getQuantity());
    }

    @Override
    public int getItemCount() {
        return variantList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView quantityCard;
        TextView quantityText;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            quantityCard = itemView.findViewById(R.id.quantityCard);
            quantityText = itemView.findViewById(R.id.quantityText);
        }
    }
}
