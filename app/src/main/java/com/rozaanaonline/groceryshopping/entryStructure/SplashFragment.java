package com.rozaanaonline.groceryshopping.entryStructure;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rozaanaonline.groceryshopping.HomeActivity;
import com.rozaanaonline.groceryshopping.R;
import com.rozaanaonline.groceryshopping.sharePrefs.SharePrefs;


public class SplashFragment extends Fragment {

    SharePrefs sharePrefs;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        sharePrefs = new SharePrefs(getContext());

        new CountDownTimer(100, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                if (sharePrefs.isLoggedIn())
                {
                    if (isAdded()) {
                        startActivity(new Intent(getActivity(), HomeActivity.class));
                        getActivity().finish();
                    }
                }
                else {
                    Navigation.findNavController(view)
                            .navigate(R.id.action_splashFragment_to_loginFragment);
                }
            }
        }.start();// afterDelay will be executed after (secs*1000) milliseconds.
    }
}