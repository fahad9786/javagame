
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JComponent;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.Timer;

/**
 *
 * @author ramcj7742
 */
public class Main extends JComponent implements ActionListener {

    // Height and Width of our game
    static final int WIDTH = 1280;
    static final int HEIGHT = 720;

    //Title of the window
    String title = "My Game";

    // sets the framerate and delay for our game
    // this calculates the number of milliseconds per frame
    // you just need to select an approproate framerate
    int desiredFPS = 60;
    int desiredTime = Math.round((1000 / desiredFPS));
    
    // timer used to run the game loop
    // this is what keeps our time running smoothly :)
    Timer gameTimer;

    // YOUR GAME VARIABLES WOULD GO HERE
    
    final long startTime = System.currentTimeMillis(); //time the program was started

    
    Font header = new Font("Arial", Font.BOLD, 100);
    Font buttons = new Font("Arial", Font.PLAIN, 50);
    
    BufferedImage currentImage;
    MainMenu menu = new MainMenu();
    int menuFrame = 1;
    
    boolean onMenu = true;
    boolean office = false;
    boolean camera = false;
    
    Rectangle newGameBut = new Rectangle(100, 460, 250, 50);
    Rectangle continueBut = new Rectangle(100, 560, 200, 50);
    
        
    // GAME VARIABLES END HERE    

    
    // Constructor to create the Frame and place the panel in
    // You will learn more about this in Grade 12 :)
    public Main(){
        // creates a windows to show my game
        JFrame frame = new JFrame(title);

        // sets the size of my game
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        // adds the game to the window
        frame.add(this);

        // sets some options and size of the window automatically
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        // shows the window to the user
        frame.setVisible(true);

        // add listeners for keyboard and mouse
        frame.addKeyListener(new Keyboard());
        Mouse m = new Mouse();
        this.addMouseMotionListener(m);
        this.addMouseWheelListener(m);
        this.addMouseListener(m);
        
        // Set things up for the game at startup
        setup();

       // Start the game loop
        gameTimer = new Timer(desiredTime,this);
        gameTimer.setRepeats(true);
        gameTimer.start();
    }

    // drawing of the game happens in here
    // we use the Graphics object, g, to perform the drawing
    // NOTE: This is already double buffered!(helps with framerate/speed)
    @Override
    public void paintComponent(Graphics g) {
        // always clear the screen first!
        g.clearRect(0, 0, WIDTH, HEIGHT);

        // GAME DRAWING GOES HERE
        
        //draws the current main image
        g.drawImage(currentImage, 0, 0, null);
        
        //drawing for spectific cases (menu, camera etc.)
        if(onMenu){
            g.setFont(header);
            g.setColor(Color.WHITE);
            g.drawString("Five", 50, 100);
            g.drawString("Nights", 50, 200);
            g.drawString("At", 50, 300);
            g.drawString("Fahad's", 50, 400);
            g.setFont(buttons);
            g.drawString("New Game", 100, 500);
            g.drawString("Continue", 100, 600);
            
            //will be deleted, just for testing button
            g.drawRect(newGameBut.x, newGameBut.y,newGameBut.width, newGameBut.height);
            g.drawRect(continueBut.x, continueBut.y, continueBut.width, continueBut.height);
        }
        
        //draws overlays
        
        // GAME DRAWING ENDS HERE
    }

    // This method is used to do any pre-setup you might need to do
    // This is run before the game loop begins!
    public void setup() {
        // Any of your pre setup before the loop starts should go here
        System.out.println(startTime);
    }

    // The main game loop
    // In here is where all the logic for my game will go
    public void loop() {
        if(onMenu){
            if(menuFrame >= menu.images.length-1){
                menuFrame = 0;
            }else{
                menuFrame++;
            }
            
            currentImage = menu.getImage(menu.images[menuFrame]);
        }
        
    }

    // Used to implement any of the Mouse Actions
    private class Mouse extends MouseAdapter {

        // if a mouse button has been pressed down
        @Override
        public void mousePressed(MouseEvent e) {
            //mouse events during the menu screen
            if(onMenu){
                if(e.getX() >= newGameBut.x && e.getX() <= newGameBut.x + newGameBut.width && e.getY() >= newGameBut.y && e.getY() <= newGameBut.y + newGameBut.height){
                    System.out.println("hi");
                    onMenu = false;
                }else if(e.getX() >= continueBut.x && e.getX() <= continueBut.x + continueBut.width && e.getY() >= continueBut.y && e.getY() <= continueBut.y + continueBut.height){
                    System.out.println("hello");
                }
            }
        }

        // if a mouse button has been released
        @Override
        public void mouseReleased(MouseEvent e) {

        }

        // if the scroll wheel has been moved
        @Override
        public void mouseWheelMoved(MouseWheelEvent e) {

        }

        // if the mouse has moved positions
        @Override
        public void mouseMoved(MouseEvent e) {

        }
    }

    // Used to implements any of the Keyboard Actions
    private class Keyboard extends KeyAdapter {

        // if a key has been pressed down
        @Override
        public void keyPressed(KeyEvent e) {

        }

        // if a key has been released
        @Override
        public void keyReleased(KeyEvent e) {

        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        loop();
        repaint();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // creates an instance of my game
        Main game = new Main();
    }
}




