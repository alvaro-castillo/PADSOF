package userInterface.administrator.banning;

import java.awt.Dimension;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import userInterface.administrator.ComboBoxPanel;
import userInterface.administrator.LabelCenteredPanel;
import userInterface.commonElements.BigTextPanel;
import userInterface.commonElements.CenteredButtonPanel;
import userInterface.commonElements.FeedButtonPanel;

/**
* This class will create a panel for banning users.
* It will include a big text indicating the purpose of the panel, a combo box
* for selecting the user, a button for confirm the ban, the go to feed button
* and a label and a text area for writing the reason of the ban.
*
* @author Miguel Álvarez Valiente, Alejandro Benimeli Miranda, Álvaro Castillo García
*/
public class BanUserPanel extends JPanel{
	
	private FeedButtonPanel feed;
	private BigTextPanel text;
	private ComboBoxPanel combo;
	private LabelCenteredPanel label;
	private TextScrollAreaPanel area;
	private CenteredButtonPanel<BanUserController> button;
	private static final long serialVersionUID = 1L;
	private BanUserController controller;
	/**
	 * Constructor of this class.
	 * 
	 * @param v vector with all the usernames in the app
	 */
	public BanUserPanel(Vector<String> v) {
		this.controller = new BanUserController(this);
		this.combo = new ComboBoxPanel(v);
		this.area = new TextScrollAreaPanel();		
		this.feed = new FeedButtonPanel(this);
		this.text = new BigTextPanel("Ban User", 100, 15, 40);		
		this.label = new LabelCenteredPanel("Reason of the ban: ");
		
		this.button = new CenteredButtonPanel<BanUserController>("Ban",controller);
				
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		this.add(feed);
		this.add(text);
		this.add(Box.createRigidArea(new Dimension(0, 10)));
		this.add(combo);
		this.add(label);
		this.add(area);
		this.add(button);
	}
	
	/**
	 * Combo box panel getter. Used by the controller.
	 * @return combo
	 * */
	public ComboBoxPanel getCombo() {
		return this.combo;
	}
	
	/**
	 * Text scroll area panel getter. Used by the controller.
	 * @return area
	 * */
	public TextScrollAreaPanel getArea() {
		return this.area;
	}
}
