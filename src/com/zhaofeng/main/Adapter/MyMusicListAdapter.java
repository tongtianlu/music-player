package com.zhaofeng.main.Adapter;

import java.util.ArrayList;

import com.zhaofeng.bean.MusicMessage;
import com.zhaofeng.data.MyDataHelper;
import com.zhaofeng.main.R;
import com.zhaofeng.main.WelcomeView;

import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

public class MyMusicListAdapter extends BaseAdapter implements ListAdapter {
private LayoutInflater inflater;
private ArrayList<Bitmap> pics;
private int screenWidth;
private int screenHeight;
private ArrayList<String> textList;
private ArrayList<MusicMessage> musiclist = null;
public MyMusicListAdapter(LayoutInflater flater,ArrayList<Bitmap> pics,int screenWidth,int screenHeight,ArrayList<MusicMessage> musiclist) {
	// TODO Auto-generated constructor stub
	this.inflater=flater;
	this.pics=pics;
	this.screenWidth=screenWidth;
	this.screenHeight=screenHeight;
	this.musiclist=musiclist;
}
	public int getCount() {
		// TODO Auto-generated method stub
		return musiclist.size();
	}

	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.music_sub_list, null);
		
		TextView musicid = (TextView)view.findViewById(R.id.music_id);
		musicid.setText(String.valueOf(musiclist.get(position).getId()));
		
		TextView title = (TextView)view.findViewById(R.id.music_title);
		title.setText(musiclist.get(position).getTitle());
		
		TextView artist = (TextView)view.findViewById(R.id.music_other_text);
		artist.setText(musiclist.get(position).getArtist());
		return view;
	}

}
