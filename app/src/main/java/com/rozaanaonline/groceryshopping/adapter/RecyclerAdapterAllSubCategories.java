package com.rozaanaonline.groceryshopping.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rozaanaonline.groceryshopping.R;
import com.rozaanaonline.groceryshopping.pojo.SubCategoryDataByProductsParser;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapterAllSubCategories extends
        RecyclerView.Adapter<RecyclerAdapterAllSubCategories.ViewHolder> {

    Context context;
    List<SubCategoryDataByProductsParser> subCategoryDataParserList = new ArrayList<>();
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;


    public RecyclerAdapterAllSubCategories(Context context, List<SubCategoryDataByProductsParser>
            subCategoryDataParserList) {

        this.context = context;
        this.subCategoryDataParserList=subCategoryDataParserList;
    }

    @NonNull
    @Override
    public RecyclerAdapterAllSubCategories.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                                         int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.item_list_all_sub_categories, parent, false);
        return new RecyclerAdapterAllSubCategories.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapterAllSubCategories.ViewHolder holder,
                                 int position) {
        Log.d("adapterSubCategory",""+subCategoryDataParserList.size());
        holder.subCategoryTitle.setText(subCategoryDataParserList.get(position).getName());

        if (subCategoryDataParserList.get(position).getProducts()!=null){
            adapter = new RecyclerAdapterProducts_SubCategory(context,
                    subCategoryDataParserList.get(position).getProducts());
            holder.recyclerView.setAdapter(adapter);
        }
    }

    @Override
    public int getItemCount() {
        return subCategoryDataParserList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        RecyclerView recyclerView;
        TextView subCategoryTitle;
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            subCategoryTitle = itemView.findViewById(R.id.subCategoryTitle);
            imageView = itemView.findViewById(R.id.imageView);

            recyclerView = itemView.findViewById(R.id.recyclerViewProducts);
            layoutManager = new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,
                    false);
            recyclerView.setLayoutManager(layoutManager);
        }
    }
}
