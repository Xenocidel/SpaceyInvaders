package xc.spaceyinvaders;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ActionMenuView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import xc.spaceyinvaders.R;

public class InGame extends AppCompatActivity {

    boolean inGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //fullscreen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        inGame = true;

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        //Layout consists of 7% saucer, 70% gameScreen, 8% ship, 15% controls
        LinearLayout ui = new LinearLayout(this);
        ui.setOrientation(LinearLayout.VERTICAL);
        ui.setBackgroundColor(Color.BLACK);

        LinearLayout.LayoutParams gameParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                (int)(metrics.heightPixels*0.7)
        );

        //saucer
        Ship saucer = new Ship(this, 6, -100, R.drawable.ship);
        //saucer.setImageResource(R.drawable.ship);

        //gameScreen
        SurfaceView gameScreen = new SurfaceView(this);
        gameScreen.setBackgroundResource(R.drawable.spacey);

        /* onTouchListener is more versatile and has more functions than onClickListener */
        gameScreen.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    //ship.shoot();
                    Log.i("button", "shoot");
                    return true;
                }
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    //ship.shootStop();
                    Log.i("button", "shootStop");
                    return true;
                }
                return false;
            }
        });

        //Ship
        Ship ship = new Ship(this, 3, metrics.widthPixels/2, R.drawable.ship);
        //aspect ratio of ship image is 0.75916:1
        LinearLayout.LayoutParams shipParams = new LinearLayout.LayoutParams(
                (int)(metrics.heightPixels*0.09*0.75916),
                (int)(metrics.heightPixels*0.09)
        );
        ship.setImageResource(R.drawable.ship);

        //Left button
        Button leftButton = new Button(this);
        LinearLayout.LayoutParams leftParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT, 1
        );
        leftButton.setBackgroundResource(R.drawable.leftbutton);
        leftButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    //ship.moveLeft();
                    Log.i("button", "left");
                    return true;
                }
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    //ship.moveLeftStop();
                    Log.i("button", "leftStop");
                    return true;
                }
                return false;
            }
        });


        //Right button
        Button rightButton = new Button(this);
        LinearLayout.LayoutParams rightParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT, 1
        );
        rightButton.setBackgroundResource(R.drawable.rightbutton);
        rightButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    //ship.moveRight();
                    Log.i("button", "right");
                    return true;
                }
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    //ship.moveRightStop();
                    Log.i("button", "rightStop");
                    return true;
                }
                return false;
            }
        });

        //controls is a nested LinearLayout for the 2 side-by-side buttons
        LinearLayout controls = new LinearLayout(this);
        controls.setOrientation(LinearLayout.HORIZONTAL);
        controls.setBackgroundColor(Color.DKGRAY);

        LinearLayout.LayoutParams controlsParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                (int)(metrics.heightPixels*0.15)
        );

        //Place Views on Layout
        ui.addView(saucer, shipParams);
        ui.addView(gameScreen, gameParams);
        ui.addView(ship, shipParams);
        controls.addView(leftButton, leftParams);
        controls.addView(rightButton, rightParams);
        ui.addView(controls, controlsParams);

        //Draw
        setContentView(ui);

    }


    @Override
    protected void onStart() {
        super.onStart();
    }


    @Override
    protected void onResume() {
        super.onResume();
    }


    @Override
    protected void onPause() {
        super.onPause();
    }


    @Override
    protected void onStop() {
        super.onStop();
    }


    @Override
    protected void onRestart() {
        super.onRestart();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }


    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    //key inputs
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //back button or keyboard escape
        if (keyCode == KeyEvent.KEYCODE_BACK || keyCode == KeyEvent.KEYCODE_ESCAPE) {
            finish();
            Intent intent = new Intent(getApplicationContext(), FullscreenActivity.class);
            startActivity(intent);
            return true;
        }

        //keyboard input left
        if (keyCode == KeyEvent.KEYCODE_DPAD_LEFT || keyCode == KeyEvent.KEYCODE_W) {
            //ship.moveLeft();
            return true;
        }

        //keyboard input right
        if (keyCode == KeyEvent.KEYCODE_DPAD_RIGHT || keyCode == KeyEvent.KEYCODE_D) {
            //ship.moveRight();
            return true;
        }

        //keyboard input shoot
        if (keyCode == KeyEvent.KEYCODE_SPACE) {
            //ship.shoot();
            return true;
        }
        return false;
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        //keyboard input left
        if (keyCode == KeyEvent.KEYCODE_DPAD_LEFT || keyCode == KeyEvent.KEYCODE_W) {
            //ship.moveLeftStop();
            return true;
        }

        //keyboard input right
        if (keyCode == KeyEvent.KEYCODE_DPAD_RIGHT || keyCode == KeyEvent.KEYCODE_D) {
            //ship.moveRightStop();
            return true;
        }

        //keyboard input shoot
        if (keyCode == KeyEvent.KEYCODE_SPACE) {
            //ship.shootStop();
            return true;
        }
        return false;
    }
}
