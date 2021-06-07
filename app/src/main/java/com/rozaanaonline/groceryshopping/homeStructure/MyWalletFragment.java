package com.rozaanaonline.groceryshopping.homeStructure;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.rozaanaonline.groceryshopping.R;

import org.jetbrains.annotations.NotNull;


public class MyWalletFragment extends Fragment {

    ImageView infoImg;
    AlertDialog alertDialog;
    TextView sendMoneyToUser;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_wallet, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        inintView(view);

        infoImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                alertDialog = new AlertDialog.Builder(getActivity()).create();
                alertDialog.setTitle("R Coins Information");
                final TextView message = new TextView(getContext());
                FrameLayout container = new FrameLayout(getContext());
                FrameLayout.LayoutParams params = new  FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                params.setMargins(20,10,10,10);
                params.leftMargin = 50;
                message.setLayoutParams(params);
                container.addView(message);
                message.setTextColor(getResources().getColor(R.color.black));
                String s = "Empty Dialog!";
                message.setText(s);
                alertDialog.setView(container);
                alertDialog.show();
            }
        });

        sendMoneyToUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_nav_my_wallet_to_sendMoneyFragment);
            }
        });
    }


    private void inintView(View view) {

        infoImg = view.findViewById(R.id.infoImg);
        sendMoneyToUser = view.findViewById(R.id.sendMoneyToUser);
    }
}