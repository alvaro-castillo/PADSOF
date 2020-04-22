package userInterface.commonElements;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
* This class will paint in a panel a text, usually in big size.
*
* @author Miguel Álvarez Valiente, Alejandro Benimeli Miranda, Álvaro Castillo García
*/
public class BigTextPanel extends JPanel{
	private String message;
	private int width;
	private int height;
	private int textSize;
	
	/**
	 * Constructor of this class.
	 * 
	 * @param message that we want to draw
	 * @param width offset of the width for centering the text
	 * @param height offset of the height for centering the text
	 * @param size of the text
	 */
	public BigTextPanel(String message, int width, int height, int size) {
		this.message = message;
		this.width = width;
		this.height = height;
		this.textSize = size;
	}

	private static final long serialVersionUID = 1L;
	
	/**
     * This function will draw into the panel the text.
     * @param g graphics object
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);  
       
        Graphics2D g2 = (Graphics2D) g;
	    Font fuente = new Font("Arial", Font.BOLD, textSize);
	    
	    g2.setFont(fuente);
	    g2.setColor(Color.BLACK);
	    g2.drawString(this.message, (this.getWidth()/2)-this.width, (this.getHeight()/2)+this.height);
    }

}