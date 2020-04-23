package userInterface.administrator.acceptance;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import application.Application;
import application.project.Project;
import application.registeredUser.RegisteredUser;
import userInterface.AppFrame;
import userInterface.administrator.feed.AdminFeedPanel;

public class AcceptProjectController extends KeyAdapter implements ActionListener {

	private AcceptProjectPanel panel;
	private Application app;
	private JFrame frame;
	
	/**
	 * Constructor of this class.
	 * 
	 * @param panel the panel that is controlled by this class
	 */
	public AcceptProjectController(AcceptProjectPanel panel) {
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
    		
    		String projectString = panel.getCombo().getSelectedObject();
    		if(projectString==null) {
    			return;
    		}
    		
    		String[] partsOfStr = projectString.split(" ");
    		
    		int id = 0;
    		
    		try {
    			id = Integer.parseInt(partsOfStr[partsOfStr.length-1]);
    		} catch (Exception exc) {
    			//error
    			return;
    		}
    		
    		Project project = app.searchProject(id);
    		if (project == null) {
    			// error
    			return;
    		}
    		
    		String votesString = panel.getTextPanel().getText();
    		int votes; 
    		try {
    			votes = Integer.parseInt(votesString);
    			System.out.println(votes);
    		} catch (Exception exc) {
    			//error
    			return;
    		}
    		String extra;
    		if(b.getText().equals("Accept")) {
    			project.setMinimumVotes(votes);
    			project.adminAcceptProject();
    			extra = "accepted.";
    		}else if(b.getText().equals("Deny")) {
    			project.adminRejectProject();
    			extra = "denied.";
    		}else {
    			return;
    		}
    		
    		JOptionPane.showMessageDialog(panel, "Project " + project.getTitle() + " is now "+ extra , "Success", JOptionPane.INFORMATION_MESSAGE);
    		
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
		String votesString = aux.getText();
		
		String projectString = panel.getCombo().getSelectedObject();
		if(projectString==null) {
			return;
		}
		String[] partsOfStr = projectString.split(" ");
		
		int id = 0;
		
		try {
			id = Integer.parseInt(partsOfStr[partsOfStr.length-1]);
		} catch (Exception exc) {
			//error
			return;
		}
		
		Project project = app.searchProject(id);
		if (project == null) {
			// error
			return;
		}
		int votes; 
		try {
			votes = Integer.parseInt(votesString);
			System.out.println(votes);
		} catch (Exception exc) {
			//error
			return;
		}
		
		project.setMinimumVotes(votes);
		project.adminAcceptProject();
		
		JOptionPane.showMessageDialog(panel, "Project " + project.getTitle() + " is now accepted.", "Success", JOptionPane.INFORMATION_MESSAGE);
		
		panel.setVisible(false);
		RegisteredUser admin = app.getAdmin();
		JPanel p =  new AdminFeedPanel(admin.getUsername(),admin.getNotificationsMessages(), app.getRegisteredUserGroups(admin),  app.getRegisteredUserVotes(admin));
		frame.add(p);
		frame.remove(panel);
		p.setVisible(true);
	}
}