package userInterface;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
public class BigTextPanel extends JPanel{
	private String message;
	private int width;
	private int height;
	public BigTextPanel(String message, int width, int height) {
		this.message = message;
		this.width = width;
		this.height = height;
	}

	private static final long serialVersionUID = 1L;
	
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);  
       
        Graphics2D g2 = (Graphics2D) g;
	    Font fuente = new Font("Arial", Font.BOLD, 40);
	    
	    g2.setFont(fuente);
	    g2.setColor(Color.BLACK);
	    g2.drawString(this.message, (this.getWidth()/2)-this.width, (this.getHeight()/2)+this.height);
    }

}