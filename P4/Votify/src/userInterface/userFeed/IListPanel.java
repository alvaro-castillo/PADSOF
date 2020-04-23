package userInterface.userFeed;

import javax.swing.event.ListSelectionEvent;
/**
* This is the interface that a the ListPanelExtender should implement 
*
* @author Miguel Álvarez Valiente, Alejandro Benimeli Miranda, Álvaro Castillo Garcí­a
*/
public interface IListPanel {
	/**
	 * This method will be invoked when the user selects an item from the list.
	 * In this case the list is for groups.
	 * 
	 * @param e the event that generated the action
	 * @param paux the panel where the list is
	 **/
	public void valueChangedGroup(ListSelectionEvent e,  ListPanel paux);
	/**
	 * This method will be invoked when the user selects an item from the list.
	 * In this case the list is for projects.
	 * 
	 * @param e the event that generated the action
	 * @param paux the panel where the list is
	 **/
	public void valueChangedProject(ListSelectionEvent e,  ListPanel paux);
	/**
	 * This method will be invoked when the user selects an item from the list.
	 * In this case the list is for notifications.
	 * 
	 * @param e the event that generated the action
	 * @param paux the panel where the list is
	 **/
	public default void valueChangedNotifications(ListSelectionEvent e,  ListPanel paux) {return;}
}
