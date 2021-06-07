package com.rozaanaonline.groceryshopping.entryStructure;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.rozaanaonline.groceryshopping.R;
import com.rozaanaonline.groceryshopping.networkingStructure.NetworkingCalls;
import com.rozaanaonline.groceryshopping.networkingStructure.NetworkingInterface;

public class SignupFragment extends Fragment implements NetworkingInterface {

    AppCompatTextView alreadyMemberTxt;
    TextView referralCodeTxt;
    EditText enterFirstName,enterLastName,enterEmail, enterMobileNumber,enterPassword,
            enterConfirmPassword,enterCouponCode;
    Button btnSignup;
    CheckBox termscheck;
    NetworkingCalls networkingCalls;
    ConstraintLayout referralCodeConstraint;
    View view1;
    LinearLayout acc;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_signup, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        acc = view.findViewById(R.id.acc);
        enterFirstName = view.findViewById(R.id.enterName);
        enterLastName = view.findViewById(R.id.enterLastName);
        enterEmail = view.findViewById(R.id.enterEmail);
        enterMobileNumber = view.findViewById(R.id.enterPhone);
        enterPassword = view.findViewById(R.id.enterPassword);
        referralCodeTxt = view.findViewById(R.id.referralCodeTxt);
        enterConfirmPassword = view.findViewById(R.id.confirmPassword);
        referralCodeConstraint = view.findViewById(R.id.referralCodeConstraint);
        enterCouponCode = view.findViewById(R.id.enterCouponCode);
        btnSignup = view.findViewById(R.id.btnSignup);
        termscheck = view.findViewById(R.id.termscheck);
        networkingCalls = new NetworkingCalls(getContext(),getActivity(),this);
        view1 = view;

        acc.setOnClickListener(v -> Navigation.findNavController(v)
                .navigate(R.id.action_signupFragment_to_loginFragment));

        btnSignup.setOnClickListener(v -> {

            if (checkInput()){

                networkingCalls.signup(enterFirstName.getText().toString(),
                        enterLastName.getText().toString(),enterEmail.getText().toString(),
                        enterPassword.getText().toString(), enterMobileNumber.getText().toString());
            }
        });

        referralCodeTxt.setOnClickListener(v -> {
            referralCodeConstraint.setVisibility(View.VISIBLE);
            referralCodeTxt.setVisibility(View.GONE);

        });
    }

    public boolean checkInput(){

        if (enterFirstName.getText().toString().isEmpty()){
            Toast.makeText(getContext(), "enter name!", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (enterLastName.getText().toString().isEmpty()){
            Toast.makeText(getContext(), "enter lastname!", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (enterEmail.getText().toString().isEmpty()){
            Toast.makeText(getContext(), "enter email!", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (enterConfirmPassword.getText().toString().isEmpty()){
            Toast.makeText(getContext(), "enter confirm password", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (enterPassword.getText().toString().isEmpty()){
            Toast.makeText(getContext(), "enter password", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (!termscheck.isChecked()){
            Toast.makeText(getContext(), "please select the terms and conditions",
                    Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (!enterPassword.getText().toString().equalsIgnoreCase(enterConfirmPassword
                .getText().toString())){
            Toast.makeText(getActivity(), "please enter same password in both the fields",
                    Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    @Override
    public <T> void networkingRequestPerformed(@Nullable MethodType type, boolean status,
                                               @Nullable T error, Object o) {

        if (type == MethodType.signUp && status){
            Toast.makeText(getContext(), "success", Toast.LENGTH_SHORT).show();
            Navigation.findNavController(view1)
                    .navigate(R.id.action_signupFragment_to_verifyNumberFragment);
        }
    }
}