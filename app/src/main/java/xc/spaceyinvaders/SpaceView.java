package xc.spaceyinvaders;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.media.AudioAttributes;
import android.media.SoundPool;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.ImageView;

/**
 * Created by Jimmy on 2016/4/5.
 */
public class SpaceView extends SurfaceView implements SurfaceHolder. Callback{


    public SpaceView(Context context) { super(context) ;
        this.context = context;
        getHolder (). addCallback(this);
        setFocusable(true);
    }

    Context context;
    Ship s;
    ImageView leftArrow;
    ImageView rightArrow;
    SpaceThread st ;
    boolean paused = true;
    @Override
    public void surfaceCreated ( SurfaceHolder holder ) {
        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        Log.d("Log.debug", "ACTION_DOWN at X="+Float.toString(event.getX())+", Y="+Float.toString(event.getY()));
                        paused = false;
                        if (event.getY() < getHeight()*3/4) {
                            if (event.getX() > getWidth() / 2) {
                                s.setMovementState(s.RIGHT);
                            } else {
                                s.setMovementState(s.LEFT);
                            }
                        }
                        return true;

                    case MotionEvent.ACTION_UP:
                        paused = true;
                        s.setMovementState(s.STOPPED);
                        Log.d("Log.debug", "ACTION_UP");
                        return true;
                }
                return false;
            }
        });
        // Launch animator thread .
        s=new Ship(this.context,getWidth(), getHeight());
        leftArrow = new ImageView(this.context);
        leftArrow.setImageResource(R.drawable.leftbutton);
        leftArrow.setAlpha(0.2f);
        leftArrow.setAdjustViewBounds(true);
        leftArrow.setMaxWidth(getWidth() / 2);
        leftArrow.setMaxHeight(getHeight() / 4);
        leftArrow.setX(0);
        leftArrow.setY(getHeight() - getHeight() / 4);
        leftArrow.setScaleType(ImageView.ScaleType.FIT_CENTER);

        rightArrow = new ImageView(this.context);
        rightArrow.setImageResource(R.drawable.rightbutton);
        rightArrow.setAlpha(0.2f);
        rightArrow.setAdjustViewBounds(true);
        rightArrow.setMaxWidth(getWidth() / 2);
        rightArrow.setMaxHeight(getHeight() / 4);
        rightArrow.setX(getWidth()/2);
        rightArrow.setY(getHeight() - getHeight() / 4);
        rightArrow.setScaleType(ImageView.ScaleType.FIT_CENTER);

        st = new SpaceThread(this);
        st.start();
    }

    @Override
    public void draw(Canvas c) {
        super.draw(c);
        c.drawColor(Color.BLACK);
        //Draw the circle
        s.draw(c);
        s.update();

    }

    @Override
    public void surfaceChanged ( SurfaceHolder holder,
                                 int format , int width , int height ) { // Respond to surface changes , e.g. ,
    }
    @Override
    public void surfaceDestroyed ( SurfaceHolder holder ) {
        // The cleanest way to stop a thread is by interrupting it. // SpaceThread regularly checks its interrupt flag. st.interrupt();
    }
}
