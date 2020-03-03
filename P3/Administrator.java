package ads;
/**
* These are the functions and variables that define the Administrator objects, the Administrator class.
*
* @author Miguel Álvarez Valiente, Alejandro Benimeli Miranda, Álvaro Castillo García
*/
public class Administrator extends RegisteredUser {
	/**
	    * Constructor of this class.
	    *
	    * @param id of the administrator.
	    * @param username of the administrator.
	    * @param password of the administrator.
	    */
	public Administrator(String id, String username, String password) {
		super(id, username, password);
	}

}
