package com.rozaanaonline.groceryshopping.homeStructure;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.rozaanaonline.groceryshopping.R;
import com.rozaanaonline.groceryshopping.adapter.RecyclerAdapterAddress;
import com.rozaanaonline.groceryshopping.networkingStructure.NetworkingCalls;
import com.rozaanaonline.groceryshopping.networkingStructure.NetworkingInterface;
import com.rozaanaonline.groceryshopping.pojo.AddressPojoParser;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class GettingAddressFragment extends Fragment implements GettingIdForSelectedAddress, NetworkingInterface {

    RecyclerView recyclerViewAddress;
    RecyclerView.LayoutManager layoutManager;
    NetworkingCalls networkingCalls;
    TextView addNewAddress;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_getting_address, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initView(view);
        networkingCalls = new NetworkingCalls(getContext(),getActivity(), this);
        networkingCalls.getAddress();

        addNewAddress.setOnClickListener(v -> Navigation.findNavController(v).navigate(R.id.action_nav_offer_to_nav_add_address));
    }

    private void initView(View view) {
        recyclerViewAddress = view.findViewById(R.id.recyclerViewAddress);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerViewAddress.setLayoutManager(layoutManager);
        addNewAddress = view.findViewById(R.id.addNewAddress);
    }

    @Override
    public void getIdForSelectedAddressFromRadioBtn(String address_id) {
        Log.e("GettingAddressFragment", "getIdForSelectedAddressFromRadioBtn "+address_id);
    }

    @Override
    public <T> void networkingRequestPerformed(@Nullable MethodType type, boolean status, @Nullable T error, Object o) {

        if (type==MethodType.allAddress && status){
            RecyclerAdapterAddress recyclerAdapterAddress = new RecyclerAdapterAddress(requireContext(),
                    (List<AddressPojoParser>) o, requireActivity(), (GettingIdForSelectedAddress) this);

            recyclerViewAddress.setAdapter(recyclerAdapterAddress);
        }
        else {
            Toast.makeText(requireContext(), ""+error, Toast.LENGTH_SHORT).show();
        }
    }
}