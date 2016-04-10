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
    Ship ship;
    Bullet[] bullet = new Bullet[200];
    int numOfBullet = 0;
    int numOfShoot = 0;
    Invaders[] invaders = new Invaders[100];
    int numOfInvaders = 0;
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
                //for now, player can only shoot when stopped and multiple bullets can be on screen at a time
                if (event.getY() < getHeight() * 3 / 4){
                    if (event.getActionMasked() == MotionEvent.ACTION_DOWN) {
                        bullet[numOfShoot].setShooting(true);
                        if(numOfShoot < 200) {
                            numOfShoot++;
                        }
                    }
                }
                switch(event.getActionIndex()) {
                    case 0:
                        switch (event.getActionMasked()) {
                            case MotionEvent.ACTION_DOWN:
                                Log.d("Log.debug", "ACTION_DOWN at X=" + Float.toString(event.getX()) + ", Y=" + Float.toString(event.getY()));
                                if (event.getY() > getHeight() * 3 / 4) {
                                    if (event.getX() > getWidth() / 2) {
                                        ship.setMovementState(ship.RIGHT);
                                        lastAction = 0;
                                    } else {
                                        ship.setMovementState(ship.LEFT);
                                        lastAction = 1;
                                    }
                                }
                                break;
                            case MotionEvent.ACTION_UP:
                                ship.setMovementState(ship.STOPPED);
                                lastAction = -1;
                                break;
                            case MotionEvent.ACTION_POINTER_UP: //two buttons pressed, first one released
                                Log.d("Log.debug", "First button released");
                                if (event.getX(1) > getWidth() /2){
                                    ship.setMovementState(ship.RIGHT);
                                }
                                else if (event.getX(1) < getWidth() /2){
                                    ship.setMovementState(ship.LEFT);
                                }
                                break;
                        }
                        return true;
                    case 1: //for handling two-touch events
                        switch(event.getActionMasked()) {
                            case MotionEvent.ACTION_POINTER_DOWN:
                                Log.d("Log.debug", "ACTION_POINTER_DOWN at X= "+Float.toString(event.getX(1)));
                                if (event.getY(1) > getHeight() * 3 / 4) {
                                    if (event.getX(1) < getWidth() / 2 && ship.getMovementState() == ship.RIGHT) { //cancel right movement
                                        ship.setMovementState(ship.STOPPED);
                                    } else if (event.getX(1) > getWidth() / 2 && ship.getMovementState() == ship.LEFT) { //cancel left movement
                                        ship.setMovementState(ship.STOPPED);
                                    }
                                }
                                break;
                            case MotionEvent.ACTION_POINTER_UP:
                                switch(lastAction){
                                    case 0:
                                        ship.setMovementState(ship.RIGHT);
                                        break;
                                    case 1:
                                        ship.setMovementState(ship.LEFT);
                                }
                                break;
                        }
                        return true;
                }
                return false;
            }
        });
        ship=new Ship(this.context,getWidth(), getHeight());
        for(int i=0; i<200; i++) {
            bullet[numOfBullet] = new Bullet(this.context, getWidth(), getHeight(), ship.getX(), ship.getY());
            numOfBullet++;
        }
        for(int column=0; column<7; column++){
            for(int row=0; row<4; row++ ){
                invaders[numOfInvaders] = new Invaders(this.context, getWidth(), getWidth(), row, column);
                numOfInvaders++;
            }
        }


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
        ship.draw(c);
        ship.update();
        for(int i=0; i<numOfBullet; i++) {
            bullet[i].draw(c);
            bullet[i].update(ship.getX());
        }
        for(int i=0; i<numOfInvaders; i++){
            if(invaders[i].isAlive){
                invaders[i].draw(c);
                invaders[i].update();
            }
        }


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
