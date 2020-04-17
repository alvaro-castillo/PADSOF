package userInterface.userFeed;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import userInterface.commonElements.BigTextPanel;

public class TwoTitlesPanel extends JPanel{

	private static final long serialVersionUID = 1L;
	private BigTextPanel text1;
	private BigTextPanel text2;
	public TwoTitlesPanel() {
		this.text1 = new BigTextPanel("Groups", 100,0);
		this.text2 = new BigTextPanel("Projects", 100,0);
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		this.add(text1);
		this.add(text2);
	}

}
