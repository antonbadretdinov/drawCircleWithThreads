package ru.company.circlestestsamsung;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;

/**
 * Created by Компьютер on 04.04.2017.
 */

public class DrawThread extends Thread {

    private SurfaceHolder surfaceHolder;

    float x,y = -1000;
    float radius = 0;

    private volatile boolean running = true;//флаг для остановки потока

    public DrawThread(Context context, SurfaceHolder surfaceHolder) {
        this.surfaceHolder = surfaceHolder;
    }

    public void requestStop() {
        running = false;
    }

    public void setCircle(float x, float y, float radius){
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    @Override
    public void run() {
        while (running) {
            Canvas canvas = surfaceHolder.lockCanvas();
            Paint paint = new Paint();
            paint.setColor(Color.YELLOW);

            if (canvas != null) {
                try {
                    canvas.drawColor(Color.BLUE);
                    canvas.drawCircle(x,y,radius,paint);
                    // рисование на canvas
                } finally {
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
                    radius+=1;

                try{
                    Thread.sleep(10);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
    }
}
