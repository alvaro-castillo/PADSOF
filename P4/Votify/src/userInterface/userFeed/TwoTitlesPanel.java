package userInterface.userFeed;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import userInterface.commonElements.BigTextPanel;

/**
* This class creates two big titles one will say Projects and the other one Groups
*
* @author Miguel Álvarez Valiente, Alejandro Benimeli Miranda, Álvaro Castillo García
*/
public class TwoTitlesPanel extends JPanel{

	private static final long serialVersionUID = 1L;
	private BigTextPanel text1;
	private BigTextPanel text2;
	
	/**
	 * Constructor of this class.
	 * 
	 * @param size the size of the two big titles
	 * @param offset integer number that will be added to the x axis to position the titles 
	 */
	public TwoTitlesPanel(int size, int offset) {
		this.text1 = new BigTextPanel("Groups", offset,0, size);
		this.text2 = new BigTextPanel("Projects", offset,0, size);
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		this.add(text1);
		this.add(text2);
	}

}
