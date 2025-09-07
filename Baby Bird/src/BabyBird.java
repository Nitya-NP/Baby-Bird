
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.image.BufferedImage;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
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
public class BabyBird extends JFrame {

    private static final long serialVersionUID = 1L;
    private static final int LIVES=4;
    private ScorePanel scorePanel = new ScorePanel(0, Color.GREEN);
    private FlightPanel flightPanel = new FlightPanel(this);
    private BirdNestPanel birdNestPanel;
     private static final String LOSE_SOUND = "/Bum.wav";

    public void initGUI() {

        //title panel
        TitleLabel titleLabel = new TitleLabel("BabyBird");
        add(titleLabel, BorderLayout.PAGE_START);

        //main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(Color.BLUE);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        add(mainPanel, BorderLayout.CENTER);

        //score panel       
        mainPanel.add(scorePanel);

        //flight panel
        mainPanel.add(flightPanel);
        
        //bottom panel
        JPanel bottomPanel=new JPanel();
        bottomPanel.setBackground(Color.BLACK);
        add(bottomPanel, BorderLayout.PAGE_END);
        
        //bird nest panel
        Bird bird= flightPanel.getBird();
        BufferedImage birdImage =bird.getImage();
        birdNestPanel= new BirdNestPanel(birdImage,LIVES-1);
        bottomPanel.add(birdNestPanel);
        
    }

    public static void main(String[] args) {
        BabyBird babyBird = new BabyBird();

    }

    public BabyBird() {
        
        initGUI();
        setTitle("Baby Bird");
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
    }
    
    public void nextBird(){
      int birdsRemaining =birdNestPanel.removeBird(); 
      if(birdsRemaining<0)
      {
          String message="You Lose!! Play Again?"; 
           FileIO.playClip(ERROR, LOSE_SOUND);
          int option= JOptionPane.showConfirmDialog(null,message );
          
          if(option==JOptionPane.YES_OPTION)
          {
            birdNestPanel.setBirdCount(LIVES-1);
            scorePanel.reset();
            flightPanel.restart();
          }
          else System.exit(0);
      }
    }
    
    public void addToScore(int points){
        scorePanel.addtoScore(points);
    }

}
