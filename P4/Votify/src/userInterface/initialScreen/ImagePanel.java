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


/*
package userInterface.initialScreen;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class ImagePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private ResizableImage resizableImg;
	
    public ImagePanel(String imgPath) {
    	
    	this.setLayout(new BorderLayout());
    	
    	imgIcon = new ImageIcon("Logo.png");
    	//initialImgIcon = new ImageIcon(imgIcon);
    	//imgIcon = new ImageIcon(imgIcon.getImage().getScaledInstance(img.getWidth(), img.getHeight(), Image.SCALE_SMOOTH));
    	
    	img = new JLabel(new ImageIcon(imgIcon.getImage().getScaledInstance(1, 1, Image.SCALE_SMOOTH)), SwingConstants.CENTER);
    	img.setBorder(null);
    	this.add(img);
    	
    	//img.setSize(1,1);
    	
    	//ImageIcon img = new ImageIcon("Logo.png");
    	//Image image = img.getImage().getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_SMOOTH);
    	
    	
    	//this.image = new ImageIcon(image);
    	this.addComponentListener(this);
    	
    	
    	//imgIcon = new ImageIcon(imgPath);
    	
    	this.setLayout(new FlowLayout());
    	resizableImg = new ResizableImage(imgPath);
    	this.add(resizableImg);
    	
    }
    
    private class ResizableImage extends JLabel {
    	
    	private ImageIcon imgIcon;
    	
    	public ResizableImage(String imgPath) {
    		imgIcon = new ImageIcon(imgPath);
    	}
    	
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
        	
        	if (newAspectRatio < oldAspectRatio) {
        		newHeight = (int)(newWidth / oldAspectRatio);
        	} else if (newAspectRatio > oldAspectRatio) {
        		newWidth = (int)(newHeight * oldAspectRatio);
        	}
            
            g.drawImage(imgIcon.getImage(), 0, 0, (int)newWidth, (int)newHeight, this);
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(50, 50);
        }
    	
    
    }
    
    


}*/