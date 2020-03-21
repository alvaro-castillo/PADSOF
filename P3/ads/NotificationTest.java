package ads;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.Month;

import org.junit.Before;
import org.junit.Test;

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
		n = new Notification("Hello", LocalDate.now());
	}

	/**
	 * Test method for {@link ads.Notification#equals(java.lang.Object)}.
	 */
	@Test
	public void testEqualsObject() {
		Notification n2 = new Notification("Hello", LocalDate.of(2019, Month.JANUARY, 1));
		Notification n3 = new Notification("Hi", LocalDate.now());
		RegisteredUser u = new RegisteredUser("12345678A","User1", "aaaabb");
		
		//Null object
		assertFalse(n.equals(null));
		//Notification with the same data
		assertTrue(n.equals(n));
		//Object of different type
		assertFalse(n.equals(u));
		//Notification with same message
		assertTrue(n.equals(n2));
		//Notification with same Date
		assertTrue(n.equals(n3));

	}

}
