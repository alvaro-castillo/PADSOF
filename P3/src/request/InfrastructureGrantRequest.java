package request;

import application.project.InfrastructureProject;
import application.project.Project;
import es.uam.eps.sadp.grants.GrantRequest;

/**
 * Class that represents grant requests for infrastructure projects
 * 
 * @author Álvaro Castillo García
 * @author Alejandro Benimeli
 * @author Miguel Álvarez Valiente
 *
 */
public class InfrastructureGrantRequest extends AbstractGrantRequest {

	/**
	 * Constructor
	 * @param p Project you want to send
	 */
	public InfrastructureGrantRequest(Project p) {
		super(p);
	}

	/**
	 * Returns extra data associated to Infrastructure Projects
	 * @return String containing the extra data
	 */
	@Override
	public String getExtraData() {
		return "District: " + ((InfrastructureProject)proj).getDistrict().getDistrictName()
				+ "\nImage: " + ((InfrastructureProject)proj).getImage();
	}

	/**
	 * Returns the kind of project
	 * @return ProjectKind
	 */
	@Override
	public ProjectKind getProjectKind() {
		return GrantRequest.ProjectKind.Infrastructure;
	}
	
	

}
