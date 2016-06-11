package com.zhaofeng.main;

import java.io.IOException;
import com.zhaofeng.data.MyDataHelper;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.SlidingDrawer;
import android.widget.TextView;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.SlidingDrawer.OnDrawerCloseListener;
import android.widget.SlidingDrawer.OnDrawerOpenListener;
import android.widget.SlidingDrawer.OnDrawerScrollListener;

public class PlayView extends Activity {
private static MediaPlayer mp = null;
private ImageView imageprev;
private ImageView imageplay;
private ImageView imagenext;

private SeekBar seekBar;
private TextView thistime;
private TextView alllong;

private String SCARCHMUSIC_BY_ID = null;
private int musicLong;
private int m;
private int ms;
private String musicartist;
private String musictitle;
private String musicyear;
private String musicalbum;
private String musicpath;
private SQLiteDatabase db;
private int musicid;
private int count_music;
private TextView playname;
private TextView playartist;
private TextView playalbum;
private TextView playyear;
public PlayView() {
	getInstence();
}
//����ģʽ--��ֹ��δ���MediaPlayer
public static MediaPlayer getInstence(){
	if (mp == null) {
		mp = new MediaPlayer();
	}else {
		mp.stop();
		mp.reset();
	}
	return mp;
}
@Override
protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	requestWindowFeature(Window.FEATURE_NO_TITLE);
	getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
	setContentView(R.layout.playview);
	init();
}

private void init() {
	Intent intent = getIntent();
	Bundle getbun = intent.getExtras();
	musicid = getbun.getInt("id");
	count_music = getbun.getInt("music_count");//���и�������
	
	// TODO Auto-generated method stub
	MyDataHelper data = new MyDataHelper(PlayView.this,"fengfeng",null, 1);
	db = data.getWritableDatabase();
	
	searchMusicMsg(musicid);

	// ��ȡ����͸���ͼƬ
	SlidingDrawer sd = (SlidingDrawer)findViewById(R.id.slidingdrawer); 
    
    sd.setOnDrawerOpenListener(new OnDrawerOpenListener(){ 
        public void onDrawerOpened() { 
        } 
    }); 
    sd.setOnDrawerCloseListener(new OnDrawerCloseListener(){ 
        public void onDrawerClosed() { 
        } 
    }); 
    sd.setOnDrawerScrollListener(new OnDrawerScrollListener(){ 
        public void onScrollEnded() { 
        } 
        public void onScrollStarted() { 
        }    
    }); 
    
	//
	playname = (TextView) findViewById(R.id.play_name);
	playname.setText("歌名: "+musictitle);
	playartist = (TextView) findViewById(R.id.play_artist);
	playartist.setText("艺术家: "+musicartist);
	playalbum = (TextView) findViewById(R.id.play_albun);
	playalbum.setText("唱片: "+musicalbum);
	playyear = (TextView) findViewById(R.id.play_year);
	playyear.setText("年代: "+musicyear);
	// ����ʱ��
	thistime = (TextView) findViewById(R.id.thistime);
	alllong = (TextView) findViewById(R.id.alltime);
	alllong.setText(m + ":" + ms);
	
	startPlay(musicpath);
	startProgress();
//	---------------------------------------------------
	
	seekBar = (SeekBar) findViewById(R.id.playseek);
	seekBar.setMax(musicLong);// ���ֵ�Ժ���Ϊ��λ
	seekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
		public void onStopTrackingTouch(SeekBar seekBar) {
			mp.seekTo(seekBar.getProgress());
			mp.start();
			seekBar.setProgress(seekBar.getProgress());
		}
		public void onStartTrackingTouch(SeekBar seekBar) {
		}
		public void onProgressChanged(SeekBar seekBar, int progress,
				boolean fromUser) {
		}
	});
	//��ȡ��Ļ��С
		DisplayMetrics dm = new DisplayMetrics();  
		getWindowManager().getDefaultDisplay().getMetrics(dm); 
		imageprev = (ImageView)findViewById(R.id.bt_prev);
		imageplay = (ImageView)findViewById(R.id.bt_play);
		imagenext = (ImageView)findViewById(R.id.bt_next);
		
		imageplay.setOnClickListener(new OnClickListener() {
			
			// TODO Auto-generated method stub
			public void onClick(View v) {
				if (!mp.isPlaying()) {
					imageplay.setImageResource(R.drawable.bt_widget_pause_nor);
					startPlay(musicpath);
				}else {
					imageplay.setImageResource(R.drawable.bt_widget_play_nor);
					startProgress();
					playAgion();
				}
			}
		});
		//��һ��
		imageprev.setOnClickListener(new OnClickListener() {
			// TODO Auto-generated method stub
			public void onClick(View v) {
				mp.stop();
				searchMusicMsg(musicid-=1);
				startPlay(musicpath);
			}
		});
		//��һ��
		imagenext.setOnClickListener(new OnClickListener() {
			// TODO Auto-generated method stub
			public void onClick(View v) {
				mp.stop();
				searchMusicMsg(musicid+=1);
				startPlay(musicpath);
			}
		});
}
///
private void searchMusicMsg(int id) {
	// TODO Auto-generated method stub
	if (id>count_music) {
		id=1;
	}	
	SCARCHMUSIC_BY_ID = "select * from allmusic where id="+id;
	Cursor cursor = db.rawQuery(SCARCHMUSIC_BY_ID, null);
	while (cursor.moveToNext()) {
		musicLong = cursor.getInt(5);
		m = musicLong / 60000;
		ms = (musicLong % 60000) / 1000;
		
		musictitle = cursor.getString(1);
		musicartist = cursor.getString(2);
		musicalbum = cursor.getString(3);
		musicyear = cursor.getString(4);
		
		musicpath = cursor.getString(6);
		
		playname = (TextView) findViewById(R.id.play_name);
		playartist = (TextView) findViewById(R.id.play_artist);
		playalbum = (TextView) findViewById(R.id.play_albun);
		playyear = (TextView) findViewById(R.id.play_year);
		alllong = (TextView) findViewById(R.id.alltime);
		seekBar = (SeekBar) findViewById(R.id.playseek);
		playname.setText("歌名: "+musictitle);
		playartist.setText("艺术家: "+musicartist);
		playalbum.setText("专辑: "+musicalbum);
		playyear.setText("年代: "+musicyear);
		alllong.setText(m + ":" + ms);
		
		seekBar.setMax(musicLong);
	}
}
//��ʼ����
//��ʼ��----��ʼ���Ÿ���
private void startPlay(String url) {
	//��ʼ��--�ж���һ���Ƿ����ڲ��ţ�������򡣡���
		try {
				mp.reset();
				mp.setDataSource(url);
				mp.prepare();//׼��
				mp.start();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
}
//�����/��ͣ
	private void playAgion() {
		try {
			if (mp.isPlaying()) {
				mp.pause();
				seekBar.setProgress(mp.getCurrentPosition());
			}else {
				mp.start();
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		}
	}
	//������ʾ�ַ�
	private Handler handler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			if (msg.what==1) {
				thistime.setText((mp.getCurrentPosition()/60000)+":"+(mp.getCurrentPosition()%60000)/1000);
			}
			if (msg.what==2) {
				thistime.setText("00:00");
			}
		}
	};
	//���¸�����
	private boolean falg = true;
	private void startProgress(){
		new Thread(new Runnable() {
			public void run() {
				while (falg) {
					try {
						Thread.sleep(100);
						if (mp.isPlaying()) {
							seekBar.setProgress(mp.getCurrentPosition());
							handler.sendEmptyMessage(1);
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}
}
