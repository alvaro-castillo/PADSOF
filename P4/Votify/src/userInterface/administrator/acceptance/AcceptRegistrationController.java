package userInterface.administrator.acceptance;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import application.Application;
import application.registeredUser.RegisteredUser;
import userInterface.AppFrame;
import userInterface.administrator.feed.AdminFeedPanel;


/**
* This is the controller of the Accept Registration Panel.
*
* @author Miguel Álvarez Valiente, Alejandro Benimeli Miranda, Álvaro Castillo García
*/
public class AcceptRegistrationController implements ActionListener {
	private AcceptRegistrationPanel panel;
	private Application app;
	private JFrame frame;
	
	/**
	 * Constructor of this class.
	 * 
	 * @param panel the panel that is controlled by this class
	 */
	public AcceptRegistrationController(AcceptRegistrationPanel panel) {
		this.panel = panel;
		this.app = Application.getApplication();
		this.frame = AppFrame.getAppFrame();
	}

	/**
	 * This method will perform the operations for accepting or rejecting a user.
	 *
	 * @param e the event caused by an action
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
    	if(o instanceof JButton) {
    		JButton b = (JButton) o;
    		
    		String s = panel.getCombo().getSelectedObject();
    		if(s==null) {
    			return;
    		}
    		RegisteredUser user = app.getUser(s);
    		if(user == null) {
    			return;
    		}
    		String extra;
    		if(b.getText().equals("Accept")) {
    			user.acceptRegistration();
    			extra = "accepted.";
    		}else if(b.getText().equals("Deny")) {
    			user.rejectRegistration();
    			extra = "denied.";
    		}else {
    			return;
    		}
    		
    		JOptionPane.showMessageDialog(panel, "User " + s + " is now "+ extra , "Success", JOptionPane.INFORMATION_MESSAGE);
    		
    		panel.setVisible(false);
    		RegisteredUser admin = app.getAdmin();
    		JPanel p =  new AdminFeedPanel(admin.getUsername(),admin.getNotificationsMessages(), app.getRegisteredUserGroups(admin),  app.getRegisteredUserVotes(admin));
    		frame.add(p);
    		frame.remove(panel);
    		p.setVisible(true);
    	}else {
    		return;
    	}
	}

}
