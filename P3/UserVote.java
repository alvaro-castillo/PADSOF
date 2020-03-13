package ads;

import java.util.ArrayList;

/**
 * Represents a user voting for a project
 * 
 * @author Álvaro Castillo García
 * @author Alejandro Benimeli
 * @author Miguel Álvarez Valienteº
 *
 */
public class UserVote extends Vote {
	
	/**
	 * The RegisteredUser that voted
	 */
	private RegisteredUser voter;
	
	/**
	 * Constructor of UserVote
	 * 
	 * @param voter RegisteredUser that voted
	 */
	public UserVote(RegisteredUser voter) {
		this.voter = voter;
	}
	
	/**
	 * Returns the user that voted
	 * 
	 * @return voter
	 */
	public RegisteredUser getUser() {
		return voter;
	}
	
	/**
	 * Returns a list of string with the username of the voter
	 * 
	 * @return list with username of voter
	 */
	@Override
	public ArrayList<String> getVoters() {
		ArrayList<String> voters = new ArrayList<String>();
		voters.add(voter.getUsername());
		return voters;
	}

}
