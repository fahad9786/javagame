
import java.io.File;
import java.util.Scanner;

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
    private long startTime;
    
    public Fahad(){
        roomNum = 0;
    }
    
    public void startTime(long time){
        this.startTime = time;
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
                this.difficulty = 3;
            }else if(diff == 5){
                this.difficulty = 4;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void moveOpprotunity(){
        
    }
    
    public int getRoom(){
        return this.roomNum;
    }
}
