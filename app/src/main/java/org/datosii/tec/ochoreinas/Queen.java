package org.datosii.tec.ochoreinas;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Queen {

    private Bitmap image;
    private int x,y;

    public Queen (Bitmap bmp) {
        image = bmp;
        x = 100;
        y = 100;
    }

    public void draw(Canvas canvas) {

        canvas.drawBitmap(image,100,100,null);
    }

    public void update(){
        y++;
    }
}
