package userInterface.userFeed;

import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import userInterface.commonElements.LabelTextPanel;

public class CenterPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private TwoTitlesPanel titles;
	private LabelTextPanel<UserFeedPanel> search;
	public CenterPanel(UserFeedPanel u) {
		
		this.titles = new TwoTitlesPanel();
		this.search = new LabelTextPanel<UserFeedPanel>(u,"Search a group or a project", 20);
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		this.add(search);
		this.add(titles);
		this.add(Box.createRigidArea(new Dimension(0, 160)));

	}

}
