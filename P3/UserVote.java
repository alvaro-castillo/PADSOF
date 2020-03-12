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
public class UserVote {
	
	private User voter;
	
	public UserVote(User voter) {
		this.voter = voter;
	}
	
	public ArrayList<String> getVoters() {
		ArrayList<String> voters = new ArrayList<String>();
		voters.add(voter.getName());
		return voters;
	}

}
