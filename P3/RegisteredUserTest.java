package ads;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;
/**
* This is the tester for the RegisteredUser class
* 
* @author Miguel Álvarez Valiente, Alejandro Benimeli Miranda, Álvaro Castillo García
*/
public class RegisteredUserTest {
	private RegisteredUser r;
	
	/**
	 * The set up of the tester creates a new RegisteredUser
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		r = new RegisteredUser("12345678A","User1", "aaaabb");
	}

	/**
	 * Test method for {@link ads.RegisteredUser#addProject(ads.Project)}.
	 */
	@Test
	public void testAddProject() {
		RegisteredUser u2 = new RegisteredUser("456789","User2", "ddssc");
		InfrastructureProject p1 = new InfrastructureProject("New skate park", " This project is created with the intention of build a new skate park", 10000.5, r , District.CENTRO,"image.png");
		SocialProject p2 = new SocialProject("Concerts", "We want a concerts week ", 800, r, "MusicLife", true , "We like pop and rock music" );
		
		//Links the first project to its creator
		assertTrue(r.addProject(p1));
		
		//Links the first project twice to its creator
		assertFalse(r.addProject(p1));
		
		//Links the first project to a user which is not the creator
		assertFalse(u2.addProject(p1));
		
		//Links the second type of project to its creator
		assertTrue(r.addProject(p2));
	}

	/**
	 * Test method for {@link ads.RegisteredUser#addGroup(ads.Group)}.
	 */
	@Test
	public void testAddGroup() {
		
		RegisteredUser u2 = new RegisteredUser("456789","User2", "ddssc");
		Group g = new Group("UAM", r);
		
		//Links the group to its creator
		assertTrue(r.addGroup(g));
		
		//Links the group twice to its creator
		assertFalse(r.addGroup(g));
		
		//Links the group to a user which is not the creator
		assertFalse(u2.addGroup(g));
	}

	/**
	 * Test method for {@link ads.RegisteredUser#leaveCreatedGroup(ads.Group)}.
	 */
	@Test
	public void testLeaveCreatedGroup() {
		RegisteredUser u2 = new RegisteredUser("456789","User2", "ddssc");
		Group g1 = new Group("UAM", r);
		Group g2 = new Group("EPS", u2);
		r.addGroup(g1);
		
		//The user leaves a group that he has created
		assertTrue(r.leaveCreatedGroup(g1));
		
		//The user leaves a group where he is not in, because he had already left that group
		assertFalse(r.leaveCreatedGroup(g1));
		
		//The user leaves a group where he is not the representative, because he has not created it
		assertFalse(r.leaveCreatedGroup(g2));
	}

	/**
	 * Test method for {@link ads.RegisteredUser#update(ads.Notification)}.
	 */
	@Test
	public void testUpdate() {
		Notification n1 = new Notification("Hi", LocalDate.now());
		Notification n2 = new Notification("Bye", LocalDate.now());
		
		//Update with a new notification
		assertTrue(r.update(n1));
		//Update with a repeated notification
		assertFalse(r.update(n1));
		//Update with a different notification
		assertTrue(r.update(n2));
	}

	/**
	 * Test method for {@link ads.RegisteredUser#equals(java.lang.Object)}.
	 */
	@Test
	public void testEqualsObject() {
		RegisteredUser u1 = new RegisteredUser("12345678A","User1", "aaaabb");
		Group g = new Group("UAM", r);
		RegisteredUser u2 = new RegisteredUser("12345678A","Juan", "aaaabb");
		RegisteredUser u3 = new RegisteredUser("77777777Z","User1", "aaaabb");
		
		//Null object
		assertFalse(r.equals(null));
		//User with the same data
		assertTrue(r.equals(u1));
		//Object of different type
		assertFalse(r.equals(g));
		//User with same id
		assertTrue(r.equals(u2));
		//User with same username
		assertTrue(r.equals(u3));
	}

}
