
import java.io.File;
import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
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
    Media hit = new Media(new File("images//Scream.mp3").toURI().toString());
    MediaPlayer scream = new MediaPlayer(hit);
    
    public AudioController(){
        
    }
}
