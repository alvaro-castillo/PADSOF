package userInterface.initialScreen;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
* This class will paint into the panel the logo of the app.
*
* @author Miguel Álvarez Valiente, Alejandro Benimeli Miranda, Álvaro Castillo García
*/
public class ImagePanel extends JPanel {

	private ImageIcon imgIcon;

	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor of this classs.
	 * 
	 * @param imgPath path where the image is stored
	 */
	public ImagePanel(String imgPath) {
    	
		imgIcon = new ImageIcon(imgPath);
    	
    }
    
	/**
     * This function will draw into the panel the image.
     * @param g graphics object
     */
    @Override
	protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        // Maintain aspect ratio
        double oldWidth = imgIcon.getIconWidth();
    	double oldHeight = imgIcon.getIconHeight();
    	double oldAspectRatio = oldWidth/oldHeight;
    	double newWidth = this.getWidth();
    	double newHeight = this.getHeight();
    	double newAspectRatio = newWidth/newHeight;
    	double definitiveWidth = newWidth;
    	double definitiveHeight = newHeight;
    	
    	if (newAspectRatio < oldAspectRatio) {
    		definitiveHeight = (int)(newWidth / oldAspectRatio);
    	} else if (newAspectRatio > oldAspectRatio) {
    		definitiveWidth = (int)(newHeight * oldAspectRatio);
    	}
        
        g.drawImage(imgIcon.getImage(), (int)((newWidth-definitiveWidth)/2), (int)((newHeight-definitiveHeight)/2), (int)definitiveWidth, (int)definitiveHeight, this);
    }

    /**
     * Preferred Size getter.
     * @return a dimension with 50 of height and width
     */
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(50,50);
    }


}