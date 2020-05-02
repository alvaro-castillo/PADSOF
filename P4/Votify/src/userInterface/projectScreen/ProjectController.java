package userInterface.projectScreen;

import java.awt.event.ActionEvent;
import java.io.IOException;

import javax.swing.JOptionPane;


import application.Application;
import application.enums.ProjectStatus;
import application.group.Group;
import application.project.Project;
import es.uam.eps.sadp.grants.InvalidRequestException;
import request.GovernmentGateway;

/**
 * This is the controller of the Project JPanel
 * 
 * @author �lvaro Castillo Garc�a
 * @author Alejandro Benimeli
 * @author Miguel �lvarez Valiente
 *
 */
public class ProjectController {

	private ProjectPanel panel;
	private Project proj;
	private Application app;

	/**
	 * Constructor
	 * 
	 * @param panel Panel the controller is controlling
	 * @param proj The project of the Project JPanel
	 */
	public ProjectController(ProjectPanel panel, Project proj) {
		this.panel = panel;
		this.proj = proj;
		this.app = Application.getApplication();
	}
	
	/**
	 * Controls the vote button behavior
	 * @param e event
	 */
	public void voteButtonPressed(ActionEvent e) {
		
		Boolean voteType = panel.getVoteType();
		
		
		if (voteType == null) {	// No type of vote selected
			return;
		}
		
		if (proj.getState() == ProjectStatus.WAITING_ACCEPTANCE) {
			JOptionPane.showMessageDialog(panel, "Can't vote until the project is approved by the administrator!", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		if (proj.getState() == ProjectStatus.ADMIN_REJECTED) {
			JOptionPane.showMessageDialog(panel, "The project has been rejected, you can't vote!", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		if (proj.getState() == ProjectStatus.EXPIRED) {
			JOptionPane.showMessageDialog(panel, "The project has expired, you can't vote!", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		if (voteType == true) { // individual vote
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
	
	/**
	 * Controls the popularity report button behavior
	 * @param e event
	 */
	public void createPopularityReport(ActionEvent e) {
		
		if (proj.getState() == ProjectStatus.ADMIN_REJECTED) {
			JOptionPane.showMessageDialog(panel, "The project has been rejected, you can't view the affinity report!", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		if (proj.getState() == ProjectStatus.EXPIRED) {
			JOptionPane.showMessageDialog(panel, "The project has expired, you can't view the affinity report!", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		if (proj.hasVoted(app.getCurrentUser())) {
			JOptionPane.showMessageDialog(panel, "This project has " + proj.getActualVotes() + " unique votes", "Popularity Report", JOptionPane.INFORMATION_MESSAGE);
		}
		
	}
	
	/**
	 * Controls the notification buttons button behavior
	 * @param e event
	 */
	public void notifButtonPressed(ActionEvent e) {
		
		if (!app.getCurrentUser().equals(proj.getCreator()) && proj.getObservers().contains(app.getCurrentUser())) {
			proj.unregisterObserver(app.getCurrentUser());
			panel.setNotifButton("Get Notifications");
		} else if (!proj.getObservers().contains(app.getCurrentUser())) {
			proj.registerObserver(app.getCurrentUser());
			panel.setNotifButton("Stop Notifications");
		}
		
	}
	
	/**
	 * Sends the project to the external entity
	 * @param e event
	 */
	public void sendButtonPressed(ActionEvent e) {
		
		GovernmentGateway gtw = GovernmentGateway.getInstance();
		
		try {
			
			gtw.sendProject(proj);
			
			panel.disableSendButton();
			
		} catch (IOException e2) {
			JOptionPane.showMessageDialog(panel, "Couldn't communicate with the gateway. Please try again!", "Error", JOptionPane.ERROR_MESSAGE);
		} catch (InvalidRequestException e2) {
			JOptionPane.showMessageDialog(panel, "Invalid request!", "Error", JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
}
