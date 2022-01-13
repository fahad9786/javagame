
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
 * @author pione
 */
public class Jaden extends Fahad{
    private BufferedImage jad1;
    private int roomNum;
    private int difficulty;
    private double chance;
    private int moveTime = 75;
    private int camNum;
    private boolean inRoom = false;
    
    private long startTime;
    
    public Jaden(Office o, Player p, MainMenu m){
        super(o, p, m);
        try{
            jad1 = ImageIO.read(new File("images//jad1.jpg"));
        }catch(Exception e){
            
        }
    }
    
    public void startNight(){
        this.startTime = System.currentTimeMillis() + 5000;
        setDifficulty();
    }
    
    public void setDifficulty(){
        try{
            Scanner scan = new Scanner(new File("Config.txt"));
            int diff = scan.nextInt();
            if(diff == 1){
                this.difficulty = 2;
            }else if(diff == 2){
                this.difficulty = 4;
            }else if(diff == 3){
                this.difficulty = 8;
            }else if(diff == 4){
                this.difficulty = 10;
            }else if(diff == 5){
                this.difficulty = 14;
            }
            System.out.println(difficulty);
            chance = difficulty * 0.05;
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
