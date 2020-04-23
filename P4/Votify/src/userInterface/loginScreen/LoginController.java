package userInterface.loginScreen;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import application.Application;
import application.registeredUser.RegisteredUser;
import userInterface.AppFrame;
import userInterface.administrator.feed.AdminFeedPanel;
import userInterface.loginScreen.LoginPanel;
import userInterface.userFeed.UserFeedPanel;

/**
* This is the controller of the Log in Panel. It will perform some methods when the login
* button is pressed.
*
* @author Miguel Álvarez Valiente, Alejandro Benimeli Miranda, Álvaro Castillo García
*/
public class LoginController extends KeyAdapter implements ActionListener {
	
	private LoginPanel panel;
	private Application app;

	/**
	 * Constructor of this class.
	 * 
	 * @param panel the panel that is controlled by this class
	 */
	public LoginController(LoginPanel panel) {
		this.panel = panel;
		this.app = Application.getApplication();

	}

	/**
	 * This will be executed when an action event is received
	 *
	 * @param e the event caused by an action
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		this.logIn();
		return;
	}
	
	/**
	 * This will be executed when a key event is received
	 *
	 * @param e the event caused by an action
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()!=KeyEvent.VK_ENTER) {
			return;
		}
		this.logIn();
		return;
	}
	
	/**
	 * This method will perform the operations for login a user into the app.
	 */
	private void logIn() {
		String userName = panel.getUsername();
		String password = panel.getUserPassword();
		
		
		if (userName.equals("")) {
			 JOptionPane.showMessageDialog(panel, "You must type a valid username.", "Error", JOptionPane.ERROR_MESSAGE);
			 return;
		}else if(password.equals("")) {
			 JOptionPane.showMessageDialog(panel, "You must type a valid password.", "Error", JOptionPane.ERROR_MESSAGE);
			 return;
		}
		
		try{
			app.logIn(userName, password);
		}catch(Exception ex) {
			JOptionPane.showMessageDialog(panel, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		
		panel.setVisible(false);
		RegisteredUser user = app.getCurrentUser();
		
		JPanel feed;
		if(user.equals(app.getAdmin())) {
			feed = new AdminFeedPanel(userName,user.getNotificationsMessages(), app.getRegisteredUserGroups(user), app.getRegisteredUserVotes(user));
		}else {
			feed = new UserFeedPanel(userName,user.getNotificationsMessages(), app.getRegisteredUserGroups(user),  app.getRegisteredUserVotes(user));
		}
		JFrame frame = AppFrame.getAppFrame();
		frame.add(feed);
		frame.remove(panel);
		return;
	}
}
