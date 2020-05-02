package application.vote;

import java.io.Serializable;
import java.util.*;

/**
 * Abstract class that represents a vote for a project
 * 
 * @author  Álvaro Castillo García
 * @author Alejandro Benimeli
 * @author Miguel Álvarez Valiente
 *
 */
public abstract class Vote implements Serializable {
	
	/**
	 * Serializable version
	 */
	private static final long serialVersionUID = 1L;
	
	public abstract List<String> getVoters();

}
