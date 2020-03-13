package ads;

import java.util.*;
import java.lang.Boolean;

/**
 * Represents a group of the Application
 * 
 * @author Miguel Álvarez Valiente
 * @author Alejandro Benimeli Miranda
 * @author Álvaro Castillo García 
 *
 */
public class Group {
	
	/*
	 * Name of the group (has to be unique), set by the representative of
	 * the group and can not be modified 
	 */
	private String name;
	
	/*
	 * Current status of the group (can be WaitingAcceptance, AdminAccepted,
	 * AdminRejected, Pending, Expired, Rejected or Approved)
	 */
	private Status status;
	
	/*
	 * Reference to the direct parent group of a subgroup (in case it has no 
	 * parent group, it will be null)
	 */
	private Group parent;
	
	/*
	 * Reference to the creator of the group, known as the representative
	 */
	private RegisteredUser representative;
	
	/*
	 * List of users that are members of a group. When the group is created
	 * the only member of the list is the representative
	 */
	private List<RegisteredUser> members;
	
	/*
	 * List of direct subgroups. It remains empty until the representative of 
	 * a group decides to create one.
	 */
	private List<Group> subgroups;
	
	/*
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
	
	/*
	 * Creates affinity report between two groups
	 * 
	 * @param group Group that you wish to execute the affinity report on
	 * @return double with the result of applying the affinity formula
	 */
	public double createAffinityReport(Group g) {
		int a=0, b=0 ;
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
		return (a+b)/(this.createdProjects.size()+g.createdProjects.size());
	}
	
	/*
	 * Creates a subgroup 
	 * 
	 * @param name Name of the new subgroup
	 * @param representative RegisteredUser that is going to be the 
	 * representative of the new subgroup
	 * @return reference to the new group
	 */
	public Group createSubgroup(String name, RegisteredUser representative) {
		if (this.representative.equals(representative)) {
			Group g = new Group(name, representative, this);
			this.subgroups.add(g);
			return g;
		}
		
		return null;
	}
	
	/*
	 * Changes the status of a group to Accepted
	 */
	public void acceptGroup() {
		status = Status.ACCEPTED;
	}
	
	/*
	 * Changes the status of a group to Rejected
	 */
	public void rejecttGroup() {
		status = Status.REJECTED;
	}
	
	/*
	 * Adds a user to a group
	 * 
	 * @param u RegisteredUser that is going to be added to the group
	 * @return boolean which returns true if the user is added without problems
	 */
	public boolean addUser(RegisteredUser u) { 
		if(members.contains(u) || userInParent(u) || userInChild(u)) { return false; }
		
		members.add(u);
		return true;
	}
	
	/*
	 * Delets a user from a group
	 * 
	 * @param u RegisteredUser that is going to be deleted from the group
	 * @return boolean which returns true if the user is deleted without problems
	 */
	public boolean deleteUser(RegisteredUser u) {
		return members.remove(u);
	}
	
	/*
	 * Getter of the members list
	 * 
	 *  @return List of RegisteredUsers
	 */
	public List<RegisteredUser> getMembers(){
		return this.members;
	}
	
	/*
	 * Checks if a user is in a parent group
	 * 
	 * @param u RegisteredUser which will be looked for in the parent groups
	 * @return boolean that will return true if the user is found
	 */
	private boolean userInParent(RegisteredUser u) {
		if (parent == null) { return false; }
		
		if (members.contains(u)) { return true; }
		
		return parent.userInParent(u);
	}
	
	/*
	 * Checks if a user is in a children group
	 * 
	 * @param u RegisteredUser which will be looked for in the children groups
	 * @return boolean that will return true if the user is found
	 */
	private boolean userInChild(RegisteredUser u) {
		boolean b = false;
		
		if (subgroups.isEmpty()) { return false; }
		
		if (members.contains(u)) { return true; }
		
		for (Group child : subgroups) {
			 b = b || child.userInChild(u);
		}
		return b;
	}
	
	/*
	 * Equals method of two groups
	 * 
	 * @param g Group that will be compared
	 * @return boolean that returns true if the groups are equal
	 */
	@Override
	public boolean equals(Object g) {
		if (g == null) { return false; }
		
		if (this == g) { return true; }
		
		if (this.getClass() != g.getClass()) { return false; }
		
		Group gr = (Group) g;
		
		return this.name.contentEquals(gr.name);
	}
}







