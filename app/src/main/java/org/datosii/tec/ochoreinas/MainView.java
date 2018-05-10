package org.datosii.tec.ochoreinas;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.SurfaceView;
import android.view.SurfaceHolder;
import android.util.AttributeSet;
import android.graphics.Bitmap;
import android.graphics.Matrix;

public class MainView extends SurfaceView implements SurfaceHolder.Callback {

    private MainThread thread;
    private Queen[] queens = new Queen[8];
    private Queen actualQueen;
    private int onQueen = 0;

    public MainView(Context context, AttributeSet attrs) {
        super(context,attrs);

        getHolder().addCallback(this);

        thread = new MainThread(getHolder(),this);
        setFocusable(true);
    }


    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        queens[onQueen] = createQueen( 10, 10);
        actualQueen = queens[onQueen];
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

    public void update(int x,int y){
        MainView mainView = findViewById(R.id.mainview);
        actualQueen.update(x,y);
        mainView.postInvalidate();
    }


    @Override
    public void draw (Canvas canvas){
        super.draw(canvas);
        if (canvas != null) {
            for (Queen queen : queens) {
                if (queen != null) {
                    queen.draw(canvas);
                }
            }
        }
    }

    public void setRun(boolean cond){
        if (cond) {
            thread.setRunning(true);
            thread.start();
        } else if (!cond) {
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
    }

    public Queen createQueen (int x , int y) {
        Bitmap b = BitmapFactory.decodeResource(getResources(),R.drawable.queen);
        return new Queen(getResizedBitmap(b,125,125),x, y);
    }

    public void getOnNewQueen(int x, int y) {
        onQueen++;
        if (onQueen > 7){
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
        queens[onQueen] = createQueen(x, y);
        actualQueen = queens[onQueen];
    }

    public Bitmap getResizedBitmap(Bitmap bm, int newWidth, int newHeight) {
        int width = bm.getWidth();
        int height = bm.getHeight();
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // CREATE A MATRIX FOR THE MANIPULATION
        Matrix matrix = new Matrix();
        // RESIZE THE BIT MAP
        matrix.postScale(scaleWidth, scaleHeight);

        // "RECREATE" THE NEW BITMAP
        Bitmap resizedBitmap = Bitmap.createBitmap(
                bm, 0, 0, width, height, matrix, false);
        bm.recycle();
        return resizedBitmap;
    }
}
