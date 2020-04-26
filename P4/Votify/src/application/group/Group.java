package application.group;

import java.util.*;

import application.enums.*;
import application.notification.*;
import application.project.*;
import application.registeredUser.*;
import java.io.Serializable;

/**
 * Represents a group of the Application
 * 
 * @author Miguel Ã�lvarez Valiente
 * @author Alejandro Benimeli Miranda
 * @author Ã�lvaro Castillo GarcÃ­a 
 *
 */
public class Group extends Subject
				   implements Serializable{
	
	/**
	 * Version for serializing objects
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Name of the group (has to be unique), set by the representative of
	 * the group and can not be modified 
	 */
	private String name;
	
	/**
	 * Current status of the group (can be WaitingAcceptance, AdminAccepted,
	 * AdminRejected, Pending, Expired, Rejected or Approved)
	 */
	private Status status;
	
	/**
	 * Reference to the direct parent group of a subgroup (in case it has no 
	 * parent group, it will be null)
	 */
	private Group parent;
	
	/**
	 * Reference to the creator of the group, known as the representative
	 */
	private RegisteredUser representative;
	
	/**
	 * List of users that are members of a group. When the group is created
	 * the only member of the list is the representative
	 */
	private List<RegisteredUser> members;
	
	/**
	 * List of direct subgroups. It remains empty until the representative of 
	 * a group decides to create one.
	 */
	private List<Group> subgroups;
	
	/**
	 * List of the projects created by the group (this means that the 
	 * representative creates a project as a group, so the project receives
	 * a vote for each member of the group)
	 */
	private List<Project> createdProjects;

	/**
	 * Group constructor
	 * 
	 * @param name String with the name of the Project
	 * @param representative RegisteredUser that is the creator and first member 
	 * of the group
	 * @param parent Group that is the parent of the group that is being created
	 * (if there is one)
	 * 
	 */
	public Group(String name, RegisteredUser representative, Group parent) {
		this.name = name;
		this.parent = parent;
		this.representative = representative;
		this.members = new ArrayList<RegisteredUser>();
		members.add(representative);
		this.subgroups = new ArrayList<Group>();		
		this.createdProjects = new ArrayList<Project>();
		this.status = Status.WAITING;
		// Subgroups share observers with their parents
		if (parent != null) {
			this.observers = parent.observers;
		}
		representative.addGroup(this);
	}
	
	/**
	 * Group constructor (in case there is not parent group)
	 * 
	 * @param name String with the name of the Project
	 * @param representative RegisteredUser that is the creator and first member
	 * of the group
	 * 
	 */
	public Group(String name, RegisteredUser representative) {
		this(name, representative, null);
	}
	
	/**
	 * Creates affinity report between two groups
	 * 
	 * @param g Group that you wish to execute the affinity report on
	 * @return double with the result of applying the affinity formula
	 */
	public double createAffinityReport(Group g) {
		int a=0, b=0 ;
		if (g == null) { 
			return -1;
		} 
		for (Project p : this.createdProjects) {
			if (p.hasVoted(g)) {
				++a;
			}
		}
		for (Project p : g.createdProjects) {
			if (p.hasVoted(this)) {
				++b;
			}
		}
		if (this.createdProjects.size() == 0 && g.createdProjects.size() == 0) { 
			return 0; 
		}
		return (a+b)/(this.createdProjects.size()+g.createdProjects.size());
	}
	
	/**
	 * Creates a subgroup 
	 * 
	 * @param name Name of the new subgroup
	 * @param representative RegisteredUser that is going to be the 
	 * representative of the new subgroup
	 * @return reference to the new group
	 */
	public Group createSubgroup(String name, RegisteredUser representative) {
		if (name == null || this.status!=Status.ACCEPTED) { 
			return null;
		}
		if (this.representative.equals(representative)) {
			Group g = new Group(name, representative, this);
			this.subgroups.add(g);
			return g;
		}
		
		return null;
	}
	
	/**
	 * Changes the status of a group to Accepted
	 */
	public void acceptGroup() {
		status = Status.ACCEPTED;
	}
	
	/**
	 * Changes the status of a group to Rejected
	 */
	public void rejectGroup() {
		status = Status.REJECTED;
	}
	
	/**
	 * Getter of the status
	 * 
	 *  @return status of the group
	 */
	public Status getStatus() {
		return this.status;
	}
	
	/**
	 * Adds a user to a group
	 * 
	 * @param u RegisteredUser that is going to be added to the group
	 * @return boolean which returns true if the user is added without problems
	 */
	public boolean addUser(RegisteredUser u) {
		if (u == null) { 
			return false; 
		}
		if ((members.contains(u) || userInParent(u) || userInChild(u) || this.status!=Status.ACCEPTED) && !u.equals(representative)) { 
			return false;
		}
		
		members.add(u);
		notifyObservers(null);
		return true;
	}
	
	/**
	 * Deletes a user from a group
	 * 
	 * @param u RegisteredUser that is going to be deleted from the group
	 * @return boolean which returns true if the user is deleted without problems
	 */
	public boolean deleteUser(RegisteredUser u) {
		if (u == null) {
			return false;
		}
		notifyObservers(null);
		return members.remove(u);
	}
	
	/**
	 * Adds a project to the created projects list
	 * 
	 * @param p Project that is going to be added
	 * @return boolean that returns true if it was added correctly
	 */
	public boolean addProject(Project p) {
		if (p == null) { 
			return false; 
		}
		if (createdProjects.contains(p)) {
			return false;
		}
		createdProjects.add(p);
		return true;
	}
	
	/**
	 * Getter of the members list
	 * 
	 *  @return List of RegisteredUsers
	 */
	public List<RegisteredUser> getMembers(){
		return this.members;
	}
	
	/**
	 * Function that checks if a user is a member of a group 
	 * 
	 * @param u the registered user we want to check
	 * @return boolean
	 * */	
	public boolean isUserInGroup(RegisteredUser u) {
		if(members.contains(u)) {
			return true;
		}
		return false;
	}
	
	/**
	 * Returns all the members of the group and subgroups (and subgroups of the subgroups, etc)
	 * @return list of members
	 */
	public List<RegisteredUser> getAllSubgroupsMembers() {
		List<RegisteredUser> membs = new ArrayList<>();
		membs.addAll(members);
		for (Group g: subgroups) {
			membs.addAll(g.getAllSubgroupsMembers());
		}
		return membs;
	}
	
	/**
	 * Checks if a user is in a parent group
	 * 
	 * @param u RegisteredUser which will be looked for in the parent groups
	 * @return boolean that will return true if the user is found
	 */
	public boolean userInParent(RegisteredUser u) {
		if (u == null) { 
			return false; 
		}
		
		if (parent == null) {
			return false;
		}
		
		if (parent.members.contains(u)) { return true; }
		
		return parent.userInParent(u);
	}
	
	/**
	 * Checks if a user is in a children group
	 * 
	 * @param u RegisteredUser which will be looked for in the children groups
	 * @return boolean that will return true if the user is found
	 */
	public boolean userInChild(RegisteredUser u) {
		for (Group g: this.subgroups) {
			if (g.members.contains(u)) {
				return true;
			} else if (g.userInChild(u)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Equals method of two groups
	 * 
	 * @param g Group that will be compared
	 * @return boolean that returns true if the groups are equal
	 */
	@Override
	public boolean equals(Object g) {
		if (g == null) { 
			return false;
		}
		
		if (this == g) { 
			return true;
		}
		
		if (this.getClass() != g.getClass()) { 
			return false;
		}
		
		Group gr = (Group) g;
		
		return this.name.contentEquals(gr.name);
	}
	
	/**
	 * Group name getter.
	 * 
	 * @return name
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Group name getter.
	 * 
	 * @return name
	 */
	public List<Group> getSubgroups() {
		return this.subgroups;
	}
	
	/**
	 * Group representative getter.
	 * 
	 * @return name
	 */
	public RegisteredUser getRepresentative() {
		return this.representative;
	}
	
	/**
	 * Parent group getter
	 * 
	 * @return parent reference, null if has no parent
	 */
	public Group getParent() {
		return this.parent;
	}
	
	/**
	 * Group projects getter.
	 * 
	 * @return name
	 */
	public List<Project> getCreatedProjects() {
		return this.createdProjects;
	}

	/**
	 * Override of the toString method
	 * 
	 * @return String with the information of the group
	 */
	@Override
	public String toString() {
		String str= "\n    Group name: " + String.format("%10s", this.name)
		+ "\n    Representative: " + String.format("%8s", this.representative.getUsername()) + "\n    Status: " + String.format("%10s", this.status) 
		+ "\n    Parent Group: ";
		
		if (this.parent == null) {
			str = str.concat(" Doesn't have");
		} else {
			str = str.concat(this.parent.getName());
		}
		str = str.concat("\n    Members: \n");
		for (RegisteredUser u : members) {
			str = str.concat("	 - " + u.getUsername() + "\n");
		}
		str = str.concat("    Subgroups: \n");
		for (Group g : subgroups) {
			str = str.concat("	 - " + g.getName() + "\n");
		}
		str = str.concat("    Created Projects: \n");
		for (Project p : createdProjects) {
			str = str.concat("	- " + p.getTitle() + "\n");
		}
		return str;
	} 

}







