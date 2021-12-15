
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Scanner;
import javax.imageio.ImageIO;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ramcj7742
 */
public class MainMenu {
    public String[] images;
    private int numFrames;
    
    public MainMenu(){
        try{
            Scanner input = new Scanner(new File("AnimationImages.txt"));
            numFrames = input.nextInt();
            
            images = new String[numFrames];
            input.nextLine(); //goes to next line
            
            for (int i = 0; i < images.length; i++) {
                images[i] = input.nextLine();
                System.out.println(images[i]);
            }
            
        }catch(Exception e){
            
        }
    }
    
    public BufferedImage getImage(String name){
        BufferedImage img = null;
        try{
            img = ImageIO.read(new File("MenuAnimation//" + name));
            System.out.println(name);
        }catch(Exception e){
            
        }
        return img;
    }
}
