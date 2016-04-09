package xc.spaceyinvaders;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by Jimmy on 2016/4/8.
 */
public class Invaders {

    int width; //screenWidth
    int height; //screenHeight
    int invadersWidth;
    int invadersHight;
    int margin;
    float xi, yi; // initial position of bullets
    float x, y; //
    float vx; //speed of invaders in Y direction
    Bitmap bitmapInvaders;
    boolean isAlive = true;

    public Invaders(Context context, int width, int height, int row, int column) {
        Bitmap tmp = BitmapFactory.decodeResource(context.getResources(), R.drawable.spacey);
        invadersWidth = width/10;
        invadersHight = height/10;
        bitmapInvaders = Bitmap.createScaledBitmap(tmp, invadersWidth, invadersHight, false);
        margin = invadersWidth/4;
        x = column * (invadersWidth + margin);
        y = row * (invadersHight + margin/2);
        vx = 20;
    }

    public void draw(Canvas c){
        Paint p = new Paint();
        p.setColor(Color.RED);

        c.drawBitmap(bitmapInvaders, x, y, p);
    }

    public void update(){

    }

    public boolean getIsAlive(){return isAlive; }
}
