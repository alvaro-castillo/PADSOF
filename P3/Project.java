package ads;
import java.time.*;
import java.util.*;

/**
 * Represents a project inside of the Application
 * 
 * @author Álvaro Castillo García
 * @author Alejandro Benimeli
 * @author Miguel Álvarez Valiente
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
	 * Is -1 until the admin sets the minimum
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
	 * @param amount Amount of money requested in Euros
	 */
	public Project(String title, String description, double amount) {
		this.title = title;
		this.description = description;
		this.amount = amount;
		this.minimumVotes = -1; // Not set until the administrator accepts the project
		this.acceptDate = acceptDate;
		this.status = WAITING_ACCEPTANCE;

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
	 * @param minimum votes needed for the project
	 */
	public void setMinimumVotes(int minimumVotes) {
		this.minimumVotes = minimumVotes;
	}
	
	/**
	 * Getter for actualVotes
	 * @return actual votes of the project
	 */
	public int getActualVotes() {
		return actualVotes;
	}
	
	/**
	 * Getter for acceptDate
	 * @return date the project was accepted
	 */
	public LocalDate getAcceptDate() {
		return acceptDate;
	}
	
	/**
	 * Getter for id
	 * @return id of the project
	 */
	public long getId() {
		return id;
	}
	
	/**
	 * Getter for state
	 * @return state of the project
	 */
	public ProjectStatus getState() {
		return status;
	}
	
	/**
	 * Changes the state to AdminAccepted
	 */
	public void adminAcceptProject() {
		status = ProjectStatus.ADMIN_ACCEPTED;
	}
	
	/**
	 * Changes the state to AdminRejected
	 */
	public void adminRejectProject() {
		status = ProjectStatus.ADMIN_REJECTED;
	}
	
	/**
	 * Changes the state to Pending
	 */
	public void pendingProject() {
		status = ProjectStatus.PENDING;
	}
	
	/**
	 * Changes the state to Expired
	 */
	public void expireProject() {
		status = ProjectStatus.EXPIRED;
	}
	
	/**
	 * Changes the state to Rejected
	 */
	public void rejectProject() {
		status = ProjectStatus.REJECTED;
	}
	
	/**
	 * Changes the state to Approved
	 */
	public void approveProject() {
		status = ProjectStatus.APPROVED;
	}
	
	/**
	 * Adds a vote to the project
	 * 
	 * @param v Vote that will be added
	 * @return Boolean indication if the vote was added succesfuly
	 */
	public boolean vote(Vote v) {
		// TODO Implementarla. Antes hay que hacer la lista de notificaciones
		
		if (v == null) {
			return false;
		}
		
		// TODO chequear que no hayan votado antes
		
		votes.add(v);
		
		return true;
	}
	
	private int countVotes() {
		
		Set<String> voters = new HashSet<>();
		
		for (Vote v: votes) {
			voters.addAll(v.getVoters());
		}

		if (actualVotes >= minimumVotes) {
			// TODO Notificar al creador del project
			//update(Nofication("Your " + this.getClass().getName() + " project: " + title + " with ID " + id + " has reached the minimun number of votes and is ready to be submited!"));
		}
		
		return voters.size();
	}

}
