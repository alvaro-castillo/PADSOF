package userInterface.administrator.acceptance;

import java.awt.Dimension;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import userInterface.administrator.ComboBoxPanel;
import userInterface.commonElements.BigTextPanel;
import userInterface.commonElements.FeedButtonPanel;
import userInterface.commonElements.LabelTextPanel;
import userInterface.commonElements.TwoButtonsPanel;
/**
* This class will create a panel for accepting the creation of projects.
* It will include a big text indicating the purpose of the panel, a combo box
* for selecting the project, two buttons panel for selecting the acceptance/rejection of the creation,
* and the go to feed button.
*
* @author Miguel Álvarez Valiente, Alejandro Benimeli Miranda, Álvaro Castillo García
*/
public class AcceptProjectPanel extends JPanel{
	private FeedButtonPanel feed;
	private BigTextPanel text;
	private ComboBoxPanel combo;
	private LabelTextPanel<AcceptProjectController> label; //TODO:Parametrize with the controller
	private TwoButtonsPanel<AcceptProjectController> buttons;
	private AcceptProjectController controller;
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor of this class.
	 * 
	 * @param v vector with all the project names that have just been created
	 */
	public AcceptProjectPanel(Vector<String> v) {
		this.controller = new AcceptProjectController(this);
		this.feed = new FeedButtonPanel(this);
		this.text = new BigTextPanel("Accept Project", 145, 10, 40);
		this.combo = new ComboBoxPanel(v);
		this.label = new LabelTextPanel<AcceptProjectController>(controller, "Introduce minimum number of votes: ", 10);
		this.buttons = new TwoButtonsPanel<AcceptProjectController>(controller, "Accept", "Deny");
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.add(feed);
		this.add(text);
		this.add(Box.createRigidArea(new Dimension(0, 20)));
		this.add(combo);
		this.add(label);
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
	
	/**
	 * Label text area panel getter. Used by the controller.
	 * @return area
	 * */
	public LabelTextPanel<AcceptProjectController> getTextPanel() {
		return this.label;
	}
}