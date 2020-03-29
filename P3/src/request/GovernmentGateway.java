package request;

import java.io.IOException;

import application.project.*;
import es.uam.eps.sadp.grants.*;
import application.enums.*;
import application.project.Project;
/**
 * Class used to communicate with the external entity
 * 
 * @author Álvaro Castillo García
 * @author Alejandro Benimeli
 * @author Miguel Álvarez Valiente
 *
 */
public class GovernmentGateway {
	
	/**
	 * Only instance of the class
	 */
	private static GovernmentGateway instance = null;
	
	/**
	 * Constructor
	 */
	private GovernmentGateway() {};
	
	/**
	 * Returns the only instance of the class
	 * @return instance of GovernmentGateway
	 */
	public static GovernmentGateway getInstance() {
		if (instance == null) {
			instance = new GovernmentGateway();
		}
		return instance;
	}

	/**
	 * Sends a project to the entity. The project needs to have at least the
	 * minimum required number of votes. The project needs to be accepted by
	 * the admin and also needs to have at least the minimum number of votes
	 * needed. This method automatically sets the state to PENDING if sent correctly
	 * @param p Project you submit
	 * @return boolean. true if the submission was correct, false otherwise
	 * @throws IOException 
	 * @throws InvalidRequestException 
	 */
	public boolean sendProject(Project p) throws IOException, InvalidRequestException {
		
		GrantRequest request;
		String id;
		
		// Has already been sent
		if (p.getRequestId() != null) {
			return false;
		}
		
		if ((p.getState() != ProjectStatus.ADMIN_ACCEPTED) || (p.getActualVotes() < p.getMinimumVotes())) {
			return false;
		}
		
		if (p instanceof SocialProject) {
			request = new SocialGrantRequest(p);
		} else if (p instanceof InfrastructureProject) {
			request = new InfrastructureGrantRequest(p);
		} else {
			return false;
		}
		
		try {
			id = CCGG.getGateway().submitRequest(request);
		} catch (IOException e) {
			throw e;
		} catch (InvalidRequestException e) {
			throw e;
		}
		
		p.setRequestId(id);
		p.pendingProject();
		
		return true;
	}
	
	/**
	 * Checks the status of a project and updates said project if the request
	 * was answered
	 * @param p Project you want to check the status of the request
	 * @return ProjectStatus (APPROVED, REJECTED or PENDING) or null if any error occurred
	 * @throws IOException 
	 * @throws InvalidIDException 
	 */
	public ProjectStatus checkProject(Project p) throws IOException, InvalidIDException {
		
		Double d;
		
		if (p.getRequestId() == null) {
			return null;
		}
		
		try {
			d = CCGG.getGateway().getAmountGranted(p.getRequestId());
		} catch (IOException e) {
			throw e;
		} catch (InvalidIDException e) {
			throw e;
		}
		
		if (d == null) {
			return ProjectStatus.PENDING;
		} else if (d == 0.0) {
			p.rejectProject();
			p.setGrantedAmount(d);
			return ProjectStatus.REJECTED;
		} else {
			p.approveProject();
			p.setGrantedAmount(d);
			return ProjectStatus.APPROVED;	
		}
		
	}
	
	
	
}
