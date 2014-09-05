package com.thenewboston.ronak;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class InternalData extends Activity implements OnClickListener{

	
	Button load,save;
	EditText sharedData;
	TextView dataResults;
	String FILENAME = "InternalStorage";
	FileOutputStream fos;
	FileInputStream fis;
	String collected;
	
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
		try {
			fos = openFileOutput(FILENAME,Context.MODE_PRIVATE);
			fos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch(arg0.getId()){
		
		case R.id.bLoad :
			
			new loadsomedata().execute(FILENAME);//when we run execute method,it calls the doInBackground method
			
			break;
		case R.id.bSavesharedprefs :
			String data = sharedData.getText().toString();
			//Saving Data via File
			/*
			File f = new File(FILENAME);
			try {
				fos = new FileOutputStream(f);
				//write data to file
				fos.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			*/
			
			try {
				fos = openFileOutput(FILENAME,Context.MODE_PRIVATE);
				fos.write(data.getBytes());
				fos.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			break;
			
		}
}
	public class loadsomedata extends AsyncTask<String,Integer,String>{

	
		ProgressDialog dialog;
		
		
		protected void onPreExecute(){
			//example of setting up variable
			dialog = new ProgressDialog(InternalData.this);
			dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
			dialog.setMax(100);
			dialog.show();
		}

		
				
		
		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			
			
			for(int i =0;i<20;i++){
				publishProgress(5);
				try {
					Thread.sleep(80);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			dialog.dismiss();
			try {
				fis = openFileInput(FILENAME);
				byte[] dataArray = new byte[fis.available()];
				while(fis.read() != -1){
					collected = new String(dataArray);
				}
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally{
				try {
					fis.close();
					return collected;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
			return null;
		}
		
		protected void onProgressUpdate(Integer...integers){
			
			dialog.incrementProgressBy(integers[0]);
			
		}
		//when return collected statement is executed it runs the onPostExecute method
		protected void onPostExecute(String result){
			dataResults.setText(result);
			
		}
	}
}
