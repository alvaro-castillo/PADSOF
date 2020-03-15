package ads;

import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException,ClassNotFoundException {
		Application app = new Application();
		RegisteredUser u1 = new RegisteredUser("12345A","User1", "aaaa");
		if(app.addUser(u1)==false) {
			System.out.println("Already existing user\n");
		}
		/*Creates two users that are equal. Should return error*/
		RegisteredUser u2 = new RegisteredUser("6789B","User2", "bbbb");
		if(app.addUser(u2)==false) {
			System.out.println("Already existing user\n");
		}
		RegisteredUser u3 = new RegisteredUser("6789B","User2 ", "bbbb");
		if(app.addUser(u3)==false) {
			System.out.println("Already existing user\n");
		}
		/*Logs in the first user*/
		if(app.logIn(u1.getUsername(), u1.getPassword())==false) {
			System.out.println("Could not log in");
		}
		/*Creates project*/
		InfrastructureProject p = new InfrastructureProject("New skate park", " This project is created with the intention of build a new skate park", 10000.5,u1, District.CENTRO,"image.png");
		if(app.addProject(p)==false) {
			System.out.println("Already existing project");
		};
		u1.addProject(p);
		
		/*Creates a group*/
		Group g= new Group("UAM", u1);
		if(app.addGroup(g)==false) {
			System.out.println("Already existing group");
		};
		u1.addGroup(g);
		
		/*Saves the data of the application*/
		app.saveApp("data.txt");
		
		/*Loads the data in other variable and checks if they are equal by printing them*/
		Application app2 = Application.loadApp("data.txt");
		System.out.println("-------Original app-------\n"+ app + "\n");
		
		System.out.println("-------Loaded app-------\n" + app2);
			
		

	}

}
