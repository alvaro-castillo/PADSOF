/**
 * 
 */
package testers;

import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;
import application.registeredUser.RegisteredUser;
import application.notification.Notification;
import modifiableDates.ModifiableDate;
/**
* This is the tester for the Notification class
* 
* @author Miguel Álvarez Valiente, Alejandro Benimeli Miranda, Álvaro Castillo García
*/
public class NotificationTest {
	private Notification n;
	
	/**
	 * The set up of the tester creates a new Notification
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		ModifiableDate.setToday();
		n = new Notification("Hello", ModifiableDate.getModifiableDate());
	}

	/**
	 * Test method for {@link application.notification.Notification#equals(java.lang.Object)}.
	 */
	@Test
	public void testEqualsObject() {

		Notification n3 = new Notification("Hi", ModifiableDate.getModifiableDate());
		RegisteredUser u = new RegisteredUser("12345678A","User1", "aaaabb");
		ModifiableDate.plusDays(5);
		Notification n2 = new Notification("Hello", ModifiableDate.getModifiableDate());
		//Null object
		assertFalse(n.equals(null));
		//Notification with the same data
		assertTrue(n.equals(n));
		//Object of different type
		assertFalse(n.equals(u));
		//Notification with same message but different date
		assertFalse(n.equals(n2));
		//Notification with same Date but different message
		assertFalse(n.equals(n3));
	}
}
