package com.thenewboston.ronak;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SQLiteexample extends Activity implements OnClickListener{

	
	EditText sqlName,sqlRate,sqlRow;
	Button sqlUpdate,sqlView,sqlGetInfo,sqlModify,sqlDelete;
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sqliteexample);
		sqlName = (EditText) findViewById (R.id.etSQLName);
		sqlRate = (EditText) findViewById (R.id.etSQLRate);
		sqlRow = (EditText) findViewById (R.id.etSQLrowInfo);
		sqlGetInfo = (Button) findViewById(R.id.bGetInfo);
		sqlModify = (Button) findViewById(R.id.bSQLModify);
		sqlDelete= (Button) findViewById(R.id.bSQLDelete);
		sqlUpdate = (Button) findViewById (R.id.bSQLupdate);
		sqlView = (Button) findViewById (R.id.bSQLopenView);
		sqlUpdate.setOnClickListener(this);
		sqlView.setOnClickListener(this);
		sqlGetInfo.setOnClickListener(this);
		sqlModify.setOnClickListener(this);
		sqlDelete.setOnClickListener(this);
	}



	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
		switch(arg0.getId())
		{
		
		case R.id.bSQLupdate :
			boolean success = true;
			try{
			String name = sqlName.getText().toString();
			String rate = sqlRate.getText().toString();
			
			HotorNot entry = new HotorNot(SQLiteexample.this);
			entry.open();
			
			entry.createEntry(name,rate);
			entry.close();
			}
			catch(Exception e){
				success = false;
				String error = e.toString();
				Dialog d = new Dialog(this);
				d.setTitle("Lost it");
				TextView tv = new TextView(this);
				tv.setText(error);
				d.setContentView(tv);
				d.show();
				
			}
			if(success == true)
			{
				Dialog d = new Dialog(this);
				d.setTitle("hurray ! successful");
				TextView tv = new TextView(this);
				tv.setText("Great");
				d.setContentView(tv);
				d.show();
				
				
				
			}
			break;
		case R.id.bSQLopenView:
			Intent i = new Intent("com.thenewboston.ronak.SQLVIEW");
			startActivity(i);
			break;
		case R.id.bGetInfo:
			try{
			String s = sqlRow.getText().toString();
			long l = Long.parseLong(s);
			HotorNot hon = new HotorNot(this);
			hon.open();
			String returnedName = hon.getName(l);
			String returnedRates = hon.getRates(l);
		    hon.close();
		    
		    sqlRate.setText(returnedRates);
		    sqlName.setText(returnedName);
		}
		catch(Exception e){
			success = false;
			String error = e.toString();
			Dialog d = new Dialog(this);
			d.setTitle("Lost it");
			TextView tv = new TextView(this);
			tv.setText(error);
			d.setContentView(tv);
			d.show();
			
		}
			break;
		case R.id.bSQLModify:
			try{
			String sMod = sqlRow.getText().toString();
			long lMod = Long.parseLong(sMod);
			String nameMod = sqlName.getText().toString();
			String rateMod = sqlRate.getText().toString();
			HotorNot hon1 = new HotorNot(this);
			hon1.open();
			hon1.modify(lMod,nameMod,rateMod);
			hon1.close();
			}
			catch(Exception e){
				success = false;
				String error = e.toString();
				Dialog d = new Dialog(this);
				d.setTitle("Lost it");
				TextView tv = new TextView(this);
				tv.setText(error);
				d.setContentView(tv);
				d.show();
				
			}
			break;
		case R.id.bSQLDelete:
			try{
			String sDelete = sqlRow.getText().toString();
			long lDelete = Long.parseLong(sDelete);
			HotorNot hon2 = new HotorNot(this);
			hon2.open();
			hon2.deleteEntry(lDelete);
			hon2.close();
			}
			catch(Exception e){
				success = false;
				String error = e.toString();
				Dialog d = new Dialog(this);
				d.setTitle("Lost it");
				TextView tv = new TextView(this);
				tv.setText(error);
				d.setContentView(tv);
				d.show();
				
			}
			break;
		
		}
	}

	
}
