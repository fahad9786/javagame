
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
    private final MediaPlayer music;
    private final MediaPlayer nightStart;
    private final MediaPlayer LirJump;
    private final MediaPlayer N1;
    private final MediaPlayer N2;
    private final MediaPlayer N3;
    private final MediaPlayer N4;
    private final MediaPlayer N5;
    MainMenu m;
    
    public AudioController(MainMenu m){
        this.m = m;
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
        Media musicMed = new Media(new File("Audio//music.mp3").toURI().toString());
        Media nightStartMed = new Media(new File("Audio//nightStart.mp3").toURI().toString());
        Media LirJumpMed = new Media(new File("Audio//gFredJump.mp3").toURI().toString());
        Media N1Med = new Media(new File("Audio//Phone guy Night 1.mp3").toURI().toString());
        Media N2Med = new Media(new File("Audio//Phone guy Night 2.mp3").toURI().toString());
        Media N3Med = new Media(new File("Audio//Phone guy Night 3.mp3").toURI().toString());
        Media N4Med = new Media(new File("Audio//Phone guy Night 4.mp3").toURI().toString());
        Media N5Med = new Media(new File("Audio//Phone guy Night 5.mp3").toURI().toString());
        
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
        music = new MediaPlayer(musicMed);
        nightStart = new MediaPlayer(nightStartMed);
        LirJump = new MediaPlayer(LirJumpMed);
        N1 = new MediaPlayer(N1Med);
        N2 = new MediaPlayer(N2Med);
        N3 = new MediaPlayer(N3Med);
        N4 = new MediaPlayer(N4Med);
        N5 = new MediaPlayer(N5Med);
        
        fan.setVolume(0.6);
        nightStart.setVolume(0.6);
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
        nightStart.seek(Duration.ZERO);
        nightStart.play();
    }
    
    public void music(){
        music.play();
    }
    
    public void lose(){
        LirJump.stop();
        lose.seek(Duration.ZERO);
        lose.play();
    }
    
    public void playCall(){
        switch (m.load(1)) {
            case 1:
                N1.seek(Duration.ZERO);
                N1.play();
                break;
            case 2:
                N2.seek(Duration.ZERO);
                N2.play();
                break;
            case 3:
                N3.seek(Duration.ZERO);
                N3.play();
                break;
            case 4:
                N4.seek(Duration.ZERO);
                N4.play();
                break;
            case 5:
                N5.seek(Duration.ZERO);
                //N5.setStartTime(Duration.ZERO);
                N5.play();
                break;
            default:
                break;
        }
    }
    
    public int getCallLength(){
        System.out.println(N5.getCurrentTime() + "  " + N5.getStopTime());
        switch (m.load(1)) {
            case 1:
                return N1.getStopTime().compareTo(N1.getCurrentTime());
            case 2:
                return N2.getStopTime().compareTo(N2.getCurrentTime());
            case 3:
                return N3.getStopTime().compareTo(N3.getCurrentTime());
            case 4:
                return N4.getStopTime().compareTo(N4.getCurrentTime());
            case 5:
                return N5.getStopTime().compareTo(N5.getCurrentTime());
            default:
                break;
        }
        return 0;
    }
    
    public void stopCall(){
        N1.stop();
        N2.stop();
        N3.stop();
        N4.stop();
        N5.stop();
    }
    
    public void monitor(){
        monitor.seek(Duration.ZERO);
        monitor.play();
    }
    
    public void nightStart(){
        fan.seek(Duration.ZERO);
        win.stop();
        win.seek(Duration.ZERO);
        music.seek(Duration.ZERO);
        fan.play();
    }
    
    public void jumpScare(){
        scream.seek(Duration.ZERO);
        scream.play();
    }
    
    public void LirJump(){
        LirJump.seek(Duration.ZERO);
        LirJump.play();
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
        powerOut.stop();
        footsteps.stop();
        music.stop();
        
    }
    
    public void door(){
        door.seek(Duration.ZERO);
        door.play();
    }
}
