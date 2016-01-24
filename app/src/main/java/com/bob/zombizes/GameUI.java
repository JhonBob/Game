package com.bob.zombizes;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by Administrator on 2016/1/24.
 */

public class GameUI extends SurfaceView implements SurfaceHolder.Callback {

    private SurfaceHolder holder;
    private RenderThread renderThread;
    private boolean isrun;
    private Canvas lockCanvas;


    private Man man;
    private Smile smile;
    private List<Smile> smiles=new CopyOnWriteArrayList<>();


    private MyButton button;


    public GameUI(Context context) {
        super(context);
        //添加Holder回掉
        holder=getHolder();
        holder.addCallback(this);
    }



    private class RenderThread extends Thread {
        @Override
        public void run() {
            while (isrun) {
                System.out.println("执行线程");
                try {
                    drawUI();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }



    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        man=new Man(getContext(),null);
        button=new MyButton(getContext(),new Point(100,getHeight()-100));
        button.setClickListener(new MyButton.ClickListener() {
            @Override
            public void click() {
                man.move(Man.DOWN);
            }
        });
        renderThread=new RenderThread();
        isrun=true;
        renderThread.start();

    }

    //绘制界面
    public void drawUI(){


        //锁定整块屏幕
        lockCanvas=holder.lockCanvas();
        //对峙图形
        Paint paint=new Paint();
        paint.setColor(Color.WHITE);
        lockCanvas.drawRect(0, 0, getWidth(), getHeight(), paint);


        //解锁画布
        man.drawSelf(lockCanvas);
        button.drawSelf(lockCanvas);
        for (Smile smile:smiles){
            smile.drawSelf(lockCanvas);
            smile.move();
            if (smile.point.x>getWidth()||smile.point.y>getHeight()
                    ||smile.point.x<0||smile.point.y<0){
                //出界回收,CopyOnWriteArrayList可在遍历时，增删元素，但内存开销很大
                smiles.remove(smile);
            }
        }
        holder.unlockCanvasAndPost(lockCanvas);
    }

    //处理屏幕触摸事件
    public void handleTouch(MotionEvent event){

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                int x=(int)event.getRawX();
                int y=(int)event.getRawY();

                if (button.isClick(new Point(x,y))){
                    button.click();
                }else {
                    smile=man.creatSmile(new Point(x,y));
                    smiles.add(smile);
                }

                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                button.setIsClick(false);
                break;
            default:
                break;
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        isrun=false;
    }
}
