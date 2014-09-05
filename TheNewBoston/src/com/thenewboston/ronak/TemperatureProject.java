package com.thenewboston.ronak;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class TemperatureProject extends Activity implements SensorEventListener{

	float Temperature;
	SensorManager sm;
	TextView tvTemp;
	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		tvTemp = (TextView)findViewById(R.id.tVtemperature);
		sm = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
		if(sm.getSensorList(Sensor.TYPE_AMBIENT_TEMPERATURE).size()!=0){
			Sensor s = sm.getSensorList(Sensor.TYPE_AMBIENT_TEMPERATURE).get(0);
			sm.registerListener(this, s,SensorManager.SENSOR_DELAY_NORMAL);
		}
		Temperature = 0;
	}

	@Override
	public void onAccuracyChanged(Sensor arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSensorChanged(SensorEvent arg0) {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(16);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	protected void onPause() {
		// TODO Auto-generated method stub
		sm.unregisterListener(this);
		super.onPause();
	}

}
