package testers;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import application.group.Group;
import application.registeredUser.RegisteredUser;
import application.enums.District;
import application.project.Project;
import application.project.InfrastructureProject;
import application.project.SocialProject;

/**
 * @author Miguel Álvarez Valiente
 * @author Alejandro Benimeli Miranda
 * @author Álvaro Castillo García 
 *
 */
public class GroupTest {
	
	private RegisteredUser u1, u2;
	private Group g1;
	private InfrastructureProject ip1;
	
	
	/*
	 * Some classes needed for the tests are instantiated before each one
	 */
	@Before
	public void setUp() {
		u1 = new RegisteredUser("1", "User1", "Hola123");
		u2 = new RegisteredUser("2", "User2", "Hola123");
		g1 = new Group("Group1", u1);
		ip1 = new InfrastructureProject("Project1", "This is Project1", 20.5, u1, District.BARAJAS, "Imagen1");
		g1.acceptGroup();
	}

	/**
	 * Test method for {@link application.group.Group#createSubgroup(java.lang.String, application.RegisteredUser)}.
	 */
	@Test
	public void testCreateSubgroup() {
		
		Group g1_2 = null;
		
		//We check it wont work with a null string for name
		assertNull(g1.createSubgroup(null, u1));
		//We check it wont work with a null representative
		assertNull(g1.createSubgroup("Group1.2", null));
		//We check it wont work with a representative that is not the one of the parent group
		assertNull(g1.createSubgroup("Group1.2", u2));
		//We check it will work with correct inputs
		assertNotNull(g1_2 = g1.createSubgroup("Group1.2", u1));
		//We check if it indeed worked
		assertTrue(g1.getSubgroups().contains(g1_2));
		
	}

	/**
	 * Test method for {@link application.group.Group#addUser(application.RegisteredUser)}.
	 */
	@Test
	public void testAddUser() {
		
		Group g1_1=null, g1_2=null;
		g1_1 = g1.createSubgroup("Group1.1", u1);
		g1_1.acceptGroup();
		g1_2 = g1_1.createSubgroup("Group1.2", u1);
		g1_2.acceptGroup();
		
		//We check it wont work with a null user
		assertFalse(g1.addUser(null));
		//We check it wont work with a user that is already on the group
		assertFalse(g1.addUser(u1));
		//We check it will work with a correct input
		assertTrue(g1_1.addUser(u2));
		//We check if it indeed worked
		assertTrue(g1_1.getMembers().contains(u2));
		//We check it wont work with a user that is already on a child group
		assertFalse(g1.addUser(u2));
		//We check it wont work with a user that is already on a parent group
		assertFalse(g1_2.addUser(u2));

	}

	/**
	 * Test method for {@link application.group.Group#deleteUser(application.RegisteredUser)}. 
	 */
	@Test
	public void testDeleteUser() {
		
		//We check it wont work with a null user
		assertFalse(g1.deleteUser(null));
		//We check it wont work with a user that was not on the group
		assertFalse(g1.deleteUser(u2));
		//We check it will work with a correct input
		assertTrue(g1.deleteUser(u1));
		//We check if it indeed worked
		assertNull(g1.getRepresentative());
		
	}

	/**
	 * Test method for {@link application.group.Group#addProject(application.Project)}.
	 */
	@Test
	public void testAddProject() {
		
		//We check it wont work with a null project
		assertFalse(g1.addProject(null));
		//We check it will work with a correct input
		assertTrue(g1.addProject(ip1));
		//We check if it indeed worked
		assertTrue(g1.getCreatedProjects().contains(ip1));
		//We check it wont work if the project was already on the group
		assertFalse(g1.addProject(ip1));
		
	}

	/**
	 * Test method for {@link application.group.Group#equals(java.lang.Object)}.
	 */
	@Test
	public void testEqualsObject() {
		
		Group g2 = new Group("Group2", u1);
		Group g3 = new Group("Group1", u1);
		g2.acceptGroup();
		g3.acceptGroup();
		
		//We check it wont work with a null group
		assertFalse(g1.equals(null));
		//We check it will work with an equal group(itself)
		assertTrue(g1.equals(g1));
		//We check it wont work with a different group
		assertFalse(g1.equals(g2));
		//We check it works with an equal group(equal name)
		assertTrue(g1.equals(g3));
	}
	
	/**
	 * Test method for {@link application.group.Group#createAffinityReport(application.Group)}.
	 */
	@Test
	public void testCreateAffinityReport() {
		
		Group g2 = new Group("Group2", u1);
		g2.acceptGroup();
		Project sp1 = new SocialProject("Project2", "Description1", 32, u1, "Association1", true, "AssociationDescription1");
		
		//We check the report is equal -1 in case it receives a null group
		assertEquals(g1.createAffinityReport(null),g2.createAffinityReport(null),-1);
		//We check the report is equal 0 in case none of the groups have created a project
		assertEquals(g1.createAffinityReport(g2),g2.createAffinityReport(g1),0);
		
		g1.addProject(ip1);
		g2.addProject(sp1);
		
		//We check the report is equal 0 if none of the groups has voted for a project from the other
		assertEquals(g1.createAffinityReport(g2),g2.createAffinityReport(g1),0);
		
		ip1.vote(g2);
		
		//We check the report is equal 0.5 if a project from one of the groups has been voted by the other, and they have 2 groups
		//between the two of them
		assertEquals(g1.createAffinityReport(g2),g2.createAffinityReport(g1),0.5);
		
		sp1.vote(g1);
		
		//We check the report is equal 1 if a project from each group has been voted by the other, and they have 2 groups
		//between the two of them
		assertEquals(g1.createAffinityReport(g2),g2.createAffinityReport(g1),1);
		
	}
	
	/**
	 * Test method for {@link application.group.Group#getAllSubgroupsMembers()}.
	 */
	@Test
	public void testGetAllSubgroupsMembers() {
		
		Group g1_1 = g1.createSubgroup("Group1.1", u1);
		Group g1_2 = g1.createSubgroup("Group1.2", u1);
		Group g1_1_1 = g1.createSubgroup("Group1.1.1", u1);
		g1_1.acceptGroup();
		g1_2.acceptGroup();
		g1_1_1.acceptGroup();
		RegisteredUser u3 = new RegisteredUser("3", "User3", "Hola123");
		RegisteredUser u4 = new RegisteredUser("4", "User4", "Hola123");
		g1_1.addUser(u2);
		g1_2.addUser(u3);
		g1_1_1.addUser(u4);
		
		//We check that all members from all the subgroups are on the list returned
		assertTrue(g1.getAllSubgroupsMembers().contains(u1));
		assertTrue(g1.getAllSubgroupsMembers().contains(u2));
		assertTrue(g1.getAllSubgroupsMembers().contains(u3));
		assertTrue(g1.getAllSubgroupsMembers().contains(u4));

	}

}
