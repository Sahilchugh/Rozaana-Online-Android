package com.rozaanaonline.groceryshopping.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.rozaanaonline.groceryshopping.R;
import com.rozaanaonline.groceryshopping.networkingStructure.BaseUrl;
import com.rozaanaonline.groceryshopping.pojo.ProductDetailDataParser;
import com.rozaanaonline.groceryshopping.pojo.SliderPojo;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProductDetailPagerAdapter extends PagerAdapter {
    private Context context;
    List<ProductDetailDataParser.Image> imageList;

    public ProductDetailPagerAdapter(Context context, List<ProductDetailDataParser.Image> imageList) {
        this.context = context;
        this.imageList = imageList;
    }

    @Override
    public int getCount() {
        return imageList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view==o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.item_product_image, container, false);
        ImageView imageView =(ImageView)view.findViewById(R.id.imageView);
        Picasso.get().load(new BaseUrl().BASE_URL_MEDIA+imageList.get(position).getImage()).into(imageView);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        ViewPager viewPager=(ViewPager) container;
        View view=(View) object;
    }
}

