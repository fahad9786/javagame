
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
    public BufferedImage[] pics;
    private int numFrames;
    
    public MainMenu(){
        try{
            Scanner input = new Scanner(new File("AnimationImages.txt"));
            numFrames = input.nextInt();
            
            images = new String[numFrames];
            pics = new BufferedImage[numFrames];
            input.nextLine(); //goes to next line
            
            for (int i = 0; i < images.length; i++) {
                images[i] = input.nextLine();
            }
            
            for (int i = 0; i < images.length; i++) {
                pics[i] = ImageIO.read(new File("MenuAnimation//" + images[i]));
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public BufferedImage getImage(int i){
        return pics[i];
    }
}
