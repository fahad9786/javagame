
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
    private BufferedImage[] pics;
    private int numFrames;
    
    private int numScary;
    public String[] scary;
    private BufferedImage[] scaryPics;
    
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
            
            numScary = input.nextInt();
            scary = new String[numScary];
            scaryPics = new BufferedImage[numScary];
            input.nextLine();
            for (int i = 0; i < scary.length; i++) {
                scary[i] = input.nextLine();
            }
            

            for (int i = 0; i < scary.length; i++) {
                System.out.println(scary[i]);
                scaryPics[i] = ImageIO.read(new File("MenuAnimation//" + scary[i]));
                System.out.println(scaryPics[i]);
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
    
    public BufferedImage getScary(int i){
        return scaryPics[i];
    }
}
