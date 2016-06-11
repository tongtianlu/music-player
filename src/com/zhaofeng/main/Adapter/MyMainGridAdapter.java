package com.zhaofeng.main.Adapter;

import java.util.ArrayList;

import com.zhaofeng.data.SystemData;
import com.zhaofeng.main.MainView;
import com.zhaofeng.main.R;
import com.zhaofeng.util.BitmapUtil;

import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

public class MyMainGridAdapter extends BaseAdapter {
private LayoutInflater inflater;
private ArrayList<Bitmap> pics;
private int screenWidth;
private int screenHeight;
private ArrayList<String> textList;
	public MyMainGridAdapter(LayoutInflater flater,ArrayList<Bitmap> pics,int screenWidth,int screenHeight) {
		this.inflater=flater;
		this.pics=pics;
		this.screenWidth=screenWidth;
		this.screenHeight=screenHeight;
		// TODO Auto-generated constructor stub
		textList=new ArrayList<String>();
		textList.add(SystemData.MAIM_ALL_TEXT);
		textList.add(SystemData.MAIM_LIKE_TEXT);
//		textList.add(SystemData.MAIM_DONWLOAD_TEXT);
		textList.add(SystemData.MAIM_SETTING_TEXT);
//		textList.add(SystemData.MAIM_SEARCH_TEXT);
		textList.add(SystemData.MAIM_EXIT_TEXT);
	}
	public int getCount() {
		// TODO Auto-generated method stub
		return pics.size();
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
		View view = inflater.inflate(R.layout.maingrid, null);
		
		ImageView pic = (ImageView)view.findViewById(R.id.maingrid_pic);
		Bitmap srcpic = pics.get(position);
		Bitmap newpic = BitmapUtil.GetNewBitmap(srcpic, screenWidth, screenHeight,(screenWidth/4)-2, screenHeight/6);
		pic.setImageBitmap(newpic);
		TextView text = (TextView)view.findViewById(R.id.maingrid_text);
		text.setText(textList.get(position));
		return view;
	}


}
