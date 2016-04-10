package xc.spaceyinvaders;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.Log;

/**
 * Created by Jimmy on 2016/4/8.
 */
public class Bullet {

    int width; //screenWidth
    int height; //screenHeight
    int bulletWidth;
    int bulletHeight;
    int margin;
    float xi, yi; // initial position of bullets
    float x, y; //
    float vy; //speed of bullets in Y direction
    Bitmap bitmapBullet;
    boolean isShooting;

    public Bullet(Context context, int width, int height, float shipXPosition, float shipYPosition) {
        Bitmap tmp = BitmapFactory.decodeResource(context.getResources(), R.drawable.bullet);
        bulletWidth = width/15;
        bulletHeight = height/25;
        bitmapBullet = Bitmap.createScaledBitmap(tmp, bulletWidth, bulletHeight, false);
        vy = 40;
        this.width = width;
        this.height = height;
        margin = bulletWidth/2;
        xi = (float)(shipXPosition + margin * 1.6); //+margin*1.6 so that bullet comes out of ship's cavity
        yi = shipYPosition-margin;
        x = xi;
        y = yi;
        isShooting = false;
        Log.d("Log.DEBUG_BULLET", "width=" + width + " height=" + height);
    }

    public void draw(Canvas c){
        Paint p = new Paint();
        p.setColor(Color.RED);
        if (isShooting) {
            c.drawBitmap(bitmapBullet, x, y, p);
        }
    }

    //public void update(float shipXPosition, enemiesPositions
    public void update(float shipXPosition){
        x = (float)(shipXPosition + margin * 1.6); //+margin*1.6 so that bullet comes out of ship's cavity
        if (isShooting) {
            float tmpY;
            tmpY = y - vy;
            if (tmpY < 0) { //todo add collision logic
                setShooting(false);
            }
            y = tmpY;
        }
        else{
            y = yi;
        }
    }

    public void setShooting(boolean shooting){
        isShooting = shooting;
    }
    
    public float getXPosiotn(){return x;}
    public float getYPosiotn(){return y;}
}
