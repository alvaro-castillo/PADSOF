package userInterface.administrator.acceptance;

import java.awt.Dimension;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import userInterface.administrator.ComboBoxPanel;
import userInterface.commonElements.BigTextPanel;
import userInterface.commonElements.FeedButtonPanel;
import userInterface.commonElements.TwoButtonsPanel;

/**
* This class will create a panel for accepting the registration of users.
* It will include a big text indicating the purpose of the panel, a combo box
* for selecting the user, two buttons panel for selecting the acceptance/rejection of the register,
* and the go to feed button.
*
* @author Miguel Álvarez Valiente, Alejandro Benimeli Miranda, Álvaro Castillo García
*/
public class AcceptRegistrationPanel extends JPanel {
	private FeedButtonPanel feed;
	private BigTextPanel text;
	private ComboBoxPanel combo;
	private TwoButtonsPanel<AcceptRegistrationController> buttons;
	private AcceptRegistrationController controller;
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor of this class.
	 * 
	 * @param v vector with all the usernames of the users that have just registrated into the app
	 */
	public AcceptRegistrationPanel(Vector<String> v) {
		this.controller = new AcceptRegistrationController(this);
		this.feed = new FeedButtonPanel(this);
		this.text = new BigTextPanel("Accept Registration", 220, 0, 40);
		this.combo = new ComboBoxPanel(v);
		this.buttons = new TwoButtonsPanel<AcceptRegistrationController>(controller, "Accept", "Deny");
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.add(feed);
		this.add(text);
		this.add(Box.createRigidArea(new Dimension(0, 20)));
		this.add(combo);
		this.add(buttons);
		this.add(Box.createRigidArea(new Dimension(0, 80)));
	}
	
	/**
	 * Combo box panel getter. Used by the controller.
	 * @return combo
	 * */
	public ComboBoxPanel getCombo() {
		return this.combo;
	}
}
