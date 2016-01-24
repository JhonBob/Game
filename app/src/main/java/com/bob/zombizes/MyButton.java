package com.bob.zombizes;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.ActionProvider;

/**
 * Created by Administrator on 2016/1/24.
 */
public class MyButton extends Sprite {
    Bitmap pressBitmap;
    boolean isClick;


    private ClickListener clickListener;

    public interface ClickListener{
        void click();
    }

    public void setClickListener(ClickListener clickListener){
        this.clickListener=clickListener;
    }


    public void click(){
        if (clickListener!=null){
            clickListener.click();
        }
    }



    public MyButton(Context context, Point point) {
        super(null, point);
        Bitmap decodeResource= BitmapFactory.decodeResource(context.getResources(), R.mipmap.bottom_default);
        this.defaultBitmap=decodeResource;
        this.pressBitmap=BitmapFactory.decodeResource(context.getResources(), R.mipmap.bottom_press);
    }

    public void setIsClick(boolean isClick) {
        this.isClick = isClick;
    }

    public boolean isClick(Point touchPoint){
        Rect rect=new Rect(this.point.x,this.point.y,this.point.x+defaultBitmap.getWidth()
        ,this.point.y+defaultBitmap.getHeight());
        isClick=rect.contains(touchPoint.x,touchPoint.y);
        return isClick;
    }

    @Override
    public void drawSelf(Canvas canvas) {
        if (isClick){
            canvas.drawBitmap(pressBitmap,this.point.x,this.point.y,null);
        }else {
            super.drawSelf(canvas);
        }
    }
}
