package userInterface.administrator.unbanUserScreen;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import userInterface.CenteredButtonPanel;
import userInterface.FeedButtonPanel;
import userInterface.TextPanel;

public class BanUserPanel extends JPanel implements ActionListener{
	
	private TextPanel text;
	private CenteredButtonPanel<BanUserPanel> button;
	private FeedButtonPanel feed;
	private ComboBoxPanel<BanUserPanel> combo;
	private TextScrollAreaPanel area;
	private LabelCenteredPanel label;
	private static final long serialVersionUID = 1L;
	
	public BanUserPanel(Vector<String> v) {
		this.text = new TextPanel("Ban User", 100, 15);
		this.button = new CenteredButtonPanel<BanUserPanel>("Ban", this);
		this.feed = new FeedButtonPanel();
		this.combo = new ComboBoxPanel<BanUserPanel>(this,v);
		this.label = new LabelCenteredPanel("Reason of the ban: ");
		this.area = new TextScrollAreaPanel();
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		this.add(feed);
		this.add(text);
		this.add(Box.createRigidArea(new Dimension(0, 10)));
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
