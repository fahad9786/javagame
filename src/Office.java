
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
    private static double power = 100;
    private boolean door1 = false;
    private boolean door2 = false;
    private boolean light1 = false;
    private boolean light2 = false; 
    private boolean cam = false;
    
    // setter classes  
    public boolean setDoor1(){
        if(door1 == false){
         door1 = true;
        }  
     return door1;
    }
    
    public boolean setDoor2(){
        if(door2 == false){
            door2 = true;
        }
        return door2;
    }
    
    public boolean light1(){
        if(light1 == false){
            light1 = true;
        }
        return light1;
    }
    
    public boolean light2(){
        if(light2 == false){
            light2 = true;
        }
        return light2;
    }
    
    public boolean cam(){
        if(cam == false){
            cam = true;
        }
        return cam;
    }
    
    
    public BufferedImage getImage(String offices){
        return null;
    }
    
     
    
    // getter method
    public  double getPower(){
        return power;
    }
    
    
    public void decreasePower(){
        // subtract 5 from the power when nothings being used every ...time
        power = power - .5;
        
        // subtract power when different electronics used
        if(door1 ==  true){
            power = power - 1;
        }
        
        if(door2 == true){
            power = power - 1;
        }
        
        if(light1 == true){
            power = power - 1;
        }
        
        if(light2 == true){
            power = power - 1;
        }        
        
        if(cam == true){
            power = power - 1;
        }
    }
    
    
}
