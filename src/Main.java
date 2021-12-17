
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
    String title = "Five Nights At Fahad's";

    // sets the framerate and delay for our game
    // this calculates the number of milliseconds per frame
    // you just need to select an approproate framerate
    int desiredFPS = 60;
    int desiredTime = Math.round((1000 / desiredFPS));

    // timer used to run the game loop
    // this is what keeps our time running smoothly :)
    Timer gameTimer;

    // YOUR GAME VARIABLES WOULD GO HERE
    public final long startTime = System.currentTimeMillis(); //time the program was started
    long loadTime; //time the pre night screen was started
    long compareTime; //this time will have its corresponding start time subtracted to calculate the time elapsed in seconds
    long faceTime; // amount of time a face is shown in the menu

    Font header = new Font("Arial", Font.BOLD, 100);
    Font buttons = new Font("Arial", Font.PLAIN, 50);
    Font noVid = new Font("Helivetica", Font.BOLD, 85);

    BufferedImage currentImage;
    MainMenu menu = new MainMenu();
    BufferedImage smile = menu.getScary(0);
    BufferedImage baw = menu.getScary(1);
    int menuFrame = 1;
    double scaryPic;
    double picNum; // chooses the image to display on menu screen
    boolean drawFace = false;
    int night; // current night the player is on

    boolean onMenu = true;
    boolean office = false;
    boolean camera = false;
    boolean loadNight = false;
    boolean isDead = false;
    boolean lookingLeft = true;

    //main menu buttons
    Rectangle newGameBut = new Rectangle(100, 460, 250, 50);
    Rectangle continueBut = new Rectangle(100, 560, 200, 50);

    //Camera Buttons
    Rectangle cam1A = new Rectangle(1078, 500, 38, 27);
    Rectangle cam1B = new Rectangle(1065, 533, 38, 27);
    Rectangle cam1C = new Rectangle(1045, 580, 38, 27);
    Rectangle cam2A = new Rectangle(1077, 652, 38, 27);
    Rectangle cam2B = new Rectangle(1077, 681, 38, 27);
    Rectangle cam3 = new Rectangle(1025, 645, 38, 27);
    Rectangle cam4A = new Rectangle(1143, 653, 38, 27);
    Rectangle cam4B = new Rectangle(1143, 681, 38, 27);
    Rectangle cam5 = new Rectangle(998, 550, 38, 27);
    Rectangle cam6 = new Rectangle(1205, 633, 38, 27);
    Rectangle cam7 = new Rectangle(1210, 550, 38, 27);
    BufferedImage curCam; // curent camera
    boolean camUI = false; // if camera needs extra UI elements (cam 6)

    Player p = new Player();

    // GAME VARIABLES END HERE    
    // Constructor to create the Frame and place the panel in
    // You will learn more about this in Grade 12 :)
    public Main() {
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
        gameTimer = new Timer(desiredTime, this);
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
        if (onMenu) {
            g.setFont(header);
            g.setColor(Color.WHITE);
            g.drawString("Five", 50, 100);
            g.drawString("Nights", 50, 200);
            g.drawString("At", 50, 300);
            g.drawString("Fahad's", 50, 400);
            g.setFont(buttons);
            g.drawString("New Game", 100, 500);
            g.drawString("Continue", 100, 600);
            if (drawFace) {
                if (picNum > 0.5) {
                    g.drawImage(smile, 750, 200, null);
                } else {
                    g.drawImage(baw, 850, 250, null);
                }
            }

        } else if (loadNight) {
            g.setFont(buttons);
            g.setColor(Color.black);
            g.fillRect(0, 0, WIDTH, HEIGHT);
            g.setColor(Color.white);
            g.drawString("Night " + night, WIDTH / 2 - 100, HEIGHT / 2 + 25);
            g.drawString((compareTime - loadTime) / 60 + "%", 1180, 700);
        } else if (office) {
            g.setColor(Color.gray);
            g.drawRect(300, 600, 600, 100);
            if (lookingLeft) {
                g.drawImage(p.butLeft, 20, 400, null);
            } else if (!lookingLeft) {
                g.drawImage(p.butRight, 1191, 400, null);
            }
        } else if (camera) {
            g.drawImage(p.map, 1000, 500, null);
            g.setColor(Color.gray);
            g.drawRect(300, 600, 600, 100);
            g.setColor(Color.white);
            if (camUI) {
                g.setFont(noVid);
                g.drawString("[VIDEO FEED UNAVAILABLE]", 45, 200);
            }
        }

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
        if (onMenu) {
            //cycles through the glitching effect images
            if (menuFrame >= menu.images.length - 1) {
                menuFrame = 0;
            } else {
                menuFrame++;
            }
            //selects a pic of fahad to show, and how long it will show for
            scaryPic = Math.random();
            compareTime = System.currentTimeMillis();
            if (scaryPic > 0.6 && scaryPic < 0.8 && (compareTime - startTime) / 1000 % 2 == 0 && !drawFace) {
                drawFace = true;
                picNum = Math.random();
                faceTime = System.currentTimeMillis();
            } else if (drawFace && (compareTime - faceTime) / 1000 > (scaryPic)) {
                drawFace = false;
            }

            currentImage = menu.getImage(menuFrame);
        } else if (loadNight) {
            //loads night for 5 seconds
            compareTime = System.currentTimeMillis();
            if ((compareTime - loadTime) / 1000 > 5) {
                loadNight = false;
                curCam = p.Cam1A;
                currentImage = p.left;
                office = true;
            }
        } else if (office) {
            if (lookingLeft) {
                currentImage = p.left;
            } else if (!lookingLeft) {
                currentImage = p.right;
            }
        } else if (camera) {
            currentImage = curCam;
        }

    }

    // Used to implement any of the Mouse Actions
    private class Mouse extends MouseAdapter {

        // if a mouse button has been pressed down
        @Override
        public void mousePressed(MouseEvent e) {
            //mouse events during the menu screen
            if (onMenu) {
                if (e.getX() >= newGameBut.x && e.getX() <= newGameBut.x + newGameBut.width && e.getY() >= newGameBut.y && e.getY() <= newGameBut.y + newGameBut.height) {
                    onMenu = false;
                    loadTime = System.currentTimeMillis();
                    loadNight = true;
                    night = menu.load(0);
                } else if (e.getX() >= continueBut.x && e.getX() <= continueBut.x + continueBut.width && e.getY() >= continueBut.y && e.getY() <= continueBut.y + continueBut.height) {
                    onMenu = false;
                    loadTime = System.currentTimeMillis();
                    loadNight = true;
                    night = menu.load(1);
                }
            } else if (office) {
                if (e.getX() > 300 && e.getX() < 900 && e.getY() > 600) {
                    camera = true;
                    office = false;
                }
            } else if (camera) {
                if (e.getX() > 300 && e.getX() < 900 && e.getY() > 600) {
                    camera = false;
                    office = true;
                    //Ubsurd amount of else if statements for each of the cameras
                } else if (e.getX() > cam1A.x && e.getX() < cam1A.x + cam1A.width && e.getY() > cam1A.y && e.getY() < cam1A.y + cam1A.height) {
                    curCam = p.Cam1A;
                    camUI = false;
                } else if (e.getX() > cam1B.x && e.getX() < cam1B.x + cam1B.width && e.getY() > cam1B.y && e.getY() < cam1B.y + cam1B.height) {
                    curCam = p.Cam1B;
                    camUI = false;
                } else if (e.getX() > cam1C.x && e.getX() < cam1C.x + cam1C.width && e.getY() > cam1C.y && e.getY() < cam1C.y + cam1C.height) {
                    curCam = p.Cam1C;
                    camUI = false;
                } else if (e.getX() > cam2A.x && e.getX() < cam2A.x + cam2A.width && e.getY() > cam2A.y && e.getY() < cam2A.y + cam2A.height) {
                    curCam = p.Cam2A;
                    camUI = false;
                } else if (e.getX() > cam2B.x && e.getX() < cam2B.x + cam2B.width && e.getY() > cam2B.y && e.getY() < cam2B.y + cam2B.height) {
                    curCam = p.Cam2B;
                    camUI = false;
                } else if (e.getX() > cam3.x && e.getX() < cam3.x + cam3.width && e.getY() > cam3.y && e.getY() < cam3.y + cam3.height) {
                    curCam = p.Cam3;
                    camUI = false;
                } else if (e.getX() > cam4A.x && e.getX() < cam4A.x + cam4A.width && e.getY() > cam4A.y && e.getY() < cam4A.y + cam4A.height) {
                    curCam = p.Cam4A;
                    camUI = false;
                } else if (e.getX() > cam4B.x && e.getX() < cam4B.x + cam4B.width && e.getY() > cam4B.y && e.getY() < cam4B.y + cam4B.height) {
                    curCam = p.Cam4B;
                    camUI = false;
                } else if (e.getX() > cam5.x && e.getX() < cam5.x + cam5.width && e.getY() > cam5.y && e.getY() < cam5.y + cam5.height) {
                    curCam = p.Cam5;
                    camUI = false;
                } else if (e.getX() > cam6.x && e.getX() < cam6.x + cam6.width && e.getY() > cam6.y && e.getY() < cam6.y + cam6.height) {
                    curCam = menu.getImage(3);
                    camUI = true;
                } else if (e.getX() > cam7.x && e.getX() < cam7.x + cam7.width && e.getY() > cam7.y && e.getY() < cam7.y + cam7.height) {
                    curCam = p.Cam7;
                    camUI = false;
                }
            } else if (office) {

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
            if (office) {
                if (e.getX() > 1000) {
                    lookingLeft = false;
                } else if (e.getX() < 280) {
                    lookingLeft = true;
                }
            }
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
