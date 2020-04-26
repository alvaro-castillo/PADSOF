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
import application.enums.Status;
import application.group.Group;
import application.registeredUser.RegisteredUser;
import userInterface.AppFrame;
import userInterface.userFeed.UserFeedPanel;

public class CreateSubgroupController extends KeyAdapter implements ActionListener {
	
	private CreateSubgroupScreen panel;
	private Application app;
	private JFrame frame;
	private Group gr;
	
	/**
	 * Constructor of this class.
	 * 
	 * @param panel the panel that is controlled by this class
	 */
	public CreateSubgroupController(CreateSubgroupScreen panel, Group gr) {
		this.panel = panel;
		this.app = Application.getApplication();
		this.frame = AppFrame.getAppFrame();
		this.gr = gr;
	}
	
	/**
	 * This method will perform the operations for creating a subgroup.
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
		if (app.searchGroup(name) != null) {
			JOptionPane.showMessageDialog(panel, "Group " + name + " already exists in the app." , "Error", JOptionPane.ERROR_MESSAGE);
			return;
		} else if (gr.getStatus() != Status.ACCEPTED) {
			JOptionPane.showMessageDialog(panel, "Parent group has to be accepted by the admin." , "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		Group g = gr.createSubgroup(name, user);
		if(app.addGroup(g)==false) {
			JOptionPane.showMessageDialog(panel, "Error creating subgroup" , "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		JOptionPane.showMessageDialog(panel, "Group " + name +  " has been created.", "Success", JOptionPane.INFORMATION_MESSAGE);
		
		panel.setVisible(false);
		
		if(user.equals(app.getAdmin())) {
			user = app.getAdmin();	
		}
		JPanel p =  new UserFeedPanel(user.getUsername(),user.getNotificationsMessages(), app.getRegisteredUserGroups(user),  app.getRegisteredUserVotes(user));
		
		frame.add(p);
		frame.remove(panel);
		p.setVisible(true);
		
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
		if (app.searchGroup(name) != null) {
			JOptionPane.showMessageDialog(panel, "Group " + name + " already exists in the app." , "Error", JOptionPane.ERROR_MESSAGE);
			return;
		} else if (gr.getStatus() != Status.ACCEPTED) {
			JOptionPane.showMessageDialog(panel, "Parent group has to be accepted by the admin." , "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		Group g = gr.createSubgroup(name, user);
		if(app.addGroup(g)==false) {
			JOptionPane.showMessageDialog(panel, "Error creating subgroup" , "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}

		JOptionPane.showMessageDialog(panel, "Group " + name +  " has been created.", "Success", JOptionPane.INFORMATION_MESSAGE);
		
		panel.setVisible(false);
		
		if(user.equals(app.getAdmin())) {
			user = app.getAdmin();	
		}
		JPanel p =  new UserFeedPanel(user.getUsername(),user.getNotificationsMessages(), app.getRegisteredUserGroups(user),  app.getRegisteredUserVotes(user));
		
		frame.add(p);
		frame.remove(panel);
		p.setVisible(true);
	}

}
