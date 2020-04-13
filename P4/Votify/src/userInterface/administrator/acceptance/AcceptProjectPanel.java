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

public class AcceptProjectPanel extends JPanel implements ActionListener {
	private FeedButtonPanel feed;
	private BigTextPanel text;
	private ComboBoxPanel<AcceptProjectPanel> combo;
	private LabelTextPanel<AcceptProjectPanel> label;
	private TwoButtonsPanel<AcceptProjectPanel> buttons;
	private static final long serialVersionUID = 1L;
	
	public AcceptProjectPanel(Vector<String> v) {
		this.feed = new FeedButtonPanel();
		this.text = new BigTextPanel("Accept Project", 145, 10);
		this.combo = new ComboBoxPanel<AcceptProjectPanel>(this,v);
		this.label = new LabelTextPanel<AcceptProjectPanel>(this, "Introduce minimum number of votes: ", 10);
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