package com.hankyo.navyBuddyApp.sealinkWebsite;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.hankyo.navyBuddyApp.R;

import java.util.Objects;

public class sealinkLoginOrRegisterActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sealink_login_or_register);

      //--receive user requested activity type from sealink fragment-----------------
        Bundle extras = getIntent().getExtras();
        assert extras != null;
        String userRequestedActivityType = extras.getString("userRequestedActivityType");
        System.out.println("userRequestedActivityType:"+ userRequestedActivityType);
      //--done! received the requested activity type from sealink fragment-----------

      //--display the user requested sealink page------------------------------------
        WebView webView = (WebView)findViewById(R.id.sealinkWebViewArea);
        //WebView ePayWeb = (WebView) webView;
        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
        webView.getSettings().getCacheMode();
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.getSettings().setJavaScriptEnabled(true);
        if(Objects.equals(userRequestedActivityType, "login")){
            webView.loadUrl("https://sealink.navy.lk/profile_hris.php#");
        }
        else if(Objects.equals(userRequestedActivityType,"register")){
            webView.loadUrl("https://sealink.navy.lk/signup.php");
        }
        //webView.loadUrl("https://epay.navy.lk/RecentMonth.aspx");
        //webView.loadUrl("https://sealink.navy.lk/profile_hris.php");
        //cookies = CookieManager.getInstance().getCookie("https://epay.navy.lk/");
        //link: https://stackoverflow.com/questions/40291852/how-do-i-open-a-webview-in-another-activity-using-edittext-for-the-url-and-a-but
        //link: https://stackoverflow.com/questions/29282428/return-back-from-webview-to-activity
        //link: https://stackoverflow.com/questions/54768003/redirecting-from-a-web-view-to-a-new-activity
      //--done! successfully showed the user requested sealink page--------------------



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}