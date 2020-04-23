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

/**
* This is the controller of the User Feed Panel.
*
* @author Miguel Ã�lvarez Valiente, Alejandro Benimeli Miranda, Ã�lvaro Castillo GarcÃ­a
*/
public class UserFeedController extends KeyAdapter{
	protected JPanel panel;
	protected Application app;
	protected JFrame frame;
	

	/**
	 * Constructor of this class.
	 * 
	 * @param panel the panel that is controlled by this class
	 */
	public UserFeedController(JPanel panel) {
		this.panel = panel;
		this.app = Application.getApplication();
		this.frame = AppFrame.getAppFrame();
	}
	
	public void valueChangedGroup(ListSelectionEvent e,  ListPanel paux) {
		panel.setVisible(false);
		String group = paux.getSelectedValue();
		//JPanel p = new Panel TODO: donde se vea informaciÃ³n del grupo
		//frame.add(p);
		//System.out.println(group);
		frame.remove(panel);
		//p.setVisible(true);
		
	}
	
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

	public void valueChangedNotifications(ListSelectionEvent e,  ListPanel paux) {
		//TODO: Realizar acciones con las notificaciones
	}
	
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
		
		//TODO: enseÃ±ar panel de bÃºsqueda
	}
}
