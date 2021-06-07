package com.rozaanaonline.groceryshopping.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.rozaanaonline.groceryshopping.R;
import com.rozaanaonline.groceryshopping.pojo.AllCategoriesPojo;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapterAllCategory  extends
        RecyclerView.Adapter<RecyclerAdapterAllCategory.ViewHolder> {

    List<AllCategoriesPojo> allCategoriesPojoList = new ArrayList<>();
    Context context;

    public RecyclerAdapterAllCategory(List<AllCategoriesPojo> allCategoriesPojoList,
                                      Context context) {
        this.allCategoriesPojoList = allCategoriesPojoList;
        this.context = context;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item_list,
                parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {

        holder.categoryTitle.setText(allCategoriesPojoList.get(position).getName());
        Picasso.get().load(allCategoriesPojoList.get(position).getImage())
                .into(holder.categoryImage);
    }

    @Override
    public int getItemCount() {
        return allCategoriesPojoList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView categoryTitle;
        ImageView categoryImage;
        CardView cardViewCat;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            categoryTitle = itemView.findViewById(R.id.categoryTitle);
            categoryImage = itemView.findViewById(R.id.categoryImage);
            cardViewCat = itemView.findViewById(R.id.cardViewCat);
        }
    }
}
