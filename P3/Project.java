package app;
import java.time.*;
import java.util.ArrayList;
import java.util.List;

import ads.assignment3.Appliance;

/**
 * Represents a project inside of the Application
 * 
 * @author Alejandro Benimeli
 *
 */
public abstract class Project {
	
	/**
	 * Title of the Project
	 */
	private String title;
	
	/**
	 * Description of the Project
	 */
	private String description;
	
	/**
	 * Amount of money requested for the project in Euros
	 */
	private double amount;
	
	/**
	 * The minimum number of votes needed to send the project to the
	 * government for approval
	 */
	private int minimumVotes = -1;
	
	/**
	 * Current number of votes
	 */
	private int actualVotes = 0;
	
	/**
	 * Date when the project was approved by the administrator.
	 */
	private LocalDate acceptDate; // change to modifiable date?
	
	/**
	 * Unique ID of the project
	 */
	private long id;
	
	/**
	 * Status of the project
	 */
	private ProjectStatus status;
	
	/**
	 * List of votes that the project has received
	 */
	List<Vote> votes = new ArrayList<Vote>();
	
	/**
	 * Used to assign unique id's to projects. When a new project is created, it's id
	 * is set to lastId + 1 
	 */
	private static long lastId = -1;

	/**
	 * Project constructor
	 * 
	 * @param title Title of the project
	 * @param description Description of the project
	 * @param amount Amount of money rquested in Euros
	 */
	public Project(String title, String description, double amount) {
		this.title = title;
		this.description = description;
		this.amount = amount;
		this.minimumVotes = -1; // Not set until the administrator accepts the project
		this.acceptDate = acceptDate; // Mirar lo de las modifiable dates
		this.status = WaitingAcceptance;

		// Assign a unique id to the project
		this.id = lastId + 1;
		Project.lastId ++;
	}
	
	/**
	 * Getter for title
	 * @return title of the project
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * Getter for description
	 * @return description of the project
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * Getter for amount
	 * @return amount of money requested in Euros
	 */
	public double getAmount() {
		return amount;
	}
	
	/**
	 * Getter for minimumVotes
	 * @return minimum votes of a project
	 */
	public int getMinimumVotes() {
		return minimumVotes;
	}
	
	/**
	 * Setter for minimumVotes
	 * @param minimumVotes 
	 */
	public void setMinimumVotes(int minimumVotes) {
		this.minimumVotes = minimumVotes;
	}
	
	public int getActualVotes() {
		return actualVotes;
	}
	
	public LocalDate getAcceptDate() {
		return acceptDate;
	}
	
	public long getId() {
		return id;
	}
	
	public ProjectStatus getState() {
		return state;
	}
	
	public void adminAcceptProject() {
		state = ProjectStatus.AdminAccepted;
	}
	
	public void adminRejectProject() {
		state = ProjectStatus.AdminRejected;
	}
	
	public void pendingProject() {
		state = ProjectStatus.Pending;
	}
	
	public void expireProject() {
		state = ProjectStatus.Expired;
	}
	
	public void rejectProject() {
		state = ProjectStatus.Rejected;
	}
	
	public void approveProject() {
		state = ProjectStatus.Approved;
	}
	
	public boolean vote(Vote v) {
		// TODO Implementarla. Antes hay que hacer la lista de notificaciones
	}

}
