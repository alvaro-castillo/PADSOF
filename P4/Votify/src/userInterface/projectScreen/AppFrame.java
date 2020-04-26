package userInterface.projectScreen;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class AppFrame extends JFrame {
	private Toolkit t;
	private Dimension dim;
	private Image img;
	
	
	private static final long serialVersionUID = 1L;
	
	public AppFrame() {
		super("Votify");
		
		super.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		this.t = Toolkit.getDefaultToolkit();
		this.dim = t.getScreenSize();
		this.img = t.getImage("Logo.png");

		super.setIconImage(this.img);
		
		super.setSize((int) (this.dim.getWidth()/2), (int) (this.dim.getHeight()/2));
		super.setLocation((int) (this.dim.getWidth()/4), (int) (this.dim.getHeight()/4));

		super.setVisible(true);
	}

}