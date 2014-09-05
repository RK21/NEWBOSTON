package com.thenewboston.ronak;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;

public class GFX extends Activity {

	MyBringBack ourView;
	WakeLock wL;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		//wake-lock
		PowerManager pM = (PowerManager)getSystemService(Context.POWER_SERVICE);
		wL = pM.newWakeLock(PowerManager.FULL_WAKE_LOCK, "WakeLock");
		super.onCreate(savedInstanceState);
		ourView = new MyBringBack(this);
		setContentView(ourView);
	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		wL.acquire();
	}
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		wL.release();
	}

}
