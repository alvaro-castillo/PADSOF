package ads;

import java.time.*;

/**
* These are the functions and variables that define the Notification objects, the Notification class.
*
* @author Miguel Álvarez Valiente, Alejandro Benimeli Miranda, Álvaro Castillo García
*/
public class Notification {
	private String content;
	private LocalDate date;
	private boolean read;
	
	/**
	 * Constructor of this class.
	 * @param content of the notification.
	 * @param date of the creation.
	 */
	public Notification(String content, LocalDate date) {
		this.content = content;
		this.date = date;
		this.read = false;
	}
	/**
	 * Notification message getter.
	 * @return content
	 */
	public String getContent() {
		return content;
	}
	/**
	 * Notification message setter.
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * Notification read getter.
	 * @return boolean value that indicates if the notification has been read or not.
	 */
	public boolean isRead() {
		return read;
	}
	/**
	 * Notification read setter to true.
	 */
	public void setRead() {
		this.read = true;
	}
	/**
	 * Notification Date getter.
	 * @return Date
	 */
	public LocalDate getDate() {
		return this.date;
	}
}
