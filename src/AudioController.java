
import java.io.File;
import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author pione
 */
public class AudioController {
    final JFXPanel fxPanel = new JFXPanel();
    MediaPlayer scream;
    MediaPlayer fan;
    MediaPlayer menu;
    MediaPlayer monitor;
    
    public AudioController(){
        Media fanMed = new Media(new File("Audio//officeAmbiance.mp3").toURI().toString());
        Media screamMed = new Media(new File("Audio//Scream.mp3").toURI().toString());
        Media menuMed = new Media(new File("Audio//menu.mp3").toURI().toString());
        Media monitorMed = new Media(new File("Audio//monitor.mp3").toURI().toString());
        
        fan = new MediaPlayer(fanMed);
        scream = new MediaPlayer(screamMed);
        menu = new MediaPlayer(menuMed);
        monitor = new MediaPlayer(monitorMed);
    }
    
    public void menu(){
        if(menu.getStopTime().compareTo(menu.getCurrentTime()) > 0){
            menu.play();
        }else{
            menu.seek(Duration.ZERO);
        }
    }
    
    public void noMenu(){
        menu.stop();
    }
    
    public void monitor(){
        monitor.seek(Duration.ZERO);
        monitor.play();
    }
    
    public void nightStart(){
        fan.seek(Duration.ZERO);
        fan.play();
    }
    
    public void jumpScare(){
        scream.seek(Duration.ZERO);
        scream.play();
    }
    
    public void noPower(){
        
    }
    
    public void nightEnd(){
        fan.stop();
    }
}
