package com.thenewboston.ronak;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.SlidingDrawer;
import android.widget.SlidingDrawer.OnDrawerOpenListener;

public class Slider extends Activity implements OnClickListener,OnCheckedChangeListener, OnDrawerOpenListener{

	Button Handle1,Handle2,Handle3,Handle4;
	CheckBox check;
	SlidingDrawer sd;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.slide);
		Handle1 = (Button)findViewById(R.id.handle1);
		Handle2 = (Button)findViewById(R.id.handle2);
		Handle3 = (Button)findViewById(R.id.handle3);
		Handle4 = (Button)findViewById(R.id.handle4);
		check = (CheckBox)findViewById(R.id.cBSlide);
		check.setOnCheckedChangeListener(this);
		sd = (SlidingDrawer)findViewById(R.id.slidingD);
		sd.setOnDrawerOpenListener(this);
		Handle1.setOnClickListener(this);
		Handle2.setOnClickListener(this);
		Handle3.setOnClickListener(this);
		Handle4.setOnClickListener(this);
	}

	@Override
	public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
		// TODO Auto-generated method stub
		if(arg0.isChecked()){
			sd.lock();
		}
		else{
			sd.unlock();
		}
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch(arg0.getId()){
		case R.id.handle1 :
			sd.open();
			break;
		case R.id.handle2:
			sd.toggle();
			break;
		case R.id.handle3:
			
			break;
		case R.id.handle4:
			sd.close();
			break;
		}
	}

	@Override
	public void onDrawerOpened() {
		// TODO Auto-generated method stub
		MediaPlayer mp = MediaPlayer.create(this, R.raw.explosion);
		mp.start();
	}

	
}
