package userInterface.commonElements;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
* This class will create a panel with a button that will return to the feed panel.
*
* @author Miguel Ã�lvarez Valiente, Alejandro Benimeli Miranda, Ã�lvaro Castillo GarcÃ­a
*/
public class FeedButtonPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JButton button;
	private FeedButtonController controller;
	
	/**
	 * Constructor of this class.
	 * 
	 * @param Panel in which this panel is included
	 */
	public FeedButtonPanel(JPanel superPanel) {
		this.controller = new FeedButtonController(superPanel);
		this.button = new JButton("Go to Feed");
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		this.add(button);
		button.addActionListener(event -> controller.feedButtonPressed(event));
	}

}
