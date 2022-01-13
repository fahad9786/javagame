
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
    private int camNum;
    Office o;
    Player p;
    MainMenu m;
    
    private long startTime;
    public BufferedImage fad1;
    
    public Fahad(Office o, Player p, MainMenu m){
        roomNum = 0;
        this.o = o;
        this.p = p;
        this.m = m;
        try{
            fad1 = ImageIO.read(new File("images//fahad_1_30.jpg"));
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void startNight(){
        this.startTime = System.currentTimeMillis() - 5000;
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
    
    public void moveOpprotunity(BufferedImage curCam){
        if(curCam == p.Cam1A){
            camNum = 0;
        }else if(curCam == p.Cam1B){
            camNum = 1;
        }else if(curCam == p.Cam7){
            camNum = 2;
        }else if(curCam == m.getImage(3)){
            camNum = 3;
        }else if(curCam == p.Cam4A){
            camNum = 4;
        }else if(curCam == p.Cam4B){
            camNum = 5;
        }else{
            camNum = -1;
        }
        if((System.currentTimeMillis() - startTime)/100 > moveTime && !o.getCam() | camNum != roomNum){
            System.out.println(camNum + ", " + roomNum);
            System.out.println("opprotunity");
            if(Math.random() < chance){
                System.out.println("move");
                roomNum++;
            }
            startTime = System.currentTimeMillis() - 1000;
        }else if((System.currentTimeMillis() - startTime)/100 > moveTime){
            System.out.println("blocked");
            startTime = System.currentTimeMillis() - 1000;
        }
    }
    
    public int getRoom(){
        return this.roomNum;
    }
}
