
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
public class Fahad {
    private int roomNum;
    private int difficulty;
    private double chance;
    private int moveTime = 100;
    Office o;
    
    private long startTime;
    public BufferedImage fad1;
    
    public Fahad(Office o){
        roomNum = 0;
        this.o = o;
        try{
            fad1 = ImageIO.read(new File("images//fahad_1_30.jpg"));
        }catch(Exception e){
            e.printStackTrace();
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
                this.difficulty = 0;
            }else if(diff == 2){
                this.difficulty = 1;
            }else if(diff == 3){
                this.difficulty = 2;
            }else if(diff == 4){
                this.difficulty = 4;
            }else if(diff == 5){
                this.difficulty = 6;
            }
            System.out.println(difficulty);
            chance = difficulty * 0.05;
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void moveOpprotunity(){
        if((System.currentTimeMillis() - startTime)/100 > moveTime && !o.getCam()){
            System.out.println("opprotunity");
            if(Math.random() < chance){
                System.out.println("move");
                roomNum++;
            }
            startTime = System.currentTimeMillis() - 1000;
        }
    }
    
    public int getRoom(){
        return this.roomNum;
    }
}
