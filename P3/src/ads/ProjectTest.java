package ads;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
* This is the tester for the Project class
* 
* @author Miguel Álvarez Valiente, Alejandro Benimeli Miranda, Álvaro Castillo García
*/
public class ProjectTest {
	
	private RegisteredUser user;
	private Project proj;
	
	/**
	 * Creates a project and user before each test
	 */
	@Before
	public void setUp() {
		user = new RegisteredUser("A", "id", "passw");
		
		// We can't create a project because it's an abstract class, so we create a subclass
		proj = new InfrastructureProject("Burger", "We want to build a Burger King in front of"
				+ "a weight loss center", 1000000, user, District.CENTRO, "H");
	}

	/**
	 * Test for {@link ads.Project#adminAcceptProject()}
	 */
	@Test
	public void testAdminAcceptProject() {
		
		// We make sure it properly changes the state
		proj.adminAcceptProject();
		assertEquals(ProjectStatus.ADMIN_ACCEPTED, proj.getState());
		
		// We make sure the creator received a notification telling them about the
		// change of the project
		assertEquals(1, user.getNotifications().size());
		
	}

	/**
	 * Test for {@link ads.Project#adminRejectProject()}
	 */
	@Test
	public void testAdminRejectProject() {
		
		// We make sure it properly changes the state
		proj.adminRejectProject();
		assertEquals(ProjectStatus.ADMIN_REJECTED, proj.getState());
		
		// We make sure the creator received a notification telling them about the
		// change of the project
		assertEquals(1, user.getNotifications().size());
		
	}

	/**
	 * Test for {@link ads.Project#pendingProject()}
	 */
	@Test
	public void testPendingProject() {
		
		// We make sure it properly changes the state
		proj.pendingProject();
		assertEquals(ProjectStatus.PENDING, proj.getState());
		
		// We make sure the creator received a notification telling them about the
		// change of the project
		assertEquals(1, user.getNotifications().size());
		
	}

	/**
	 * Test for {@link ads.Project#expireProject()}
	 */
	@Test
	public void testExpireProject() {
		
		// We make sure it properly changes the state
		proj.expireProject();
		assertEquals(ProjectStatus.EXPIRED, proj.getState());
		
		// We make sure the creator received a notification telling them about the
		// change of the project
		assertEquals(1, user.getNotifications().size());
		
	}

	/**
	 * Test for {@link ads.Project#rejectProject()}
	 */
	@Test
	public void testRejectProject() {
		
		// We make sure it properly changes the state
		proj.rejectProject();
		assertEquals(ProjectStatus.REJECTED, proj.getState());
		
		// We make sure the creator received a notification telling them about the
		// change of the project
		assertEquals(1, user.getNotifications().size());
		
	}

	/**
	 * Test for {@link ads.Project#approveProject()}
	 */
	@Test
	public void testApproveProject() {
		
		// We make sure it properly changes the state
		proj.approveProject();
		
		assertEquals(ProjectStatus.APPROVED, proj.getState());
		
		// We make sure the creator received a notification telling them about the
		// change of the project
		assertEquals(1, user.getNotifications().size());
		
	}

	/**
	 * Test for {@link ads.Project#vote(ads.RegisteredUser)}
	 */
	@Test
	public void testVoteRegisteredUser() {
		
		// First we make sure there's only one vote (the creator's)
		assertEquals(1, proj.getActualVotes());
		
		// The creator tries to vote again and fails
		assertFalse(proj.vote(user));
		assertEquals(1, proj.getActualVotes());
		
		// A new user votes and then there's two votes
		assertTrue(proj.vote(new RegisteredUser("a","b","c")));
		assertEquals(2, proj.getActualVotes());
		
		// We try to pass a null RegisteredUser
		RegisteredUser nullUser = null;
		assertFalse(proj.vote(nullUser));
		assertEquals(2, proj.getActualVotes());
		
	}

	/**
	 * Test for {@link ads.Project#vote(ads.Group)}
	 */
	@Test
	public void testVoteGroup() {
		
		// First we make sure there's only one vote (the creator's)
		assertEquals(1, proj.getActualVotes());
		
		// The creator creates a group and votes with it but it still has 1 vote
		Group testGroup = new Group("Cool Kids Club", user);
		testGroup.acceptGroup();
		
		assertTrue(proj.vote(testGroup));
		assertEquals(1, proj.getActualVotes());
		
		// The same group tries to vote again
		assertFalse(proj.vote(testGroup));
		
		// A new user joins the group and now it has 2 votes
		RegisteredUser user2 = new RegisteredUser("a","b","c");
		user2.acceptRegistration();
		testGroup.addUser(user2);
		
		assertEquals(2, proj.getActualVotes());
		
		// We try to pass a null Group
		Group nullGroup = null;
		
		assertFalse(proj.vote(nullGroup));
		assertEquals(2, proj.getActualVotes());
		
	}
	
	/**
	 * Test for {@link ads.Project#update(ads.Notification)}
	 */
	@Test
	public void testUpdate() {
		
		// This method is called by other
		assertTrue(proj.update(null));
		
	}

	/**
	 * Test for {@link ads.Project#hasVoted(ads.RegisteredUser)}
	 */
	@Test
	public void testHasVotedRegisteredUser() {
		
		// Check if the creator has voted
		assertTrue(proj.hasVoted(user));
		
		// Check if a user that hasn't voted has voted
		RegisteredUser user2 = new RegisteredUser("Aitor Tilla","12341234N","nugget");
		assertFalse(proj.hasVoted(user2));
		
		// Now he votes and we check again
		proj.vote(user2);
		assertTrue(proj.hasVoted(user2));
		
	}

	/**
	 * Test for {@link ads.Project#hasVoted(ads.Group)}
	 */
	@Test
	public void testHasVotedGroup() {
		
		// Check if a group that hasn't voted has voted
		Group testGroup = new Group("Minecraft Homies", user);
		assertFalse(proj.hasVoted(testGroup));
		
		// Now it votes and we check again
		proj.vote(testGroup);
		assertTrue(proj.hasVoted(testGroup));
		
	}

}
