package userInterface.initialScreen;

import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class ImagePanel extends JPanel{

	private ImageIcon image;

	private static final long serialVersionUID = 1L;
	
    public ImagePanel() {
       this.image = new ImageIcon("Logo.png");

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); 
        this.image.paintIcon(this, g, 210, 0);
    }

}