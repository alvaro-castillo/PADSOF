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
	
	private RegisteredUser voter;
	
	public UserVote(RegisteredUser voter) {
		this.voter = voter;
	}
	
	public ArrayList<String> getVoters() {
		ArrayList<String> voters = new ArrayList<String>();
		voters.add(voter.getUsername());
		return voters;
	}

}
