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
    SpaceView spaceView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //fullscreen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        spaceView = new SpaceView(this);
        setContentView(spaceView);

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
