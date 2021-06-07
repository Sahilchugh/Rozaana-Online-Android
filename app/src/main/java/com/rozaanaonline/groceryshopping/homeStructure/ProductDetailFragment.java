package com.rozaanaonline.groceryshopping.homeStructure;

import android.graphics.Paint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.rozaanaonline.groceryshopping.R;
import com.rozaanaonline.groceryshopping.adapter.ProductDetailPagerAdapter;
import com.rozaanaonline.groceryshopping.adapter.RecyclerAdapterQuantity;
import com.rozaanaonline.groceryshopping.adapter.RecyclerAdapterTopProducts;
import com.rozaanaonline.groceryshopping.adapter.ViewPagerAdapter;
import com.rozaanaonline.groceryshopping.networkingStructure.NetworkingCalls;
import com.rozaanaonline.groceryshopping.networkingStructure.NetworkingInterface;
import com.rozaanaonline.groceryshopping.pojo.ProductDetailDataParser;
import com.rozaanaonline.groceryshopping.pojo.SliderPojo;
import com.rozaanaonline.groceryshopping.pojo.TopProductParser;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;


public class ProductDetailFragment extends Fragment implements NetworkingInterface {

    private ViewPager imagesPager;
    private TabLayout imagesTab;
    private TextView productNameText, mrpText, priceText, brandText, shelfLifeText, aboutProductText, moreProductsText, recentViewedText;
    private RecyclerView quantityRecyclerview, moreProductsRecyclerview, recentProductRecyclerview;
    private NetworkingCalls networkingCalls;
    private int id;
    ProductDetailDataParser productDetailDataParser;
    List<ProductDetailDataParser.Image> imageList;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            id = getArguments().getInt("variantId");

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_product_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        init(view);

        networkingCalls = new NetworkingCalls(getContext(), getActivity(), this);
        imagesTab.setupWithViewPager(imagesPager, true);
        quantityRecyclerview.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        moreProductsRecyclerview.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        recentProductRecyclerview.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));

        imageList = new ArrayList<>();

        networkingCalls.getProductDetails(id);


    }

    private void init(View view) {
        imagesPager = view.findViewById(R.id.imagesPager);
        imagesTab = view.findViewById(R.id.imagesPagerTab);
        productNameText = view.findViewById(R.id.productNameText);
        mrpText = view.findViewById(R.id.mrpText);
        priceText = view.findViewById(R.id.priceText);
        brandText = view.findViewById(R.id.brandNameText);
        shelfLifeText = view.findViewById(R.id.bestBeforeValueText);
        aboutProductText = view.findViewById(R.id.aboutProductValueText);
        moreProductsText = view.findViewById(R.id.moreProductsText);
        recentViewedText = view.findViewById(R.id.recentlyProductsText);
        quantityRecyclerview = view.findViewById(R.id.quantityRecyclerview);
        moreProductsRecyclerview = view.findViewById(R.id.moreProductsRecyclerview);
        recentProductRecyclerview = view.findViewById(R.id.recentlyProductsRecyclerview);
    }

    @Override
    public <T> void networkingRequestPerformed(@Nullable @org.jetbrains.annotations.Nullable MethodType type, boolean status, @Nullable @org.jetbrains.annotations.Nullable T error, Object o) {

        if (type == MethodType.fetchProductDetail && status){

            productDetailDataParser = (ProductDetailDataParser) o;
            imageList.addAll(productDetailDataParser.getImages());

            ProductDetailPagerAdapter productDetailPagerAdapter = new ProductDetailPagerAdapter(getContext(), imageList);
            imagesPager.setAdapter(productDetailPagerAdapter);

            productNameText.setText(productDetailDataParser.getTitle());
            mrpText.setPaintFlags(mrpText.getPaintFlags()| Paint.STRIKE_THRU_TEXT_FLAG);
            mrpText.setText(productDetailDataParser.getAmount());
            priceText.setText(productDetailDataParser.getPrice());
            aboutProductText.setText(productDetailDataParser.getParentProduct().getDescription());
            brandText.setText(productDetailDataParser.getParentProduct().getManufacturedBy());
            shelfLifeText.setText(productDetailDataParser.getShelf_life());

            RecyclerAdapterQuantity recyclerAdapterQuantity = new RecyclerAdapterQuantity(getContext(), productDetailDataParser.getVariants());
            quantityRecyclerview.setAdapter(recyclerAdapterQuantity);


        } else if (type == MethodType.sliderBanners && !status){
            Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
        }
    }
}