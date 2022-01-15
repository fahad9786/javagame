
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
public class Andrew extends Fahad{
    
    public BufferedImage and2;
    public BufferedImage and1;
    public BufferedImage andDoor;
    private BufferedImage andJump;
    private int roomNum;
    private int difficulty;
    private double chance;
    private int moveTime = 85;
    private boolean inRoom = false;
    Fahad f;

    private long startTime;

    
    public Andrew(Office o, Player p, MainMenu m, Fahad f) {
        super(o, p, m);
        this.f = f;
        roomNum = 0;
        try {
            and2 = ImageIO.read(new File("images//and2_1.jpg"));
            and1 = ImageIO.read(new File("images//and1_.png"));
            andJump = ImageIO.read(new File("images//and3_.jpg"));
            andDoor = ImageIO.read(new File("images//and2.png"));
        } catch (Exception e) {

        }
    }
    
    @Override
    public void startNight() {
        this.startTime = System.currentTimeMillis() + 5000;
        setDifficulty();
    }

    @Override
    public void setDifficulty() {
        try {
            Scanner scan = new Scanner(new File("Config.txt"));
            int diff = scan.nextInt();
            if (diff == 1) {
                this.difficulty = 1;
            } else if (diff == 2) {
                this.difficulty = 2;
            } else if (diff == 3) {
                this.difficulty = 3;
            } else if (diff == 4) {
                this.difficulty = 7;
            } else if (diff == 5) {
                this.difficulty = 13;
            }
            System.out.println(difficulty);
            chance = difficulty * 0.05;
            System.out.println(chance);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void moveOpprotunity() {
        //makes sure andrew isn't in the same hall as Fahad
        System.out.println(f.getRoom());
        if(roomNum > 3 && roomNum == f.getRoom()){
            roomNum = 1;
            return;
        }
        if ((System.currentTimeMillis() - startTime) / 100 > moveTime) {
            if (roomNum < 3 && Math.random() < chance) {
                roomNum++;
                startTime = System.currentTimeMillis() - 1000;
            }else if(roomNum + 1 != f.getRoom() && roomNum == 3 && Math.random() < chance){
                roomNum++;
                startTime = System.currentTimeMillis() - 1000;
            }else if(roomNum + 1 != f.getRoom() && roomNum == 4 && Math.random() < chance){
                roomNum++;
                startTime = System.currentTimeMillis() - 1000;
            }else if(roomNum + 1 != f.getRoom() && roomNum == 5 && Math.random() < chance){
                roomNum++;
                startTime = System.currentTimeMillis() - 1000;
            }else if(roomNum == 6 && Math.random() < chance && o.getDoor1()){
                roomNum = 1;
                startTime = System.currentTimeMillis() - 1000;
            }else if(roomNum + 1 != f.getRoom() && roomNum == 6 && Math.random() < chance && !o.getDoor1()){
                roomNum = 7;
                inRoom = true;
                startTime = System.currentTimeMillis() - 1000;
            }else{
                startTime = System.currentTimeMillis() - 1000;
            }

        }
    }

    @Override
    public int getRoom() {
        return roomNum;
    }
    
    @Override
    public boolean inRoom(){
        return inRoom;
    }
    
    @Override
    public double getChance(){
        return chance;
    }
    
    @Override
    public BufferedImage jumpScare(){
        return andJump;
    }
    
    @Override
    public void reset(){
        roomNum = 0;
        inRoom = false;
    }
    
}
