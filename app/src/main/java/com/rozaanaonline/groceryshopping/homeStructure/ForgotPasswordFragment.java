package com.rozaanaonline.groceryshopping.homeStructure;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.rozaanaonline.groceryshopping.HomeActivity;
import com.rozaanaonline.groceryshopping.R;
import com.rozaanaonline.groceryshopping.networkingStructure.NetworkingCalls;
import com.rozaanaonline.groceryshopping.networkingStructure.NetworkingInterface;

public class ForgotPasswordFragment extends Fragment implements NetworkingInterface {
    private Button sendBtn;
    private EditText numberInput;
    private NetworkingCalls networkingCalls;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_forgot_password, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        init(view);
        networkingCalls = new NetworkingCalls(getContext(), getActivity(), this);

        sendBtn.setOnClickListener(v -> {
            if (checkInput()){
                networkingCalls.sendForgotOtp(numberInput.getText().toString());
            }
        });
    }

    private boolean checkInput() {
        if (numberInput.length()==0 || numberInput.length()!=10){
            numberInput.setError("Please enter a valid number");
            return false;
        }
        return true;
    }

    private void init(View view) {
        sendBtn = view.findViewById(R.id.sendBtn);
        numberInput = view.findViewById(R.id.numberInput);
    }

    @Override
    public <T> void networkingRequestPerformed(@Nullable MethodType type, boolean status,
                                               @Nullable T error, Object o) {

        if (type==MethodType.sendForgotOtp && status){
            Toast.makeText(getContext(), "Success", Toast.LENGTH_SHORT).show();
            Bundle bundle = new Bundle();
            bundle.putString("mobile",numberInput.getText().toString());

            Navigation.findNavController(getView())
                    .navigate(R.id.action_forgotPasswordFragment_to_verifyNumberFragment, bundle);

        } else if (type==MethodType.sendForgotOtp && !status){
            Toast.makeText(getContext(), "Something Went Wrong", Toast.LENGTH_SHORT).show();

        }
    }
}