package xc.spaceyinvaders;


import android.content.Context;
import android.widget.ImageView;

/**
 * Created by Aaron on 2016-03-31.
 */
public class Ship extends ImageView {
    int speed;
    double loc;
    int resId; //resource ID to draw

    public Ship(Context context, int speed, double startLoc, int resId) {
        super(context);
        this.speed = speed;
        this.loc = startLoc;
        this.resId = resId;
    }

    public void moveLeft(){


    }

    public void moveRight(){


    }

    public void shoot(){


    }

    public void moveLeftStop(){


    }

    public void moveRightStop(){


    }

    public void shootStop(){


    }

}
