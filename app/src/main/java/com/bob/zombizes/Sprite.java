package com.bob.zombizes;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;

/**
 * Created by Administrator on 2016/1/24.
 */

//精灵
public class Sprite {
    //默认图片
    protected Bitmap defaultBitmap;
    //点阵
    protected Point point;

    //构造
    public Sprite(Bitmap defaultBitmap, Point point){
        super();
        this.defaultBitmap=defaultBitmap;
        if (point==null){
            this.point=new Point(0,0);
        }else {
            this.point = point;
        }
    }

    //绘制自身
    public void drawSelf(Canvas canvas) {
        if (defaultBitmap != null) {
            canvas.drawBitmap(defaultBitmap, point.x, point.y, null);

        }
    }
}
