package com.rozaanaonline.groceryshopping.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rozaanaonline.groceryshopping.R;
import com.rozaanaonline.groceryshopping.pojo.Category_SubCat_SubSubCat_PojoParser;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapterSubSubCategory extends
        RecyclerView.Adapter<RecyclerAdapterSubSubCategory.ViewHolder> {

    Context context;
    List<Category_SubCat_SubSubCat_PojoParser.Child.Child_1>
            subSubCategoryPojoList = new ArrayList<>();

    public RecyclerAdapterSubSubCategory(Context context,
                                         List<Category_SubCat_SubSubCat_PojoParser.Child.Child_1>
                                                 subSubCategoryPojoList) {
        this.context = context;
        this.subSubCategoryPojoList = subSubCategoryPojoList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sub_sub_category_list,
                parent, false);
        return new RecyclerAdapterSubSubCategory.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.subSubCategoryTitle.setText(subSubCategoryPojoList.get(position).getName());
        Picasso.get().load(subSubCategoryPojoList.get(position).getImage()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return subSubCategoryPojoList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView subSubCategoryTitle;
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            subSubCategoryTitle = itemView.findViewById(R.id.sub_sub_category);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}
