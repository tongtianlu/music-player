package com.zhaofeng.util;  
  
import android.content.Context;  
import android.view.View;  
import android.view.View.OnClickListener;  
import android.view.ViewGroup.LayoutParams;  
import android.widget.ImageView;  
import android.widget.ImageView.ScaleType;  
import android.widget.LinearLayout;  
import android.widget.Toast;  
  
import java.util.ArrayList;  

import com.zhaofeng.main.R;
  
public class SlideImageLayout {  
    private ArrayList<ImageView> mImageList = null;  
    private Context mContext = null;  
    private ImageView[] mImageViews = null;   
    private ImageView mImageView = null;  
    private int pageIndex = 0;  
      
    public SlideImageLayout(Context context) {  
        this.mContext = context;  
        mImageList = new ArrayList<ImageView>();  
    }  
      
    public View getSlideImageLayout(int id){  
        LinearLayout imageLinerLayout = new LinearLayout(mContext);  
        LinearLayout.LayoutParams imageLinerLayoutParames = new LinearLayout.LayoutParams(  
                LinearLayout.LayoutParams.FILL_PARENT,   //婊氬姩鍥剧墖鐨勫～鍏呮柟寮�
                LinearLayout.LayoutParams.FILL_PARENT,  
                1);  
          
        ImageView iv = new ImageView(mContext);  
        iv.setBackgroundResource(id);  
        iv.setOnClickListener(new ImageOnClickListener());  
        imageLinerLayout.addView(iv,imageLinerLayoutParames);  
        mImageList.add(iv);  
          
        return imageLinerLayout;  
    }  
      
    public View getLinearLayout(View view,int width,int height){  
        LinearLayout linerLayout = new LinearLayout(mContext);  
        LinearLayout.LayoutParams linerLayoutParames = new LinearLayout.LayoutParams(width,height,1);  
        linerLayout.setPadding(2, 0, 2, 0);  
        linerLayout.addView(view, linerLayoutParames);  
          
        return linerLayout;  
    }  
      
    public void setCircleImageLayout(int size){  
        mImageViews = new ImageView[size];  
    }  
      
    public ImageView getCircleImageLayout(int index){  
        mImageView = new ImageView(mContext);    
        mImageView.setLayoutParams(new LayoutParams(10,10));  
        mImageView.setScaleType(ScaleType.CENTER);  
          
        mImageViews[index] = mImageView;  
           
        if (index == 0) {    
            mImageViews[index].setBackgroundResource(R.drawable.club_pin_blue);    
        } else {    
            mImageViews[index].setBackgroundResource(R.drawable.club_pin_gray);    
        }    
           
        return mImageViews[index];  
    }  
      
    public void setPageIndex(int index){  
        pageIndex = index;  
    }  
      
    private class ImageOnClickListener implements OnClickListener{  
        public void onClick(View v) {  
            Toast.makeText(mContext,pageIndex,1000).show();  
        }  
    }  
   
}  