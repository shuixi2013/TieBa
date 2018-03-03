package com.kittendev.tieba.activity;

import android.support.v7.app.*;
import android.os.*;
import com.kittendev.tieba.*;
import android.content.*;
import retrofit2.*;
import com.kittendev.tieba.api.*;

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		startActivity(new Intent(this, LoginActivity.class));
		
		Retrofit retrofit = new Retrofit.Builder()
			.baseUrl("https://wappass.baidu.com/")
			.build();

		retrofit.create(TieBaService.class);
	}
}
