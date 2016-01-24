package com.bob.zombizes;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;

/**
 * Created by Administrator on 2016/1/24.
 */
public class Smile extends Sprite {
    private Point targetPoint;
    int speed=6;
    private int dx;
    private int dy;

    public Smile(Context context, Point point,Point targetPoint) {
        super(null, point);
        this.targetPoint=targetPoint;
        Bitmap decodeResource=BitmapFactory.decodeResource(context.getResources(), R.mipmap.rating_small);
        this.defaultBitmap=decodeResource;

        //坐标运算
        int X=targetPoint.x-point.x;
        int Y=targetPoint.y-point.y;
        int D=(int)Math.sqrt(X * X + Y * Y);
        dx=speed*X/D;
        dy=speed*Y/D;
    }

    public void move(){
        this.point.x+=dx;
        this.point.y+=dy;
    }
}
