package com.rozaanaonline.groceryshopping.homeStructure;

import android.app.ProgressDialog;
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
import android.widget.TextView;
import android.widget.Toast;

import com.rozaanaonline.groceryshopping.R;
import com.rozaanaonline.groceryshopping.adapter.RecyclerAdapterGetCart;
import com.rozaanaonline.groceryshopping.networkingStructure.NetworkingCalls;
import com.rozaanaonline.groceryshopping.networkingStructure.NetworkingInterface;
import com.rozaanaonline.groceryshopping.pojo.GetCartPojoParser;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class GetCartFragment extends Fragment implements NetworkingInterface,UpdateQuantityInterface {

    RecyclerView recyclerViewCartOrders;
    RecyclerView.LayoutManager layoutManager;
    NetworkingCalls networkingCalls;
    TextView actualPrice,actualTotalPrice;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_get_cart, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initView(view);
        networkingCalls = new NetworkingCalls(requireContext(),requireActivity(),this);
        networkingCalls.getCartProducts();
    }

    private void initView(View view) {

        recyclerViewCartOrders = view.findViewById(R.id.recyclerViewCartOrders);
        actualPrice = view.findViewById(R.id.actualPrice);
        actualTotalPrice = view.findViewById(R.id.actualTotalPrice);
        layoutManager = new LinearLayoutManager(requireContext());
        recyclerViewCartOrders.setLayoutManager(layoutManager);
    }

    @Override
    public <T> void networkingRequestPerformed(@Nullable  MethodType type, boolean status, @Nullable  T error, Object o) {

        if (type == MethodType.allCartProducts && status){
            RecyclerAdapterGetCart recyclerAdapterGetCart = new RecyclerAdapterGetCart(requireContext(), (List<GetCartPojoParser>) o,this);
            recyclerViewCartOrders.setAdapter(recyclerAdapterGetCart);
            recyclerAdapterGetCart.notifyDataSetChanged();
        }
        else if (type == MethodType.allCartProducts && !status){
            Toast.makeText(getContext(), ""+error, Toast.LENGTH_SHORT).show();
        }
        else if (type == MethodType.updateCart && status){
            networkingCalls.getCartProducts();
        }
        else if (type == MethodType.updateCart && !status){
            Toast.makeText(getContext(), ""+error, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void updateQuantity(int cartItemId, int productId, int quantity) {

        networkingCalls.updateCartQuantity(cartItemId,productId,quantity);
    }
}