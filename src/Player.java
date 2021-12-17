
import java.awt.image.BufferedImage;
import java.io.File;
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
public class Player {
    public BufferedImage map;
    public BufferedImage Cam1A;
    public BufferedImage Cam1B;
    public BufferedImage Cam1C;
    public BufferedImage Cam2A;
    public BufferedImage Cam2B;
    public BufferedImage Cam3;
    public BufferedImage Cam4A;
    public BufferedImage Cam4B;
    public BufferedImage Cam5;
    public BufferedImage Cam7;
    
    public BufferedImage left;
    public BufferedImage right;
    public BufferedImage butLeft;
    public BufferedImage butRight;
    public BufferedImage leftDoor;
    public BufferedImage rightDoor;
    public BufferedImage leftHallLit;
    public BufferedImage rightHallLit;
    
    public Player(){
        try{
            map = ImageIO.read(new File("images//Cam_Map.PNG"));
            Cam1A = ImageIO.read(new File("images//Cam1A.jpg"));
            Cam1B = ImageIO.read(new File("images//Cam1B.jpg"));
            Cam1C = ImageIO.read(new File("images//Cam1C.jpg"));
            Cam2A = ImageIO.read(new File("images//Cam2A.PNG"));
            Cam2B = ImageIO.read(new File("images//Cam2B.jpg"));
            Cam3 = ImageIO.read(new File("images//Cam3.jpg"));
            Cam4A = ImageIO.read(new File("images//Cam4A.jpg"));
            Cam4B = ImageIO.read(new File("images//Cam4B.jpg"));
            Cam5 = ImageIO.read(new File("images//Cam5.jpg"));
            Cam7 = ImageIO.read(new File("images//Cam7.jpg"));
            left = ImageIO.read(new File("images//officeLeft.jpg"));
            right = ImageIO.read(new File("images//officeRight.jpg"));
            butLeft = ImageIO.read(new File("images//leftButton.PNG"));
            butRight = ImageIO.read(new File("images//rightButton.PNG"));
            leftDoor = ImageIO.read(new File("images//leftDoor.PNG"));
            rightDoor = ImageIO.read(new File("images//rightDoor.PNG"));
            leftHallLit = ImageIO.read(new File("images//leftHallLit.jpg"));
            rightHallLit = ImageIO.read(new File("images//rightHallLit.jpg"));
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
