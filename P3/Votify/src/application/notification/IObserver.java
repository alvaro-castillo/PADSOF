package application.notification;

/**
 * Interface that represents an observer waiting to be notified when a subject changes
 * 
 * @author Álvaro Castillo García
 * @author Alejandro Benimeli
 * @author Miguel Álvarez Valiente
 */
public interface IObserver {
	
	/**
	 * Updates something in the class that implements it (n of votes, notifications, ...)
	 * @param n Notification that is added to the list of notifications or null if not needed
	 */
	boolean update(Notification n);

}
