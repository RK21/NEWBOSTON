package com.thenewboston.ronak;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnTouchListener;

public class GFXSurface extends Activity implements OnTouchListener{

	MyBringBackSurface ourSurfaceView;
	float x;
	float y;
	float sX,sY,fX,fY,dX,dY,aniX,aniY,scaledX,scaledY;
	Bitmap bmp,test;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		ourSurfaceView = new MyBringBackSurface(this);
		ourSurfaceView.setOnTouchListener(this);
		x = 0;
		y = 0;
		sX = 0;
		sY = 0;
		fX = 0;
		fY = 0;
		scaledX = 0;
		scaledY = 0;
		aniX = 0;
		aniY = 0;
		dX = 0;
		dY = 0;
		bmp = BitmapFactory.decodeResource(getResources(), R.drawable.greenball);
		test = BitmapFactory.decodeResource(getResources(), R.drawable.images);
		setContentView(ourSurfaceView);
		
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		ourSurfaceView.pause();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		ourSurfaceView.resume();
	}

	@Override
	public boolean onTouch(View v, MotionEvent e) {
		// TODO Auto-generated method stub
		
		try {
			Thread.sleep(50);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		x = e.getX();
		y = e.getY();
		switch(e.getAction()){
		
		case MotionEvent.ACTION_DOWN :
			sX = e.getX();
			sY = e.getY();
			scaledX = 0;
			scaledY = 0;
			aniX = 0;
			aniY = 0;
			dX = 0;
			dY = 0;
			fX = 0;
			fY = 0;
			break;
		case MotionEvent.ACTION_UP:
			fX = e.getX();
			fY = e.getY();
			dX = fX - sX;
			dY = fY - sY;
			scaledX = dX/30;
			scaledY = dY/30;
			x = y = 0;
			break;
		}
		return true;
	}
 
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
				if(x != 0 && y != 0)
				{
					
					canvas.drawBitmap(bmp, x - (bmp.getWidth()/2), y - (bmp.getHeight()/2), null);
				}
				if(sX != 0 && sY != 0)
				{
					
					canvas.drawBitmap(test, sX - (test.getWidth()/2), sY - (test.getHeight()/2), null);
				}
				if(fX != 0 && fY != 0)
				{
					canvas.drawBitmap(bmp, fX - (bmp.getWidth()/2)-aniX, fY - (bmp.getHeight()/2)-aniY, null);
					canvas.drawBitmap(test, fX - (test.getWidth()/2), fY - (test.getHeight()/2), null);
					
				}
				aniX = aniX + scaledX;
				aniY = aniY + scaledY;
				
				Holder.unlockCanvasAndPost(canvas);
				
			}
		}

	}

}
