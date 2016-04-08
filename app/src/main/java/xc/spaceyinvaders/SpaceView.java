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
    Bullet b;
    ImageView leftArrow;
    ImageView rightArrow;
    SpaceThread st ;
    @Override
    public void surfaceCreated ( SurfaceHolder holder ) {
        //ship movement
        setOnTouchListener(new OnTouchListener() {
            int lastAction = -1;
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getActionIndex()) {
                    case 0:
                        switch (event.getActionMasked()) {
                            case MotionEvent.ACTION_DOWN:
                                Log.d("Log.debug", "ACTION_DOWN at X=" + Float.toString(event.getX()) + ", Y=" + Float.toString(event.getY()));
                                if (event.getY() > getHeight() * 3 / 4) {
                                    if (event.getX() > getWidth() / 2) {
                                        s.setMovementState(s.RIGHT);
                                        lastAction = 0;
                                    } else {
                                        s.setMovementState(s.LEFT);
                                        lastAction = 1;
                                    }
                                }
                                break;
                            case MotionEvent.ACTION_UP:
                                s.setMovementState(s.STOPPED);
                                lastAction = -1;
                                break;
                            case MotionEvent.ACTION_POINTER_UP: //two buttons pressed, first one released
                                Log.d("Log.debug", "First button released");
                                if (event.getX(1) > getWidth() /2){
                                    s.setMovementState(s.RIGHT);
                                }
                                else if (event.getX(1) < getWidth() /2){
                                    s.setMovementState(s.LEFT);
                                }
                                break;
                        }
                        return true;
                    case 1: //for handling two-touch events
                        switch(event.getActionMasked()) {
                            case MotionEvent.ACTION_POINTER_DOWN:
                                Log.d("Log.debug", "ACTION_POINTER_DOWN at X= "+Float.toString(event.getX(1)));
                                if (event.getY(1) > getHeight() * 3 / 4) {
                                    if (event.getX(1) < getWidth() / 2 && s.getMovementState() == s.RIGHT) { //cancel right movement
                                        s.setMovementState(s.STOPPED);
                                    } else if (event.getX(1) > getWidth() / 2 && s.getMovementState() == s.LEFT) { //cancel left movement
                                        s.setMovementState(s.STOPPED);
                                    }
                                }
                                break;
                            case MotionEvent.ACTION_POINTER_UP:
                                switch(lastAction){
                                    case 0:
                                        s.setMovementState(s.RIGHT);
                                        break;
                                    case 1:
                                        s.setMovementState(s.LEFT);
                                }
                                break;
                        }
                        return true;
                }
                return false;
            }
        });
        s=new Ship(this.context,getWidth(), getHeight());
        b = new Bullet(this.context, getWidth(), getHeight(), s.getX(), s.getY());

        //todo images not showing, not a high priority
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

        // Launch animator thread
        st = new SpaceThread(this);
        st.start();
    }

    @Override
    public void draw(Canvas c) {
        super.draw(c);
        c.drawColor(Color.BLACK);
        s.draw(c);
        s.update();
        b.draw(c);
        b.update(s.getX());
        rightArrow.draw(c);
        leftArrow.draw(c);
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
