package com.rozaanaonline.groceryshopping.entryStructure;

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
import android.widget.TextView;
import android.widget.Toast;

import com.rozaanaonline.groceryshopping.HomeActivity;
import com.rozaanaonline.groceryshopping.R;
import com.rozaanaonline.groceryshopping.networkingStructure.NetworkingCalls;
import com.rozaanaonline.groceryshopping.networkingStructure.NetworkingInterface;


public class LoginFragment extends Fragment implements NetworkingInterface {

    TextView signupText, forgotPasswordText;
    NetworkingCalls networkingCalls;
    EditText nameInput, passwordInput;
    Button loginBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        signupText = view.findViewById(R.id.signupText);
        forgotPasswordText = view.findViewById(R.id.forgotText);
        nameInput = view.findViewById(R.id.nameInput);
        passwordInput = view.findViewById(R.id.passwordInput);
        loginBtn = view.findViewById(R.id.loginBtn);

        networkingCalls = new NetworkingCalls(getContext(),getActivity(),this);

        loginBtn.setOnClickListener(v -> {
            if (checkInput()){
                networkingCalls.login(nameInput.getText().toString(),
                        passwordInput.getText().toString());
            }
        });

        forgotPasswordText.setOnClickListener(v -> Navigation.findNavController(v)
                .navigate(R.id.action_loginFragment_to_forgotPasswordFragment));
        signupText.setOnClickListener(v -> Navigation.findNavController(v)
                .navigate(R.id.action_loginFragment_to_signupFragment));
    }

    private boolean checkInput() {
        if (nameInput.length() == 0){
            nameInput.setError("Enter the details");
            return false;
        }

        if (passwordInput.length() == 0){
            passwordInput.setError("Enter password");
            return false;
        }

        return true;
    }

    @Override
    public <T> void networkingRequestPerformed(@Nullable MethodType type, boolean status,
                                               @Nullable T error, Object o) {

        if (type==MethodType.login && status){
            Toast.makeText(getContext(), "Success", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getContext(), HomeActivity.class));
            getActivity().finish();
        }
    }
}