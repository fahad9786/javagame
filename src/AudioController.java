
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
    private final MediaPlayer scream;
    private final MediaPlayer fan;
    private final MediaPlayer menu;
    private final MediaPlayer monitor;
    private final MediaPlayer win;
    private final MediaPlayer powerOut;
    private final MediaPlayer footsteps;
    private final MediaPlayer atDoor;
    private final MediaPlayer buttonNotWork;
    private final MediaPlayer lose;
    private final MediaPlayer door;
    
    public AudioController(){
        Media fanMed = new Media(new File("Audio//officeAmbiance.mp3").toURI().toString());
        Media screamMed = new Media(new File("Audio//Scream.mp3").toURI().toString());
        Media menuMed = new Media(new File("Audio//menu.mp3").toURI().toString());
        Media monitorMed = new Media(new File("Audio//monitor.mp3").toURI().toString());
        Media winMed = new Media(new File("Audio//win.mp3").toURI().toString());
        Media powerOutMed = new Media(new File("Audio//powerOut.mp3").toURI().toString());
        Media footstepsMed = new Media(new File("Audio//footsteps.mp3").toURI().toString());
        Media atDoorMed = new Media(new File("Audio//atDoor.mp3").toURI().toString());
        Media buttonNotWorkMed = new Media(new File("Audio//buttonNotWork.mp3").toURI().toString());
        Media loseMed = new Media(new File("Audio//static.mp3").toURI().toString());
        Media doorMed = new Media(new File("Audio//door.mp3").toURI().toString());
        
        fan = new MediaPlayer(fanMed);
        scream = new MediaPlayer(screamMed);
        menu = new MediaPlayer(menuMed);
        monitor = new MediaPlayer(monitorMed);
        win = new MediaPlayer(winMed);
        powerOut = new MediaPlayer(powerOutMed);
        footsteps = new MediaPlayer(footstepsMed);
        atDoor = new MediaPlayer(atDoorMed);
        buttonNotWork = new MediaPlayer(buttonNotWorkMed);
        lose = new MediaPlayer(loseMed);
        door = new MediaPlayer(doorMed);
    }
    
    public void menu(){
        lose.stop();
        if(menu.getStopTime().compareTo(menu.getCurrentTime()) > 0){
            menu.play();
        }else{
            menu.seek(Duration.ZERO);
        }
    }
    
    public void noMenu(){
        menu.stop();
    }
    
    public void lose(){
        lose.seek(Duration.ZERO);
        lose.play();
    }
    
    public void monitor(){
        monitor.seek(Duration.ZERO);
        monitor.play();
    }
    
    public void nightStart(){
        fan.seek(Duration.ZERO);
        win.stop();
        win.seek(Duration.ZERO);
        fan.play();
    }
    
    public void jumpScare(){
        scream.seek(Duration.ZERO);
        scream.play();
    }
    
    public void noPower(){
        fan.stop();
        powerOut.seek(Duration.ZERO);
        powerOut.play();
    }
    
    public void win(){
        win.play();
    }
    
    public void atDoor(){
        atDoor.seek(Duration.ZERO);
        atDoor.play();
    }
    
    public void buttonNotWork(){
        buttonNotWork.seek(Duration.ZERO);
        buttonNotWork.play();
    }
    
    public void footsteps(){
        footsteps.seek(Duration.ZERO);
        footsteps.play();
    }
    
    public void nightEnd(){
        fan.stop();
    }
    
    public void door(){
        door.seek(Duration.ZERO);
        door.play();
    }
}
