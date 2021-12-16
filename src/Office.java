
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
    public static double power = 100;
    public boolean door1 = false;
    public boolean door2 = false;
    public boolean light1 = false;
    public boolean light2 = false; 
    public boolean cam = false;
    
    public BufferedImage getImage(String offices){
        return 
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
