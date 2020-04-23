package userInterface.administrator.feed;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.Vector;
import javax.swing.JPanel;
import userInterface.userFeed.*;
/**
* This class will create the Administrator Feed Panel.
* It includes the top buttons panel, the list panel with the notifications, a center panel
* with information about the projects and groups of the user and a panel with the tools
* of the administrator.
*
* @author Miguel Álvarez Valiente, Alejandro Benimeli Miranda, Álvaro Castillo García
*/
public class AdminFeedPanel extends JPanel{
	private TopButtonsPanel<AdminFeedPanel> buttons;
	private ListPanel notifications;
	private CenterPanel<AdminFeedController> center;
	private AdminToolsPanel tools;
	private AdminFeedController controller;
	private static final long serialVersionUID = 1L;

	/**
	 * COnstructor of this class.
	 * 
	 * @param username name of the user that is logged in
	 * @param notification a vector that contains notifications in form of string
	 * @param groups a vector with the name of the groups to show
	 * @param projects a vector with the name of the projects to show
	 */
	public AdminFeedPanel(String username, Vector<String> notification, Vector<String> groups, Vector<String> projects) {
		this.controller = new AdminFeedController(this);
		this.buttons = new TopButtonsPanel<AdminFeedPanel>(this, "Create New Group", "Create New Project", "Log Out","Notifications", username);
		this.notifications = new ListPanel(notification, new Dimension(120,150), controller, 0);
		this.center = new CenterPanel<AdminFeedController>(30, 60, groups, projects, new Dimension(130,150), controller);
		this.tools = new AdminToolsPanel(controller);
		
		this.setLayout(new BorderLayout());
		this.add(buttons, BorderLayout.NORTH);
		this.add(center,BorderLayout.CENTER);
		this.add(notifications, BorderLayout.EAST);
		this.add(tools, BorderLayout.WEST);
	}
}
