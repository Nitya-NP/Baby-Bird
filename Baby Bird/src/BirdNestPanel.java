
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Nitya
 */
public class BirdNestPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private static final int MARGIN = 10;
    private static final int SPACING = 20;
    private BufferedImage image;
    private int count = 0;
    private int birdWidth;
    private int birdHeight;
    private int width;
    private int height;

    public BirdNestPanel(BufferedImage image, int count) {
        this.image = image;
        this.count = count;
       
        birdWidth = image.getWidth();
        birdHeight = image.getHeight();
        width = birdWidth * count + 2 * MARGIN + (count - 1) * SPACING;
        height = birdHeight + 2 * MARGIN;
    }

    public void paintComponent(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(0, 0, width, height);

        for (int i = 0; i < count; i++) {
            int x = MARGIN + (birdWidth + SPACING) * i;
            g.drawImage(image, x, MARGIN, null);
        }
    }

    @Override
    public Dimension getPreferredSize() {
        Dimension size = new Dimension(width, height);
        return size;
    }

    public int removeBird() {
        count--;
        repaint();
        return count;
    }
    
    public void setBirdCount(int count){
      this.count=count;
      repaint();
    }

}
