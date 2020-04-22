package userInterface.initialScreen;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
* This class creates a panel with a big text that says Votify.
*
* @author Miguel Álvarez Valiente, Alejandro Benimeli Miranda, Álvaro Castillo García
*/
public class TextPanel extends JPanel{

	private static final long serialVersionUID = 1L;
	
    /**
     * This function will paint into the panel the title.
     * @param g graphics object
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);  
       
        Graphics2D g2 = (Graphics2D) g;
	    Font fuente = new Font("Arial", Font.BOLD, this.getWidth()/6);
	    
	    g2.setFont(fuente);
	    g2.setColor(Color.BLUE);
	    g2.drawString("Votify", (this.getWidth()/2)-145, (this.getHeight()/2)+20);
    }

}