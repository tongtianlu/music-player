package com.zhaofeng.main.Adapter;

import java.util.ArrayList;
import java.util.List;

import com.zhaofeng.data.MyDataHelper;
import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

public  class  ExpandableAdapter extends  BaseExpandableListAdapter  
{  
	private ArrayList<String> tempArray;
	private  List<String> groupArray;  
	private  List<List<String>> childArray;
	
	private String selectallArtist = "select distinct artist from allmusic";
	private String selectMusicBytitle = null;

    private Activity activity;
	private String title;
	private String queryartist;  
      
    public  ExpandableAdapter(Activity a)  
    {  
        activity = a;  
        groupArray = new  ArrayList<String>();  
        childArray = new  ArrayList<List<String>>();  
        
        MyDataHelper data = new MyDataHelper(activity,"fengfeng",null, 1);
        SQLiteDatabase db = data.getWritableDatabase();
        //get the artist
        Cursor cursor = db.rawQuery(selectallArtist, null);
        while (cursor.moveToNext()) {
			queryartist = cursor.getString(0);
			System.out.println("the all artist name that i get:-----"+queryartist);
			groupArray.add(queryartist);
		}
        cursor.close();
        
        for (int  index = 0 ; index <groupArray.size(); ++index)  
        {  
        	String selectMusicBytitle="select * from allmusic where title='"+groupArray.get(index).trim()+"'";
        	Cursor cursor2 = db.rawQuery(selectMusicBytitle, null);
        	tempArray = new ArrayList<String>();
              while (cursor2.moveToNext()) {
      			tempArray.add(cursor2.getString(1));
      			System.out.println("the get group string is:>>>>"+cursor2.getString(0)+""+cursor2.getString(1));
      			}
        	childArray.add(tempArray);  
        	cursor2.close();
        }  
        //
        //get the every artist's music name
    }  
    //
    public  Object getChild(int  groupPosition, int  childPosition)  
    {  
        return  childArray.get(groupPosition).get(childPosition);  
    }  
    public  long  getChildId(int  groupPosition, int  childPosition)  
    {  
    return  childPosition;  
    }  
    public  int  getChildrenCount(int  groupPosition)  
    {  
        return  childArray.get(groupPosition).size();  
    }  
    public  View getChildView(int  groupPosition, int  childPosition,  
            boolean  isLastChild, View convertView, ViewGroup parent)  
    {  
        String string = childArray.get(groupPosition).get(childPosition);  
        return  getGenericView(string);  
    }  
    // group method stub   
    public  Object getGroup(int  groupPosition)  
    {  
        return  groupArray.get(groupPosition);  
    }  
    public  int  getGroupCount()  
    {  
        return  groupArray.size();  
    }  
    public  long  getGroupId(int  groupPosition)  
    {  
        return  groupPosition;  
    }  
    public  View getGroupView(int  groupPosition, boolean  isExpanded,  
            View convertView, ViewGroup parent)  
    {  
        String string = groupArray.get(groupPosition);  
        return  getGenericView(string);  
    }  
    // View stub to create Group/Children 's View   
    public  TextView getGenericView(String string)  
    {  
        // Layout parameters for the ExpandableListView   
        AbsListView.LayoutParams layoutParams = new  AbsListView.LayoutParams(  
                ViewGroup.LayoutParams.FILL_PARENT, 64 );  
        TextView text = new  TextView(activity);  
        text.setLayoutParams(layoutParams);  
        // Center the text vertically   
        text.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);  
        // Set the text starting position   
        text.setPadding(36 , 0 , 0 , 0 );  
        text.setText(string);  
        return  text;  
    }  
    public  boolean  hasStableIds()  
    {  
        return  false ;  
    }  
    public  boolean  isChildSelectable(int  groupPosition, int  childPosition)  
    {  
        return  true ;  
    }  
    }  