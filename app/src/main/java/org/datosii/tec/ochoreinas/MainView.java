package org.datosii.tec.ochoreinas;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.SurfaceView;
import android.view.SurfaceHolder;
import android.util.AttributeSet;
import android.graphics.Paint;
import android.graphics.Color;

public class MainView extends SurfaceView implements SurfaceHolder.Callback {

    private MainThread thread;
    private Queen queen;

    public MainView(Context context, AttributeSet attrs) {
        super(context,attrs);

        getHolder().addCallback(this);

        thread = new MainThread(getHolder(),this);
        setFocusable(true);

    }


    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        queen = new Queen(BitmapFactory.decodeResource(getResources(),R.drawable.queen));

        thread.setRunning(true);
        thread.start();

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;

        while(retry) {
            try{
                thread.setRunning(false);
                thread.join();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            retry = false;
        }

    }

    public void update(){
        queen.update();
    }

    @Override
    public void draw (Canvas canvas){
        super.draw(canvas);
        if (canvas != null) {
            queen.draw(canvas);
//            canvas.drawColor(Color.WHITE);
//            canvas.drawBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.table),
//                    0,0,null);
//            Paint paint = new Paint();
//            paint.setColor(Color.rgb(250, 0, 0));
//            canvas.drawRect(100, 100, 200, 200, paint);
        }
    }
}
