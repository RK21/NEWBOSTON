package com.thenewboston.ronak;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class AccelerometerProject extends Activity implements SensorEventListener {

	float x,y,sensorX,sensorY;
	Bitmap ball;
	SensorManager sm;
	MyBringBackSurface ourSurfaceView;
	public class MyBringBackSurface extends SurfaceView implements Runnable{

		SurfaceHolder Holder;
		Thread thread = null;
		boolean isRunning = false;
		
		public MyBringBackSurface(Context context) {
			super(context);
			// TODO Auto-generated constructor stub
			Holder = getHolder();
			
		}

		public void pause(){
			isRunning = false;
			while(true){
			try {
				thread.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}
			thread = null;
		}
		public void resume(){
			isRunning = true;
			thread = new Thread(this);
			thread.start();
			
		}
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			while(isRunning){
				if(!Holder.getSurface().isValid())
					continue;
				
				Canvas canvas = Holder.lockCanvas();
				canvas.drawRGB(10, 10, 254);
				float centerX = canvas.getWidth()/2 - ball.getWidth()/2;
				float centerY = canvas.getHeight()/2 - ball.getHeight()/2;
				canvas.drawBitmap(ball, centerX + sensorX*20, centerY + sensorY*20, null);
				Holder.unlockCanvasAndPost(canvas);
				
			}
		}

	}
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		sm = (SensorManager) getSystemService (Context.SENSOR_SERVICE);
		if(sm.getSensorList(Sensor.TYPE_ACCELEROMETER).size()!=0){
			
			Sensor s = sm.getSensorList(Sensor.TYPE_ACCELEROMETER).get(0);
			sm.registerListener(this, s, SensorManager.SENSOR_DELAY_NORMAL);
		}
		x=y=sensorX=sensorY = 0;
		ball = BitmapFactory.decodeResource(getResources(), R.drawable.greenball);
		ourSurfaceView = new MyBringBackSurface(this);
		ourSurfaceView.resume();
		setContentView(ourSurfaceView );
		
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		sm.unregisterListener(this);
		super.onPause();
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
		sensorX = arg0.values[0];
		sensorY = arg0.values[1];
	}

}
