package com.rozaanaonline.groceryshopping.homeStructure;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.rozaanaonline.groceryshopping.R;
import com.rozaanaonline.groceryshopping.adapter.RecyclerAdapterAllCategoryHome;
import com.rozaanaonline.groceryshopping.adapter.RecyclerAdapterSuperDeals;
import com.rozaanaonline.groceryshopping.adapter.RecyclerAdapterTopProducts;
import com.rozaanaonline.groceryshopping.adapter.ViewPagerAdapter;
import com.rozaanaonline.groceryshopping.databinding.FragmentDashboardBinding;
import com.rozaanaonline.groceryshopping.networkingStructure.BaseUrl;
import com.rozaanaonline.groceryshopping.networkingStructure.NetworkingCalls;
import com.rozaanaonline.groceryshopping.networkingStructure.NetworkingInterface;
import com.rozaanaonline.groceryshopping.pojo.AllCategoriesPojo;
import com.rozaanaonline.groceryshopping.pojo.DealsDataParser;
import com.rozaanaonline.groceryshopping.pojo.SliderPojo;
import com.rozaanaonline.groceryshopping.pojo.StaticAdvertisementPojoParser;
import com.rozaanaonline.groceryshopping.pojo.TopProductParser;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DashboardFragment extends Fragment  implements NetworkingInterface {

    private FragmentDashboardBinding binding;
    RecyclerView categoryRecyclerview,topProductsRecyclerview, superDealsRecyclerview;
    List<ImageView>imageViewList = new ArrayList<>();
    ImageView [] imgViewArr;
    NetworkingCalls networkingCalls;
    List<StaticAdvertisementPojoParser> staticBannersPojoList;
    ViewPager pager1, pager2, pager3;
    TabLayout tab1, tab2, tab3;
    ProgressDialog progressDialog;
    List<SliderPojo> sliderPojoList;
    List<SliderPojo> sliderPojoListTop;
    List<SliderPojo> sliderPojoListMid;
    List<SliderPojo> sliderPojoListBottom;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setTitle("Please Wait");
        progressDialog.setMessage("Fetching Data");
        progressDialog.show();

        Collections.addAll(imageViewList, imgViewArr);
        tab1.setupWithViewPager(pager1, true);
        tab2.setupWithViewPager(pager2, true);
        tab3.setupWithViewPager(pager3, true);

        sliderPojoListTop = new ArrayList<>();
        sliderPojoListMid = new ArrayList<>();
        sliderPojoListBottom = new ArrayList<>();

        categoryRecyclerview.setLayoutManager(new GridLayoutManager(getContext(),3));
        topProductsRecyclerview.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        superDealsRecyclerview.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        networkingCalls = new NetworkingCalls(getContext(),getActivity(),this);
        networkingCalls.getStaticBanners();
    }

    private void init(View view) {

        imgViewArr = new ImageView[]{view.findViewById(R.id.image1),view.findViewById(R.id.image2),
                view.findViewById(R.id.image3), view.findViewById(R.id.image4),view.findViewById(R.id.image5),
                view.findViewById(R.id.image6),view.findViewById(R.id.image7)};

        pager1 = view.findViewById(R.id.pager1);
        pager2 = view.findViewById(R.id.pager2);
        pager3 = view.findViewById(R.id.pager3);
        tab1 = view.findViewById(R.id.tab1);
        tab2 = view.findViewById(R.id.tab2);
        tab3 = view.findViewById(R.id.tab3);
        categoryRecyclerview = view.findViewById(R.id.categoryRecyclerview);
        topProductsRecyclerview = view.findViewById(R.id.topProductsRecyclerview);
        superDealsRecyclerview = view.findViewById(R.id.superDealsRecyclerview);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public <T> void networkingRequestPerformed(@Nullable  MethodType type, boolean status,
                                               @Nullable T error, Object o) {
        if (type == MethodType.staticBanners && status){

            staticBannersPojoList = (List<StaticAdvertisementPojoParser>) o;
            for (int i = 0; i <imageViewList.size() ; i++) {
                Picasso.get().load(new BaseUrl().BASE_URL_MEDIA+staticBannersPojoList.get(i).getImage())
                        .into(imageViewList.get(i));
            }
            networkingCalls.getSliderBanners();
        } else if (type == MethodType.staticBanners && !status){
            Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
            progressDialog.dismiss();
        } else if (type == MethodType.sliderBanners && status){

            sliderPojoList = (List<SliderPojo>) o;

            for (int i =0; i<sliderPojoList.size(); i++){
                if (sliderPojoList.get(i).getPosition().equalsIgnoreCase("top")){
                    sliderPojoListTop.add(sliderPojoList.get(i));
                } else if (sliderPojoList.get(i).getPosition().equalsIgnoreCase("mid")){
                    sliderPojoListMid.add(sliderPojoList.get(i));
                } else if (sliderPojoList.get(i).getPosition().equalsIgnoreCase("bottom")){
                    sliderPojoListBottom.add(sliderPojoList.get(i));
                }
            }

            ViewPagerAdapter adapter1 = new ViewPagerAdapter(getContext(), sliderPojoListTop);
            ViewPagerAdapter adapter2 = new ViewPagerAdapter(getContext(), sliderPojoListMid);
            ViewPagerAdapter adapter3 = new ViewPagerAdapter(getContext(), sliderPojoListBottom);
            pager1.setAdapter(adapter1);
            pager2.setAdapter(adapter2);
            pager3.setAdapter(adapter3);
            networkingCalls.getAllCategories();
        } else if (type == MethodType.sliderBanners && !status){
            Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
            progressDialog.dismiss();
        } else if (type==MethodType.allCategories && status){
            RecyclerAdapterAllCategoryHome adapter = new RecyclerAdapterAllCategoryHome((List<AllCategoriesPojo>)o,getContext());
            categoryRecyclerview.setAdapter(adapter);
            networkingCalls.getTopProducts();
        } else if (type == MethodType.allCategories && !status){
            Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
            progressDialog.dismiss();
        } else if (type==MethodType.topProducts && status){
            RecyclerAdapterTopProducts adapterTopProduct = new RecyclerAdapterTopProducts(requireContext(), (List<TopProductParser>) o);
            topProductsRecyclerview.setAdapter(adapterTopProduct);
            networkingCalls.getSuperDetails();
        } else if (type == MethodType.topProducts && !status){
            Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
            progressDialog.dismiss();
        } else if (type==MethodType.fetchSuperDeals && status){
            RecyclerAdapterSuperDeals adapterSuperDeals = new RecyclerAdapterSuperDeals(requireContext(), (List<DealsDataParser>) o);
            superDealsRecyclerview.setAdapter(adapterSuperDeals);
            progressDialog.dismiss();
        } else if (type == MethodType.fetchSuperDeals && !status){
            Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
            progressDialog.dismiss();
        }
    }
}