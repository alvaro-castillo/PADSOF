package userInterface.initialScreen;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
public class TextPanel extends JPanel{


	private static final long serialVersionUID = 1L;
	

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