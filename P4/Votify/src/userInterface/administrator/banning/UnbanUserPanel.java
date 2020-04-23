package userInterface.administrator.banning;

import java.awt.Dimension;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import userInterface.administrator.ComboBoxPanel;
import userInterface.commonElements.BigTextPanel;
import userInterface.commonElements.CenteredButtonPanel;
import userInterface.commonElements.FeedButtonPanel;

/**
* This class will create a panel for unbanning users.
* It will include a big text indicating the purpose of the panel, a combo box
* for selecting the user, a button for confirm the unban and the go to feed button.
*
* @author Miguel Álvarez Valiente, Alejandro Benimeli Miranda, Álvaro Castillo García
*/
public class UnbanUserPanel extends JPanel{
	private BigTextPanel text;
	private CenteredButtonPanel<UnbanUserController> button;
	private FeedButtonPanel feed;
	private ComboBoxPanel combo;
	private UnbanUserController controller;
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor of this class.
	 * 
	 * @param v vector with the users that are banned
	 */
	public UnbanUserPanel(Vector<String> v) {
		this.controller = new UnbanUserController(this);
		this.combo = new ComboBoxPanel(v);		
		this.text = new BigTextPanel("Unban User", 120, 0, 40);
		this.button = new CenteredButtonPanel<UnbanUserController>("Unban", controller);
		this.feed = new FeedButtonPanel(this);
		
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		this.add(feed);
		this.add(text);
		this.add(Box.createRigidArea(new Dimension(0, 20)));
		this.add(combo);
		this.add(button);
	}
	
	/**
	 * Combo box panel getter. Used by the controller.
	 * @return combo
	 * */
	public ComboBoxPanel getCombo() {
		return this.combo;
	}
}
