package userInterface.userFeed;

import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import userInterface.commonElements.LabelTextPanel;
/**
* This class represents the center panel of a user feed.
* It contains two big titles (TwoTitlesPanel), two scroll list panels (GroupsAndProjectsPanel)
* and a label with a text for the searches(LabelTextPanel).
*
* @author Miguel Álvarez Valiente, Alejandro Benimeli Miranda, Álvaro Castillo García
*/
public class CenterPanel<A extends KeyAdapter & IListPanel> extends JPanel {

	private static final long serialVersionUID = 1L;
	private TwoTitlesPanel titles;
	private LabelTextPanel<KeyAdapter> search;
	private GroupsAndProjectsPanel projectsAndGroups;
	
	/**
	 * Constructor of this class. 
	 *
	 * @param size the size of the two big titles
	 * @param offset integer number that will be added to the x axis to position the titles 
	 * @param groups a vector with the name of the groups to show
	 * @param projects a vector with the name of the projects to show
	 * @param d dimensions of the scroll panels for the projects and groups
	 */
	public CenterPanel(int size, int offset, Vector<String> groups, Vector<String> projects, Dimension d, A controller) {
		
		this.titles = new TwoTitlesPanel(size, offset);
		this.search = new LabelTextPanel<KeyAdapter>(controller,"Search a group or a project", 20);
		this.projectsAndGroups = new GroupsAndProjectsPanel(groups,projects, d, controller);
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		this.add(search);
		this.add(titles);

		this.add(projectsAndGroups);

	}

}
