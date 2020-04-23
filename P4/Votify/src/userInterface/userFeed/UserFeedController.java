package userInterface.userFeed;

import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;


/**
* This is the controller of the User Feed Panel.
*
* @author Miguel Álvarez Valiente, Alejandro Benimeli Miranda, Álvaro Castillo Garcí­a
*/
public class UserFeedController extends ListPanelExtender{
	
	/**
	 * Constructor of this class.
	 * 
	 * @param panel the panel that is controlled by this class
	 */
	public UserFeedController(JPanel panel) {
		super(panel);
	}
	
	/**
	 * This method will be invoked when the user selects an item from the list.
	 * In this case the list is for notifications.
	 * 
	 * @param e the event that generated the action
	 * @param paux the panel where the list is
	 **/
	@Override
	public void valueChangedNotifications(ListSelectionEvent e,  ListPanel paux) {
		//TODO: Realizar acciones con las notificaciones
	}
}
