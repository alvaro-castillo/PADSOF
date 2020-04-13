package application;


import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import application.enums.*;
import application.notification.*;
import application.project.*;
import application.group.*;
import application.registeredUser.*;
import es.uam.eps.sadp.grants.CCGG;
import modifiableDates.ModifiableDate;

import java.io.*;
import java.time.LocalDate;

/**
* These are the functions and variables that define the Application object, the Application class.
*
* @author Miguel Álvarez Valiente, Alejandro Benimeli Miranda, Álvaro Castillo García
*/
public class Application extends Subject
						 implements Serializable {
	
	/**
	 * Name of the application.
	 */
	private String name; 
	
	/**
	 * A list that contains all the groups in the app.
	 */
	private List<Group> groups;
	
	/**
	 * A list that contains all the projects in the app.
	 */
	private List<Project> projects;
	
	/**
	 * A list that contains all the users in the app.
	 */
	private List<RegisteredUser> users;
	
	/**
	 * An attribute that stores the user that is in the app at the current moment.
	 */
	private RegisteredUser currentUser;
	
	/**
	 * This attribute stores the user which is the admin of the application.
	 */
	private RegisteredUser admin; 
	
	/**
	 * This field stores the version for serialization.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor of this class.
	 */
	public Application() {
		this.groups = new ArrayList<Group>();
		this.projects = new ArrayList<Project>();
		this.users = new ArrayList<RegisteredUser>();
		this.name = "Votify";
		ModifiableDate.setToday();
		CCGG.getGateway().setDate(ModifiableDate.getModifiableDate());
	}
	/**
	 * Groups in the application getter.
	 * @return groups, a list with all the groups.
	 */
	public List<Group> getGroups() {
		return groups;
	}
	/**
	 * Projects in the application getter.
	 * @return projects,  a list with all the groups.
	 */
	public List<Project> getProjects() {
		return projects;
	}
	/**
	 * Users in the application getter.
	 * @return users, a list with all the groups.
	 */
	public List<RegisteredUser> getUsers() {
		return users;
	}
	/**
	 * Current user getter.
	 * @return currentUser
	 */
	public RegisteredUser getCurrentUser() {
		return currentUser;
	}
	/**
	 * Current user setter.
	 */
	public void setCurrentUser(RegisteredUser currentUser) {
		this.currentUser = currentUser;
	}
	/**
	 * Application name getter.
	 * @return name
	 */
	public String getName() {
		return name;
	}
	/**
	 * Function that adds a registered to user to the users list.
	 * It checks if the id and username are unique
	 * @param u user that we want to add.
	 * @return boolean
	 */	
	public boolean addUser(RegisteredUser u) {
		if (u == null) {
			return false;
		}
		for(RegisteredUser us: users) {
			if(us.equals(u)) {
				return false;
			}
		}
		notifyObservers(new Notification("New User '" + u.getUsername() + "' (id " + u.getId() + ") has been created", ModifiableDate.getModifiableDate()));
		return users.add(u);
	}
	/**
	 * Function that adds a group to the groups list.
	 * It checks if the name is unique
	 * @param g group that we want to add.
	 * @return boolean
	 */
	public boolean addGroup(Group g) {
		if (g == null) {
			return false;
		}
		for(Group gr: groups) {
			if(gr.equals(g)) {
				return false;
			}
		}
		notifyObservers(new Notification("New Group '" + g.getName() + "' has been created", ModifiableDate.getModifiableDate()));
		return groups.add(g);
	}
	/**
	 * Function that adds a project to the projects list.
	 * @param p project that we want to add.
	 * @return boolean
	 */
	public boolean addProject(Project p) {
		if (p == null) {
			return false;
		}
		for(Project pr: projects) {
			if(pr.equals(p)) {
				return false;
			}
		}
		notifyObservers(new Notification("New Project '" + p.getTitle() + "' with ID " + p.getId() + " has been created", ModifiableDate.getModifiableDate()));
		return projects.add(p);
	}
	/**
	 * Function that removes a project from the projects list.
	 * @param p project that we want to remove.
	 * @return boolean
	 */
	public boolean removeProject(Project p) {
		return projects.remove(p);
	}
	/**
	 * Administrator of the application setter.
	 */
	public void setAdmin(RegisteredUser a) {
		a.acceptRegistration();
		this.admin=a;
		registerObserver(this.admin);
	}
	/**
	 * Administrator of the application getter.
	 * @return admin
	 */
	public RegisteredUser getAdmin() {
		return this.admin;
	}
	/**
	 * Function that log in a user.
	 * It checks if the name is and password coincide with the user ones. In addition the user shouldn't be banned
	 * @param u username we want to log in.
	 * @param p password we want to check.
	 * @return boolean
	 */
	public boolean logIn(String u, String p ) {
		if(this.currentUser==null) {
			for(RegisteredUser r: users) {
				if(r.isBan()==false && r.getStatus()==Status.ACCEPTED) {
					if(u==r.getUsername()) {
						if(p==r.getPassword()) {
							this.currentUser=r;
							return true;
						}
					}
				}
			}
		}
		return false;		
	}
	/**
	 * Function that logs out a user by changing the currentUser parameter.
	 */
	public void logOut() {
		this.currentUser=null;
	}
	/**
	 * Function that search a project from the projects list.
	 * @param id of the project that we want to look for.
	 * @return p 
	 */
	public Project searchProject(long id) {
		for(Project p: projects) {
			if(p.getId()==id) {
				return p;
			}
		}
		return null;
	}
	/**
	 * Function that search a project from the projects list.
	 * @param name of the project that we want to look for.
	 * @return p List of projects with the same name.
	 */
	public List<Project> searchProject(String name) {
		List<Project> ps = new ArrayList<Project>();
		for(Project p: projects) {
			if(p.getTitle()==name) {
				ps.add(p);
			}
		}
		return ps;
	}
	/**
	 * Function that search a group from the groups list.
	 * @param name the group name that we want to look for.
	 * @return g 
	 */
	public Group searchGroup(String name) {
		for(Group g: groups) {
			if(g.getName()==name) {
				return g;
			}
		}
		return null;
	}
	
	/**
	 * Function that saves the application on an external file.
	 * @param filename of the file.
	 * @throws IOException
	 */
	public void saveApp(String filename) throws IOException{
		ObjectOutputStream output =
				new ObjectOutputStream(
				new FileOutputStream( filename ));
		
		output.writeObject(this);
		output.close();
	}
	/**
	 * Function that loads the application written on an external file.
	 * @param filename of the file.
	 * @return app an Application object;
	 * @throws ClassNotFoundException 
	 * @throws IOException
	 */
	public static Application loadApp(String filename) throws IOException, ClassNotFoundException{
		ObjectInputStream input = 
				new ObjectInputStream(
						new FileInputStream(filename));
		Application app = (Application) input.readObject();
		input.close();
		
		ModifiableDate.setToday();
		CCGG.getGateway().setDate(ModifiableDate.getModifiableDate());
		
		return app;
	}
	
	/**
	 * This method overrides the default toString method.
	 * @return s string with the information of the object.
	 */
	@Override
	public String toString() {
		String s= "App name: " + this.name +"\n" + "Users in the app: \n";
		
		for(RegisteredUser us: users) {
			s=s.concat(us.toString()+"\n");
		}
		
		s=s.concat("\nGroups in the app: \n");
		for(Group g: groups) {
			s=s.concat(g.toString()+"\n");
		}
		s=s.concat("\nProjects in the app: \n");
		for(Project p: projects) {
			s=s.concat(p.toString()+"\n");
		}
		
		s=s.concat("\nCurrent user: \n"+ this.currentUser +"\n");
		s=s.concat("Admin: \n"+ this.admin +"\n");
		
		return s;
	}
	/**
	 * Function that checks if there are any expired projects at the real time and returns them.
	 * This cannot be tested because it needs real time (30 real days passed from the creation of the project).
	 * @return expired List with all expired projects
	 */
	public List<Project> checkExpiredProjects() {
		LocalDate now = LocalDate.now(); 
		List<Project> expired = new ArrayList<Project>();
		for(Project p: projects) {
			if(p.getActualVotes()<p.getMinimumVotes()) {
			LocalDate d = p.getAcceptDate();
				if(now.compareTo(d.plusDays(30))==0) {
					p.expireProject();
					expired.add(p);
					RegisteredUser c = p.getCreator();
					c.update(new Notification("Your " + p.getClass().getName() + " project: " + p.getTitle() + " with ID " + p.getId() + " has expired!", LocalDate.now()));
				}
			}
		}
		return expired;
	}
	/**
	 * Function that checks if there are any expired projects at a time passed by argument (as if it was the real time) and returns them.
	 * @param actualDate date that is used with modifiable Dates if we want to check the expired projects
	 * @return expired List with all expired projects
	 */
	public List<Project> checkExpiredProjects(LocalDate actualDate) {
 
		List<Project> expired = new ArrayList<Project>();
		for(Project p: projects) {
			if(p.getActualVotes()<p.getMinimumVotes()) {
			LocalDate d = p.getAcceptDate();
				if(actualDate.compareTo(d.plusDays(30))==0) {
					p.expireProject();
					expired.add(p);
					RegisteredUser c = p.getCreator();
					c.update(new Notification("Your " + p.getClass().getName() + " project: " + p.getTitle() + " with ID " + p.getId() + " has expired!", LocalDate.now()));
				}
			}
		}
		return expired;
	}
	
	/**
	 * Function that checks if there are any banned users and returns their username.
	 * @return banned Vector with all banned usernames
	 */
	public Vector <String> getBannedUsers() {
		Vector<String> banned = new Vector<String> ();
		
		for(RegisteredUser r: users) {
			if(r.isBan()==true) {
				banned.add(r.getUsername());
			}
		}
		
		return banned;
	}
	
	/**
	 * Function that checks if there are any pending for acceptance projects and returns their title and id.
	 * @return pending Vector with all pending for acceptance projects
	 */
	public Vector <String> getPendingAcceptanceProjects() {
		Vector<String> pending = new Vector<String> ();
		
		for(Project p: projects) {
			if(p.getState()==ProjectStatus.WAITING_ACCEPTANCE) {
				pending.add(p.getTitle() + " ID: " + p.getId());
			}
		}
		
		return pending;
	}
	
	/**
	 * Function that checks if there are any waiting for acceptance groups and returns their name.
	 * @return waiting Vector with all waiting for acceptance group names
	 */
	public Vector <String> getPendingAcceptanceGroups() {
		Vector<String> waiting = new Vector<String> ();
		
		for(Group g: groups) {
			if(g.getStatus()==Status.WAITING) {
				waiting.add(g.getName());
			}
		}
		
		return waiting;
	}
	
	/**
	 * Function that checks if there are any waiting for acceptance users and returns their username.
	 * @return waiting Vector with all waiting for acceptance usernames
	 */
	public Vector <String> getPendingAcceptanceUsers() {
		Vector<String> waiting = new Vector<String> ();
		
		for(RegisteredUser r: users) {
			if(r.getStatus()==Status.WAITING) {
				waiting.add(r.getUsername());
			}
		}
		
		return waiting;
	}
}
