package testers;

import static org.junit.Assert.*;

import org.junit.Test;
import java.io.IOException;

import application.enums.ProjectStatus;
import application.project.Project;
import application.project.SocialProject;
import es.uam.eps.sadp.grants.CCGG;
import es.uam.eps.sadp.grants.InvalidIDException;
import es.uam.eps.sadp.grants.InvalidRequestException;
import modifiableDates.ModifiableDate;
import request.GovernmentGateway;
import application.registeredUser.RegisteredUser;

/**
* This is the tester for the GovernmentGateway class
* 
* @author Miguel Álvarez Valiente, Alejandro Benimeli Miranda, Álvaro Castillo García
*/
public class GovernmentGatewayTest {


	/**
	 * Test for {@link request.GovernmentGateway#getInstance()}
	 */
	@Test
	public void testGetInstance() {
		
		// Assure the function returns an instance of the class
		assertTrue(GovernmentGateway.getInstance() instanceof GovernmentGateway);
		
	}
	/**
	 * Test for {@link request.GovernmentGateway#sendProject(application.project.Project)}
	 * @throws IOException
	 * @throws InvalidRequestException
	 */
	@Test
	public void testSendProject() throws IOException, InvalidRequestException {
		
		Project p = new SocialProject("Title", "Description", 1234.56, 
				new RegisteredUser("a", "12345678h", "1234"),"Org", true, "Org description");
		
		// Try to send a project not approved by the admin
		assertFalse(GovernmentGateway.getInstance().sendProject(p));
		
		
		// Try to send a project without the minimum votes (Only has the vote of the creator)
		p.adminAcceptProject();
		p.setMinimumVotes(2);
		
		assertFalse(GovernmentGateway.getInstance().sendProject(p));
		
		// We add a vote to have the minimum amount of votes
		p.vote(new RegisteredUser("b", "00000000A", "password"));
		
		// We get an IOException. (We force the entity to give an IOException the next time we send a project)
		CCGG.getGateway().testIOException(1, true);
		
		try {
			GovernmentGateway.getInstance().sendProject(p);
			fail("Expected IOException to be thrown");
		} catch (IOException e) {
		}
		
		// We dont get an exception and the project meets the requirements
		CCGG.getGateway().testIOException(1, false);
		
		assertTrue(GovernmentGateway.getInstance().sendProject(p));
		
		// Try to send the same project a second time
		assertFalse(GovernmentGateway.getInstance().sendProject(p));
		
	}
	/**
	 * Test for {@link request.GovernmentGateway#checkProject(application.project.Project)}
	 * @throws IOException
	 * @throws InvalidRequestException
	 * @throws InvalidIDException
	 */
	@Test
	public void testCheckProject() throws IOException, InvalidRequestException, InvalidIDException {
		
		Project p = new SocialProject("Title", "Description", 1234.56, 
				new RegisteredUser("a", "12345678h", "1234"),"Org", true, "Org description");
		
		ModifiableDate.setToday();
		
		// Check the status of a project that wasn't sent
		assertNull(GovernmentGateway.getInstance().checkProject(p));
		
		//We send a project with all the requirements (Avoiding IOExceptions)
		p.adminAcceptProject();
		p.setMinimumVotes(1);
		
		CCGG.getGateway().testIOException(3, false);
		GovernmentGateway.getInstance().sendProject(p);
		
		// Check the status without waiting anything
		assertEquals(GovernmentGateway.getInstance().checkProject(p), ProjectStatus.PENDING);
		
		// Advance 10 days and check that is either Approved or Rejected
		ModifiableDate.plusDays(10);
		CCGG.getGateway().setDate(ModifiableDate.getModifiableDate());
		assertNotEquals(GovernmentGateway.getInstance().checkProject(p), ProjectStatus.PENDING);
		
	}
}
