package com.rozaanaonline.groceryshopping.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.rozaanaonline.groceryshopping.R;
import com.rozaanaonline.groceryshopping.homeStructure.GettingIdForSelectedAddress;
import com.rozaanaonline.groceryshopping.pojo.AddressPojoParser;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapterManageAddress extends RecyclerView.Adapter<RecyclerAdapterManageAddress.ViewHolder>{

    Context context;
    List<AddressPojoParser> addressPojoList = new ArrayList<>();

    public RecyclerAdapterManageAddress(Context context,
                                  List<AddressPojoParser> addressPojoList) {
        this.context = context;
        this.addressPojoList = addressPojoList;
    }

    @NonNull
    @NotNull
    @Override
    public RecyclerAdapterManageAddress.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent,
                                                                int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.address_manage_item_list, parent, false);        //myorders_list
        return new RecyclerAdapterManageAddress.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull RecyclerAdapterManageAddress.ViewHolder holder, int position) {


        holder.name.setText(addressPojoList.get(position).getFirstName()+" "+addressPojoList.get(position).getLastName());
        holder.phoneNumber.setText(addressPojoList.get(position).getTelephone());
        holder.address.setText(addressPojoList.get(position).getApartment());
        holder.pincode.setText(addressPojoList.get(position).getPostcode());
        holder.landmark.setText(addressPojoList.get(position).getLandmark());


    }

    @Override
    public int getItemCount() {
        return addressPojoList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        CardView cardViewAddress;
        RadioButton radioBtn;
        Button editAddressBtn,deleteAddress;
        TextView name,address,pincode,phoneNumber,landmark;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            cardViewAddress = itemView.findViewById(R.id.addressCard);
            radioBtn = itemView.findViewById(R.id.radioBtn);
            name = itemView.findViewById(R.id.name);
            phoneNumber = itemView.findViewById(R.id.phoneNumber);
            address = itemView.findViewById(R.id.address);
            pincode = itemView.findViewById(R.id.pincode);
            editAddressBtn = itemView.findViewById(R.id.editAddressBtn);
            deleteAddress = itemView.findViewById(R.id.deleteAddressBtn);
            landmark = itemView.findViewById(R.id.landmark);

        }
    }
}
