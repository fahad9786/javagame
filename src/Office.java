
import java.awt.image.BufferedImage;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author ramcj7742
 */
public class Office {

    // create instance variavles 
    private double power = 100;
    private boolean door1 = false;
    private boolean door2 = false;
    private boolean light1 = false;
    private boolean light2 = false;
    private boolean cam = false;
    private long last = System.currentTimeMillis();
    private int totalOn;

    public Office() {

    }

    //resets the power
    public void reset() {
        this.power = 100;
        this.door1 = false;
        this.door2 = false;
        this.light1 = false;
        this.light2 = false;
        this.cam = false;
    }

    // setter classes  
    public boolean setDoor1() {
        if (door1 == false) {
            door1 = true;
        } else if (door1 == true) {
            door1 = false;
        }
        return door1;
    }

    public boolean setDoor2() {
        if (door2 == false) {
            door2 = true;
        } else if (door2 == true) {
            door2 = false;
        }
        return door2;
    }

    public boolean setLight1() {
        if (light1 == false) {
            light1 = true;
        } else if (light1 == true) {
            light1 = false;
        }
        return light1;
    }

    public boolean setLight2() {
        if (light2 == false) {
            light2 = true;
        } else if (light2 == true) {
            light2 = false;
        }
        return light2;
    }

    public boolean setCam() {
        if (cam == false) {
            cam = true;
        } else if (cam == true) {
            cam = false;
        }
        return cam;
    }
    
    public void setDoor1spec(boolean bool){
        if(bool == true){
            door1 = true;
        }else{
            door1 = false;
        }
    }
    
    public void setDoor2spec(boolean bool){
        if(bool == true){
            door2 = true;
        }else{
            door2 = false;
        }
    }
    
    public void setLight1spec(boolean bool){
        if(bool == true){
            light1 = true;
        }else{
            light1 = false;
        }
    }
    
    public void setLight2spec(boolean bool){
        if(bool == true){
            light2 = true;
        }else{
            light2 = false;
        }
    }

    public boolean getDoor1() {
        return door1;
    }

    public boolean getDoor2() {
        return door2;
    }

    public boolean getLight1() {
        return light1;
    }

    public boolean getLight2() {
        return light2;
    }

    public boolean getCam() {
        return cam;
    }

    public BufferedImage getImage(String offices) {
        return null;
    }

    // getter method
    public double getPower() {
        return power;
    }
    
    //returns an int representing the total number of electronics being used
    public int totalOn(){
        totalOn = 0;
        if(door1){
            totalOn++;
        }
        if(door2){
            totalOn++;
        }
        if(light1){
            totalOn++;
        }
        if(light2){
            totalOn++;
        }
        if(cam){
            totalOn++;
        }
        return totalOn;
    }

    //sets all elecronics off when power is out
    public void allOff() {
        door1 = false;
        door2 = false;
        light1 = false;
        light2 = false;
        cam = false;
    }

    public void decreasePower(long time) {
        if (power > 0) {
            if ((time - last) / 100 > 20) {
                // subtract from the power when nothings being used every ...time
                power = power - 0.5;

                // subtract power when different electronics used
                if (door1 == true) {
                    power = power - 1;
                }

                if (door2 == true) {
                    power = power - 1;
                }

                if (light1 == true) {
                    power = power - 1;
                }

                if (light2 == true) {
                    power = power - 1;
                }

                if (cam == true) {
                    power = power - 0.3;
                }
                last = System.currentTimeMillis();
            }
        }
    }

}
