package xc.spaceyinvaders;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by Aaron on 2016-04-05.
 */
public class SpaceView extends SurfaceView implements SurfaceHolder.Callback {
    public SpaceView(Context context) {
        super(context);
        getHolder().addCallback(this);
        setFocusable(true);
        //Initialize game state variables
    }

    public SpaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        getHolder().addCallback(this);
        setFocusable(true);
        //Initialize game state variables
    }

    public SpaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        getHolder().addCallback(this);
        setFocusable(true);
        //Initialize game state variables
    }

    @TargetApi(21)
    public SpaceView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        getHolder().addCallback(this);
        setFocusable(true);
        //Initialize game state variables
    }

    SpaceThread st;
    int numCol = 5;
    int numRow = 4;

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        //Construct game initial state
        Ship[][] enemies = new Ship[numCol][numRow];
        for (int x = 0; x<numCol; x++){
            for (int y = 0; y<numRow; y++){
                enemies[x][y] = new Ship(getContext());
            }
        }
        //Launch animator thread
        st = new SpaceThread(this);
        st.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.BLACK); //to reset the canvas to black
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        // Respond to surface changes, e.g., aspect ratio changes
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        st.interrupt();
    }
}
