package userInterface.loginScreen;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import application.Application;
import application.registeredUser.RegisteredUser;
import userInterface.AppFrame;
import userInterface.loginScreen.LoginPanel;
import userInterface.userFeed.UserFeedPanel;

public class LoginController implements ActionListener {
	
	private LoginPanel panel;
	private Application app;

	public LoginController(LoginPanel panel) {
		this.panel = panel;
		this.app = Application.getApplication();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
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
		
		UserFeedPanel feed;
		if(user.equals(app.getAdmin())) {
			feed = new UserFeedPanel(userName,user.getNotificationsMessages()); // Cambiar a AdminFeed
		}else {
			feed = new UserFeedPanel(userName,user.getNotificationsMessages());
		}
		JFrame frame = AppFrame.getAppFrame();
		frame.add(feed);
		frame.remove(panel);
		return;
	}

}
