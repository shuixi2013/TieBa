package com.kittendev.tieba.activity;

import android.support.v7.app.*;
import android.os.*;
import com.kittendev.tieba.*;
import android.webkit.*;
import android.widget.*;

public class LoginActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		WebView webView = (WebView) findViewById(R.id.login_webView);
		WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setUserAgentString("Mozilla/5.0 (Linux; U; Android 7.0; zh-cn; MI 5 Build/NRD90M) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/53.0.2785.146 Mobile Safari/537.36 XiaoMi/MiuiBrowser/9.2.11");
        settings.setSupportZoom(true);
        webView.setWebViewClient(new WebViewClient() {

				private String mocookie;
				public void onPageFinished(WebView webView, String str) {
					String cookie = CookieManager.getInstance().getCookie("https://wappass.baidu.com/");
					if (cookie != null && cookie.contains("STOKEN=")) {
						mocookie = cookie;
						Toast.makeText(LoginActivity.this, cookie, Toast.LENGTH_SHORT).show();
					}
				}
			});
        webView.loadUrl("https://wappass.baidu.com/");
	}
	
}
