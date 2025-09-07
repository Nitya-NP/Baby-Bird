
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.Timer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Nitya
 */
public class FlightPanel extends JPanel {

    private static final String BACK_IMAGE_FILE = "/backGround.jpg";
    public static final int WIDTH = 600;
    public static final int HEIGHT = 600;
    private static final int SEPARATION = 40;
    private static final Font BIG_FONT = new Font(Font.SERIF, Font.BOLD, 20);
    private static final String THUD_SOUND = "/boing.wav";
    private FontMetrics fm = getFontMetrics(BIG_FONT);
    private int count = 0;
    private BabyBird babyBird;
    private Bird bird;
    private Timer timer;
    private ArrayList<Wall> walls = new ArrayList<>();
    private static BufferedImage BackImage;
  
    public FlightPanel(BabyBird babybird) {

        BackImage= FileIO.readImageFile(this, BACK_IMAGE_FILE);
        this.babyBird = babybird;
        bird = new Bird(HEIGHT);

        setFont(BIG_FONT);

        Wall wall = new Wall(fm);
        walls.add(wall);

        setFocusable(true);
        requestFocusInWindow(true);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                char key = e.getKeyChar();
                if (key == ' ') {
                    bird.startFlapping();
                    bird.move();
                    repaint();
                }
            }
        });

        timer = new Timer(40, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Step 3: After each timer delay, call timedAction()
                timedAction();
            }
        });

        timer.start();
    }

    public Dimension getPreferredSize() {

        Dimension size = new Dimension(WIDTH, HEIGHT);
        return size;
    }

    public void paintComponent(Graphics g) {
        //background
        //g.setColor(Color.lightGray);
       // g.fillRect(0, 0, WIDTH, HEIGHT);
        g.drawImage(BackImage, 0 ,0, null);


        //bird
        bird.draw(g);

        //walls
        for (int i = 0; i < walls.size(); i++) {
            Wall wall = walls.get(i);
            wall.draw(g);
        }

    }

    private void timedAction() {

        int changeY = bird.getChangeY();

        //move bird  
        bird.move();
        int paintX = bird.getX();
        int paintY = bird.getY();

        if (changeY > 0) {
            paintY -= changeY;
        }

        int paintWidth = bird.getWidth();
        int paintHeight = bird.getHeight() + Math.abs(changeY);
        repaint(paintX, paintY, paintWidth, paintHeight);

        //move walls
        for (int i = 0; i < walls.size(); i++) {
            Wall wall = walls.get(i);
            wall.move();
            paintX = wall.getX();
            paintY = wall.getY();
            paintWidth = wall.getWidth() - wall.getChangeX();
            paintHeight = HEIGHT;

            if (wall.isPastWindoeEdge()) {
                walls.remove(i);
                int points = wall.getPoints();
                babyBird.addToScore(points);
            }

        }
        count++;
        if (count > SEPARATION) {
            Wall wall = new Wall(fm);
            walls.add(wall);
            count = 0;
        }

        //check for collision
        Wall firstWall = walls.get(0);
        Rectangle birdBounds = bird.getBounds();
        Rectangle topWallBounds = firstWall.getTopBounds();
        Rectangle bottomWallBounds = firstWall.getBottomBounds();

        if (birdBounds.intersects(topWallBounds) || birdBounds.intersects(bottomWallBounds)) {
            nextLife();
            //timer.stop();
        }
        //should another wall be added?
        //repaint
        repaint();
    }

    public Bird getBird() {
        return bird;
    }

    private void nextLife() {
        FileIO.playClip(ERROR, THUD_SOUND);
        babyBird.nextBird();
        count = 0;
        walls.clear();
        Wall wall = new Wall(fm);
        walls.add(wall);
        repaint();

    }
    
    public void restart(){
     count=0;
     bird=new Bird(HEIGHT);
     walls.clear();
     Wall wall=new Wall(fm);
     walls.add(wall);
     repaint();
    }
}
