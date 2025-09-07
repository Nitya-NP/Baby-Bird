
import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
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
public class ScorePanel extends JPanel{

    private int initialScore = 0;
    private int score = 0;
    private static final Font SMALL_FONT = new Font(Font.MONOSPACED, Font.PLAIN, 36);
    private static final Font BIG_FONT = new Font(Font.MONOSPACED, Font.BOLD, 33);
    private JLabel scoreLabel = new JLabel("0");
    private JLabel scoreTitleLabel = new JLabel("Score: ");
   
    public ScorePanel(int initialScore, Color color) {
        
        this.initialScore = initialScore;
        setBackground(color);
        scoreTitleLabel.setFont(SMALL_FONT);        
        add(scoreTitleLabel);
        scoreLabel.setFont(BIG_FONT);
        add(scoreLabel);
    }
 
    public void addtoScore(int points){
        score += points;
        scoreLabel.setText(score + "");
    }
    
    public void reset(){
        score = initialScore;
        scoreLabel.setText(score + "");
    }    
    
}
