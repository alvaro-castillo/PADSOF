package userInterface.administrator.banning;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import userInterface.*;
import userInterface.administrator.ComboBoxPanel;

public class UnbanUserPanel extends JPanel implements ActionListener{
	private BigTextPanel text;
	private CenteredButtonPanel<UnbanUserPanel> button;
	private FeedButtonPanel feed;
	private ComboBoxPanel<UnbanUserPanel> combo;
	private static final long serialVersionUID = 1L;
	
	public UnbanUserPanel(Vector<String> v) {
		this.text = new BigTextPanel("Unban User", 120, 0);
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
