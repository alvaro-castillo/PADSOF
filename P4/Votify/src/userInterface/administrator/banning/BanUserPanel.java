package userInterface.administrator.banning;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
* and a label and a text area for writting the reason of the ban.
*
* @author Miguel Álvarez Valiente, Alejandro Benimeli Miranda, Álvaro Castillo García
*/
public class BanUserPanel extends JPanel implements ActionListener{
	
	private FeedButtonPanel feed;
	private BigTextPanel text;
	private ComboBoxPanel<BanUserPanel> combo;
	private LabelCenteredPanel label;
	private TextScrollAreaPanel area;
	private CenteredButtonPanel<BanUserPanel> button;
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor of this class.
	 * 
	 * @param v vector with all the usernames in the app
	 */
	public BanUserPanel(Vector<String> v) {
		this.feed = new FeedButtonPanel();
		this.text = new BigTextPanel("Ban User", 100, 15, 40);
		this.combo = new ComboBoxPanel<BanUserPanel>(this,v);
		this.label = new LabelCenteredPanel("Reason of the ban: ");
		this.area = new TextScrollAreaPanel();
		this.button = new CenteredButtonPanel<BanUserPanel>("Ban", this);
				
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		this.add(feed);
		this.add(text);
		this.add(Box.createRigidArea(new Dimension(0, 20)));
		this.add(combo);
		this.add(label);
		this.add(area);
		this.add(button);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
