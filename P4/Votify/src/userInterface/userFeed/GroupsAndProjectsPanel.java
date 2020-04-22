package userInterface.userFeed;

import java.awt.Dimension;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

/**
* This class creates two panels with scrolls for storing the groups and projects of a user and 
* show them in the central panel of the user feed.
*
* @author Miguel Álvarez Valiente, Alejandro Benimeli Miranda, Álvaro Castillo García
*/
public class GroupsAndProjectsPanel extends JPanel {
	
	private ListPanel groups;
	private ListPanel projects;
	
	
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of this class.
	 * @param groups a vector with the name of the groups to show
	 * @param projects a vector with the name of the projects to show
	 * @param d dimensions of the scroll panels for the projects and groups
	 * @param controller the controller of the panel
	 */
	public GroupsAndProjectsPanel(Vector<String> groups, Vector<String> projects, Dimension d, UserFeedController controller) {
		this.groups = new ListPanel(groups, d, controller, 1);
		this.projects = new ListPanel(projects, d, controller, -1);
		
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		this.add(this.groups);
		this.add(this.projects);
	}



}
