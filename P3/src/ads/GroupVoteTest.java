package ads;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.Test;

/**
* This is the tester for the GroupVote class
* 
* @author Miguel Álvarez Valiente, Alejandro Benimeli Miranda, Álvaro Castillo García
*/
public class GroupVoteTest {

	/**
	 * Test method for {@link ads.GroupVote#getVoters()}.
	 */
	@Test
	public void testGetVoters() {
		
		// We define 3 users
		RegisteredUser u1 = new RegisteredUser("12345678Y", "Raul", "1234");
		RegisteredUser u2 = new RegisteredUser("12121212N", "Gonzalo", "1234");
		RegisteredUser u3 = new RegisteredUser("00000000A", "Pepe", "1234");
		
		// We create a group
		Group g = new Group("Parent group", u1); // Parent group created by u1
		g.acceptGroup(); // Needed to create subgroups

		
		// add users to subgroups
		g.addUser(u2);	// U2 in two subgroups should count as only one vote
		g.addUser(u2);
		g.addUser(u3);
		// u1 votes implicitly as well
		
		// Creates a group vote with the parent group (which includes its subgroups)
		GroupVote gVote = new GroupVote(g);

		List<String> usersList = Arrays.asList(u1.getUsername(), u2.getUsername(), u3.getUsername());
		Collections.sort(usersList);
		
		// Check that theres 3 votes and not 4
		assertEquals(3, gVote.getVoters().size());
		
		// Check that the usernames of the voters are correct
		List<String> result = gVote.getVoters();
		Collections.sort(result);
		
		assertEquals(usersList, result);
	
	}

}
