package request;

import application.project.Project;
import es.uam.eps.sadp.grants.*;

/**
 * Abstract class that implements some of the methods of the GrantRequest interface
 * @author Miguel Álvarez Valiente, Alejandro Benimeli Miranda, Álvaro Castillo García
 */
public abstract class AbstractGrantRequest implements GrantRequest {
	
	/**
	 * Project associated to the request
	 */
	protected Project proj;

	/**
	 * Constructor
	 * @param p project of the grant request
	 */
	public AbstractGrantRequest(Project p) {
		this.proj = p;
	}

	/**
	 * Returns the description of the project of the grant request
	 * @return String containing the description
	 */
	@Override
	public String getProjectDescription() {
		return proj.getDescription();
	}

	/**
	 * Returns the title of the project of the grant request
	 * @return string containing the title
	 */
	@Override
	public String getProjectTitle() {
		return proj.getTitle();
	}

	/**
	 * Returns the amount requested
	 * @return amount requested
	 */
	@Override
	public double getRequestedAmount() {
		return proj.getAmount();
	}

}
