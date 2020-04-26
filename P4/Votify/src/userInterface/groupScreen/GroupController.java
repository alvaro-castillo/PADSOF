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
	
	public GroupController(GroupPanel panel, Group gr) {
		this.panel = panel;
		this.gr = gr;
		this.app = Application.getApplication();
		this.frame = AppFrame.getAppFrame();
	}
	
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
	
	public void leaveButtonPressed(ActionEvent e) {
		
		if (gr.getMembers().contains(app.getCurrentUser())) {
			if (gr.deleteUser(app.getCurrentUser())) {
				panel.showJoin();
				panel.disableAffinity();
			}		
		}

		
	}
	
	public void createAffinityReport(ActionEvent e) {
		
		// todo
		
	}
	
	public void selectSubgroup(ListSelectionEvent e, JList<String> subgroups) {
		
		String group = subgroups.getSelectedValue();
		
		Group g = app.searchGroup(group);
		if (g == null) {
			// error
			return;
		}
		
		changeToGroup(g);
		
	}
	
	public void changeToGroup(Group g) {
		
		JPanel p = new GroupPanel(g, app.getCurrentUser());
		
		panel.setVisible(false);
		frame.add(p);
		frame.remove(panel);
		
	}
	
	public void createSubgroup(ActionEvent e) {
		
		JPanel p = new CreateSubgroupScreen(gr);
		
		panel.setVisible(false);
		frame.add(p);
		frame.remove(panel);
		
	}
	
	
}
