package com.thenewboston.ronak;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;

public class Browser extends Activity implements OnClickListener {

	WebView wV;
	Button go,back,forward,reload,clearhistory;
	EditText etBrowse;
	InputMethodManager imm;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.browser);
	    imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
		wV = (WebView)findViewById(R.id.wVBrowser);
		wV.getSettings().setJavaScriptEnabled(true);//setting Javascript which is required by some websites
		wV.setWebViewClient(new ViewClient()); // for displaying links in same webview
		wV.getSettings().setUseWideViewPort(true); // kind of sets up like a normal browser view
		wV.getSettings().setLoadWithOverviewMode(true); // makes the webpage to be displayed completely zoomed-out
		try{
		wV.loadUrl("http://www.google.com");
		}catch(Exception e){
			e.printStackTrace();
		}
		go = (Button)findViewById(R.id.bGo);
		back = (Button)findViewById(R.id.bBack);
		forward = (Button)findViewById(R.id.bForward);
		reload = (Button)findViewById(R.id.bReload);
		clearhistory = (Button)findViewById(R.id.bClearHistory);
		etBrowse = (EditText)findViewById(R.id.eTBrowser); 
		go.setOnClickListener(this);
		back.setOnClickListener(this);
		forward.setOnClickListener(this);
		reload.setOnClickListener(this);
		clearhistory.setOnClickListener(this);
	}
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch(arg0.getId()){
		case R.id.bGo :
			String website = etBrowse.getText().toString();
			wV.loadUrl(website);
			imm.hideSoftInputFromInputMethod(etBrowse.getWindowToken(), 0); // hiding the keyboard once finished typing url and pressing Go button
			break;
		case R.id.bBack	:
			if(wV.canGoBack())
			wV.goBack();
			break;
		case R.id.bForward :
			if(wV.canGoForward())
				wV.goForward();
			break;
		case R.id.bReload :
			wV.reload();
			break;
		case R.id.bClearHistory :
			wV.clearHistory();
			break;
		
		}
		
	}

	
}
