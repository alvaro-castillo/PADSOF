package ads;

import java.util.ArrayList;

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
