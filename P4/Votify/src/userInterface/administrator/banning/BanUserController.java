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
* This is the controller of the Ban User Panel.
*
* @author Miguel Álvarez Valiente, Alejandro Benimeli Miranda, Álvaro Castillo García
*/
public class BanUserController implements ActionListener{
	
	private BanUserPanel panel;
	private Application app;
	private JFrame frame;
	
	/**
	 * Constructor of this class.
	 * 
	 * @param panel the panel that is controlled by this class
	 */
	public BanUserController(BanUserPanel panel) {
		this.panel = panel;
		this.app = Application.getApplication();
		this.frame = AppFrame.getAppFrame();
	}
	
	/**
	 * This method will perform the operations for banning a user.
	 *
	 * @param e the event caused by an action
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String s = panel.getCombo().getSelectedObject();
		if(s==null) {
			return;
		}
		RegisteredUser bannedUser = app.getUser(s);
		if(bannedUser == null) {
			return;
		}
		String reason = panel.getArea().getReason();
		
		if(reason.equals("")) {
			bannedUser.banUser();
		}else {
			bannedUser.banUser(reason);
		}
		
		JOptionPane.showMessageDialog(panel, "User " + s + " is now banned.", "Success", JOptionPane.INFORMATION_MESSAGE);
		
		panel.setVisible(false);
		RegisteredUser admin = app.getAdmin();
		JPanel p =  new AdminFeedPanel(admin.getUsername(),admin.getNotificationsMessages(), app.getRegisteredUserGroups(admin),  app.getRegisteredUserVotes(admin));
		frame.add(p);
		frame.remove(panel);
		p.setVisible(true);
		
	}

}
