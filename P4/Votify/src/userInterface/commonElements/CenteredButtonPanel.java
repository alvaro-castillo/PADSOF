package userInterface.commonElements;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
/**
* This class will create a button in the center. It is used by several classes
*
* @author Miguel Álvarez Valiente, Alejandro Benimeli Miranda, Álvaro Castillo García
*/
public class CenteredButtonPanel <A extends ActionListener> extends JPanel {

	private static final long serialVersionUID = 1L;
	private JButton button;
	
	/**
	 * Constructor of this class.
	 * 
	 * @param text string that will be shown in the button
	 * @param actionListener the object that will act as an action listener
	 */
	public CenteredButtonPanel(String text, A actionListener) {
		this.button = new JButton(text);
		
		button.addActionListener(actionListener);
		this.add(button);
	}

}
