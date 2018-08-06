package sg.edu.rp.c346.rpwebsites;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class SiteActivity extends AppCompatActivity {

    WebView wvSite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_site);
        wvSite = findViewById(R.id.webViewPage);

        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        wvSite.setWebViewClient(new WebViewClient());
        wvSite.loadUrl(url);
    }
}
