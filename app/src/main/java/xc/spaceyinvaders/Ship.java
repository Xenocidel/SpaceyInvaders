package xc.spaceyinvaders;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

public class Ship {

    int width;
    int height;
    float x,y;
    float vx; // speed of ship in x direction
    Bitmap bitmapShip;

    final int STOPPED = 0;
    final int LEFT = 1;
    final int RIGHT = 2;
    int shipMoving  =STOPPED;



    public Ship(Context context, int width, int height) {
        Bitmap originalBitmap =
                BitmapFactory.decodeResource(context.getResources(), R.drawable.ship);
        Bitmap resizedBitmap =
                Bitmap.createScaledBitmap(originalBitmap, width/6, height/8, false);
        bitmapShip = resizedBitmap;
        x= width / 2 - width/12; //bottom center of screen
        y= height - height/8;
        vx=40;
        this.width=width;
        this.height=height;
        Log.d("Log.DEBUG", "width=" + width + " height=" + height);
    }

    void draw(Canvas c) {
        Paint p = new Paint();
        p.setColor(Color.RED);

        c.drawBitmap(bitmapShip, x, y, p);
    }

    void update() {
        if(shipMoving == LEFT) {
            x = x - vx;
            if (x <= 0 ) {
                x = 0;
            }
        }

        if(shipMoving == RIGHT){
            x = x + vx;
            if (x >= (width-200)) {
                x = width - 200;
            }
        }
    }

    public void setMovementState(int state){
        shipMoving = state;
    }

    public float getX(){
        return x;
    }
}
