package com.zhaofeng.main;

import java.util.Random;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.zhaofeng.data.MyDataHelper;
import com.zhaofeng.util.BitmapUtil;

public class WelcomeView extends Activity {
	private boolean flag = true;
	private int count=0;
	private int alpanum=0;
	private ImageView iv;
	
	String TITLE = MediaStore.Audio.Media.TITLE;//名称
	String ARTIST = MediaStore.Audio.Media.ARTIST;//艺术家
	String ALBUM = MediaStore.Audio.Media.ALBUM;//唱片
	String YEAR = MediaStore.Audio.Media.YEAR;//年代
	String DURATION = MediaStore.Audio.Media.DURATION;//时长
	String PATH =  MediaStore.Audio.Media.DATA;//路径
	
	Uri uri_meta = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
	
	private String createtable = "create table if not exists allmusic(id integer primary key,title varchar(50),artist varchar(20),albun varchar(50),year varchar(20),duration integer,path varchar(100))";
	private String insertdata = "insert into allmusic(title,artist,albun,year,duration,path) values(?,?,?,?,?,?)";
	private String droptable ="drop table if exists allmusic";
	
	private Handler hannext = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			if (msg.what==0) {
				iv.setAlpha(alpanum+=1);
			}
			if (msg.what==-1) {
				iv.setAlpha(alpanum-=1);
			}
			if (msg.what==1) {
				nextpage();
			}
		}
	};
	private int screenHeight;
	private int screenWidth;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.welcome);
		
		searchMusic();
		//获取屏幕大小
		DisplayMetrics dm = new DisplayMetrics();  
		getWindowManager().getDefaultDisplay().getMetrics(dm); 
		screenHeight= dm.heightPixels;  
		screenWidth =dm.widthPixels;
		
		init();
	}

	private void init() {
		int[] welcomepics = {R.drawable.welcome1,R.drawable.welcome2,R.drawable.welcome3,R.drawable.welcome4};
		Random random = new Random();
		random.nextInt(5);
		iv = (ImageView)findViewById(R.id.welcomeimage);
		int whichpic = random.nextInt(3);
		Bitmap srcpic = BitmapFactory.decodeResource(getResources(),welcomepics[whichpic]);
		Bitmap newpic = BitmapUtil.GetNewBitmap(srcpic, screenWidth, screenHeight,screenWidth, screenHeight);
		iv.setImageBitmap(newpic);
		new Thread(new Runnable() {
			public void run() {
				while (flag) {
					try {
						Thread.sleep(10);
						count++;
						hannext.sendEmptyMessage(0);
						if (count>255) {
							hannext.sendEmptyMessage(-1);
						}
						if (count>500) {
							hannext.sendEmptyMessage(1);
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}
	private void searchMusic() {
		// TODO Auto-generated method stub
		try {
			MyDataHelper data = new MyDataHelper(WelcomeView.this,"fengfeng",null, 1);
			SQLiteDatabase db = data.getWritableDatabase();
			db.execSQL(droptable);
			db.execSQL(createtable);
			
			//初始化遍历sdcard下的音乐---各种文件信息
	        Cursor cs_meta = getContentResolver().query(uri_meta, new String[]{TITLE, ARTIST, ALBUM, YEAR,DURATION,PATH}, null, null, null);  
	    	if(cs_meta != null && cs_meta.getCount() > 0){
				while (cs_meta.moveToNext()) {
					db.execSQL(insertdata,new Object[]{cs_meta.getString(0),cs_meta.getString(1),cs_meta.getString(2),cs_meta.getString(3),cs_meta.getString(4),cs_meta.getString(5)});
				}
			}
			cs_meta.close();
		} catch (Exception e) {
		}
	}

	private void nextpage(){
		flag=false;
		finish();
		Intent intent = new Intent(WelcomeView.this,MainView.class);
		WelcomeView.this.startActivity(intent);
	}
}
