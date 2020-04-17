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

public class BanUserPanel extends JPanel implements ActionListener{
	
	private FeedButtonPanel feed;
	private BigTextPanel text;
	private ComboBoxPanel<BanUserPanel> combo;
	private LabelCenteredPanel label;
	private TextScrollAreaPanel area;
	private CenteredButtonPanel<BanUserPanel> button;
	private static final long serialVersionUID = 1L;
	
	public BanUserPanel(Vector<String> v) {
		this.feed = new FeedButtonPanel();
		this.text = new BigTextPanel("Ban User", 100, 15);
		this.combo = new ComboBoxPanel<BanUserPanel>(this,v);
		this.label = new LabelCenteredPanel("Reason of the ban: ");
		this.area = new TextScrollAreaPanel();
		this.button = new CenteredButtonPanel<BanUserPanel>("Ban", this);
		
		
		
		
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
