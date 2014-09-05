package com.thenewboston.ronak;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SharedPrefs extends Activity implements OnClickListener {

	Button load,save;
	EditText sharedData;
	TextView dataResults;
	public static String filename = "MyStringfile";
	SharedPreferences sp;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sharedpreferences);
		load = (Button) findViewById(R.id.bLoad);
		save = (Button) findViewById(R.id.bSavesharedprefs);
		sharedData = (EditText)findViewById(R.id.eTSharedData);
		dataResults = (TextView)findViewById(R.id.tVdataresults);
		load.setOnClickListener(this);
		save.setOnClickListener(this);
		sp = getSharedPreferences(filename,0);
	}
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch(arg0.getId()){
		
		case R.id.bLoad :
			sp = getSharedPreferences(filename,0);
			String returnedData = sp.getString("sharedstring", "couldn't load data");
			dataResults.setText(returnedData);
			break;
			
		case R.id.bSavesharedprefs :
			String ETdata = sharedData.getText().toString();
			SharedPreferences.Editor editor = sp.edit();
			editor.putString("sharedstring", ETdata);
			editor.commit();
			break;
			
		}
	}

	
	
}
