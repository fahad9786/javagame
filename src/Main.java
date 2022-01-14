
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
    BufferedImage fahad;

    MainMenu menu = new MainMenu();
    BufferedImage smile = menu.getScary(0);
    BufferedImage baw = menu.getScary(1);
    int menuFrame = 1;
    double scaryPic;
    double picNum; // chooses the image to display on menu screen
    boolean drawFace = false;
    int night; // current night the player is on
    int time;
    int winScreenTime = 6; //time spent on win screen - set as 1 second over disired time
    
    //the following labeled 'timer' are for when the power goes out
    double footstepTimer;
    double musicTimer;
    double jumpscareTimer;
    boolean playedFootsteps = false;
    boolean playedMusic = false;

    boolean onMenu = true;
    boolean office = false;
    boolean camera = false;
    boolean loadNight = false;
    boolean isDead = false;
    boolean winScreen = false;
    boolean loseScreen = false;
    boolean lookingLeft = true;
    boolean lightsOut = false;
    boolean FahadInRoom = false;
    boolean JadenInRoom = false;
    boolean noPower = false;
    boolean powerAudio = false; //keeps track of if the out of power audio has already played. prevents audio repeating forever
    boolean doorSeenJad = false; //switches if jaden has been seen outside the door

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

    //door buttons
    Rectangle leftDoorBut = new Rectangle(35, 426, 42, 100);
    Rectangle rightDoorBut = new Rectangle(1203, 426, 42, 100);
    Rectangle leftHallLight = new Rectangle(35, 547, 42, 100);
    Rectangle rightHallLight = new Rectangle(1203, 547, 42, 100);

    Player p = new Player();
    Office o = new Office();
    Fahad f = new Fahad(o, p, menu);
    Jaden j = new Jaden(o, p, menu);
    TimeController t = new TimeController();
    AudioController a = new AudioController();

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
            g.drawString("N" + menu.load(1), 325, 600);
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
        } else if (!noPower && office) {
            g.setColor(Color.white);
            g.setFont(buttons);
            g.drawString(Math.round(o.getPower()) + "%", 1, 50);
            g.drawString(t.getTime() + "AM", 1150, 50);
            g.setColor(Color.gray);
            g.drawRect(300, 600, 600, 100);
            if (lookingLeft) {
                g.drawImage(p.butLeft, 20, 400, null);
                if (o.getDoor1()) {
                    g.drawImage(p.leftDoor, 100, 50, null);
                } else if (o.getLight1()) {
                    g.drawImage(p.leftHallLit, 100, 50, null);
                }
                if (j.getRoom() == 6 && o.getLight1()) {
                    g.drawImage(j.jad1, 200, 100, null);
                }
            } else if (!lookingLeft) {
                g.drawImage(p.butRight, 1191, 400, null);
                if (o.getDoor2()) {
                    g.drawImage(p.rightDoor, 980, 50, null);
                } else if (o.getLight2()) {
                    g.drawImage(p.rightHallLit, 980, 50, null);
                }
            }
        } else if (camera) {
            //draws Fahad in the correct room
            if (curCam == p.Cam1A && f.getRoom() == 0) {
                g.drawImage(f.fad1, 500, 300, null);
            } else if (curCam == p.Cam1B && f.getRoom() == 1) {
                g.drawImage(f.fad1, 600, 10, null);
            } else if (curCam == p.Cam7 && f.getRoom() == 2) {
                g.drawImage(f.fad1, 450, 300, null);
            } else if (curCam == p.Cam4A && f.getRoom() == 4) {
                g.drawImage(f.fad1, 500, 300, null);
            } else if (curCam == p.Cam4B && f.getRoom() == 5) {
                g.drawImage(f.fad2, 0, 0, null);
            }

            //draws Jaden in the correct room
            if (curCam == p.Cam1A && j.getRoom() == 0) {
                g.drawImage(j.jad1, 250, 350, null);
            } else if (curCam == p.Cam1B && j.getRoom() == 1) {
                g.drawImage(j.jad1, 300, 400, null);
            } else if (curCam == p.Cam5 && j.getRoom() == 2) {
                g.drawImage(j.jad1, 600, 300, null);
            } else if (curCam == p.Cam2A && j.getRoom() == 3) {
                g.drawImage(j.jad1, 700, 150, null);
            } else if (curCam == p.Cam3 && j.getRoom() == 4) {
                g.drawImage(j.jad1, 500, 200, null);
            } else if (curCam == p.Cam2B && j.getRoom() == 5) {
                g.drawImage(j.jad1, 700, 200, null);
            }

            g.setColor(Color.white);
            g.setFont(buttons);
            g.setColor(Color.gray);
            g.drawRect(300, 600, 600, 100);
            g.setColor(Color.white);
            g.drawImage(p.map, 1000, 500, null);
            g.drawString(Math.round(o.getPower()) + "%", 1, 50);
            g.drawString(t.getTime() + "AM", 1150, 50);
            if (camUI) {
                g.setFont(noVid);
                g.drawString("[VIDEO FEED UNAVAILABLE]", 45, 200);
            }

        } else if (winScreen) {
            g.setColor(Color.black);
            g.fillRect(0, 0, 1280, 720);
            g.setColor(Color.white);
            g.setFont(noVid);
            g.drawString("6AM", WIDTH / 2 - 75, HEIGHT / 2 - 25);
            g.setFont(buttons);
            g.drawString("Congratulations!", WIDTH / 2 - 175, HEIGHT / 2 + 75);
        } else if (loseScreen) {
            g.setColor(Color.black);
            g.fillRect(0, 0, 1280, 720);
            g.setColor(Color.white);
            g.setFont(noVid);
            g.drawString("[GAME_OVER]", 20, 100);
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
            a.menu();
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
                a.nightStart();
            }
        } else if (office && !noPower) {
            FahadInRoom = f.inRoom();
            JadenInRoom = j.inRoom();
            if (lookingLeft) {
                currentImage = p.left;
            } else if (!lookingLeft) {
                currentImage = p.right;
            }
            o.decreasePower(System.currentTimeMillis());
            f.moveOpprotunity(curCam);
            j.moveOpprotunity();
            if (t.getTime() == 6) {
                office = false;
                winScreen = true;
                compareTime = System.currentTimeMillis() - 1000;
                a.nightEnd();
                o.reset();
            } else if (o.getPower() <= 0) {
                noPower = true;
                o.allOff();
            }else if(doorSeenJad && j.getRoom() != 6){
                doorSeenJad = false;
            }
        } else if (office && noPower) {
            if (lookingLeft) {
                currentImage = p.leftNoPower;
            } else if (!lookingLeft) {
                currentImage = p.rightNoPower;
            }

            if (t.getTime() == 6) {
                office = false;
                winScreen = true;
                noPower = false;
                compareTime = System.currentTimeMillis() - 1000;
                a.nightEnd();
                o.reset();
            }
        } else if (camera) {
            JadenInRoom = j.inRoom();
            currentImage = curCam;
            o.decreasePower(System.currentTimeMillis());
            f.moveOpprotunity(curCam);
            j.moveOpprotunity();
            if (t.getTime() == 6) {
                camera = false;
                winScreen = true;
                compareTime = System.currentTimeMillis() - 1000;
                a.nightEnd();
                o.reset();
            } else if (o.getPower() <= 0) {
                noPower = true;
                office = true;
                camera = false;
                o.allOff();
            }
        } else if (winScreen) {
            a.win();
            if (((System.currentTimeMillis() - compareTime) / 1000) % winScreenTime == 0) {
                System.out.println("hi");
                winScreen = false;
                onMenu = true;
                menu.nextNight();
            }
        } else if (isDead) {
            if (((System.currentTimeMillis() - compareTime) / 1000) % 2 == 0) {
                currentImage = null; //clears the current image so the jumpscare image does not flash again
                loseScreen = true;
                isDead = false;
                a.lose();
            }
        } else if (loseScreen) {
            if (((System.currentTimeMillis() - compareTime) / 1000) % 10 == 0) {
                loseScreen = false;
                FahadInRoom = false;
                JadenInRoom = false;
                onMenu = true;
            }
        }
        
        if(JadenInRoom){
            o.setDoor1spec(false);
            o.setLight1spec(false);
        }else if(FahadInRoom){
            o.setDoor2spec(false);
            o.setLight2spec(false);
        }

        if (office && FahadInRoom && Math.random() < f.getChance() / 2 && ((System.currentTimeMillis() - compareTime) /1000) % 6 == 0) {
            currentImage = f.jumpScare();
            a.nightEnd();
            a.jumpScare();
            office = false;
            isDead = true;
            compareTime = System.currentTimeMillis() - 1000;
        } else if (JadenInRoom && Math.random() < j.getChance() / 2 && office  && ((System.currentTimeMillis() - compareTime) /1000) % 5 == 0 || camera && JadenInRoom && Math.random() < j.getChance() / 2  && ((System.currentTimeMillis() - compareTime) /1000) % 3 == 0) {
            currentImage = j.jumpScare();
            a.nightEnd();
            a.jumpScare();
            office = false;
            camera = false;
            isDead = true;
            compareTime = System.currentTimeMillis() - 1000;
        }
        //when the power goes out
        if (noPower && !powerAudio) {
            JadenInRoom = false;
            FahadInRoom = false;
            powerAudio = true;
            a.noPower();
            footstepTimer = Math.round(Math.random() * 10 + 3);
            System.out.println(footstepTimer);
            musicTimer = footstepTimer + (Math.random() * (Math.random() * 15));
            System.out.println(musicTimer);
            jumpscareTimer = Math.round(Math.random() * 10);
            System.out.println(jumpscareTimer);
            compareTime = System.currentTimeMillis() - 1000;
        }else if (noPower) {
            System.out.println(((System.currentTimeMillis() - compareTime)/1000));
            if(!playedFootsteps && ((System.currentTimeMillis() - compareTime)/1000) > footstepTimer){
                a.footsteps();
                playedFootsteps = true;
            }else if(!playedMusic && Math.round(((System.currentTimeMillis() - compareTime)/1000) % musicTimer) == 0){
                a.music();
                playedMusic = true;
                compareTime = System.currentTimeMillis() - 1000;
            }else if(playedFootsteps && playedMusic && (Math.round(System.currentTimeMillis() - compareTime)/1000) == jumpscareTimer){
                currentImage = f.jumpScare();
                a.nightEnd();
                a.jumpScare();
                office = false;
                isDead = true;
                noPower = false;
                compareTime = System.currentTimeMillis() - 1000;
            }
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
                    night = menu.load(0);
                    onMenu = false;
                    powerAudio = false;
                    loadTime = System.currentTimeMillis();
                    t.nightStart();
                    o.reset();
                    a.noMenu();
                    f.reset();
                    j.reset();
                    f.startNight();
                    j.startNight();
                    loadNight = true;
                } else if (e.getX() >= continueBut.x && e.getX() <= continueBut.x + continueBut.width && e.getY() >= continueBut.y && e.getY() <= continueBut.y + continueBut.height) {
                    onMenu = false;
                    powerAudio = false;
                    loadTime = System.currentTimeMillis();
                    t.nightStart();
                    o.reset();
                    a.noMenu();
                    f.reset();
                    j.reset();
                    f.startNight();
                    j.startNight();
                    loadNight = true;
                    night = menu.load(1);
                }
            } else if (office) {
                if (!noPower && e.getX() > 300 && e.getX() < 900 && e.getY() > 600) {
                    camera = true;
                    a.monitor();
                    o.setCam();
                    office = false;
                } else if (lookingLeft) {
                    if (!noPower && e.getX() > leftDoorBut.x && e.getX() < leftDoorBut.x + leftDoorBut.width && e.getY() > leftDoorBut.y && e.getY() < leftDoorBut.y + leftDoorBut.height) {
                        if(!JadenInRoom){
                            o.setDoor1();
                            a.door();
                        }else{
                            a.buttonNotWork();
                        }
                    } else if (!noPower && e.getX() > leftHallLight.x && e.getX() < leftHallLight.x + leftHallLight.width && e.getY() > leftHallLight.y && e.getY() < leftHallLight.y + leftHallLight.height) {
                        if(!JadenInRoom){
                            o.setLight1();
                            if(j.getRoom() == 6 && !doorSeenJad){
                                a.atDoor();
                                doorSeenJad = true;
                            }
                        }else{
                            a.buttonNotWork();
                        }
                    }
                } else if (!lookingLeft) {
                    if (!noPower && e.getX() > rightDoorBut.x && e.getX() < rightDoorBut.x + rightDoorBut.width && e.getY() > rightDoorBut.y && e.getY() < rightDoorBut.y + rightDoorBut.height) {
                        if(!FahadInRoom){
                            o.setDoor2();
                            a.door();
                        }else{
                            a.buttonNotWork();
                        }
                    } else if (!noPower && e.getX() > rightHallLight.x && e.getX() < rightHallLight.x + rightHallLight.width && e.getY() > rightHallLight.y && e.getY() < rightHallLight.y + rightHallLight.height) {
                        if(!FahadInRoom){
                            o.setLight2();
                        }else{
                            a.buttonNotWork();
                        }
                    }
                }
            } else if (camera) {
                if (e.getX() > 300 && e.getX() < 900 && e.getY() > 600) {
                    camera = false;
                    a.monitor();
                    o.setCam();
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
