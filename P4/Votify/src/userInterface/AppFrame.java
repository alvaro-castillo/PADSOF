package userInterface;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;

/**
* This class will create the frame that will be used for adding all the panels.
* It is created in such a way that it will appear in the center of the screen with a size
* of half the screen size. It will also have the text "Votify" and the logo of the app.
*
* @author Miguel Álvarez Valiente, Alejandro Benimeli Miranda, Álvaro Castillo García
*/
public class AppFrame extends JFrame {
	private Toolkit t;
	private Dimension dim;
	private Image img;
	
	private static AppFrame INSTANCE;
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor of this class.
	 * It is private so it can follow the singleton pattern.
	 */
	private AppFrame() {
		super("Votify");
		
		super.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		this.t = Toolkit.getDefaultToolkit();
		this.dim = t.getScreenSize();
		this.img = t.getImage("Logo.png");

		super.setIconImage(this.img);
		
		super.setSize((int) (this.dim.getWidth()/2), (int) (this.dim.getHeight()/2));
		super.setLocation((int) (this.dim.getWidth()/4), (int) (this.dim.getHeight()/4));
		super.setVisible(true);
		this.addWindowListener(new FrameConfiguration());
	}
	
	/**
	 * AppFrame getter. It follows the singleton pattern.
	 * @return INSTANCE .
	 */
	public static AppFrame getAppFrame() {
		if(INSTANCE == null) {
			INSTANCE= new AppFrame();
		}
		return INSTANCE;
	}
	
}