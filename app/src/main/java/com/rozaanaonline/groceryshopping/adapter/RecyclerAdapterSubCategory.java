package com.rozaanaonline.groceryshopping.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rozaanaonline.groceryshopping.R;
import com.rozaanaonline.groceryshopping.pojo.Category_SubCat_SubSubCat_PojoParser;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapterSubCategory  extends
        RecyclerView.Adapter<RecyclerAdapterSubCategory.ViewHolder> {

    Context context;
    List<Category_SubCat_SubSubCat_PojoParser.Child> subCategoryPojoList = new ArrayList<>();
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;

    boolean cardIsOpen = true;

    public RecyclerAdapterSubCategory(Context context,
                                      List<Category_SubCat_SubSubCat_PojoParser.Child>
                                              subCategoryPojoList) {
        this.context = context;
        this.subCategoryPojoList=subCategoryPojoList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sub_category_list,
                parent, false);
        return new RecyclerAdapterSubCategory.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d("adapterSubCategory",""+subCategoryPojoList.size());
        holder.subCategoryTitle.setText(subCategoryPojoList.get(position).getName());
        Picasso.get().load(subCategoryPojoList.get(position).getImage()).into(holder.imageView);

        if (subCategoryPojoList.get(position).getChildren()!=null){

            adapter = new RecyclerAdapterSubSubCategory(context,subCategoryPojoList.get(position).getChildren());
            holder.recyclerView.setAdapter(adapter);
        }
        else{
            holder.arrowLayout.setVisibility(View.GONE);
            holder.view1.setVisibility(View.GONE);
        }

        if (cardIsOpen){
            holder.recyclerView.setVisibility(View.GONE);
            holder.view1.setVisibility(View.GONE);
        }

        holder.arrowLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (cardIsOpen){
                    holder.downArrow.setVisibility(View.VISIBLE);
                    holder.upArrow.setVisibility(View.GONE);
                    holder.recyclerView.setVisibility(View.GONE);       // close card
                    holder.view1.setVisibility(View.GONE);
                    cardIsOpen = false;
                }else {
                    holder.downArrow.setVisibility(View.GONE);
                    holder.upArrow.setVisibility(View.VISIBLE);
                    holder.recyclerView.setVisibility(View.VISIBLE);
                    holder.view1.setVisibility(View.VISIBLE);
                    cardIsOpen = true;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return subCategoryPojoList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        RecyclerView recyclerView;
        TextView subCategoryTitle;
        ImageView imageView,downArrow,upArrow;
        LinearLayout arrowLayout;
        View view1;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            subCategoryTitle = itemView.findViewById(R.id.subCategoryTitle);
            imageView = itemView.findViewById(R.id.imageView);

            recyclerView = itemView.findViewById(R.id.recyclerView3);
            layoutManager = new LinearLayoutManager(context);
            recyclerView.setLayoutManager(layoutManager);
            downArrow = itemView.findViewById(R.id.downArrow);
            view1 = itemView.findViewById(R.id.view1);
            arrowLayout = itemView.findViewById(R.id.arrowLayout);
            upArrow = itemView.findViewById(R.id.upArrow);
        }
    }

}