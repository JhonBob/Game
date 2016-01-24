package com.bob.zombizes;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;

// 做游戏的原则：面向对象
public class MainActivity extends Activity {

    private  GameUI UI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UI=new GameUI(this);
        setContentView(UI);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        UI.handleTouch(event);
        return true;
    }
}
