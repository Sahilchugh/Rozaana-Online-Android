package com.rozaanaonline.groceryshopping.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.rozaanaonline.groceryshopping.R;
import com.rozaanaonline.groceryshopping.pojo.DealsDataParser;
import com.rozaanaonline.groceryshopping.pojo.TopProductParser;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapterSuperDeals extends
        RecyclerView.Adapter<RecyclerAdapterSuperDeals.ViewHolder>{

    Context context;
    List<DealsDataParser> dealsDataParserList = new ArrayList<>();
    public RecyclerAdapterSuperDeals(Context context,
                                     List<DealsDataParser> dealsDataParserList) {
        this.context = context;
        this.dealsDataParserList = dealsDataParserList;
    }

    @NonNull
    @NotNull
    @Override
    public RecyclerAdapterSuperDeals.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent,
                false);
        return new RecyclerAdapterSuperDeals.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull RecyclerAdapterSuperDeals.ViewHolder holder, int position) {

        holder.productTitle.setText(dealsDataParserList.get(position).getTitle());
      /*  Picasso.get().load(productArrayList.get(position).getImage())
                .into(holder.imageView);*/
        holder.actualPrice.setText("₹ "+dealsDataParserList.get(position).getPrice());
        holder.strikedPrice.setPaintFlags(holder.strikedPrice.getPaintFlags()|
                Paint.STRIKE_THRU_TEXT_FLAG);
        holder.strikedPrice.setText("₹ "+dealsDataParserList.get(position).getAmount());

        holder.qty.setText(""+dealsDataParserList.get(position).getQuantity());

        if (dealsDataParserList.get(position).getrCoins()!=0){
            holder.rCoins.setText("You will earn "+dealsDataParserList.get(position).getrCoins()+" R coins");
        }
        else
            holder.rCoins.setVisibility(View.GONE);

        holder.itemLayout.setOnClickListener(v ->{
            Bundle bundle = new Bundle();
            bundle.putInt("variantId", dealsDataParserList.get(position).getId());
            Navigation.findNavController(v).navigate(R.id.action_nav_dashboard_to_nav_product_detail, bundle);
        });
    }

    @Override
    public int getItemCount() {
        return dealsDataParserList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView productTitle,qty,actualPrice,strikedPrice,discount,rCoins;
        ImageView imageView;
        CardView itemLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            productTitle = itemView.findViewById(R.id.title);
            qty = itemView.findViewById(R.id.qty);
            imageView = itemView.findViewById(R.id.prodImage);
            actualPrice = itemView.findViewById(R.id.actualPrice);
            strikedPrice =itemView.findViewById(R.id.strikedPrice);
            discount =itemView.findViewById(R.id.discount);
            rCoins = itemView.findViewById(R.id.rCoins);
            itemLayout = itemView.findViewById(R.id.itemLayout);
        }
    }
}
