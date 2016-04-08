package xc.spaceyinvaders;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
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

    public Bullet(Context context, int width, int height, float shipXPosition, float shipYPosition) {
        Bitmap tmp = BitmapFactory.decodeResource(context.getResources(), R.drawable.bullet);
        bulletWidth = width/15;
        bulletHeight = height/25;
        bitmapBullet = Bitmap.createScaledBitmap(tmp, bulletWidth, bulletHeight, false);
        vy = 300;
        this.width = width;
        this.height = height;
        margin = bulletWidth/2;
        xi = shipXPosition+margin;
        yi = shipYPosition-margin;
        x = xi;
        y = yi;
        Log.d("Log.DEBUG_BULLET", "width=" + width + " height=" + height);
    }

    public void draw(Canvas c){
        Paint p = new Paint();
        p.setColor(Color.RED);

        c.drawBitmap(bitmapBullet, x, y, p);
    }


    public void update(float shipXPositon){
        float tmpY;
        tmpY= y - vy;
        if(tmpY<0){
            tmpY = yi;
            x = shipXPositon + margin;
        }
        y = tmpY;


    }
}
