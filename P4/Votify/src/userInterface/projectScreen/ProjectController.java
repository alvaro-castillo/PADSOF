package userInterface.projectScreen;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;


import application.Application;
import application.group.Group;
import application.project.Project;

public class ProjectController {

	private ProjectPanel panel;
	private Project proj;
	private Application app;

	public ProjectController(ProjectPanel panel, Project proj) {
		this.panel = panel;
		this.proj = proj;
		this.app = Application.getApplication();
	}
	
	public void voteButtonPressed(ActionEvent e) {
		
		Boolean voteType = panel.getVoteType();
		
		if (voteType == null) {	// No type of vote selected
			return;
		} else if (voteType == true) { // individual vote
			if (!proj.vote(app.getCurrentUser())) {
				JOptionPane.showMessageDialog(panel, "You have already voted!", "Error", JOptionPane.ERROR_MESSAGE);
			} else {
				panel.activatePopularityReportButton();
			}
		} else {
			if (panel.getSelectedGroup() == null) {
				JOptionPane.showMessageDialog(panel, "You haven't selected a group!", "Error", JOptionPane.ERROR_MESSAGE);
			} else {
				Group g = app.searchGroup(panel.getSelectedGroup());
				if (g == null) {
					JOptionPane.showMessageDialog(panel, "Couldn't find that Group in the Application", "Internal Error", JOptionPane.ERROR_MESSAGE);
				} else if (!proj.vote(g)) {
					JOptionPane.showMessageDialog(panel, "That group has already voted!", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
		
	}
	
	public void createPopularityReport(ActionEvent e) {

		if (proj.hasVoted(app.getCurrentUser())) {
			JOptionPane.showMessageDialog(panel, "This project has " + proj.getActualVotes() + " unique votes", "Popularity Report", JOptionPane.INFORMATION_MESSAGE);
		}
		
	}
	
	public void notifButtonPressed(ActionEvent e) {
		
		if (!app.getCurrentUser().equals(proj.getCreator()) && proj.getObservers().contains(app.getCurrentUser())) {
			proj.unregisterObserver(app.getCurrentUser());
			panel.setNotifButton("Get Notifications");
		} else if (!proj.getObservers().contains(app.getCurrentUser())) {
			proj.registerObserver(app.getCurrentUser());
			panel.setNotifButton("Stop Notifications");
		}
		
	}
	
}
