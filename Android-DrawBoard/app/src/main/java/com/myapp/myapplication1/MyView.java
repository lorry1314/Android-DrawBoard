package com.myapp.myapplication1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.SurfaceHolder.Callback;
import android.view.View;
import android.view.View.OnTouchListener;

public class MyView extends SurfaceView implements Callback ,OnTouchListener{
    private Paint p=new Paint();//画笔对象实例化
    private Path path=new Path();//路径对象实例化

    public MyView(Context context, AttributeSet attrs) {    //构造方法
        super(context, attrs);
        getHolder().addCallback(this);
        //初始化画笔
        p.setColor(Color.BLACK);//设置画笔颜色
        p.setTextSize(15);//设置画笔大小
        p.setStyle(Paint.Style.STROKE);//设置画笔类型为空心
        setOnTouchListener(this);
    }

    //清理画布
    public void clear(){
        path.reset();
        draw();
    }
    //绘画函数
    public void draw(){
        Canvas canvas=getHolder().lockCanvas();//锁定画布
        canvas.drawColor(Color.WHITE);//初始画布颜色
        canvas.drawPath(path,p);//调用drawPath方法进行绘画
        getHolder().unlockCanvasAndPost(canvas);//解锁画布

    }


    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        draw();//surfaceView被执行时调用

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
                path.moveTo(event.getX(), event.getY());
                draw();
            case MotionEvent.ACTION_MOVE:
                path.lineTo(event.getX(),event.getY());
                draw();
        }
        return true;
    }
}
