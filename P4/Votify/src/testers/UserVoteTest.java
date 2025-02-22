package testers;

import static org.junit.Assert.*;

import org.junit.Test;

import application.vote.UserVote;

import java.util.*;
import application.registeredUser.RegisteredUser;
/**
* This is the tester for the UserVote class
* 
* @author Miguel Álvarez Valiente, Alejandro Benimeli Miranda, Álvaro Castillo García
*/
public class UserVoteTest {
	/**
	 * Test method for {@link application.vote.UserVote#getVoters()}.
	 */
	@Test
	public void testGetVoters() {
		
		RegisteredUser u = new RegisteredUser("12345678Y", "Default", "1234");
		UserVote uVote = new UserVote(u);
		List<String> usersList = new ArrayList<>();
		
		// Adds the username of the voter to the list
		usersList.add(u.getUsername());

		// Checks if the list with the voter's username is correct
		assertEquals(usersList, uVote.getVoters());
	
	}

}

