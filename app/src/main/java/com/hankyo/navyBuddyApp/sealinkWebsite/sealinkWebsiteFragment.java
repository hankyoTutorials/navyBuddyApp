package com.hankyo.navyBuddyApp.sealinkWebsite;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.hankyo.navyBuddyApp.R;

import java.util.zip.Inflater;

public class sealinkWebsiteFragment extends Fragment {



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.sealink_website_fragment,container,false);
        //open sealink login page in browser:
        Button loginToSealinkButton = view.findViewById(R.id.loginToSealinkButton);
        loginToSealinkButton.setOnClickListener(view1 -> {
                System.out.println("loginToSealinkButton is pressed!");
                //Intent loginToSealinkViaBrowser = new Intent(Intent.ACTION_VIEW, Uri.parse("https://sealink.navy.lk/"));
                //startActivity(loginToSealinkViaBrowser);
                Intent intent = new Intent(requireContext(), sealinkLoginOrRegisterActivity.class);
                intent.putExtra("userRequestedActivityType","login");
                startActivity(intent);
        });

        //open sealink sign up page in web browser to register an account:
        Button registerAnSealinkAccountButton = view.findViewById(R.id.registerAnSealinkAccountButton);
        registerAnSealinkAccountButton.setOnClickListener(view1 -> {
            System.out.println("registerAnSealinkAccountButton is pressed!");
            //Intent openSignupPageViaBrowser = new Intent(Intent.ACTION_VIEW, Uri.parse("https://sealink.navy.lk/signup.php"));
            //startActivity(openSignupPageViaBrowser);
            Intent intent = new Intent(requireContext(), sealinkLoginOrRegisterActivity.class);
            intent.putExtra("userRequestedActivityType","register");
            startActivity(intent);
        });


        //return inflater.inflate(R.layout.sealink_website_fragment, container, false);
        return view;
    }
}