package userInterface.administrator.acceptance;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
public class AcceptProjectPanel extends JPanel implements ActionListener {
	private FeedButtonPanel feed;
	private BigTextPanel text;
	private ComboBoxPanel<AcceptProjectPanel> combo;
	private LabelTextPanel<?> label; //TODO:Parametrize with the controller
	private TwoButtonsPanel<AcceptProjectPanel> buttons;
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor of this class.
	 * 
	 * @param v vector with all the project names that have just been created
	 */
	public AcceptProjectPanel(Vector<String> v) {
		this.feed = new FeedButtonPanel();
		this.text = new BigTextPanel("Accept Project", 145, 10, 40);
		this.combo = new ComboBoxPanel<AcceptProjectPanel>(this,v);
		this.label = new LabelTextPanel<?>(this, "Introduce minimum number of votes: ", 10);
		this.buttons = new TwoButtonsPanel<AcceptProjectPanel>(this, "Accept", "Deny");
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.add(feed);
		this.add(text);
		this.add(Box.createRigidArea(new Dimension(0, 20)));
		this.add(combo);
		this.add(label);
		this.add(buttons);
		this.add(Box.createRigidArea(new Dimension(0, 60)));
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}