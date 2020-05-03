package userInterface.createGroupScreen;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import application.Application;
import application.group.Group;
import application.registeredUser.RegisteredUser;
import userInterface.AppFrame;
import userInterface.administrator.feed.AdminFeedPanel;
import userInterface.userFeed.UserFeedPanel;
/**
* This is the controller of the Create Group Panel.
*
* @author Miguel Ã�lvarez Valiente, Alejandro Benimeli Miranda, Ã�lvaro Castillo GarcÃ­a
*/
public class CreateGroupController extends KeyAdapter implements ActionListener{
	private CreateGroupScreen panel;
	private Application app;
	private JFrame frame;
	
	/**
	 * Constructor of this class.
	 * 
	 * @param panel the panel that is controlled by this class
	 */
	public CreateGroupController(CreateGroupScreen panel) {
		this.panel = panel;
		this.app = Application.getApplication();
		this.frame = AppFrame.getAppFrame();
	}
	
	/**
	 * This method will perform the operations for creating a user.
	 * It is invoked when the create button is pressed.
	 *
	 * @param e the event caused by an action
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String name = panel.getLabelTextPanel().getText();
		if (name.equals("")) {
			 JOptionPane.showMessageDialog(panel, "You must type a valid group name.", "Error", JOptionPane.ERROR_MESSAGE);
			 return;
		}
		RegisteredUser user = app.getCurrentUser();
		Group g = new Group(name, user);
		if(app.addGroup(g)==false) {
			JOptionPane.showMessageDialog(panel, "Group " + name + " already exists in the app." , "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		JOptionPane.showMessageDialog(panel, "Group " + name +  " has been created.", "Success", JOptionPane.INFORMATION_MESSAGE);
		
		panel.setVisible(false);
		
		JPanel feed;
		
		if(user.equals(app.getAdmin())) {
			feed = new AdminFeedPanel(user.getUsername(),user.getNotificationsMessages(), app.getRegisteredUserGroups(user), app.getRegisteredUserVotes(user));
		}else {
			feed = new UserFeedPanel(user.getUsername(),user.getNotificationsMessages(), app.getRegisteredUserGroups(user),  app.getRegisteredUserVotes(user));
		}
		
		JFrame frame = AppFrame.getAppFrame();
		frame.add(feed);
		frame.remove(panel);		
	}

	/**
	 * This method will perform the operations for creating a user.
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
		String name = aux.getText();
		if (name.equals("")) {
			 JOptionPane.showMessageDialog(panel, "You must type a valid group name.", "Error", JOptionPane.ERROR_MESSAGE);
			 return;
		}
		RegisteredUser user = app.getCurrentUser();
		Group g = new Group(name, user);
		if(app.addGroup(g)==false) {
			JOptionPane.showMessageDialog(panel, "Group " + name + " already exists in the app." , "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		JOptionPane.showMessageDialog(panel, "Group " + name +  " has been created.", "Success", JOptionPane.INFORMATION_MESSAGE);
		
		panel.setVisible(false);
		JPanel p;
		if(user.equals(app.getAdmin())) {
			user = app.getAdmin();
			p =  new AdminFeedPanel(user.getUsername(),user.getNotificationsMessages(), app.getRegisteredUserGroups(user),  app.getRegisteredUserVotes(user));
		}else {
			p =  new UserFeedPanel(user.getUsername(),user.getNotificationsMessages(), app.getRegisteredUserGroups(user),  app.getRegisteredUserVotes(user));
		}
		frame.add(p);
		frame.remove(panel);
		p.setVisible(true);
	}
}
