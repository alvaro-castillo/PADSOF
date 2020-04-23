package userInterface.userFeed;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;

import application.Application;
import application.project.InfrastructureProject;
import application.project.Project;
import application.project.SocialProject;
import userInterface.AppFrame;
import userInterface.projectScreen.InfrastructureProjectPanel;
import userInterface.projectScreen.SocialProjectPanel;
import userInterface.searchPanel.SearchPanel;

/**
* This is the abstract class used in the search controller and user feed controller
* as both have the same methods.
*
* @author Miguel Álvarez Valiente, Alejandro Benimeli Miranda, Álvaro Castillo García
*/
public abstract class ListPanelExtender extends KeyAdapter implements IListPanel{
	protected JFrame frame;
	protected JPanel panel;
	protected Application app;
	
	/**
	 * Constructor of this class.
	 * 
	 * @param panel the panel that is controlled by this class
	 */
	public ListPanelExtender(JPanel panel) {
		this.frame = AppFrame.getAppFrame();
		this.panel = panel;
		this.app = Application.getApplication();
	}

	/**
	 * This method will be invoked when the user selects an item from the list.
	 * In this case the list is for groups.
	 * 
	 * @param e the event that generated the action
	 * @param paux the panel where the list is
	 **/
	@Override
	public void valueChangedGroup(ListSelectionEvent e,  ListPanel paux) {
		panel.setVisible(false);
		String group = paux.getSelectedValue();
		//JPanel p = new Panel TODO: donde se vea informaciÃ³n del grupo
		//frame.add(p);
		//System.out.println(group);
		frame.remove(panel);
		//p.setVisible(true);
		
	}

	/**
	 * This method will be invoked when the user selects an item from the list.
	 * In this case the list is for projects.
	 * 
	 * @param e the event that generated the action
	 * @param paux the panel where the list is
	 **/
	@Override
	public void valueChangedProject(ListSelectionEvent e,  ListPanel paux) {
		panel.setVisible(false);
		String projectString = paux.getSelectedValue();
		
		String[] partsOfStr = projectString.split(" ");
		
		int id = 0;
		
		try {
			id = Integer.parseInt(partsOfStr[partsOfStr.length-1]);
		} catch (Exception exc) {
			//error
			return;
		}
		
		Project p = app.searchProject(id);
		if (p == null) {
			// error
			return;
		}
		
		JPanel pan = null;
		if (p instanceof InfrastructureProject) {
			pan = new InfrastructureProjectPanel((InfrastructureProject)p, app.getCurrentUser());
		} else if (p instanceof SocialProject) {
			pan = new SocialProjectPanel((SocialProject)p, app.getCurrentUser());
		} else {
			return;
		}

		panel.setVisible(false);
		frame.add(pan);
		frame.remove(panel);
		
	}
	
	/**
	 * This method will perform the operations for querying groups and projects.
	 * It is invoked when a key is pressed, but it will perform the whole operation
	 * when ENTER is pressed.
	 *
	 * @param e the event caused by an action
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()!=KeyEvent.VK_ENTER) {
			return;
		}
		
		Object o = e.getSource();
		
		if(!(o instanceof  JTextField) || o==null) {
			return;
		}
		JTextField aux = (JTextField) o;
		String s = aux.getText();
		if(s.equals("")) {
			return;
		}
		JPanel pan = new SearchPanel(app.queryGroups(s), app.queryProjects(s));
		panel.setVisible(false);
		frame.add(pan);
		frame.remove(panel);
	}

}
