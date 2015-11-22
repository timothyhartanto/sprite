package com.example.proto.surfaceviewex;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

public class SurfaceViewEx extends Activity implements View.OnTouchListener{

    OurView ov;
    Bitmap ball, unit;
    float x, y;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ov = new OurView(this);
        ov.setOnTouchListener(this);
        ball = BitmapFactory.decodeResource(getResources(), R.drawable.blueball);
        unit = BitmapFactory.decodeResource(getResources(), R.drawable.unit);
        x = y = 0;
        setContentView(ov);
    }

    @Override
    protected void onPause(){
        super.onPause();
        ov.pause();
    }

    @Override
    protected void onResume(){
        super.onResume();
        ov.resume();
    }

    public class OurView extends SurfaceView implements Runnable{

        Thread t = null;
        SurfaceHolder holder;
        boolean flagOK = false;
        Sprite sprite;
        //boolean spriteLoaded = false;

        public OurView(Context context){
            super(context);
            holder = getHolder();
        }

        @Override
        public void run() {
            sprite = new Sprite(this, unit);

            while(flagOK){
                //perform canvas drawing
                if(!holder.getSurface().isValid()){
                    continue;
                }

                Canvas c = holder.lockCanvas();
                drawIt(c);
                holder.unlockCanvasAndPost(c);
                }
        }

        protected void drawIt(Canvas canvas){
            canvas.drawARGB(250, 50, 200, 255);
            canvas.drawBitmap(ball, x - (ball.getWidth()), y - (ball.getHeight()), null);
            sprite.drawIt(canvas);
        }

        public void pause(){
            flagOK = false;
            while(true){
                try{
                    t.join();
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
                break;
            }
            t = null;
        }

        public void resume(){
            flagOK = true;
            t = new Thread(this);
            t.start();
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        // uncomment for a lighter process
//        try {
//            Thread.sleep(50);
//        }catch(InterruptedException e){
//            e.printStackTrace();
//        }

        switch(event.getAction()){
            case MotionEvent.ACTION_MOVE:
                x = event.getX();
                y = event.getY();
                break;
            case MotionEvent.ACTION_DOWN:
                x = event.getX();
                y = event.getY();
                break;
            case MotionEvent.ACTION_UP:
                x = event.getX();
                y = event.getY();
                break;
        }
        return true;
    }
}
