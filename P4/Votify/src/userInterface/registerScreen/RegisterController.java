package userInterface.registerScreen;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import application.Application;
import application.registeredUser.RegisteredUser;
import userInterface.AppFrame;
import userInterface.loginScreen.LoginPanel;

public class RegisterController implements ActionListener {
	
	private RegisterPanel panel;
	private Application app;

	public RegisterController(RegisterPanel panel) {
		this.panel = panel;
		this.app = Application.getApplication();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String userName = panel.getUsername();
		String userId = panel.getUserId();
		String password = panel.getUserPassword();
		
		if (userName.equals("")) {
			 JOptionPane.showMessageDialog(panel, "You must type a valid username.", "Error", JOptionPane.ERROR_MESSAGE);
			 return;
		}else if(userId.equals("")) {
			 JOptionPane.showMessageDialog(panel, "You must type a valid ID.", "Error", JOptionPane.ERROR_MESSAGE);
			 return;
		}else if(password.equals("")) {
			 JOptionPane.showMessageDialog(panel, "You must type a valid password.", "Error", JOptionPane.ERROR_MESSAGE);
			 return;
		}
		
		RegisteredUser u = new RegisteredUser(userId,userName,password);
		
		if(app.addUser(u)==false) {
			JOptionPane.showMessageDialog(panel, "Username or ID already registered in the app", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		JOptionPane.showMessageDialog(panel, "Account created successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
		
		panel.setVisible(false);
		AppFrame.getAppFrame().add(new LoginPanel());
		
		return;
	}

}
