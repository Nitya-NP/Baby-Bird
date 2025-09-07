
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JFrame;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Nitya
 */
public class MyTimer extends JFrame{
    
       public static void main(String[] args) {
        MyTimer timer = new MyTimer();
    }
 
    public MyTimer() {
        initGUI();
        setTitle("My Timer");
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
 
    public void initGUI() {
        TitleLabel titleLabel = new TitleLabel("My Timer");
        add(titleLabel, BorderLayout.PAGE_START);
        ScorePanel score = new ScorePanel(10, Color.RED);
        add(score, BorderLayout.PAGE_END);
    }
    
}
