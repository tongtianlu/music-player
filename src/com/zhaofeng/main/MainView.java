package com.zhaofeng.main;

import java.util.ArrayList;

import android.app.TabActivity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabWidget;

import com.zhaofeng.main.Adapter.MyMain2GridAdapter;
import com.zhaofeng.main.Adapter.MyMainGridAdapter;

public class MainView extends TabActivity {
    /** Called when the activity is first created. */
	private LayoutInflater inflater;
	private int screenHeight;
	private int screenWidth;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    	requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
        init();
    }
    
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
    	// TODO Auto-generated method stub
    	if (keyCode == KeyEvent.KEYCODE_BACK) {
    		System.exit(0);
    	}
    	return super.onKeyDown(keyCode, event);
    }

	private void init() {
		//��ȡ��Ļ��С
		DisplayMetrics dm = new DisplayMetrics();  
		getWindowManager().getDefaultDisplay().getMetrics(dm); 
		screenHeight= dm.heightPixels;  
		screenWidth =dm.widthPixels;
		// TODO Auto-generated method stub
		TabHost roottab = this.getTabHost();
		LayoutInflater.from(this).inflate(R.layout.main,roottab.getTabContentView(), true);
		
		roottab.addTab(roottab.newTabSpec("1").setIndicator("",getResources().getDrawable(R.drawable.tab1)).setContent(R.id.tab1));
		roottab.addTab(roottab.newTabSpec("2").setIndicator("",getResources().getDrawable(R.drawable.tab2)).setContent(R.id.tab2));
		roottab.addTab(roottab.newTabSpec("3").setIndicator("",getResources().getDrawable(R.drawable.tab3)).setContent(R.id.tab3));
		roottab.addTab(roottab.newTabSpec("4").setIndicator("",getResources().getDrawable(R.drawable.tab4)).setContent(R.id.tab4));
		
		final TabWidget tabWidget = roottab.getTabWidget();
		//set backgroundcolor for every tab
		for (int j = 0; j < tabWidget.getChildCount(); j++) {
			if (j>0) {
				tabWidget.getChildAt(j).setBackgroundColor(Color.parseColor("#E9E9E9"));
			}else {
				tabWidget.getChildAt(j).setBackgroundColor(Color.parseColor("#AEAEAE"));
			}
		}
		//Tab click change listener
		roottab.setOnTabChangedListener(new OnTabChangeListener() {
			public void onTabChanged(String tabId) {
				for (int i = 0; i < tabWidget.getChildCount(); i++) {
					tabWidget.getChildAt(i).setBackgroundColor(Color.parseColor("#E9E9E9"));
				}
				tabWidget.getChildAt(Integer.valueOf(tabId)-1).setBackgroundColor(Color.parseColor("#AEAEAE"));
			}
		});
		///
		
		inflater = LayoutInflater.from(MainView.this);
		ArrayList<Bitmap> pics = new ArrayList<Bitmap>();
		pics.add(BitmapFactory.decodeResource(getResources(),R.drawable.main1));
		pics.add(BitmapFactory.decodeResource(getResources(),R.drawable.main2));
		pics.add(BitmapFactory.decodeResource(getResources(),R.drawable.main3));
		pics.add(BitmapFactory.decodeResource(getResources(),R.drawable.main4));
		
		final GridView maingrid = (GridView)findViewById(R.id.main_grid);
		maingrid.setAdapter(new MyMainGridAdapter(inflater,pics,screenWidth,screenHeight));
		maingrid.setOnItemClickListener(new OnItemClickListener() {
				public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
						long arg3) {
					LinearLayout gridviewback = (LinearLayout) arg1;
					if (arg2==0) {
						Intent intent = new Intent(MainView.this,AllMusicView.class);
						startActivity(intent);
					}
					for (int i = 0; i < arg0.getCount(); i++) {
						LinearLayout gridview_text_temp = (LinearLayout) arg0.getChildAt(i);
						gridview_text_temp.setBackgroundDrawable(null);
						gridview_text_temp.setBackgroundColor(Color.WHITE);
					}
					gridviewback.setBackgroundColor(Color.parseColor("#37AEF1"));
			}
		});
		//
		ArrayList<Bitmap> types = new ArrayList<Bitmap>();
		types.add(BitmapFactory.decodeResource(getResources(),R.drawable.bt_soundeffect_equallizerunit_bass_hl));
		types.add(BitmapFactory.decodeResource(getResources(),R.drawable.bt_soundeffect_equallizerunit_classical_hl));
		types.add(BitmapFactory.decodeResource(getResources(),R.drawable.bt_soundeffect_equallizerunit_dance_hl));
		
		types.add(BitmapFactory.decodeResource(getResources(),R.drawable.bt_soundeffect_equallizerunit_folk_hl));
		types.add(BitmapFactory.decodeResource(getResources(),R.drawable.bt_soundeffect_equallizerunit_highpitch_hl));
		types.add(BitmapFactory.decodeResource(getResources(),R.drawable.bt_soundeffect_equallizerunit_intelligence_hl));
		
		types.add(BitmapFactory.decodeResource(getResources(),R.drawable.bt_soundeffect_equallizerunit_jazz_hl));
		types.add(BitmapFactory.decodeResource(getResources(),R.drawable.bt_soundeffect_equallizerunit_pop_hl));
		types.add(BitmapFactory.decodeResource(getResources(),R.drawable.bt_soundeffect_equallizerunit_rock_hl));
		types.add(BitmapFactory.decodeResource(getResources(),R.drawable.bt_soundeffect_equallizerunit_voice_hl));
		
		final GridView maingrid2 = (GridView)findViewById(R.id.main_grid2);
		maingrid2.setAdapter(new MyMain2GridAdapter(inflater,types,screenWidth,screenHeight));
		maingrid2.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				LinearLayout gridviewback = (LinearLayout) arg1;
				if (arg2==0) {
//					Intent intent = new Intent(MainView.this,AllMusicView.class);
//					startActivity(intent);
				}
				for (int i = 0; i < arg0.getCount(); i++) {
					LinearLayout gridview_text_temp = (LinearLayout) arg0.getChildAt(i);
					gridview_text_temp.setBackgroundDrawable(null);
					gridview_text_temp.setBackgroundColor(Color.WHITE);
				}
				gridviewback.setBackgroundColor(Color.parseColor("#37AEF1"));
			}
		});
		///
	}
}