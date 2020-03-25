package ads;

import java.io.Serializable;
import java.util.*;

/**
 * Class that represents a vote from a whole project
 * 
 * @author Álvaro Castillo García
 * @author Alejandro Benimeli
 * @author Miguel Álvarez Valiente
 * 
 */
public class GroupVote extends Vote
					   implements Serializable {
	
	/**
	 * Group that voted for the project
	 */
	private Group voterGroup;
	
	/**
	 * Version for serialize objects
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor of GroupVote
	 * 
	 * @param voterGroup Group that votes
	 */
	public GroupVote(Group voterGroup) {
		this.voterGroup = voterGroup;
	}
	
	/**
	 * Returns a list of string with the username of the voter/voters inside of the group
	 * 
	 * @return list with username of voter/s inside of the group
	 */
	@Override
	public List<String> getVoters() {
		List<String> voters = new ArrayList<String>();
		for (RegisteredUser u: voterGroup.getAllSubgroupsMembers()) {
			voters.add(u.getUsername());
		}
		return voters;
	}

	/**
	 * Returns the group that votes
	 * 
	 * @return voterGroup
	 */
	public Group getGroup() {
		return voterGroup;
	}
	
	/**
	 * toString method override
	 */
	@Override
	public String toString() {
		return "Group vote by: " + voterGroup.getName();
	}

}
