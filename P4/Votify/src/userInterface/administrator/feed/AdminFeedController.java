package userInterface.administrator.feed;

import java.awt.event.ActionEvent;

import javax.swing.JPanel;

import userInterface.administrator.acceptance.*;
import userInterface.administrator.banning.*;
import userInterface.userFeed.UserFeedController;

/**
* This is the controller of the Admin Feed Panel.
*
* @author Miguel Álvarez Valiente, Alejandro Benimeli Miranda, Álvaro Castillo García
*/
public class AdminFeedController extends UserFeedController{
	
	/**
	 * Constructor of this class.
	 * 
	 * @param panel the panel that is controlled by this class
	 */
	public AdminFeedController(JPanel panel) {
		super(panel);
	}

	public void actionPerformedRegistration(ActionEvent e) {
		panel.setVisible(false);
		JPanel p = new AcceptRegistrationPanel(app.getPendingAcceptanceUsers());
		frame.add(p);
		frame.remove(panel);
		p.setVisible(true);
	}
	
	public void actionPerformedGroup(ActionEvent e) {
		panel.setVisible(false);
		JPanel p = new AcceptGroupPanel(app.getPendingAcceptanceGroups());
		frame.add(p);
		frame.remove(panel);
		p.setVisible(true);
	}
	
	public void actionPerformedProject(ActionEvent e) {
		panel.setVisible(false);
		JPanel p = new AcceptProjectPanel(app.getPendingAcceptanceProjects());
		frame.add(p);
		frame.remove(panel);
		p.setVisible(true);
	}
	
	public void actionPerformedBan(ActionEvent e) {
		panel.setVisible(false);
		JPanel p = new BanUserPanel(app.getRegisteredUsers());
		frame.add(p);
		frame.remove(panel);
		p.setVisible(true);
		
	}
	public void actionPerformedUnban(ActionEvent e) {
		panel.setVisible(false);
		JPanel p = new UnbanUserPanel(app.getBannedUsers());
		frame.add(p);
		frame.remove(panel);
		p.setVisible(true);
	}

}
