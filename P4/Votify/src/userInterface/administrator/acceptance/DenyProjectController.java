package userInterface.administrator.acceptance;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import application.Application;
import application.project.Project;
import application.registeredUser.RegisteredUser;
import userInterface.AppFrame;
import userInterface.administrator.feed.AdminFeedPanel;

/**
* This is the controller of the Deny Project Panel.
*
* @author Miguel Álvarez Valiente, Alejandro Benimeli Miranda, Álvaro Castillo García
*/
public class DenyProjectController extends KeyAdapter implements ActionListener {
	private DenyProjectPanel panel;
	private Application app;
	private JFrame frame;
	private Project project;
	
	/**
	 * Constructor of this class.
	 * 
	 * @param panel the panel that is controlled by this class
	 * @param project the project that we will have to deny
	 */
	public DenyProjectController(DenyProjectPanel panel, Project project) {
		this.panel = panel;
		this.app = Application.getApplication();
		this.frame = AppFrame.getAppFrame();
		this.project = project;
	}

	/**
	 * This method will be executed when the accept button is pressed.
	 *
	 * @param e the event caused by an action
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
    		
		denyProject(panel.getTextPanel().getText());

   	}
		
	/**
	 * This method will be executed when a key is pressed but will perform
	 * other operations when the enter key is pressed.
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
		denyProject(aux.getText());
		
	}
	
	/**
	 * Method for rejecting a project
	 * 
	 * @param reason reason of the rejection
	 */
	private void denyProject(String reason) {
		
		project.adminRejectProject(reason);
		
		JOptionPane.showMessageDialog(panel, "Project " + project.getTitle() + " is now denied.", "Success", JOptionPane.INFORMATION_MESSAGE);
		
		panel.setVisible(false);
		RegisteredUser admin = app.getAdmin();
		JPanel p =  new AdminFeedPanel(admin.getUsername(),admin.getNotificationsMessages(), app.getRegisteredUserGroups(admin),  app.getRegisteredUserVotes(admin));
		frame.add(p);
		frame.remove(panel);
		p.setVisible(true);
		
	}
}
