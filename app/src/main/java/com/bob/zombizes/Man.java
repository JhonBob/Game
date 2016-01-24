package com.bob.zombizes;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;

/**
 * Created by Administrator on 2016/1/24.
 */
public class Man extends Sprite {
    private Context context;
    public static final int DOWN=0;
    private int speed=6;

    public Man(Context context, Point point) {
        super(null, point);
        this.context=context;
        Bitmap decodeResource=BitmapFactory.decodeResource(context.getResources(), R.mipmap.avatar_boy);
        this.defaultBitmap=decodeResource;
    }


    public Smile creatSmile(Point targetPoint){
        Smile smile=new Smile(context,new Point(this.point.x+50,this.point.y+45),targetPoint);
        return smile;
    }

    public void move(int direction){
        if (direction==DOWN){
            this.point.y+=speed;
        }
    }
}
