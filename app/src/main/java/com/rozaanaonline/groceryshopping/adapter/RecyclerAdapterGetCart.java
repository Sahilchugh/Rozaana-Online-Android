package com.rozaanaonline.groceryshopping.adapter;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Paint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rozaanaonline.groceryshopping.R;
import com.rozaanaonline.groceryshopping.homeStructure.UpdateQuantityInterface;
import com.rozaanaonline.groceryshopping.networkingStructure.BaseUrl;
import com.rozaanaonline.groceryshopping.pojo.GetCartPojoParser;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapterGetCart extends RecyclerView.Adapter<RecyclerAdapterGetCart.ViewHolder> {

    Context context;
    List<GetCartPojoParser> getCartPojoParserList = new ArrayList<>();
    List<Integer>quantityCounterPerItemList = new ArrayList<>();
    UpdateQuantityInterface updateQuantityInterface;

    public RecyclerAdapterGetCart(Context context, List<GetCartPojoParser> getCartPojoParserList, UpdateQuantityInterface updateQuantityInterface) {
        this.context = context;
        this.getCartPojoParserList = getCartPojoParserList;
        this.updateQuantityInterface = updateQuantityInterface;
    }

    @NonNull
    @NotNull
    @Override
    public RecyclerAdapterGetCart.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.get_cart_items_list,
                parent, false);
        return new RecyclerAdapterGetCart.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull RecyclerAdapterGetCart.ViewHolder holder,
                                 int position) {

        holder.prodName.setText(getCartPojoParserList.get(position).getProduct().getTitle());
        Picasso.get().load(getCartPojoParserList.get(position).getProduct().getImage())
                .into(holder.prodImage);
        holder.actualPrice.setText("₹ "+getCartPojoParserList.get(position).getProduct().getPrice());
        holder.strikedPrice.setPaintFlags(holder.strikedPrice.getPaintFlags()|
                Paint.STRIKE_THRU_TEXT_FLAG);
        holder.strikedPrice.setText("₹ "+getCartPojoParserList.get(position).getProduct().getAmount());
        holder.qtyCounter.setText(""+getCartPojoParserList.get(position).getQuantity());
        holder.offPercentage.setText(getCartPojoParserList.get(position).getProduct().getDiscount()+" %");

        holder.plusImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                updateQuantityInterface.updateQuantity(getCartPojoParserList.get(position).getId(),getCartPojoParserList.get(position).getProduct().getId(),getCartPojoParserList.get(position).getQuantity()+1);
            }
        });

        holder.minusImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                updateQuantityInterface.updateQuantity(getCartPojoParserList.get(position).getId(),getCartPojoParserList.get(position).getProduct().getId(),getCartPojoParserList.get(position).getQuantity()-1);
            }
        });

    }

    @Override
    public int getItemCount() {
        return getCartPojoParserList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView prodName,actualPrice,strikedPrice,qty,qtyCounter,offPercentage,removeFromBasket,finalAmt;
        ImageView prodImage,minusImg,plusImg;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            prodName = itemView.findViewById(R.id.prodName);
            actualPrice = itemView.findViewById(R.id.actualPrice);
            strikedPrice = itemView.findViewById(R.id.strikedPrice);
            qty = itemView.findViewById(R.id.qty);
            qtyCounter = itemView.findViewById(R.id.qtyCounter);
            offPercentage = itemView.findViewById(R.id.offPercentage);
            removeFromBasket = itemView.findViewById(R.id.removeFromBasket);
            finalAmt = itemView.findViewById(R.id.finalAmt);
            prodImage = itemView.findViewById(R.id.prodImage);
            minusImg = itemView.findViewById(R.id.minusImg);
            plusImg = itemView.findViewById(R.id.plusImg);

        }
    }
}
