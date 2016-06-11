package com.zhaofeng.util;

import android.graphics.Bitmap;
import android.graphics.Matrix;

public class BitmapUtil
{
   public static Bitmap GetNewBitmap(Bitmap oldBitmap,float screenWidth,float screenHeight,float newWidth,float newHeight)
   {
	   //��ȡͼƬԭʼ��С
	    int width = oldBitmap.getWidth();
	    int height = oldBitmap.getHeight();
	   //��ȡ��Ļ��С		 
        float scaleWidth  = newWidth  / width;
        float scaleHeight = newHeight / height;
        //�õ���ͼƬ
        Matrix matrix = new Matrix(); 
        matrix.postScale(scaleWidth,scaleHeight); //���Ϳ�Ŵ���С�ı���
        Bitmap newBitmap = Bitmap.createBitmap(oldBitmap,0,0,width,height,matrix,true);
	    return newBitmap;
   }
}
