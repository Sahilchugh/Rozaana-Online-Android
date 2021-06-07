package com.rozaanaonline.groceryshopping.homeStructure;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rozaanaonline.groceryshopping.R;
import com.rozaanaonline.groceryshopping.adapter.RecyclerAdapterAllCategory;
import com.rozaanaonline.groceryshopping.adapter.RecyclerAdapterAllSubCategories;
import com.rozaanaonline.groceryshopping.networkingStructure.NetworkingCalls;
import com.rozaanaonline.groceryshopping.networkingStructure.NetworkingInterface;
import com.rozaanaonline.groceryshopping.pojo.AllCategoriesPojo;
import com.rozaanaonline.groceryshopping.pojo.SubCategoryDataByProductsParser;

import java.util.List;

public class ViewCategoryFragment extends Fragment implements NetworkingInterface {

    NetworkingCalls networkingCalls;
    RecyclerView recyclerView,recyclerViewSubCategory;
    RecyclerView.LayoutManager layoutManager,layoutManagerSubCat;
    RecyclerView.Adapter adapter,adapterSubCat;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_view_category, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        networkingCalls = new NetworkingCalls(getContext(),getActivity(),this);
        recyclerView = view.findViewById(R.id.categoriesRecyclerView);
        recyclerViewSubCategory = view.findViewById(R.id.recyclerViewSubCategory);

        layoutManager = new LinearLayoutManager(getContext(),
                LinearLayoutManager.HORIZONTAL, false);

        recyclerView.setLayoutManager(layoutManager);

        layoutManagerSubCat = new LinearLayoutManager(getContext());
        recyclerViewSubCategory.setLayoutManager(layoutManagerSubCat);

        networkingCalls.getAllCategories();

    }

    @Override
    public <T> void networkingRequestPerformed(@Nullable MethodType type, boolean status,
                                               @Nullable  T error, Object o) {

        if (type==MethodType.allCategories && status){
            adapter = new RecyclerAdapterAllCategory((List<AllCategoriesPojo>)o,getContext());
            recyclerView.setAdapter(adapter);
            networkingCalls.getProductsAccordingToSubCategory();

        }
        else if (type==MethodType.allSubCategories && status){
            adapterSubCat = new RecyclerAdapterAllSubCategories(getContext(), (List<SubCategoryDataByProductsParser>) o);
            recyclerViewSubCategory.setAdapter(adapterSubCat);
        }
    }
}