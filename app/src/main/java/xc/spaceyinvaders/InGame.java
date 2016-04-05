package xc.spaceyinvaders;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;

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

        //Layout consists of 0.1 ufo, 0.9 gameScreen, 0.2 controls (sum = 1.1)
        setContentView(R.layout.activity_ingame);
        final Ship ufo = (Ship)findViewById(R.id.ufo);
        final SpaceView gameScreen = (SpaceView)findViewById(R.id.gameScreen);
        final ImageButton leftButton = (ImageButton)findViewById(R.id.leftButton);
        final ImageButton rightButton = (ImageButton)findViewById(R.id.rightButton);

        //arbitrary speeds
        ufo.setSpeed(9);
        //ship.setSpeed(3);

        //Upon tapping the ship or gameScreen, call shoot
        gameScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ship.shoot();
                Log.i("button", "shoot");
            }
        });

        //Controls will use onTouchListener for hold functionality
        //Left button
        leftButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //ship.moveLeft();
                //Log.i("button", Float.toString(ship.pos));
                return true;
            }
        });


        //Right button
        rightButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //ship.moveRight();
                //Log.i("button", Float.toString(ship.pos));
                return true;
            }
        });

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
