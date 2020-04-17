package testers;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.*;

import org.junit.Before;
import org.junit.Test;

import application.*;
import application.enums.District;
import application.project.*;
import modifiableDates.ModifiableDate;
import application.registeredUser.RegisteredUser;
import application.group.Group;
/**
* This is the tester for the Application class
* 
* @author Miguel Álvarez Valiente, Alejandro Benimeli Miranda, Álvaro Castillo García
*/
public class ApplicationTest {
	Application app;
	/**
	 * The set up of the tester creates a new Application
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		app =Application.getApplication();
	}

	/**
	 * Test method for {@link application.Application#addUser(application.RegisteredUser)}.
	 */
	@Test
	public void testAddUser() {
		RegisteredUser u1 = new RegisteredUser("456789","User1", "padsof2291");
		RegisteredUser u2 = new RegisteredUser("12345X","User2", "EPS");
		//Adds a new user
		assertTrue(app.addUser(u1));
		//Adds another user
		assertTrue(app.addUser(u2));
		//Adds a user that is already in the app
		assertFalse(app.addUser(u2));

	}

	/**
	 * Test method for {@link application.Application#addGroup(application.Group)}.
	 */
	@Test
	public void testAddGroup() {
		RegisteredUser u = new RegisteredUser("12345678A","Rector", "uamMola");
		Group g1 = new Group("UAM", u);
		Group g2 = new Group("EPS", u);
		
		//Adds a new group
		assertTrue(app.addGroup(g1));
		//Adds another group
		assertTrue(app.addGroup(g2));
		//Adds a group that is already in the app
		assertFalse(app.addGroup(g2));
	}

	/**
	 * Test method for {@link application.Application#addProject(application.project.Project)}.
	 */
	@Test
	public void testAddProject() {
		RegisteredUser u = new RegisteredUser("12345678A","Rector", "uamMola");
		InfrastructureProject p1 = new InfrastructureProject("New skate park", " This project is created with the intention of build a new skate park", 10000.5, u , District.CENTRO,"image.png");
		SocialProject p2 = new SocialProject("Concerts", "We want a concerts week ", 800, u, "MusicLife", true , "We like pop and rock music" );
		
		
		//Adds a new project
		assertTrue(app.addProject(p1));
		//Adds another project
		assertTrue(app.addProject(p2));
		//Adds a project that is already in the app
		assertFalse(app.addProject(p1));
		//Adds a project that is already in the app
		assertFalse(app.addProject(p2));
	}

	/**
	 * Test method for {@link application.Application#removeProject(application.project.Project)}.
	 */
	@Test
	public void testRemoveProject() {
		RegisteredUser u = new RegisteredUser("12345678A","Rector", "uamMola");
		InfrastructureProject p1 = new InfrastructureProject("New skate park", " This project is created with the intention of build a new skate park", 10000.5, u , District.CENTRO,"image.png");
		SocialProject p2 = new SocialProject("Concerts", "We want a concerts week ", 800, u, "MusicLife", true , "We like pop and rock music" );
		
		app.addProject(p1);
		//Removes a project that is in the app
		assertTrue(app.removeProject(p1));
		//Removes a project that is not in the app
		assertFalse(app.removeProject(p2));
		
	}

	/**
	 * Test method for {@link application.Application#logIn(java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testLogIn() {
		RegisteredUser u1 = new RegisteredUser("456789","User1", "padsof2291");
		RegisteredUser u2 = new RegisteredUser("12345X","User2", "EPS");
		RegisteredUser u3 = new RegisteredUser("0000000A","User3", "uam");
		
		app.addUser(u1);
		app.addUser(u2);
		app.addUser(u3);
		
		u1.acceptRegistration();
		u2.acceptRegistration();
		//Logs in the first user
		assertTrue(app.logIn("User1", "padsof2291"));
		//Logs in a user while there is already one user using the app
		assertFalse(app.logIn("User2", "EPS"));
		
		app.logOut();
		//Try to log in a user with an erroneous password 
		assertFalse(app.logIn("User1", "padsof3321"));
		//Try to log in a user with an erroneous username 
		assertFalse(app.logIn("User12", "padsof2291"));
		
		u2.banUser();
		//Try to log in a user that is banned
		assertFalse(app.logIn("User2", "EPS"));
		
		u2.rejectRegistration();
		u2.unbanUser();
		//Try to log in a user that has been rejected
		assertFalse(app.logIn("User2", "EPS"));
		
		//Try to log in a user that hasn't been accepted yet
		assertFalse(app.logIn("User3", "uam"));

	}

	/**
	 * Test method for {@link application.Application#searchProject(long)}.
	 */
	@Test
	public void testSearchProjectLong() {
		RegisteredUser u = new RegisteredUser("12345678A","Rector", "uamMola");
		InfrastructureProject p1 = new InfrastructureProject("New skate park", " This project is created with the intention of build a new skate park", 10000.5, u , District.CENTRO,"image.png");
		SocialProject p2 = new SocialProject("Concerts", "We want a concerts week ", 800, u, "MusicLife", true , "We like pop and rock music" );
		
		app.addProject(p1);
		app.addProject(p2);
		
		//Searches the first project
		assertEquals(p1, app.searchProject(5));

		//Searches the second project
		assertEquals(p2, app.searchProject(6));
		//Searches a project that does not exist
		assertNull(app.searchProject(4));
	}

	/**
	 * Test method for {@link application.Application#searchProject(java.lang.String)}.
	 */
	@Test
	public void testSearchProjectString() {
		List<Project> a;
		List<Project> b;
		RegisteredUser u = new RegisteredUser("12345678A","Rector", "uamMola");
		InfrastructureProject p1a = new InfrastructureProject("New skate", " This project is created with the intention of build a new skate park", 10000.5, u , District.CENTRO,"image.png");
		InfrastructureProject p1b = new InfrastructureProject("New skate", " This project is created with the intention of build a new skate shop", 2300, u , District.BARAJAS,"image1.png");
		SocialProject p2 = new SocialProject("Concerts", "We want a concerts week ", 800, u, "MusicLife", true , "We like pop and rock music" );
		
		app.addProject(p1a);
		app.addProject(p1b);
		app.addProject(p2);
		
		//Searches a project that is not in the app
		a = app.searchProject("Old");
		
		assertTrue(a.isEmpty());

		//Searches the projects 1a and 1b
		a = app.searchProject("New skate");
		
		assertTrue(a.contains(p1b));
		assertTrue(a.contains(p1b));

		//Searches the project 2
		b = app.searchProject("Concerts");
		assertTrue(b.contains(p2));

	}

	/**
	 * Test method for {@link application.Application#searchGroup(java.lang.String)}.
	 */
	@Test
	public void testSearchGroup() {
		
		RegisteredUser u = new RegisteredUser("12345678A","Rector", "uamMola");
		Group g1 = new Group("UAM", u);
		Group g2 = new Group("EPS", u);
		app.addGroup(g1);
		app.addGroup(g2);
		
		//Searches the first group
		assertEquals(g1, app.searchGroup("UAM"));
		//Searches the second group
		assertEquals(g2, app.searchGroup("EPS"));
		//Searches a group that does not exist
		assertNull(app.searchGroup("Padsof"));
	}

	/**
	 * Test method for {@link application.Application#loadApp(java.lang.String)}.
	 * As exceptions are handled we do not need to test other cases than a positive one.
	 * In addition we do not have an equals() method for Application. It will make no sense to create it, just for this function.
	 * We were able to print the app, save it, load it, and check the second one. They both showed the same information.
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	@Test
	public void testLoadApp() throws IOException, ClassNotFoundException{
		app.saveApp("data.dat");
		// Loads an app
		assertNotNull(Application.loadApp("data.dat"));

	}

	/**
	 * Test method for {@link application.Application#checkExpiredProjects()}.
	 */
	@Test
	public void testCheckExpiredProjects() {
		List<Project> expiredProjects;

		RegisteredUser u = new RegisteredUser("12345678A","Rector", "uamMola");
		InfrastructureProject p1 = new InfrastructureProject("New skate", " This project is created with the intention of build a new skate shop", 2300, u , District.BARAJAS,"image1.png");
		SocialProject p2 = new SocialProject("Concerts", "We want a concerts week ", 800, u, "MusicLife", true , "We like pop and rock music" );
		SocialProject p3 = new SocialProject("Food trucks", "There should be a food trucks expo", 800, u, "Food Lovers", false , "Eating is our passion" );
		
		app.addProject(p1);
		app.addProject(p2);
		app.addProject(p3);
		
		ModifiableDate.setToday();
		
		p1.setMinimumVotes(200);
		p2.setMinimumVotes(200);
		
		p1.adminAcceptProject();
		p2.adminAcceptProject();
		
		
		//Check that there are no expired projects. The date is the same of the projects acceptance
		ModifiableDate.setToday();
		
		expiredProjects = app.checkExpiredProjects(ModifiableDate.getModifiableDate());
		
		assertTrue(expiredProjects.isEmpty());
		
		//Check that there are two expired projects. p3 should not be expired as the admin has not accepted it yet
		ModifiableDate.plusDays(30);

		expiredProjects = app.checkExpiredProjects(ModifiableDate.getModifiableDate());

		assertTrue(expiredProjects.contains(p1));
		assertTrue(expiredProjects.contains(p2));
		assertFalse(expiredProjects.contains(p3));
		
	}
}
