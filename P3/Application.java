package ads;


import java.util.ArrayList;
import java.util.List;
/**
 * Cambios en el diagrama:
 * 
 * funcion logOut es void--> no devuelve nada
 * 
 * funciones createUser; createGroup; createInfrastructureProject; createSocialProject ahora son
 * addGroup; addUser; addProject--> simplemente añaden al array cada objeto. La tarea de crear los
 * objetos se realizará desde la interfaz llamando al constructor.
 * 
 * searchProject no puede recibir un string con el nombre ya que puede haber dos proyectos con el mismo 
 * nombre. Recibirá un long id.
 * 
 * unregistered user no existe.
 * 
* These are the functions and variables that define the Application objects, the Application class.
*
* @author Miguel Álvarez Valiente, Alejandro Benimeli Miranda, Álvaro Castillo García
*/
public class Application {
	private String name; 
	private List<Group> groups;
	private List<Project> projects;
	private List<RegisteredUser> users;
	private RegisteredUser currentUser;
	
	
	/**
	 * Constructor of this class.
	 */
	public Application() {
		this.groups = new ArrayList<Group>();
		this.projects = new ArrayList<Project>();
		this.users = new ArrayList<RegisteredUser>();
		this.name = "Votify";
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
		for(RegisteredUser us: users) {
			if(us.getId() == u.getId() || u.getUsername() == us.getUsername()) {
				return false;
			}
		}
		return users.add(u);
	}
	/**
	 * Function that adds a group to the groups list.
	 * It checks if the name is unique
	 * @param g group that we want to add.
	 * @return boolean
	 */
	public boolean addGroup(Group g) {
		for(Group gr: groups) {
			if(gr.getName()==g.getName()) {
				return false;
			}
		}
		return groups.add(g);
	}
	/**
	 * Function that adds a project to the projects list.
	 * @param p project that we want to add.
	 * @return boolean
	 */
	public boolean addProject(Project p) {
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
	 * Function that log in a user.
	 * It checks if the name is and password coincide with the user ones.
	 * @param u username we want to log in.
	 * @param p password we want to check.
	 * @return boolean
	 */
	public boolean logIn(String u, String p ) {
		for(RegisteredUser r: users) {
			if(u==r.getUsername()) {
				if(p==r.getPassword()) {
					this.currentUser=r;
					return true;
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
	 * Function that search a group from the groups list.
	 * @param name the group name that we want to look for.
	 * @return g 
	 */
	public Project searchGroup(String name) {
		for(Group g: groups) {
			if(g.getName()==name) {
				return g;
			}
		}
		return null;
	}
	
	public void notifyObserver(Notification n) {
		// TODO Implementarla. Antes hay que hacer la interfaz observer
	}
	
	public Project[] checkExpiredProjects() {
		// TODO Implementarla. Depende de lo del localdate
	}
	
}
