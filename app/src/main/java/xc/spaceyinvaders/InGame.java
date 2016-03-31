package xc.spaceyinvaders;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
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

        //Layout consists of game on top and left/right buttons on bottom
        LinearLayout ui = new LinearLayout(this);
        ui.setOrientation(LinearLayout.VERTICAL);
        ui.setBackgroundColor(Color.BLACK);

        LinearLayout controls = new LinearLayout(this);
        controls.setOrientation(LinearLayout.HORIZONTAL);
        controls.setBackgroundColor(Color.DKGRAY);

        //Game Screen
        SurfaceView gameScreen = new SurfaceView(this);
        LinearLayout.LayoutParams gameParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT, 3
        ); //weighted 3/4 ratio of game screen to left/right buttons below
        gameScreen.setBackgroundResource(R.drawable.space);

        /* onTouchListener is more versatile and has more functions than onClickListener */
        gameScreen.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    //ship.shoot();
                    Log.i("button", "shoot");
                    return true;
                }
                return false;
            }
        });

        //Left button
        Button leftButton = new Button(this);
        LinearLayout.LayoutParams leftParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT, 1
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
                return false;
            }
        });


        //Right button
        Button rightButton = new Button(this);
        LinearLayout.LayoutParams rightParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT, 1
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
                return false;
            }
        });


        //Place Views on Layout
        //controls are in a LinearLayout, which is nested in the UI's LinearLayout
        ui.addView(gameScreen, gameParams);
        ui.addView(controls, leftParams);
        controls.addView(leftButton, leftParams);
        controls.addView(rightButton, rightParams);

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

}
