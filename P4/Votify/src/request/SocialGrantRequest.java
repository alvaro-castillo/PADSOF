package request;

import application.project.Project;
import application.project.SocialProject;
import es.uam.eps.sadp.grants.GrantRequest;

/**
 * Class that represents grant requests for social projects
 * 
 * @author Álvaro Castillo García
 * @author Alejandro Benimeli
 * @author Miguel Álvarez Valiente
 *
 */
public class SocialGrantRequest extends AbstractGrantRequest {

	/**
	 * Constructor
	 * @param p Project you want to send
	 */
	public SocialGrantRequest(Project p) {
		super(p);
	}


	/**
	 * Returns extra data associated to social projects
	 * @return String containing the extra data
	 */
	@Override
	public String getExtraData() {
		return "Association: " + ((SocialProject)proj).getAssociation() + "\nIs national: " 
				+ ((SocialProject)proj).getNational() + "\nDescription: " 
				+ ((SocialProject)proj).getDescription();
	}

	/**
	 * Returns the kind of project
	 * @return ProjectKind
	 */
	@Override
	public ProjectKind getProjectKind() {
		return GrantRequest.ProjectKind.Social;
	}

}
