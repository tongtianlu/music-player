package com.zhaofeng.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class ImageUtil {
private static Bitmap bitmap;

public static Bitmap getBitmap(String url){
	File file = new File(url);
	try {
		FileInputStream inputStream = new FileInputStream(file);
		byte[] imagebytes = StreamTool.getBytes(inputStream);
		bitmap = BitmapFactory.decodeByteArray(imagebytes, 0, imagebytes.length);
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	} catch (Exception e) {
		e.printStackTrace();
	}
	return bitmap;
}
}
