package com.kittendev.tieba.activity;

import android.os.*;
import android.support.v7.app.*;
import android.webkit.*;
import android.widget.*;
import com.kittendev.tieba.*;
import java.io.*;
import okhttp3.*;
import org.jsoup.*;

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
						WebSettings settings = webView.getSettings();
						settings.setUserAgentString("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36");
						webView.setWebViewClient(new WebViewClient() {

								private String pccookie;
								public void onPageFinished(WebView webView, String str) {
									String cookie = CookieManager.getInstance().getCookie("https://wappass.baidu.com/");
									if (cookie != null && cookie.contains("STOKEN=")) {
										pccookie = cookie;
										Toast.makeText(LoginActivity.this, pccookie, Toast.LENGTH_SHORT).show();
										OkHttpClient okHttpClient = new OkHttpClient();
										Request request = new Request.Builder().addHeader("User-Agent", "Mozilla/5.0 (Linux; Android 8.0.0; MI 6 Build/OPR1.170623.027; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/65.0.3325.74 Mobile Safari/537.36").url("https://tieba.baidu.com/home/main?un=BoHe%E5%A4%A7%E7%A5%9E&fr=index&red_tag=z0176787127").get().build();
										okHttpClient.newCall(request).enqueue(new Callback() {
												@Override
												public void onFailure(Call call, IOException e) {

												}
												@Override
												public void onResponse(Call call, Response response) {
													try {
														Toast.makeText(LoginActivity.this, Jsoup.parse(response.body().string()).select("j_home_concern_forum_item").first().text(), Toast.LENGTH_LONG).show();
													} catch (IOException e) {

													}
												}
											});
									}
								}
							});
						webView.loadUrl("https://tieba.baidu.com/");
						
					}
				}
			});
        webView.loadUrl("https://wappass.baidu.com/");
	}
}
