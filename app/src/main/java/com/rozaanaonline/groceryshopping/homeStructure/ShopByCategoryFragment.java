package com.rozaanaonline.groceryshopping.homeStructure;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import com.rozaanaonline.groceryshopping.R;
import com.rozaanaonline.groceryshopping.adapter.RecyclerAdapterCategory;
import com.rozaanaonline.groceryshopping.networkingStructure.NetworkingCalls;
import com.rozaanaonline.groceryshopping.networkingStructure.NetworkingInterface;
import com.rozaanaonline.groceryshopping.pojo.Category_SubCat_SubSubCat_PojoParser;

import java.util.HashMap;
import java.util.List;


public class ShopByCategoryFragment extends Fragment implements NetworkingInterface {

    ExpandableListView expandableListView;
    ExpandableListAdapter expandableListAdapter;
    List<String> expandableListTitle;
    HashMap<String, List<String>> expandableListDetail;
    NetworkingCalls networkingCalls;

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter<RecyclerAdapterCategory.ViewHolder> adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_shop_by_category, container,
                false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Log.e("ExploreAllCategoryFrag", "");
        recyclerView = view.findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        networkingCalls = new NetworkingCalls(getContext(),getActivity(),this);
        networkingCalls.exploreAllCategories();

    }

    @Override
    public <T> void networkingRequestPerformed(@Nullable MethodType type,
                                               boolean status, @Nullable T error, Object o) {

        if (type==MethodType.allCategoriesSubCategories && status){

            adapter = new RecyclerAdapterCategory(getContext(), (List<Category_SubCat_SubSubCat_PojoParser>) o);
            recyclerView.setAdapter(adapter);
        }
    }
}