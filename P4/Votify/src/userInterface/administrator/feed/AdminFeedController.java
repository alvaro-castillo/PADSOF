package userInterface.administrator.feed;

import java.awt.event.ActionEvent;

import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;

import application.notification.Notification;
import userInterface.administrator.acceptance.*;
import userInterface.administrator.banning.*;
import userInterface.userFeed.ListPanel;
import userInterface.userFeed.UserFeedController;

/**
* This is the controller of the Admin Feed Panel.
*
* @author Miguel Álvarez Valiente, Alejandro Benimeli Miranda, Álvaro Castillo García
*/
public class AdminFeedController extends UserFeedController{
	
	/**
	 * Constructor of this class.
	 * 
	 * @param panel the panel that is controlled by this class
	 */
	public AdminFeedController(JPanel panel) {
		super(panel);
	}
	
	/**
	 * This method will show the screen for accepting a registration
	 *
	 * @param e the event caused by an action
	 */
	public void actionPerformedRegistration(ActionEvent e) {
		panel.setVisible(false);
		JPanel p = new AcceptRegistrationPanel(app.getPendingAcceptanceUsers());
		frame.add(p);
		frame.remove(panel);
		p.setVisible(true);
	}
	
	/**
	 * This method will show the screen for accepting a group
	 *
	 * @param e the event caused by an action
	 */
	public void actionPerformedGroup(ActionEvent e) {
		panel.setVisible(false);
		JPanel p = new AcceptGroupPanel(app.getPendingAcceptanceGroups());
		frame.add(p);
		frame.remove(panel);
		p.setVisible(true);
	}
	
	/**
	 * This method will show the screen for accepting a project
	 *
	 * @param e the event caused by an action
	 */
	public void actionPerformedProject(ActionEvent e) {
		panel.setVisible(false);
		JPanel p = new AcceptOrDenyProjectPanel(app.getPendingAcceptanceProjects());
		frame.add(p);
		frame.remove(panel);
		p.setVisible(true);
	}
	
	/**
	 * This method will show the screen for banning a user
	 *
	 * @param e the event caused by an action
	 */
	public void actionPerformedBan(ActionEvent e) {
		panel.setVisible(false);
		JPanel p = new BanUserPanel(app.getRegisteredUsers());
		frame.add(p);
		frame.remove(panel);
		p.setVisible(true);
		
	}
	
	/**
	 * This method will show the screen for unbanning a user
	 *
	 * @param e the event caused by an action
	 */
	public void actionPerformedUnban(ActionEvent e) {
		panel.setVisible(false);
		JPanel p = new UnbanUserPanel(app.getBannedUsers());
		frame.add(p);
		frame.remove(panel);
		p.setVisible(true);
	}

	
	/**
	 * This method will be invoked when the user selects an item from the list.
	 * In this case the list is for notifications.
	 * 
	 * @param e the event that generated the action
	 * @param paux the panel where the list is
	 **/
	@Override
	public void valueChangedNotifications(ListSelectionEvent e,  ListPanel paux) {
		String s = paux.getSelectedValue();
		
		Notification n = app.getCurrentUser().getNotificationSelected(s);
		
		if(n==null) {
			return;
		}
		if(n.isRead()==true) {
			return;
		}
		n.setRead();
		AdminFeedPanel p = (AdminFeedPanel) panel;
		p.setNotifications(s, n.toString());
	}
}
