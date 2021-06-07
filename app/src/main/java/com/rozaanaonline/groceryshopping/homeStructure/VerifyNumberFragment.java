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
import android.widget.TextView;
import android.widget.Toast;

import com.rozaanaonline.groceryshopping.R;
import com.rozaanaonline.groceryshopping.networkingStructure.NetworkingCalls;
import com.rozaanaonline.groceryshopping.networkingStructure.NetworkingInterface;

import in.aabhasjindal.otptextview.OTPListener;
import in.aabhasjindal.otptextview.OtpTextView;

public class VerifyNumberFragment extends Fragment implements NetworkingInterface {
    private Button verifyBtn;
    private OtpTextView otpTextView;
    NetworkingCalls networkingCalls;
    private TextView numberText;
    private String mobile, otp;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mobile = getArguments().getString("mobile");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_verify_number, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        init(view);
        numberText.append(mobile);
        networkingCalls = new NetworkingCalls(getContext(), getActivity(), this);

        otpTextView.setOtpListener(new OTPListener() {
            @Override
            public void onInteractionListener() {
                // fired when user types something in the Otpbox
            }
            @Override
            public void onOTPComplete(String otpEntry) {
                otp = otpEntry;
                networkingCalls.validateOtp(mobile, otp);
            }
        });
    }

    private void init(View view) {

        otpTextView = view.findViewById(R.id.otpView);
        numberText = view.findViewById(R.id.numberText);

    }

    @Override
    public <T> void networkingRequestPerformed(@Nullable MethodType type, boolean status,
                                               @Nullable T error, Object o) {

        if (type==MethodType.validateForgotOtp && status){
            Toast.makeText(getContext(), "Success", Toast.LENGTH_SHORT).show();
            Bundle bundle = new Bundle();
            bundle.putString("mobile",mobile);
            bundle.putString("otp",otp);

            Navigation.findNavController(getView())
                    .navigate(R.id.action_verifyNumberFragment_to_changePasswordFragment, bundle);

        } else if (type==MethodType.validateForgotOtp && !status){
            Toast.makeText(getContext(), "Something Went Wrong", Toast.LENGTH_SHORT).show();

        }
    }
}