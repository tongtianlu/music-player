package com.zhaofeng.main;

import java.util.ArrayList;
import android.app.TabActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabWidget;

import com.zhaofeng.bean.MusicMessage;
import com.zhaofeng.data.MyDataHelper;
import com.zhaofeng.main.Adapter.ExpandableAdapter;
import com.zhaofeng.main.Adapter.MyMusicListAdapter;

public class AllMusicView extends TabActivity {
private int screenHeight;
private int screenWidth;
private LayoutInflater inflater;
private String selectdata = "select * from allmusic";

private ArrayList<MusicMessage> musiclist = null;
private MusicMessage message = null;

@Override
protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	requestWindowFeature(Window.FEATURE_NO_TITLE);
	getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
	init();
}

private void init() {
	
	//get the screen size
	DisplayMetrics dm = new DisplayMetrics();  
	getWindowManager().getDefaultDisplay().getMetrics(dm); 
	screenHeight= dm.heightPixels;  
	screenWidth =dm.widthPixels;
	
	//table
	TabHost roottab = this.getTabHost();
	LayoutInflater.from(this).inflate(R.layout.allmusiclist,roottab.getTabContentView(), true);
	
	roottab.addTab(roottab.newTabSpec("1").setIndicator("",getResources().getDrawable(R.drawable.tab1)).setContent(R.id.alllist_tab1));
	roottab.addTab(roottab.newTabSpec("2").setIndicator("",getResources().getDrawable(R.drawable.tab2)).setContent(R.id.alllist_tab2));
	
	//expandable
	ExpandableListView expandableListView = (ExpandableListView)findViewById(R.id.expandableListView);  
	expandableListView.setAdapter(new ExpandableAdapter(AllMusicView.this)); 

	
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
	// TODO Auto-generated method stub
	ListView listView = (ListView)findViewById(R.id.allmusic_list);
	listView.setOnItemClickListener(new OnItemClickListener() {

		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			TextView textview = (TextView)view.findViewById(R.id.music_id);
			Intent intent = new Intent(AllMusicView.this,PlayView.class);
			Bundle bundle = new Bundle();
			bundle.putInt("id",Integer.valueOf(textview.getText().toString().trim()));
			bundle.putInt("music_count",musiclist.size());
			
			intent.putExtras(bundle);
			startActivity(intent);
		}
	});
	inflater = LayoutInflater.from(AllMusicView.this);
	
	//Read data from db 
	musiclist = new ArrayList<MusicMessage>();
	MyDataHelper data = new MyDataHelper(AllMusicView.this,"fengfeng",null, 1);
	SQLiteDatabase db = data.getWritableDatabase();
	
	Cursor cursor = db.rawQuery(selectdata, null);
	while (cursor.moveToNext()) {
		message = new MusicMessage(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getInt(5),cursor.getString(6));
		musiclist.add(message);
	}
	cursor.close();//don't forget to close the cursor after use.
	
	listView.setAdapter(new MyMusicListAdapter(inflater, null, screenHeight, screenHeight,musiclist));
}
@Override
	protected void onDestroy() {
		super.onDestroy();
		finish();
	}
}
