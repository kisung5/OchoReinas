package org.datosii.tec.ochoreinas;

import android.content.Context;
import android.view.SurfaceView;
import android.view.SurfaceHolder;

public class MainView extends SurfaceView implements SurfaceHolder.Callback {

    public MainView(Context context) {
        super(context);

        getHolder().addCallback(this);
    }


    @Override
    public void surfaceCreated(SurfaceHolder holder) {

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }
}
