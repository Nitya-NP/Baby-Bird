
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Nitya
 */
public class Wall {

    private static final String WALL_IMAGE_FILE = "/wall.jpg";
    private static final int POINTS_OFFSET=80;
    private static final int CHANGE_X = -10;
    private static final int TOP_MIN = 100;
    private static final int TOP_MAX = 300;
    private static final int GAP_MIN = 100;
    private static final int GAP_MAX = 240;
    private static BufferedImage wallImage;
    private static int width = 72;
    private static int height = 600;

    private int x = FlightPanel.WIDTH;
    private int bottomY;
    private int topHeight;
    private int bottomHeight;
    private int points=1;
    private int pointsX;
    
    private BufferedImage topImage;
    private BufferedImage bottomImage;
    
    private Random rand = new Random();
    
    private String pointsString;

    public Wall(FontMetrics fm) {

        if (wallImage == null) {
            wallImage = FileIO.readImageFile(this, WALL_IMAGE_FILE);
            width = wallImage.getWidth();
            height = wallImage.getHeight();
        }

        int range = GAP_MAX - GAP_MIN;
        int pick = rand.nextInt(range);
        int gap = pick + GAP_MIN;

        //calculating ratio
        float ratio=(float)pick/range;
        
        //range 1 to 10
        int intValue= (int)(ratio*10);
        
         //smallest gap 
        points=10-intValue;        
        pointsString=""+points;
        int pointsWidth=fm.stringWidth(pointsString);
        pointsX= (width/2)-pointsWidth;
        
        range = TOP_MAX - TOP_MIN;
        pick = rand.nextInt(range);
        topHeight = pick + TOP_MIN;

        bottomY = topHeight + gap;
        bottomHeight = height- bottomY;
        topImage = wallImage.getSubimage(0,(height - topHeight), width, topHeight);
        bottomImage = wallImage.getSubimage(0, 0, width, bottomHeight);

    }

    public void draw(Graphics g) {
       
        if (wallImage == null) {
            g.setColor(Color.CYAN);
            g.fillRect(x, 0, width, topHeight);
            g.fillRect(x, bottomY, width, bottomHeight);
        } else {
            g.drawImage(topImage, x, 0, null);
            g.drawImage(bottomImage, x, bottomY, null);
        }

        g.setColor(Color.BLACK);
        g.drawString(pointsString, x+pointsX, bottomY+POINTS_OFFSET);
    }

    public void move() {
        x = CHANGE_X + x;
    }

    public boolean isPastWindoeEdge() {
        int rightEdgeX = x + width;
        return rightEdgeX < 0;
    }

    public Rectangle getTopBounds() {
        Rectangle bounds = new Rectangle(x, 0, width, topHeight);
        return bounds;
    }

    public Rectangle getBottomBounds() {
        Rectangle bounds = new Rectangle(x, bottomY, width, bottomHeight);
        return bounds;
    }
    public int getPoints(){
        return points;
    }
    
    public int getX(){
     return x;
    }
    public int getY(){
     return 0;
    }
    
    public int getWidth(){
     return width;
    }
    
    public int getChangeX(){
     return CHANGE_X;
    }
}
