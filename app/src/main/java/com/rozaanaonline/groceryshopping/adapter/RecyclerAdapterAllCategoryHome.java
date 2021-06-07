package com.rozaanaonline.groceryshopping.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.rozaanaonline.groceryshopping.R;
import com.rozaanaonline.groceryshopping.pojo.AllCategoriesPojo;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapterAllCategoryHome  extends
        RecyclerView.Adapter<RecyclerAdapterAllCategoryHome.ViewHolder> {

    List<AllCategoriesPojo> allCategoriesPojoList = new ArrayList<>();
    Context context;
    public int count = 1;

    public RecyclerAdapterAllCategoryHome(List<AllCategoriesPojo> allCategoriesPojoList,
                                      Context context) {
        this.allCategoriesPojoList = allCategoriesPojoList;
        this.context = context;
    }

    @NonNull
    @NotNull
    @Override
    public RecyclerAdapterAllCategoryHome.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item_home,
                parent, false);
        return new RecyclerAdapterAllCategoryHome.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull RecyclerAdapterAllCategoryHome.ViewHolder holder, int position) {
        holder.categoryTitle.setText(allCategoriesPojoList.get(position).getName());
        Picasso.get().load(allCategoriesPojoList.get(position).getImage())
                .into(holder.categoryImage);

        ShapeDrawable shape = new ShapeDrawable();
        if (count==1){
            holder.consBackground.setBackgroundColor(Color.parseColor("#BBe9c6c4"));
            //holder.offPercentage.setBackgroundColor(R.drawable.ellipse);
            // Create a border programmatically

            shape.getPaint().setColor(context.getResources().getColor(R.color.base_theme));
            shape.getPaint().setStyle(Paint.Style.STROKE);
            shape.getPaint().setStrokeWidth(2);
            holder.offPercentage.setBackgroundColor(context.getResources().getColor(R.color.base_theme));

            // Assign the created border to  widget
            holder.offPercentage.setBackground(shape);
            count++;
        }
        else if (count==2){
            holder.consBackground.setBackgroundColor(Color.parseColor("#BBf4ebf8"));

            shape.getPaint().setColor(context.getResources().getColor(R.color.black));
            shape.getPaint().setStyle(Paint.Style.STROKE);
            shape.getPaint().setStrokeWidth(2);

            // Assign the created border to  widget
            holder.offPercentage.setBackground(shape);
            count++;
        }
        else if (count==3){
            holder.consBackground.setBackgroundColor(Color.parseColor("#BBfff8e5"));

            shape.getPaint().setColor(Color.parseColor("#BBfff8e5"));
            shape.getPaint().setStyle(Paint.Style.STROKE);
            shape.getPaint().setStrokeWidth(1);

            // Assign the created border to  widget
            holder.offPercentage.setBackground(shape);
            count++;
        }
        else if (count==4){
            holder.consBackground.setBackgroundColor(Color.parseColor("#BBEDF7FC"));

            shape.getPaint().setColor(Color.parseColor("#BBEDF7FC"));
            shape.getPaint().setStyle(Paint.Style.STROKE);
            shape.getPaint().setStrokeWidth(1);

            // Assign the created border to  widget
            holder.offPercentage.setBackground(shape);
            count++;
        }
        else if (count==5){
            holder.consBackground.setBackgroundColor(Color.parseColor("#BB98b8ed"));

            shape.getPaint().setColor(Color.parseColor("#BB98b8ed"));
            shape.getPaint().setStyle(Paint.Style.STROKE);
            shape.getPaint().setStrokeWidth(1);

            // Assign the created border to  widget
            holder.offPercentage.setBackground(shape);
            count=1;
        }
    }

    @Override
    public int getItemCount() {
        return allCategoriesPojoList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView categoryTitle,offPercentage;
        ImageView categoryImage;
        CardView cardViewCat;
        ConstraintLayout consBackground;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            categoryTitle = itemView.findViewById(R.id.title);
            categoryImage = itemView.findViewById(R.id.prodImage);
            cardViewCat = itemView.findViewById(R.id.cardCategory);
            consBackground = itemView.findViewById(R.id.consBackground);
            offPercentage = itemView.findViewById(R.id.offPercentage);
        }
    }
}
