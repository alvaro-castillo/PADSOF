package userInterface.administrator.acceptance;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import userInterface.*;
import userInterface.administrator.ComboBoxPanel;


public class AcceptRegistrationPanel extends JPanel implements ActionListener {
	private FeedButtonPanel feed;
	private BigTextPanel text;
	private ComboBoxPanel<AcceptRegistrationPanel> combo;
	private TwoButtonsPanel<AcceptRegistrationPanel> buttons;
	private static final long serialVersionUID = 1L;
	
	public AcceptRegistrationPanel(Vector<String> v) {
		this.feed = new FeedButtonPanel();
		this.text = new BigTextPanel("Accept Registration", 220, 0);
		this.combo = new ComboBoxPanel<AcceptRegistrationPanel>(this,v);
		this.buttons = new TwoButtonsPanel<AcceptRegistrationPanel>(this, "Accept", "Deny");
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.add(feed);
		this.add(text);
		this.add(Box.createRigidArea(new Dimension(0, 20)));
		this.add(combo);
		this.add(buttons);
		this.add(Box.createRigidArea(new Dimension(0, 80)));
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}


}
