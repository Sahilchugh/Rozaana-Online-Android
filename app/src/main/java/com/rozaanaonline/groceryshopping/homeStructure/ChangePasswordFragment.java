package com.rozaanaonline.groceryshopping.homeStructure;

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

import com.rozaanaonline.groceryshopping.R;
import com.rozaanaonline.groceryshopping.networkingStructure.NetworkingCalls;
import com.rozaanaonline.groceryshopping.networkingStructure.NetworkingInterface;

public class ChangePasswordFragment extends Fragment implements NetworkingInterface {
    private Button confirmBtn;

    private String mobile, otp;
    private EditText newPasswordInput, confirmPasswodInput;

    NetworkingCalls networkingCalls;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mobile = getArguments().getString("mobile");
            otp = getArguments().getString("otp");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_change_password, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        init(view);
        networkingCalls = new NetworkingCalls(getContext(), getActivity(), this);

        confirmBtn.setOnClickListener(v -> {
            if (checkInput()){
                networkingCalls.createNewPassword(mobile, otp, newPasswordInput.getText().toString());
            }
        });
    }

    private boolean checkInput() {
        if (newPasswordInput.length() <8){
            newPasswordInput.setError("The password must be of atleast 8 characters");
            return false;
        }
        if (!newPasswordInput.getText().toString().equalsIgnoreCase(confirmPasswodInput.getText()
                .toString())){
            Toast.makeText(getContext(), "Please enter the same password in both fields",
                    Toast.LENGTH_SHORT).show();
        }
        return true;
    }

    private void init(View view) {
        newPasswordInput = view.findViewById(R.id.newPasswordInput);
        confirmPasswodInput = view.findViewById(R.id.confirmPasswordInput);
        confirmBtn = view.findViewById(R.id.confirmBtn);
    }

    @Override
    public <T> void networkingRequestPerformed(@Nullable MethodType type, boolean status,
                                               @Nullable T error, Object o) {
        if (type==MethodType.createPassword && status){
            Toast.makeText(getContext(), "Password Changed Succesfully", Toast.LENGTH_SHORT).show();

            Navigation.findNavController(getView()).navigate(R.id.action_changePasswordFragment_to_loginFragment);
        } else if (type==MethodType.createPassword && !status)
        {
            Toast.makeText(getContext(), "Something Went Wrong", Toast.LENGTH_SHORT).show();

        }
    }
}