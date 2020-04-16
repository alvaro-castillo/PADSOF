package userInterface.userFeed;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JPanel;


public class UserFeedPanel extends JPanel implements ActionListener {
	private TopButtonsPanel<UserFeedPanel> buttons;
	private ListPanel notifications;
	private CenterPanel center;

	private static final long serialVersionUID = 1L;

	public UserFeedPanel(String username, Vector<String> v) {
		this.buttons = new TopButtonsPanel<UserFeedPanel>(this, "Create New Group", "Create New Project", "Log Out","Notifications", username);
		this.notifications = new ListPanel(v);
		this.center = new CenterPanel(this);
		
		
		this.setLayout(new BorderLayout());
		this.add(buttons, BorderLayout.CENTER);
		this.add(center,BorderLayout.SOUTH);
		this.add(notifications, BorderLayout.EAST);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}


}
