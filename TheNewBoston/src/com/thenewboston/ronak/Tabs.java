package com.thenewboston.ronak;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

public class Tabs extends Activity implements OnClickListener{

	TabHost th;
	TabSpec specs;
	Button start,stop,addTab;
	TextView showResults;
	long starttime = 0,stoptime;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tabbs);
		th = (TabHost)findViewById(R.id.tabhost);
		th.setup();
		start = (Button)findViewById(R.id.bstartwatch);
		stop = (Button)findViewById(R.id.bstopwatch);
		addTab = (Button)findViewById(R.id.baddtab);
		showResults = (TextView)findViewById(R.id.tVShowResults);
		start.setOnClickListener(this);
		stop.setOnClickListener(this);
		addTab.setOnClickListener(this);
		specs = th.newTabSpec("tag1");
		specs.setContent(R.id.tab1);
		specs.setIndicator("StopWatch");
		th.addTab(specs);
		specs = th.newTabSpec("tag2");
		specs.setContent(R.id.tab2);
		specs.setIndicator("Tab 2");
		th.addTab(specs);
		specs = th.newTabSpec("tag3");
		specs.setContent(R.id.tab3);
		specs.setIndicator("Add a Tab");
		th.addTab(specs);
	}
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch(arg0.getId()){
		
		case R.id.bstartwatch :
			starttime = System.currentTimeMillis();
			break;
		case R.id.bstopwatch :
			stoptime = System.currentTimeMillis();
			if(starttime != 0){
			long result = stoptime-starttime;
			int millis = (int) result;
			int seconds = (int) result/1000;
			int minutes = (int)seconds/60;
			millis = millis%100;
			seconds = seconds%60;
			showResults.setText(String.format(" %d:%02d %02d: ", minutes,seconds,millis));
			}
			break;
		case R.id.baddtab :
			
			TabSpec ourSpec = th.newTabSpec("tag1");
			ourSpec.setContent(new TabHost.TabContentFactory() {
				
				@Override
				public View createTabContent(String arg0) {
					// TODO Auto-generated method stub
					TextView text = new TextView(Tabs.this);
					text.setText("This is a new Tab");
					return (text);
				}
			});
			ourSpec.setIndicator("New Tab");
			th.addTab(ourSpec);
			
			break;
		
		
		}
	}

	
	
}
