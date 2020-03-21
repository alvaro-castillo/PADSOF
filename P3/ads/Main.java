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
	public static void main(String[] args) throws IOException,ClassNotFoundException {
		Application app = new Application();
		RegisteredUser u1 = new RegisteredUser("12345A","User1", "aaaa");
		
		if(app.addUser(u1)==false) {
			System.out.println("Already existing user\n");
		}
		
		/*Sets the first user to be registered in as the administrator of the app*/
		app.setAdmin(u1);
		
		/*Creates two users that are equal. Should return error*/
		RegisteredUser u2 = new RegisteredUser("6789B","User2", "bbbb");
		if(app.addUser(u2)==false) {
			System.out.println("Already existing user\n");
		}
		RegisteredUser u3 = new RegisteredUser("6789B","User2 ", "bbbb");
		if(app.addUser(u3)==false) {
			System.out.println("Already existing user\n");
		}
		
		/*Logs in the admin*/
		if(app.logIn(u1.getUsername(), u1.getPassword())==false) {
			System.out.println("Could not log in");
		}
		/*Accepts registration of user 2*/
		u2.acceptRegistration();
		/*Log out the admin*/
		app.logOut();
		
		/*Logs in the second user*/
		if(app.logIn(u2.getUsername(), u2.getPassword())==false) {
			System.out.println("Could not log in");
		}
		/*Creates project*/
		InfrastructureProject p = new InfrastructureProject("New skate park", " This project is created with the intention of build a new skate park", 10000.5,u2, District.CENTRO,"image.png");
		if(app.addProject(p)==false) {
			System.out.println("Already existing project");
		};
		u2.addProject(p);
		
		/*Creates a group*/
		Group g= new Group("UAM", u2);
		if(app.addGroup(g)==false) {
			System.out.println("Already existing group");
		};
		u2.addGroup(g);
		
		/*Saves the data of the application*/
		app.saveApp("data.txt");
		
		/*Loads the data in other variable and checks if they are equal by printing them*/
		Application app2 = Application.loadApp("data.txt");
		System.out.println("-------Original app-------\n"+ app + "\n");
		
		System.out.println("-------Loaded app-------\n" + app2);
			
		

	}

}
