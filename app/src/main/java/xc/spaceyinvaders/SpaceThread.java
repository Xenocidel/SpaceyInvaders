package xc.spaceyinvaders;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

/**
 * Created by Aaron on 2016-04-05.
 */
public class SpaceThread extends Thread {
    SpaceView sv;
    boolean gameLoaded = false;
    public SpaceThread(SpaceView sv) {
        this.sv=sv;
    }
    public void run() {
        SurfaceHolder sh = sv.getHolder();
        // Main game loop.
        while( !Thread.interrupted() ) {
        //You might want to do game specific processing in a method you call here
            if (!gameLoaded) { //loading is done in the thread to prevent screen freezing effect
                sv.loadGame();
                gameLoaded = true;
            }
            Canvas c = sh.lockCanvas(null);
            try {
                synchronized(sh) {
                    sv.draw(c);
                    sv.update();
                }
            } catch (Exception e) {
            } finally {
                if ( c != null ) {
                    sh.unlockCanvasAndPost(c);
                }
            }
            // Set the frame rate by setting this delay
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                // Thread was interrupted while sleeping.
                return;
            }
        }
    }
}