package ads;

import java.util.*;
import java.io.Serializable;
/**
* These are the functions and variables that define the RegisteredUser objects, the RegisteredUser class.
*
* @author Miguel Álvarez Valiente, Alejandro Benimeli Miranda, Álvaro Castillo García
*/
public class RegisteredUser implements Serializable{
	private String id;
	private String username;
	private String password;
	private boolean ban;
	private boolean notifications;
	private Status status;
	private List<Group> createdGroups;
	private List<Project> createdProjects;
	private List<Notification> notification;
	private static final long serialVersionUID = 1L;
	
	/**
	    * Constructor of this class.
	    *
	    * @param id of the user.
	    * @param username of the user.
	    * @param password of the user.
	    */	
	public RegisteredUser(String id, String username, String password) {
		this.id = id;
		this.ban = false;
		this.username = username;
		this.password = password;
		this.notifications = false;
		this.status = Status.WAITING;
		this.createdGroups = new ArrayList<Group>();
		this.createdProjects = new ArrayList<Project>();
		this.notification= new ArrayList<Notification>();
		
	}

	/**
	 * User status getter.
	 * @return status
	 */
	public Status getStatus() {
		return status;
	}
	/**
	 * Function that accepts the registration of a user.
	 */
	public void acceptRegistration() {
		this.status=Status.ACCEPTED;
	}
	/**
	 * Function that rejects the registration of a user.
	 */
	public void rejectRegistration() {
		this.status=Status.REJECTED;
	}
	/**
	 * User id getter.
	 * @return id
	 */
	public String getId() {
		return id;
	}
	/**
	 * User id setter.
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * User username getter.
	 * @return username
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * User username setter.
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * User status getter.
	 * @return status
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * User password setter.
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * User ban getter.
	 * @return ban boolean indicating if the user is banned or not
	 */
	public boolean isBan() {
		return ban;
	}
	/**
	 * Function that bans a user.
	 */
	public void banUser() {
		this.ban = true;
	}
	/**
	 * Function that unbans a user.
	 */
	public void unbanUser() {
		this.ban = false;
	}
	/**
	 * User boolean notifications getter.
	 * @return notifications boolean indicating if the user has notifications or not
	 */
	public boolean isNotifications() {
		return notifications;
	}
	/**
	 * User boolean notifications setter.
	 */
	public void setNotifications(boolean notifications) {
		this.notifications = notifications;
	}
	
	/**
	 * Function that adds a project to the user created projects list.
	 * @param p project that the user has created.
	 * @return boolean
	 */
	public boolean addProject(Project p) {
		return createdProjects.add(p);
	}
	/**
	 * Function that adds a group to the user created groups list.
	 * @param g group that the user has created.
	 * @return boolean
	 */
	public boolean addGroup(Group g) {
		return createdGroups.add(g);
	}
	/**
	 * Function that removes a group from the user created groups list.
	 * @param g group that the user wants to leave.
	 * @return boolean
	 */
	public boolean leaveCreatedGroup(Group g) {
		return createdGroups.remove(g);
	}
	/**
	 * Function that adds a notification to the notification list.
	 * @return boolean.
	 */
	public boolean update(Notification n) {
		if(this.notifications == false) {
			this.notifications = true;
		}
		return notification.add(n);
	}
	/**
	 * User notifications getter.
	 * @return notification a list with all the notifications that the user has.
	 */
	public List<Notification> getNotifications() {
		return this.notification;
	}
	
	/**
	 * This method overrides the default equal method among Registered Users.
	 * @param obj. The object to be compared.
	 * @return true if both users are equal.
	 */
	@Override
	public boolean equals(Object obj) {
		if(this==obj) {
			return true;
		}
		if(obj==null) {
			return false;
		}
		RegisteredUser that = (RegisteredUser)obj;
		if(this.username.contains(that.getUsername()) || this.id.contains(that.getId())) {
			return true;
		}else {
			return false;
		}
	}

	/**
	 * This method overrides the default toString method.
	 * @return s string with the information of the object.
	 */
	@Override
	public String toString() {
		String s= "\n  User id: " + String.format(Locale.ROOT, "%10s", this.id) + "   Username: "
	              + String.format(Locale.ROOT, "%8s", this.username) +
				"   Password: " + String.format(Locale.ROOT, "%8s", this.password) +
				"   User status: " + String.format(Locale.ROOT, "%8s", this.status) + "\n";
		if(this.ban==true) {
			s=s.concat(" User is banned");
		}
		if(this.notifications==true) {
			s=s.concat(" User has notifications: \n");
			for(Notification n: notification) {
				s=s.concat(n.toString()+"\n");
			}			
		}
		s=s.concat("  Groups that the user has created: \n");
		for(Group g: createdGroups) {
			s=s.concat(g.toString()+"\n");
		}
		s=s.concat("  Projects the user has created: \n");
		for(Project p: createdProjects) {
			s=s.concat(p.toString()+"\n");
		}
		return s;
		
	}
}
