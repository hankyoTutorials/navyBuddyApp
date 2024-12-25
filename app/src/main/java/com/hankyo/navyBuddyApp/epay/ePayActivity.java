package com.hankyo.navyBuddyApp.epay;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.hankyo.navyBuddyApp.R;

public class ePayActivity extends AppCompatActivity {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_epay);


        //display the epay page:
        WebView webView = (WebView)findViewById(R.id.epayWebViewArea);
        //WebView ePayWeb = (WebView) webView;
        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
        webView.getSettings().getCacheMode();
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("https://epay.navy.lk/RecentMonth.aspx");
        //webView.loadUrl("https://sealink.navy.lk/profile_hris.php");
        //cookies = CookieManager.getInstance().getCookie("https://epay.navy.lk/");
        //link: https://stackoverflow.com/questions/40291852/how-do-i-open-a-webview-in-another-activity-using-edittext-for-the-url-and-a-but
        //link: https://stackoverflow.com/questions/29282428/return-back-from-webview-to-activity
        //link: https://stackoverflow.com/questions/54768003/redirecting-from-a-web-view-to-a-new-activity


        //help window:
        ConstraintLayout helpWindowInEpay = (ConstraintLayout)findViewById(R.id.helpWindowInEpay);
        //show help button:
        Button helpButton = (Button)findViewById(R.id.helpButtonInEpay);
        helpButton.setOnClickListener(view -> helpWindowInEpay.setVisibility(View.VISIBLE));
        //hide help button:
        Button closeHelpButton = (Button)findViewById(R.id.closeHelpWindowButtonInEpay);
        closeHelpButton.setOnClickListener( view1 -> helpWindowInEpay.setVisibility(View.INVISIBLE));


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.ePay), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }


//    public View onCreateView(Bundle savedInstanceState, View view, LayoutInflater inflater, ViewGroup container) {
//        View webView = view.findViewById()
//    }
}