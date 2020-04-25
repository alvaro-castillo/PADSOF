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
* This class will create a panel for accepting or rejecting the creation of projects.
* It will include a big text indicating the purpose of the panel, a combo box
* for selecting the project, two buttons panel for selecting the acceptance/rejection of the creation,
* and the go to feed button.
*
* @author Miguel Álvarez Valiente, Alejandro Benimeli Miranda, Álvaro Castillo García
*/
public class AcceptOrDenyProjectPanel extends JPanel{
	private FeedButtonPanel feed;
	private BigTextPanel text;
	private ComboBoxPanel combo;
	private TwoButtonsPanel<AcceptOrDenyProjectController> buttons;
	private AcceptOrDenyProjectController controller;
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor of this class.
	 * 
	 * @param v vector with all the project names that have just been created
	 */
	public AcceptOrDenyProjectPanel(Vector<String> v) {
		this.controller = new AcceptOrDenyProjectController(this);
		this.feed = new FeedButtonPanel(this);
		this.text = new BigTextPanel("Accept Project", 145, 10, 40);
		this.combo = new ComboBoxPanel(v);
		this.buttons = new TwoButtonsPanel<AcceptOrDenyProjectController>(controller, "Accept", "Deny");
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.add(feed);
		this.add(text);
		this.add(Box.createRigidArea(new Dimension(0, 20)));
		this.add(combo);
		this.add(buttons);
		this.add(Box.createRigidArea(new Dimension(0, 60)));
	}
	
	/**
	 * Combo box panel getter. Used by the controller.
	 * @return combo
	 * */
	public ComboBoxPanel getCombo() {
		return this.combo;
	}

}