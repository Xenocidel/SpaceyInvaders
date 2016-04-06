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

/**
 * Created by Jimmy on 2016/4/5.
 */
public class SpaceView extends SurfaceView implements Runnable{



    Thread gameThread = null;

    SurfaceHolder sh;

    // check if the the game is playing, this variable's value will be modified by different thread
    volatile boolean inGame;

    //game is paused when start
    boolean paused = true;

    Canvas canvas;
    Paint paint;

    long fps;

    //screen size
    int screenX, screenY;


    //ship class declaration
    Ship ship;

    //ship
    Bitmap bitmapShip;



    public SpaceView(Context context, AttributeSet attrs) {
        super(context, attrs);

        paint = new Paint();
        bitmapShip = BitmapFactory.decodeResource(getResources(), R.drawable.ship);
        screenX = getHeight();
        screenY = getWidth();


        //bitmapShip.setHeight(1/10 * screenY);
        //bitmapShip.setWidth(1 / 10 * screenY);
    }

    //constructor
    public SpaceView(Context context){
        super(context);

        paint = new Paint();
        bitmapShip = BitmapFactory.decodeResource(getResources(), R.drawable.ship);
        screenX = getHeight();
        screenY = getWidth();


        //bitmapShip.setHeight(1/10 * screenY);
        //bitmapShip.setWidth(1/10 * screenY);

    }



    @Override
    public void run() {
        sh = this.getHolder();
        while(inGame){

            long startFrameTime = System.currentTimeMillis();

            if(!paused){
                update();}

            draw();

            long actionTime = System.currentTimeMillis() - startFrameTime;
            if(actionTime > 0){
                fps = 1000 / actionTime;
            }
        }

    }

    public void update(){
        ship.update(fps, screenX);
    }

    public void draw(){

        if(sh.getSurface().isValid()){

            //lock canvas
            canvas = sh.lockCanvas();

            //draw background
            canvas.drawColor(Color.BLACK);

            paint.setColor(Color.BLACK);


            //draw ship
            canvas.drawBitmap(bitmapShip, ship.getPosition(), screenY / 2, paint);

            //draw bullet

            //draw spacey(enemy)


            //unlock canvas and show up
            sh.unlockCanvasAndPost(canvas);
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch(event.getAction()){

            case MotionEvent.ACTION_DOWN:
                paused = false;

                if(event.getX() > screenX / 2){
                    ship.setState(ship.RIGHT);
                }else{
                    ship.setState(ship.LEFT);
                }

                break;

            case MotionEvent.ACTION_UP:
                paused = true;

                ship.setState(ship.STOPPED);

                break;

        }

        return true;
    }

    public void pause(){

        inGame = false;

        try{
            gameThread.join();
        }catch(InterruptedException e){

        }
    }

    public void resume(){

        inGame = true;
        gameThread = new Thread(this);
        gameThread.start();

    }
}