package org.datosii.tec.ochoreinas;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class MainThread extends Thread {
    private SurfaceHolder surfaceHolder;
    private MainView mainView;
    private boolean running;
    public static Canvas canvas;

    public MainThread (SurfaceHolder surfaceHolder, MainView mainView) {

        super();
        this.surfaceHolder = surfaceHolder;
        this.mainView = mainView;
    }

    public void setRunning(boolean isRunnig) {
        running = isRunnig;
    }

    @Override
    public void run(){
        while(running) {
            canvas = null;

            try {
                canvas = this.surfaceHolder.lockCanvas();

                

            } catch (Exception e) {

            }
        }
    }

}
