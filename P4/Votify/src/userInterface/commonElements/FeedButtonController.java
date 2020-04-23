package userInterface.commonElements;

import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

import application.Application;
import application.registeredUser.RegisteredUser;
import userInterface.AppFrame;
import userInterface.administrator.feed.AdminFeedPanel;
import userInterface.userFeed.UserFeedPanel;

public class FeedButtonController {
	
	private JPanel panel;
	private Application app;

	public FeedButtonController(JPanel panel) {
		this.panel = panel;
		this.app = Application.getApplication();
	}
	
	public void feedButtonPressed(ActionEvent e) {

		RegisteredUser user = app.getCurrentUser();
		
		panel.setVisible(false);
		
		
		JPanel feed;
		
		new UserFeedPanel(user.getUsername(),user.getNotificationsMessages(), app.getRegisteredUserGroups(user),  app.getRegisteredUserVotes(user));
		if(user.equals(app.getAdmin())) {
			feed = new AdminFeedPanel(user.getUsername(),user.getNotificationsMessages(), app.getRegisteredUserGroups(user), app.getRegisteredUserVotes(user));
		}else {
			feed = new UserFeedPanel(user.getUsername(),user.getNotificationsMessages(), app.getRegisteredUserGroups(user),  app.getRegisteredUserVotes(user));
		}
		
		JFrame frame = AppFrame.getAppFrame();
		frame.add(feed);
		frame.remove(panel);
		
	}

}
