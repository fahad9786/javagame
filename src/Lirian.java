
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
public class Lirian extends Fahad{
    
    public BufferedImage LirAtDesk;
    private BufferedImage LirJump;

    
    public Lirian(Office o, Player p, MainMenu m) {
        super(o, p, m);
        try {
            LirAtDesk = ImageIO.read(new File("images//LirAtDesk.jpg"));
            LirJump = ImageIO.read(new File("images//LirJump.jpg"));
        } catch (Exception e) {

        }
    }
    
    @Override
    public BufferedImage jumpScare(){
        return LirJump;
    }
    
    
    
}
