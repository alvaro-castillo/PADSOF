package userInterface.projectScreen;

import javax.swing.JFrame;
import javax.swing.JPanel;

import application.Application;
import application.enums.District;
import application.group.Group;
import application.project.InfrastructureProject;
import application.project.SocialProject;
import application.registeredUser.RegisteredUser;
import userInterface.groupScreen.GroupPanel;


public class Main {

	public static void main(String[] args) {
		JFrame m = new AppFrame();
		
		RegisteredUser regUser = new RegisteredUser("a", "a", "password");
		RegisteredUser regUser2 = new RegisteredUser("b", "b", "password");
		RegisteredUser regUser3 = new RegisteredUser("c", "c", "password");
		RegisteredUser regUser4 = new RegisteredUser("d", "d", "password");
		regUser2.acceptRegistration();
		regUser3.acceptRegistration();
		regUser4.acceptRegistration();
		InfrastructureProject iProj = new InfrastructureProject("Matar a los Camioneros", "Por el bien comun proponemos que se ejecute publicamente a todos los camioneros del reino de España.", 123456.78, regUser, District.CHAMBERI, "LogoHighRes.png");
		SocialProject socProj = new SocialProject("Matar a Jensen", "Jensen must die", 1234, regUser, "Org", false, "Reeeee");
		Group g = new Group("Minecrafteros", regUser);
		g.acceptGroup();
		Group g2 = g.createSubgroup("hola", regUser);
		g2.acceptGroup();
		
		Application.getApplication().addProject(socProj);
		Application.getApplication().addProject(iProj);
		Application.getApplication().addUser(regUser);
		Application.getApplication().addUser(regUser2);
		Application.getApplication().addUser(regUser3);
		Application.getApplication().addUser(regUser4);
		
		Application.getApplication().addGroup(g);
		Application.getApplication().addGroup(g2);
		
		//g2.addUser(regUser2);
		//g2.addUser(regUser3);
		g.addUser(regUser4);
		
		for (RegisteredUser u: g.getMembers()) {
			System.out.print(u.getUsername() + " ");
		}/*
		System.out.print("\n----------------\n");
		for (RegisteredUser u: g2.getMembers()) {
			System.out.print(u.getUsername() + " ");
		}*/
		
		Application.getApplication().setCurrentUser(regUser);
		
		//JPanel initial = new InfrastructureProjectPanel(iProj, regUser);
		//JPanel initial = new SocialProjectPanel(socProj, regUser);
		JPanel initial = new GroupPanel(g,Application.getApplication().getCurrentUser());
		m.add(initial);
		m.revalidate();
		initial.setVisible(true);
	}

}
