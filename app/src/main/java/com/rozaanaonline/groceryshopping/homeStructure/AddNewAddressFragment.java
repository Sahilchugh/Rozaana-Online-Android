package com.rozaanaonline.groceryshopping.homeStructure;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.rozaanaonline.groceryshopping.R;
import com.rozaanaonline.groceryshopping.networkingStructure.NetworkingCalls;
import com.rozaanaonline.groceryshopping.networkingStructure.NetworkingInterface;

import org.jetbrains.annotations.NotNull;


public class AddNewAddressFragment extends Fragment implements NetworkingInterface {

    EditText enterName,enterPhone,enterState,enterPassword,enterCity,enterPinCode,
            enterAddress,enterLandmark,enterLastName;
    CheckBox defaultAddressCheck;
    Button btnSubmit;
    TextView applyText;
    Boolean isAddressChecked = false;
    NetworkingCalls networkingCalls;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_new_address, container, false);
    }

    @Override
    public void onViewCreated(@NotNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initViews(view);
        networkingCalls = new NetworkingCalls(getContext(),getActivity(),this);


        applyText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(applyText.getText().toString().equalsIgnoreCase(APPLY)){
                    networkingCalls.verifyPincode(enterPinCode.getText().toString());
                }
                else if (applyText.getText().toString().equalsIgnoreCase(CHANGE)){
                    enterPinCode.setEnabled(true);
                    enterPinCode.setText("");
                    applyText.setText(APPLY);
                }
            }
        });

        btnSubmit.setOnClickListener(v -> {

            if (checkInput()){
                networkingCalls.addAddress(enterName.getText().toString(),
                        enterLastName.getText().toString(),enterPhone.getText().toString(),
                        enterState.getText().toString(),enterCity.getText().toString(),
                        enterPinCode.getText().toString(),enterAddress.getText().toString(),
                        enterLandmark.getText().toString(),isAddressChecked.toString());
            }
        });

    }

    private void initViews(View view){

        enterName = view.findViewById(R.id.enterName);
        enterPhone = view.findViewById(R.id.enterPhone);
        enterState = view.findViewById(R.id.enterState);
        enterPassword = view.findViewById(R.id.enterPassword);
        enterCity = view.findViewById(R.id.enterCity);
        enterPinCode = view.findViewById(R.id.enterPinCode);
        applyText = view.findViewById(R.id.applyText);
        enterAddress = view.findViewById(R.id.enterAddress);
        enterLandmark = view.findViewById(R.id.enterLandmark);
        enterLastName = view.findViewById(R.id.enterLastName);
        defaultAddressCheck = view.findViewById(R.id.defaultAddressCheck);
        btnSubmit = view.findViewById(R.id.btnSubmit);

        enterState.setEnabled(false);
        enterCity.setEnabled(false);


        defaultAddressCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked){
                    isAddressChecked = true;
                }
                else {
                    isAddressChecked = false;
                }
            }
        });
        Log.e("isAddressChecked", ""+isAddressChecked);
    }

    private boolean checkInput(){

        if (enterName.getText().toString().isEmpty()){
            Toast.makeText(getContext(), "enter name!", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (enterLastName.getText().toString().isEmpty()){
            Toast.makeText(getContext(), "enter lastname!", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (enterPhone.getText().toString().isEmpty()){
            Toast.makeText(getContext(), "enter phone!", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (enterPinCode.getText().toString().isEmpty()){
            Toast.makeText(getContext(), "enter pin code", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (enterAddress.getText().toString().isEmpty()){
            Toast.makeText(getContext(), "enter address", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (enterLandmark.getText().toString().isEmpty()){
            Toast.makeText(getContext(), "enter landmark", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (applyText.getText().toString().equalsIgnoreCase(APPLY)){
            Toast.makeText(getContext(), "Please verify your pincode",
                    Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    @Override
    public <T> void networkingRequestPerformed(@Nullable MethodType type, boolean status,
                                               @Nullable T error, Object o) {

        if (type==MethodType.verifyPincode && status){
            applyText.setText(CHANGE);
            enterPinCode.setEnabled(false);
        }

        if (type==MethodType.addAddress && status){
            getActivity().onBackPressed();

        }
    }
    private final String CHANGE = "Change";
    private final String APPLY = "Apply";
}