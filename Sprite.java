package com.example.proto.surfaceviewex;

import android.content.Context;
import com.example.proto.surfaceviewex.SurfaceViewEx.OurView;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class Sprite{

    int x, y, xSpeed, ySpeed, height, width;
    Bitmap b;
    OurView ov;
    int currentFrame = 0 ;
    int direction = 0;

    public Sprite(OurView ourView, Bitmap bitmap){
        b = bitmap;
        ov = ourView;
        height = b.getHeight() / 7;
        width = b.getWidth() / 6;
        x = ov.getWidth();
        y = 0;
        xSpeed = 5;
        ySpeed = 0;
    }

    private void update(){

//        /*
//         * 0 = up
//         * 1 = down
//         * 2 = left
//         * 3 = right
//         */

        //going down
        if(x > ov.getWidth() - width - xSpeed){
            xSpeed = 0;
            ySpeed = 5;
            direction = 1;
        }
        //going left
        if(y > ov.getHeight() - height - ySpeed){
            xSpeed =-5;
            ySpeed = 0;
            direction = 2;
        }
        //going up
        if(x + xSpeed < 0){
            x = 0;
            xSpeed = 0;
            ySpeed = -5;
            direction = 0;
        }
        //going right
        if(y + ySpeed < 0){
            y = 0;
            xSpeed = 5;
            ySpeed = 0;
            direction = 3;
        }
//        if(x < 0) {
//            xSpeed = 0;
//            x = 0;
//        }
        try {
            Thread.sleep(50);
        }catch(Exception e){
            e.printStackTrace();
        }

        currentFrame = ++currentFrame % 6;
        x += xSpeed;
        y += ySpeed;
    }

    public void drawIt(Canvas canvas){
        update();
        int srcX = currentFrame * width;
        int srcY = direction * height;
        Rect src = new Rect(srcX, srcY, srcX + width - 35, height + srcY - 70);
        Rect dst = new Rect(x, y, x+width, y+height);
        canvas.drawBitmap(b, src, dst, null);
    }
}
