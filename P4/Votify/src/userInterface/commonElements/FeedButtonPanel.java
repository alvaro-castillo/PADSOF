package userInterface.commonElements;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
* This class will create a panel with a button that will return to the feed panel.
*
* @author Miguel Álvarez Valiente, Alejandro Benimeli Miranda, Álvaro Castillo Garcí­a
*/
public class FeedButtonPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JButton button;
	private FeedButtonController controller;
	
	/**
	 * Constructor of this class.
	 * 
	 * @param superPanel in which this panel is included
	 */
	public FeedButtonPanel(JPanel superPanel) {
		this.controller = new FeedButtonController(superPanel);
		this.button = new JButton("Go to Feed");
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		this.add(button);
		button.addActionListener(event -> controller.feedButtonPressed(event));
	}

}
