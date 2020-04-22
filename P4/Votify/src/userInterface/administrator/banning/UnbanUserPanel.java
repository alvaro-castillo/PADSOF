package userInterface.administrator.banning;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
public class UnbanUserPanel extends JPanel implements ActionListener{
	private BigTextPanel text;
	private CenteredButtonPanel<UnbanUserPanel> button;
	private FeedButtonPanel feed;
	private ComboBoxPanel<UnbanUserPanel> combo;
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor of this class.
	 * 
	 * @param v vector with the users that are banned
	 */
	public UnbanUserPanel(Vector<String> v) {
		this.text = new BigTextPanel("Unban User", 120, 0, 40);
		this.button = new CenteredButtonPanel<UnbanUserPanel>("Unban", this);
		this.feed = new FeedButtonPanel();
		this.combo = new ComboBoxPanel<UnbanUserPanel>(this,v);
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		this.add(feed);
		this.add(text);
		this.add(Box.createRigidArea(new Dimension(0, 20)));
		this.add(combo);
		this.add(button);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
