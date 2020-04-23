package userInterface.administrator.banning;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import application.Application;
import application.registeredUser.RegisteredUser;
import userInterface.AppFrame;
import userInterface.administrator.feed.AdminFeedPanel;

/**
* This is the controller of the Unban User Panel.
*
* @author Miguel Álvarez Valiente, Alejandro Benimeli Miranda, Álvaro Castillo García
*/
public class UnbanUserController implements ActionListener{
	private UnbanUserPanel panel;
	private Application app;
	private JFrame frame;
	

	/**
	 * Constructor of this class.
	 * 
	 * @param panel the panel that is controlled by this class
	 */
	public UnbanUserController(UnbanUserPanel panel) {
		this.panel = panel;
		this.app = Application.getApplication();
		this.frame = AppFrame.getAppFrame();
	}
	
	/**
	 * This method will perform the operations for unbanning a user.
	 *
	 * @param e the event caused by an action
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String s = panel.getCombo().getSelectedObject();
		if(s==null) {
			return;
		}
		RegisteredUser unbannedUser = app.getUser(s);
		if(unbannedUser == null) {
			return;
		}
		unbannedUser.unbanUser();
		
		JOptionPane.showMessageDialog(panel, "User " + s + " is now unbanned.", "Success", JOptionPane.INFORMATION_MESSAGE);
		
		panel.setVisible(false);
		RegisteredUser admin = app.getAdmin();
		JPanel p =  new AdminFeedPanel(admin.getUsername(),admin.getNotificationsMessages(), app.getRegisteredUserGroups(admin),  app.getRegisteredUserVotes(admin));
		frame.add(p);
		frame.remove(panel);
		p.setVisible(true);
	}

}
