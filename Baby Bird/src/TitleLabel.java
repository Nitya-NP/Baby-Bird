
import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Nitya
 */
public class TitleLabel extends JLabel {
     
    public TitleLabel(String title) {
        Font font = new Font(Font.MONOSPACED, Font.BOLD, 52);
        setFont(font);
        setBackground(Color.BLACK);
        setForeground(Color.cyan);
        setOpaque(true);
        setHorizontalAlignment(JLabel.CENTER);
        setText(title);
    }
    
}
