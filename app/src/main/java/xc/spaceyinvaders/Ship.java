package xc.spaceyinvaders;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Ship {

    private int shipSpeed;

    public final int STOPPED = 0;
    public final int LEFT = 1;
    public final int RIGHT = 2;

    private int shipPosition;


    private int shipMovingState = STOPPED;

    //constructor of Ship
    public Ship(int screenX){

        shipPosition = screenX / 2;

        shipSpeed = 500;
    }

    public void setState(int state){
        shipMovingState = state;
    }

    public void update(long fps, int screenX){
        if(shipMovingState == LEFT){
            shipPosition -= shipSpeed /fps;
            if(shipPosition < 0){
                shipPosition = 0;
            }
        }

        if(shipMovingState == RIGHT){
            shipPosition += shipSpeed / fps;
            if(shipPosition > screenX){
                shipPosition = screenX;
            }
        }
    }

    public int getPosition(){
        return  shipPosition;
    }
}
