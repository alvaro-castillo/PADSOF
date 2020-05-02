package userInterface.groupScreen;

import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;

import application.Application;
import application.enums.Status;
import application.group.Group;
import userInterface.AppFrame;
import userInterface.createGroupScreen.CreateSubgroupScreen;

public class GroupController {
	
	private GroupPanel panel;
	private Group gr;
	private Application app;
	private JFrame frame;
	
	/**
	 * Controller
	 * 
	 * @param panel panel it controls
	 * @param gr group you controll
	 */
	public GroupController(GroupPanel panel, Group gr) {
		this.panel = panel;
		this.gr = gr;
		this.app = Application.getApplication();
		this.frame = AppFrame.getAppFrame();
	}
	
	/**
	 * Controls the behavior of the join button
	 * @param e event
	 */
	public void joinButtonPressed(ActionEvent e) {

		if (!gr.getMembers().contains(app.getCurrentUser())) {
			if (gr.addUser(app.getCurrentUser())) {
				panel.showLeave();
				panel.enableAffinity();
			} else if (gr.getStatus() != Status.ACCEPTED) {
				JOptionPane.showMessageDialog(panel, "The group needs to be accepted by the admin first!", "Error", JOptionPane.ERROR_MESSAGE);
			} else if (gr.userInChild(app.getCurrentUser()) || gr.userInParent(app.getCurrentUser())) {
				JOptionPane.showMessageDialog(panel, "You are already in a child or parent group!", "Error", JOptionPane.ERROR_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(panel, "Can't join the group", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}

	}
	
	/**
	 * Controls the behavior of the leave button
	 * @param e event
	 */
	public void leaveButtonPressed(ActionEvent e) {
		
		if (gr.getMembers().contains(app.getCurrentUser())) {
			if (gr.deleteUser(app.getCurrentUser())) {
				panel.showJoin();
				panel.disableAffinity();
			}		
		}

		
	}
	
	/**
	 * Creates a pop up with the affinity report
	 * @param e event
	 */
	public void createAffinityReport(ActionEvent e) {
		
		Group g2 = app.searchGroup(panel.getSelectedGroup());
		if (g2 == null) {
			return;
		}
		
		JOptionPane.showMessageDialog(panel, "This 2 groups have a " + gr.createAffinityReport(g2) + " affinity!", "Affinity Report", JOptionPane.INFORMATION_MESSAGE);
		
	}
	
	/**
	 * Changes the screen to the subgroup selected
	 * @param e event
	 * @param subgroups list of subgroups
	 */
	public void selectSubgroup(ListSelectionEvent e, JList<String> subgroups) {
		
		String group = subgroups.getSelectedValue();
		
		Group g = app.searchGroup(group);
		if (g == null) {
			// error
			return;
		}
		
		changeToGroup(g);
		
	}
	
	/**
	 * Changes the screen to the group passed
	 * @param g group you want to show
	 */
	public void changeToGroup(Group g) {
		
		JPanel p = new GroupPanel(g, app.getCurrentUser());
		
		panel.setVisible(false);
		frame.add(p);
		frame.remove(panel);
		
	}
	
	/**
	 * Changes the screen to the create subgroup screen
	 * @param e event
	 */
	public void createSubgroup(ActionEvent e) {
		
		JPanel p = new CreateSubgroupScreen(gr);
		
		panel.setVisible(false);
		frame.add(p);
		frame.remove(panel);
		
	}
	
}
