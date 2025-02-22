package mainTester;

import java.io.IOException;

import application.enums.District;
import application.enums.ProjectStatus;
import application.project.InfrastructureProject;
import request.GovernmentGateway;
import application.Application;
import application.group.*;
import application.registeredUser.*;
import es.uam.eps.sadp.grants.CCGG;
import modifiableDates.ModifiableDate;
/**
* This is the main class program which illustrates all the functionality we have created and
* prints some results on the terminal. It does not include a GUI.
*
* @author Miguel Álvarez Valiente, Alejandro Benimeli Miranda, Álvaro Castillo García
*/
public class MainTester {
	/**
	* This is the main program.
	* It prints on the terminal all steps done and at the end it prints the data stored in the application.
	* The data is correctly indented so that the person executing it, is able to understand everything.
	* @throws Exception 
	* @throws IOException
	* @throws ClassNotFoundException
	*/
	public static void main(String[] args) throws Exception {
		Application app = Application.getApplication();
		RegisteredUser u1 = new RegisteredUser("00000000A","Administrator", "aaaa");
		
		System.out.println("Creating the Administrator user\n");
		if(app.addUser(u1)==false) {
			System.out.println("	Already existing user\n");
		}
			
		// Creates two users
		System.out.println("Creating two more users: 'User2' and 'User3'\n");
		RegisteredUser u2 = new RegisteredUser("22222222B","User2", "bbbb");
		if(app.addUser(u2)==false) {
			System.out.println("	Already existing user\n");
		}
		RegisteredUser u3 = new RegisteredUser("33333333C","User3", "cccccccc");
		if(app.addUser(u3)==false) {
			System.out.println("	Already existing user\n");
		}
		
		// Logs in the admin to accept registration of user 2 and user 3
		System.out.println("Administrator accepting the registration of both users\n");
		app.logIn(u1.getUsername(), u1.getPassword());

		u2.acceptRegistration();
		u3.acceptRegistration();
		app.logOut();
		
		// Logs in the second user to create a project and a group
		System.out.println("User2 creates a new Infrastructure Project: 'New skate park'\n");
		app.logIn(u2.getUsername(), u2.getPassword());
		InfrastructureProject p = new InfrastructureProject("New skate park", " This project is created with the intention of build a new skate park", 10000.5,u2, District.CENTRO,"Logo.png");
		if(app.addProject(p)==false) {
			System.out.println("	Already existing project");
		};
		u2.addProject(p);
		InfrastructureProject p2 = new InfrastructureProject("New big skate park", " This project is created with the intention of build a new skate park", 10000.5,u2, District.CENTRO,"image.png");
		if(app.addProject(p2)==false) {
			System.out.println("	Already existing project");
		};
		System.out.println("User2 creates a new group: 'UAM'\n");
		Group g1= new Group("UAM", u2);
		if(app.addGroup(g1)==false) {
			System.out.println("	Already existing group");
		};
		u2.addGroup(g1);
		app.logOut();
		
		// Logs in the admin to accept the creation of the group
		System.out.println("Administator accepts the group: 'UAM'\n");
		app.logIn(u1.getUsername(), u1.getPassword());
		g1.acceptGroup();
		app.logOut();
		
		// Logs in the second user to create two subgroups
		System.out.println("User2 creates two subgroups of 'UAM': 'EPS' and 'Facultad de Ciencias' \n");
		app.logIn(u2.getUsername(), u2.getPassword());
		Group g1a = g1.createSubgroup("EPS", u2);
		app.addGroup(g1a);
		Group g1b = g1.createSubgroup("Facultad de Ciencias", u2);
		app.addGroup(g1b);
		app.logOut();
		

		// Logs in the admin to accept the creation of the subgroups and the project. It also sets two votes as the minimum of the project
		System.out.println("Administator accepts the subgroups: 'EPS' and 'Facultad de Ciencias'");
		System.out.println("He accepts the project: 'New skate park' too, and sets the minimum number of votes to 2\n");
		app.logIn(u1.getUsername(), u1.getPassword());
		g1a.acceptGroup();
		g1b.acceptGroup();
		p.adminAcceptProject();
		p.setMinimumVotes(2);

		app.logOut();
		// Logs in the third user and enrolls subgroup 1a, try to enter in 1 and enrolls 1b
		System.out.println("User3 enrolls subgroup 'EPS' try to enter in 'UAM' (getting an error) and enrolls 'Facultad de Ciencias'\n");
		
		app.logIn(u3.getUsername(), u3.getPassword());
		if(g1a.addUser(u3)==false) {
			System.out.println("	Error while enrolling the group " + g1a.getName());
		}
		if(g1.addUser(u3)==false) {
			System.out.println("	Error while enrolling the group " + g1.getName());
		}
		if(g1b.addUser(u3)==false) {
			System.out.println("	Error while enrolling the group " + g1b.getName());
		}
		
		app.logOut();
		
		// Logs in the second user so he can vote to a project as a group representative
		System.out.println("\nUser2 votes to project: 'New skate park' as the representative of the group: 'UAM'\n");
		
		app.logIn(u2.getUsername(), u2.getPassword());
		if(p.vote(g1)==false) {
			System.out.println("	Error while voting to project " + p.getTitle());
		};
		
		// Gets the instance of the GovernmentGateway and sends the project p for funding as it has the minimum number of votes requiered
		GovernmentGateway gateway = GovernmentGateway.getInstance();
		
		System.out.println("\n\nSending a project that has the minimum number of votes\n");
		for (int i=0; i<2; ++i) {
			try {
				if (gateway.sendProject(p) == true) {
					System.out.println("\tProject sent successfully!");
				} else {
					System.out.println("\tError sending the project");
				}
				break;
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		
		// Advance time
		ModifiableDate.plusDays(8);
		CCGG.getGateway().setDate(ModifiableDate.getModifiableDate());
		
		System.out.println("\n\nChecking the state of a project grant request\n");
		// Try to check a project (We retry in case there's any IO error)
		ProjectStatus stat;
		for (int i=0; i<2; ++i) {
			try {
				if ((stat = gateway.checkProject(p)) != null) {
					System.out.println("\tProject's request status checked correcly!. The state is " + stat + "\n");
				} else {
					System.out.println("\tError checking the state of the project");
				}
				break;
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		app.logOut();
		
		// Saves the data of the application. No one is logged in.
		System.out.println("Finally we save the app in the file 'data.dat' and we print the data of the whole application\n");
		try{
			app.saveApp("data.dat");
		}catch(IOException e) {
			System.out.println("	Error while saving the app. There is an error with the filename");
		}
		// Prints the data of all the Application. No user should be stored in the current user attribute.				
		System.out.println("-------Votify Application Data-------\n"+ app + "\n");
	}
}
