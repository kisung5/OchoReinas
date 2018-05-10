package org.datosii.tec.ochoreinas;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Queen {

    private Bitmap image;
    private int x,y;

    public Queen (Bitmap bmp , int x , int y) {
        image = bmp;
        this.x = x;
        this.y = y;
    }

    public void draw(Canvas canvas) {

        canvas.drawBitmap(image, x, y,null);
    }

    public void update(int x, int y){

        this.x = x;
        this.y = y;
    }
}
