package xc.spaceyinvaders;


import android.annotation.TargetApi;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by Aaron on 2016-03-31.
 */
public class Ship extends ImageView {

    /* Constructors needed by ImageView */
    public Ship(Context context) {
        super(context);
    }

    public Ship(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public Ship(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(21)
    public Ship(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    //Start of Ship implementation
    float speed;
    float pos;

    public void setSpeed(float speed){
        this.speed = speed;
    }

    public void moveLeft(){
        pos-=speed;
    }

    public void moveRight(){
        pos+=speed;
    }

    public void shoot(){

    }

}
