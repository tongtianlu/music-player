package com.zhaofeng.data;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DatabaseConnection {
	private static MyDataHelper data=null;
	private static SQLiteDatabase db=null;
	private static Cursor cursor=null;
	public static Cursor getCursor(Activity activity,String sql){
		if (data==null) {
			data = new MyDataHelper(activity,"fengfeng",null, 1);
			db = data.getWritableDatabase();
			cursor = db.rawQuery(sql, null);
		}
		db.close();//don't forget to close the database after use.
		return cursor;
	}
}
