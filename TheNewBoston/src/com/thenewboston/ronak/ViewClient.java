package com.thenewboston.ronak;

import android.webkit.WebView;
import android.webkit.WebViewClient;

public class ViewClient extends WebViewClient {

	@Override
	public boolean shouldOverrideUrlLoading(WebView v,String url){
		
		v.loadUrl(url);
		return true;
	}
}
