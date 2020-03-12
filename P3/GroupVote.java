package ads;

import java.util.ArrayList;

/**
 * Class that represents a vote from a whole project
 * 
 * @author Álvaro Castillo García
 * @author Alejandro Benimeli
 * @author Miguel Álvarez Valiente
 * 
 */
public class GroupVote {
	
	private Group voterGroup;
	
	public GroupVote(Group voterGroup) {
		this.voterGroup = voterGroup;
	}
	
	public List<String> getVoters() {
		List<String> voters = new ArrayList<String>();
		for (User u: voterGroup.getMembers()) {
			voters.add(voter.getName());
		}
		return voters;
	}

}
