
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
public class Jaden extends Fahad {

    public BufferedImage jad1;
    private BufferedImage jadJump;
    private int roomNum;
    private int difficulty;
    private double chance;
    private int moveTime = 75;
    private int camNum;
    private boolean inRoom = false;

    private long startTime;

    public Jaden(Office o, Player p, MainMenu m) {
        super(o, p, m);
        roomNum = 0;
        try {
            jad1 = ImageIO.read(new File("images//jad1.jpg"));
            jadJump = ImageIO.read(new File("images//jaden_2cr.jpg"));
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
                this.difficulty = 4;
            } else if (diff == 4) {
                this.difficulty = 8;
            } else if (diff == 5) {
                this.difficulty = 14;
            }
            System.out.println(difficulty);
            chance = difficulty * 0.05;
            System.out.println(chance);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void moveOpprotunity() {
        if ((System.currentTimeMillis() - startTime) / 100 > moveTime) {
            if (roomNum < 6 && Math.random() < chance) {
                roomNum++;
                startTime = System.currentTimeMillis() - 1000;
            }else if(roomNum == 6 && Math.random() < chance && o.getDoor1()){
                roomNum = 1;
            }else if(roomNum == 6 && Math.random() < chance && !o.getDoor1()){
                roomNum = 7;
                inRoom = true;
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
        return jadJump;
    }
    
}
