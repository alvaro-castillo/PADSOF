package ads;

import java.util.ArrayList;

public class GroupVote {
	
	private Group voterGroup;
	
	public GroupVote(Group voterGroup) {
		this.voterGroup = voterGroup;
	}
	
	public ArrayList<String> getVoters() {
		ArrayList<String> voters = new ArrayList<String>();
		for (User u: voterGroup.getMembers()) {
			voters.add(voter.getName());
		}
		return voters;
	}

}
