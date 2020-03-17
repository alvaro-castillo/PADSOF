package ads;

import java.io.Serializable;
import java.util.*;

/**
 * Represents a user voting for a project
 * 
 * @author Álvaro Castillo García
 * @author Alejandro Benimeli
 * @author Miguel Álvarez Valienteº
 *
 */
public class UserVote extends Vote implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
	public List<String> getVoters() {
		ArrayList<String> voters = new ArrayList<String>();
		voters.add(voter.getUsername());
		return voters;
	}

}
