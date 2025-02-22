package userInterface.registerScreen;

import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import application.Application;
import application.registeredUser.RegisteredUser;
import userInterface.AppFrame;
import userInterface.initialScreen.InitialPanel;
import userInterface.loginScreen.LoginPanel;
/**
* This is the controller of the Register Panel. It will perform some methods when the register
* button is pressed.
*
* @author Miguel Álvarez Valiente, Alejandro Benimeli Miranda, Álvaro Castillo García
*/
public class RegisterController extends KeyAdapter {
	
	private RegisterPanel panel;
	private Application app;

	/**
	 * Constructor of this class.
	 * 
	 * @param panel the panel that is controlled by this class
	 */
	public RegisterController(RegisterPanel panel) {
		this.panel = panel;
		this.app = Application.getApplication();
	}

	/**
	 * This will be executed when the register button is pressed.
	 *
	 * @param e the event caused by an action
	 */
	public void pressedRegister(ActionEvent e) {
		this.register();
	}
	
	/**
	 * This will be executed when the initial screen button is pressed
	 *
	 * @param e the event caused by an action
	 */
	public void goToMainScreen(ActionEvent e) {
		JFrame m = AppFrame.getAppFrame();
		m.remove(panel);
    	JPanel p = InitialPanel.getInitialPanel();
    	m.add(p);
    	p.setVisible(true);
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
		this.register();
		return;
	}
	
	/**
	 * This method will perform the operations for registering a user.
	 */
	private void register() {
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
		}else if(userId.length()!=9) {
			JOptionPane.showMessageDialog(panel, "You must type a valid ID with 9 characters.", "Error", JOptionPane.ERROR_MESSAGE);
			 return;
		}
		
		for(int i=0; i<8; i++) {
			if(Character.isLetter(userId.charAt(i))) {
				JOptionPane.showMessageDialog(panel, "You must type a valid ID with 9 digits.", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
		
		RegisteredUser u = new RegisteredUser(userId,userName,password);
		
		if(app.addUser(u)==false) {
			JOptionPane.showMessageDialog(panel, "Username or ID already registered in the app", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		JOptionPane.showMessageDialog(panel, "Account created successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
		
		panel.setVisible(false);
		JFrame frame = AppFrame.getAppFrame();
		frame.add(new LoginPanel());
		frame.remove(panel);
		return;
	}

}
