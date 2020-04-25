package userInterface.commonElements;

import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

import application.Application;
import application.registeredUser.RegisteredUser;
import userInterface.AppFrame;
import userInterface.administrator.feed.AdminFeedPanel;
import userInterface.userFeed.UserFeedPanel;

/**
* This is the controller of the Go to feed button.
*
* @author Miguel Álvarez Valiente, Alejandro Benimeli Miranda, Álvaro Castillo García
*/
public class FeedButtonController {
	
	private JPanel panel;
	private Application app;
	
	/**
	 * Constructor of this class.
	 * 
	 * @param panel the panel that is controlled by this class
	 */
	public FeedButtonController(JPanel panel) {
		this.panel = panel;
		this.app = Application.getApplication();
	}
	/**
	 * This method will be invoked when the user press the feed button.
	 * 
	 * @param e the event that generated the action
	 **/
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
