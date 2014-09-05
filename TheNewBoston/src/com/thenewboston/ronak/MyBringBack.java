package com.thenewboston.ronak;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.view.View;

public class MyBringBack extends View {

	Bitmap gball;
	float changingY;
	Typeface font;
	
	public MyBringBack(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		
		gball = BitmapFactory.decodeResource(getResources(), R.drawable.greenball);
		font = Typeface.createFromAsset(context.getAssets(), "Promocyja096.ttf");
		changingY = 0;
		
	}

	@Override
	public void draw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.draw(canvas);
		canvas.drawColor(Color.WHITE);
		Paint txtpaint = new Paint();
		txtpaint.setTextAlign(Align.CENTER);
		txtpaint.setARGB(50, 254, 148, 48);
		txtpaint.setTextSize(50);
		txtpaint.setTypeface(font);
		canvas.drawText("Ronak", canvas.getWidth()/2, 0, txtpaint);
		
		
		canvas.drawBitmap(gball, canvas.getWidth()/2, changingY, null);
		if(changingY < canvas.getHeight()){
			changingY += 15;
		}
		else{changingY = 0;}
		Rect r = new Rect();
		r.set(0, 400, canvas.getWidth(), 550);
		Paint p = new Paint();
		p.setColor(Color.BLUE);
		canvas.drawRect(r, p);
		invalidate();
	}

	
}
