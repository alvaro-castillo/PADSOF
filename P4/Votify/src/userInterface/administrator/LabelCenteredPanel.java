package userInterface.administrator;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
* This class will create a label in the center of the panel.
* This will be a common element for some administrator panels.
*
* @author Miguel Álvarez Valiente, Alejandro Benimeli Miranda, Álvaro Castillo García
*/
public class LabelCenteredPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel label;
	
	/**
	 * Constructor of this class.
	 * 
	 * @param s string shown on the label
	 */
	public LabelCenteredPanel(String s) {
		this.label = new JLabel(s);
		this.add(label);
	}
}
