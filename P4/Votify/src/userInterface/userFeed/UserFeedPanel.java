package userInterface.userFeed;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.Vector;

import javax.swing.JPanel;


/**
* This class will create the User Feed Panel.
* It includes the top buttons panel, the list panel with the notifications and a center panel
* with information about the projects and groups of the user.
*
* @author Miguel Álvarez Valiente, Alejandro Benimeli Miranda, Álvaro Castillo García
*/
public class UserFeedPanel extends JPanel{
	private TopButtonsPanel<UserFeedPanel> buttons;
	private ListPanel notifications;
	private CenterPanel<UserFeedController> center;
	private UserFeedController controller;
	
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of this class.
	 * 
	 * @param username name of the user that is logged in
	 * @param notification a vector that contains notifications in form of string
	 * @param groups a vector with the name of the groups to show
	 * @param projects a vector with the name of the projects to show
	 */
	public UserFeedPanel(String username, Vector<String> notification, Vector<String> groups, Vector<String> projects) {
		this.controller = new UserFeedController(this);
		this.buttons = new TopButtonsPanel<UserFeedPanel>(this, "Create New Group", "Create New Project", "Log Out","Notifications", username);
		this.notifications = new ListPanel(notification, new Dimension(120,150), controller, 0);
		this.center = new CenterPanel<UserFeedController>(30, 65, groups, projects, new Dimension(150,150), controller);

		
		this.setLayout(new BorderLayout());
		this.add(buttons, BorderLayout.NORTH);
		this.add(center,BorderLayout.CENTER);
		this.add(notifications, BorderLayout.EAST);
	}

}
