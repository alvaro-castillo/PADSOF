package ads;

import java.io.IOException;
/**
* This is the main class program which illustrates all the functionality we have created.
*
* @author Miguel Álvarez Valiente, Alejandro Benimeli Miranda, Álvaro Castillo García
*/
public class Main {
	/**
	* This is the main program.
	* @throws IOException
	* @throws ClassNotFoundException
	*/
	public static void main(String[] args) {
		Application app = new Application();
		RegisteredUser u1 = new RegisteredUser("00000000A","Administrator", "aaaa");
		
		System.out.println("Creating the Administrator user\n");
		if(app.addUser(u1)==false) {
			System.out.println("	Already existing user\n");
		}
		
		/*Sets the first user to be registered in as the administrator of the app*/
		app.setAdmin(u1);
		
		/*Creates two users*/
		System.out.println("Creating two more users: 'User2' and 'User3'\n");
		RegisteredUser u2 = new RegisteredUser("22222222B","User2", "bbbb");
		if(app.addUser(u2)==false) {
			System.out.println("	Already existing user\n");
		}
		RegisteredUser u3 = new RegisteredUser("33333333C","User3", "cccccccc");
		if(app.addUser(u3)==false) {
			System.out.println("	Already existing user\n");
		}
		
		/*Logs in the admin to accept registration of user 2 and user 3*/
		System.out.println("Administrator accepting the registration of both users\n");
		if(app.logIn(u1.getUsername(), u1.getPassword())==false) {
			System.out.println("	Could not log in");
		}
		u2.acceptRegistration();
		u3.acceptRegistration();
		app.logOut();
		
		/*Logs in the second user to create a project and a group*/
		System.out.println("User2 creates a new Infrastructure Project: 'New skate park'\n");
		if(app.logIn(u2.getUsername(), u2.getPassword())==false) {
			System.out.println("	Could not log in");
		}
		InfrastructureProject p = new InfrastructureProject("New skate park", " This project is created with the intention of build a new skate park", 10000.5,u2, District.CENTRO,"image.png");
		if(app.addProject(p)==false) {
			System.out.println("	Already existing project");
		};
		u2.addProject(p);
		
		System.out.println("User2 creates a new group: 'UAM'\n");
		Group g1= new Group("UAM", u2);
		if(app.addGroup(g1)==false) {
			System.out.println("	Already existing group");
		};
		u2.addGroup(g1);
		app.logOut();
		
		/*Logs in the admin to accept the creation of the group*/
		System.out.println("Administator accepts the group: 'UAM'\n");
		if(app.logIn(u1.getUsername(), u1.getPassword())==false) {
			System.out.println("	Could not log in");
		}
		g1.acceptGroup();
		app.logOut();
		
		/*Logs in the second user to create two subgroups*/
		System.out.println("User2 creates two subgroups of 'UAM': 'EPS' and 'Facultad de Ciencias' \n");
		if(app.logIn(u2.getUsername(), u2.getPassword())==false) {
			System.out.println("	Could not log in");
		}
		Group g1a = g1.createSubgroup("EPS", u2);
		Group g1b = g1.createSubgroup("Facultad de Ciencias", u2);
		app.logOut();
		

		/*Logs in the admin to accept the creation of the subgroups and the project. It also sets two votes as the minimum of the project*/
		System.out.println("Administator accepts the subgroups: 'EPS' and 'Facultad de Ciencias'");
		System.out.println("He accepts the project: 'New skate park' too, and sets the minimum number of votes to 2\n");
		if(app.logIn(u1.getUsername(), u1.getPassword())==false) {
			System.out.println("	Could not log in");
		}
		g1a.acceptGroup();
		g1b.acceptGroup();
		p.adminAcceptProject();
		p.setMinimumVotes(2);

		app.logOut();
		/*Logs in the third user and enrolls subgroup 1a, try to enter in 1 and enrolls 1b*/
		System.out.println("User3 enrolls subgroup 'EPS' try to enter in 'UAM' (getting an error) and enrolls 'Facultad de Ciencias'\n");
		
		if(app.logIn(u3.getUsername(), u3.getPassword())==false) {
			System.out.println("	Could not log in");
		}
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
		
		/*Logs in the second user so he can vote to a project as a group representative*/
		System.out.println("\nUser2 votes to project: 'New skate park' as the representative of the group: 'UAM'\n");
		
		if(app.logIn(u2.getUsername(), u2.getPassword())==false) {
			System.out.println("	Could not log in");
		}
		if(p.vote(g1)==false) {
			System.out.println("	Error while voting to project " + p.getTitle());
		};
		
		app.logOut();
		
		/*Saves the data of the application*/
		System.out.println("Finally we save the app in the file 'data.txt' and we print the data of the whole application\n");
		try{
			app.saveApp("data.txt");
		}catch(IOException e) {
			System.out.println("	Error while saving the app. There is an error with the filename");
		}
		
		/*Loads the data in other variable and checks if they are equal by printing them
		try{
			Application app2 = Application.loadApp("data.txt");
		}catch(ClassNotFoundException c) {
			System.out.println("	Error while loading the app. There is a class that does not match");
		}catch(IOException e) {
			System.out.println("	Error while loading the app. There is an error with the filename");
		}
		*/
		System.out.println("-------Votify Application Data-------\n"+ app + "\n");
		
		//System.out.println("-------Loaded app-------\n" + app2);
			
		

	}

}
