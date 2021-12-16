
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.PrintWriter;
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
    
    Scanner nightNum;
    private int night;
    
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
                scaryPics[i] = ImageIO.read(new File("MenuAnimation//" + scary[i]));
            }
            
            for (int i = 0; i < images.length; i++) {
                pics[i] = ImageIO.read(new File("MenuAnimation//" + images[i]));
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public int load(int b){
        try{
            if(b == 1){
                nightNum = new Scanner(new File("Config.txt"));
                return nightNum.nextInt();
            }else{
                PrintWriter output = new PrintWriter(new File("Config.txt"));
                output.println(1);
                output.close();
                return 1;
            }
        }catch(Exception e){
            
        }
        return 1;
        
    }
    
    
    
    public BufferedImage getImage(int i){
        return pics[i];
    }
    
    public BufferedImage getScary(int i){
        return scaryPics[i];
    }
}
