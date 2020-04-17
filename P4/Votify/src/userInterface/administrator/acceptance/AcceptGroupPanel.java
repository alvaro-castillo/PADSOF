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
import userInterface.commonElements.TwoButtonsPanel;


public class AcceptGroupPanel extends JPanel implements ActionListener {
	private FeedButtonPanel feed;
	private BigTextPanel text;
	private ComboBoxPanel<AcceptGroupPanel> combo;
	private TwoButtonsPanel<AcceptGroupPanel> buttons;
	private static final long serialVersionUID = 1L;
	
	public AcceptGroupPanel(Vector<String> v) {
		this.feed = new FeedButtonPanel();
		this.text = new BigTextPanel("Accept Group", 145, 0);
		this.combo = new ComboBoxPanel<AcceptGroupPanel>(this,v);
		this.buttons = new TwoButtonsPanel<AcceptGroupPanel>(this, "Accept", "Deny");
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
