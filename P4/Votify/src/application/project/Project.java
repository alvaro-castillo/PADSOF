package application.project;
import java.io.Serializable;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

import application.group.*;
import application.registeredUser.*;
import application.enums.ProjectStatus;
import application.notification.IObserver;
import application.notification.Notification;
import application.notification.Subject;
import application.vote.GroupVote;
import application.vote.UserVote;
import application.vote.Vote;
import modifiableDates.ModifiableDate;

/**
 * Represents a project inside of the Application
 * 
 * @author Álvaro Castillo García
 * @author Alejandro Benimeli
 * @author Miguel Álvarez Valiente
 *
 */
public abstract class Project extends Subject 
							  implements Serializable, IObserver {
	
	/**
	 * Version for serialize objects
	 */
	private static final long serialVersionUID = 1L;

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
	 * Creator of the project
	 */
	private RegisteredUser creator;
	
	/**
	 * Request id. Will be set when sending it to the external entity
	 */
	private String requestId = null;
	
	/**
	 * Amount granted by the external entity
	 */
	private double grantedAmount = -1;
	
	/**
	 * Project constructor
	 * 
	 * @param title Title of the project
	 * @param description Description of the project
	 * @param amount Amount of money requested in Euros
	 * @param creator Creator of the project
	 */
	public Project(String title, String description, double amount, RegisteredUser creator) {
		this.title = title;
		this.description = description;
		this.amount = amount;
		this.minimumVotes = -1; // Not set until the administrator accepts the project
		this.acceptDate = null;
		this.status = ProjectStatus.WAITING_ACCEPTANCE;
		this.creator = creator;
		
		// we don't use vote() because if the project isn't accepted it doesn't count the vote
		// but we want to always have the creator's vote
		votes.add(new UserVote(creator));
		update(null);
		
		// Adds the creator as an observer
		registerObserver(creator);

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
	 * @param minimumVotes votes needed for the project
	 */
	public void setMinimumVotes(int minimumVotes) {
		this.minimumVotes = minimumVotes;
		if (actualVotes >= minimumVotes) {
			creator.update(new Notification("Your " + this.getClass().getSimpleName() + ": \"" + title + "\" with ID " + id + " has reached the minimun number of votes and is ready to be submited!", ModifiableDate.getModifiableDate()));
		}
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
	 * Returns the creator of the project
	 * @return creator
	 */
	public RegisteredUser getCreator() {
		return creator;
	}
	
	/**
	 * Getter for state
	 * @return state of the project
	 */
	public ProjectStatus getState() {
		return status;
	}
	
	/**
	 * Getter for requestId
	 * @return id of the request
	 */
	public String getRequestId() {
		return requestId;
	}
	
	/**
	 * Setter for requestId
	 * @param requ request id of the project
	 */
	public void setRequestId(String requ) {
		requestId = requ;
	}
	
	/**
	 * Getter for grantedAmount
	 * @return amount granted by the entity
	 */
	public double getGrantedAmount() {
		return grantedAmount;
	}
	
	/**
	 * Setter for grantedAmount
	 * @param am amount granted by the entity
	 */
	public void setGrantedAmount(double am) {
		grantedAmount = am;
	}
	
	/**
	 * Changes the state to AdminAccepted and sets the minimum votes
	 */
	public void adminAcceptProject() {
		status = ProjectStatus.ADMIN_ACCEPTED;
		acceptDate = ModifiableDate.getModifiableDate();
		notifyObservers(new Notification("Project '" + title + "' with ID " + id + " has been accepted by the administrator", ModifiableDate.getModifiableDate()));
	}
	
	/**
	 * Changes the state to AdminRejected
	 */
	public void adminRejectProject() {
		status = ProjectStatus.ADMIN_REJECTED;
		notifyObservers(new Notification("Project '" + title + "' with ID " + id + " has been rejected by the administrator", ModifiableDate.getModifiableDate()));
	}
	
	/**
	 * Changes the state to Pending
	 */
	public void pendingProject() {
		status = ProjectStatus.PENDING;
		notifyObservers(new Notification("Project '" + title + "' with ID " + id + " is waiting to be approved by the government", ModifiableDate.getModifiableDate()));
	}
	
	/**
	 * Changes the state to Expired
	 */
	public void expireProject() {
		status = ProjectStatus.EXPIRED;
		notifyObservers(new Notification("Project '" + title + "' with ID " + id + " has expired", ModifiableDate.getModifiableDate()));
	}
	
	/**
	 * Changes the state to Rejected
	 */
	public void rejectProject() {
		status = ProjectStatus.REJECTED;
		notifyObservers(new Notification("Project '" + title + "' with ID " + id + " has been rejected by the government", ModifiableDate.getModifiableDate()));
	}
	
	/**
	 * Changes the state to Approved
	 */
	public void approveProject() {
		status = ProjectStatus.APPROVED;
		notifyObservers(new Notification("Project '" + title + "' with ID " + id + " has been approved by the government", ModifiableDate.getModifiableDate()));
	}
	
	/**
	 * Adds a UserVote to the project
	 * 
	 * @param user RegisteredUser that votes
	 * @return Boolean indication if the vote was added successfully
	 */
	public boolean vote(RegisteredUser user) {
		
		if (user == null) {
			return false;
		}
		
		// If the user has already voted or if the project hasn't been accepted or has been rejected
		if (hasVoted(user)) {
			return false;
		}
		
		Vote v = new UserVote(user);
		
		votes.add(v);
		
		update(null);
		
		return true;
	}
	
	/**
	 * Adds a GroupVote to the project
	 * 
	 * @param group Group that votes
	 * @return Boolean indication if the vote was added successfully
	 */
	public boolean vote(Group group) {
		
		if (group == null) {
			return false;
		}
		
		// If the group has already voted
		if (hasVoted(group)) {
			return false;
		}
		
		Vote v = new GroupVote(group);
		
		votes.add(v);
		
		// Updates the vote count and checks if the minimum is reached
		update(null);
		// The project now observes the group to see if anyone joins or leaves to recalculate the votes
		group.registerObserver(this); 
		
		return true;
	}
	
	/**
	 * Returns the amount of unique votes the project has
	 * 
	 * @return number of votes
	 */
	private int countVotes() {

		Set<String> voters = new HashSet<>();
		
		for (Vote v: votes) {
			voters.addAll(v.getVoters());
		}

		return voters.size();
	}
	
	/**
	 * Updates the vote count of the project whenever its called
	 * @param n Notification (Its not needed so pass null instead)
	 * @return Always returns true
	 */
	public boolean update(Notification n) {
		
		actualVotes = countVotes();
		
		if ((actualVotes >= minimumVotes) && (minimumVotes != -1)) {
			creator.update(new Notification("Your " + this.getClass().getSimpleName() + ": \"" + title + "\" with ID " + id + " has reached the minimun number of votes and is ready to be submited!", ModifiableDate.getModifiableDate()));
		}
		
		return true;
	}
	
	/**
	 * Determines if a registered user has voted for the project
	 * 
	 * @param user
	 * @return boolean telling us whether the registered user has voted for the project or not
	 */
	public boolean hasVoted(RegisteredUser user) {
		
		for (Vote v: votes) {
			
			if (v instanceof UserVote) {
				if ( ((UserVote)v).getUser().equals(user) ) {
					return true;
				}
			}
			
		}
		
		return false;
	}
	
	/**
	 * Determines if a group has voted for the project
	 * 
	 * @param group
	 * @return boolean telling us whether the group has voted for the project or not
	 */
	public boolean hasVoted(Group group) {
		
		for (Vote v: votes) {
			
			if (v instanceof GroupVote) {
				if ( ((GroupVote)v).getGroup().equals(group) ) {
					return true;
				}
			}
			
		}
		
		return false;
	}
	
	/**
	 * Determines if a group has voted for the project in some group
	 * 
	 * @param group
	 * @return boolean telling us whether the user has voted for the project or not
	 */
	public boolean hasVotedInGroup(RegisteredUser u) {
		
		for (Vote v: votes) {
			
			if (v instanceof GroupVote) {
				if ( ((GroupVote)v).getVoters().contains(u.getUsername())==true) {
					return true;
				}
			}
			
		}
		
		return false;
	}
	
	/**
	 * Equals method override
	 * @param obj The object to be compared
	 * @return true if both are equal, false if not
	 */
	@Override
	public boolean equals(Object obj) {
		if(this==obj) {
			return true;
		}
		if(obj==null) {
			return false;
		}
		if (this.getClass() != obj.getClass()) {
			return false;
		}
		Project that = (Project)obj;
		if (this.id == that.id) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * toString method override
	 */
	@Override
	public String toString() {
		
		String s = "    "+this.getClass().getSimpleName() + "\n     Project id: " + String.format("%8d", this.id) +   "   Project Title: " 
					+ String.format("%25s", this.title) + "   Creator: " + String.format("%8s", this.creator.getUsername()) + "\n"
				+ "     Description: \n\t";
		
		int lineLength = 100, curr = 0;
		for (int i=0; i<description.length(); ++i) {
			if (curr <= lineLength) {
				s += description.charAt(i);
				++curr;
			} else {
				s += "\n\t";
				curr = 0;
			}
		}
		
		s += "\n     Status: " + String.format("%18s", this.status) + "   Accept Date: ";
		
		if (this.acceptDate == null) {
			s += "Not accepted yet";
		} else {
			s += this.acceptDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		}
		
		s += "\n     Amount Requested: " + String.format("%8.2f", this.amount) + "€   Minimum Votes Needed: ";
		
		if (this.minimumVotes == -1) {
			s += String.format("%8s", "Not set");
		} else {
			s += String.format("%8d", this.minimumVotes);
		}
		
		s += "   Current Votes: " + String.format("%8d", this.actualVotes) + "\n"
				+ "     Votes:\n";
		
		for (Vote v: this.votes) {
			s += "\t" + v + "\n";
		}
		
		
		return s;
	}

}
