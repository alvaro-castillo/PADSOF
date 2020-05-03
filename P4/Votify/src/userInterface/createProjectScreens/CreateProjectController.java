package userInterface.createProjectScreens;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import application.Application;
import application.enums.District;
import application.group.Group;
import application.project.InfrastructureProject;
import application.project.SocialProject;
import application.registeredUser.RegisteredUser;
import userInterface.AppFrame;
import userInterface.userFeed.UserFeedPanel;

/**
 * This is a class to contain and manage the different controllers in charged 
 * of the different panels that have to do with the creation of a project
 * 
 * @author Miguel Álvarez Valiente, Alejandro Benimeli Miranda, Álvaro Castillo García
 *
 */
public class CreateProjectController {
	
	/**
	 * References to all the controllers that the class manages. Each of them is
	 * instantiated when the corresponding panel is instantiated too
	 */
	public CP1Controller cp1C;
	public CP2Controller cp2C;
	public CIPController cipC;
	public CSPController cspC;
	
	/**
	 * References to all the panels managed by the different controllers. Each of
	 * them is instantiated when the one before it has ended its function
	 */
	private CreateProjectPanel1 cp1;
	private CreateProjectPanel2 cp2;
	private CreateInfrastructureProjectPanel cip;
	private CreateSocialProjectPanel csp;
	
	/**
	 * Reference to Application
	 */
	private Application app;
	/**
	 * Reference to the main frame
	 */
	private JFrame frame;
	
	/**
	 * All the fields that will be filled in with the data of the project 
	 */
	private String title;
	private String description;
	private double amount;
	private RegisteredUser creator;
	private Group group; 
	
	private District district;
	private String image;
	
	public String socialGroup;
	private boolean national;
	private String socialGroupDescription;
	
	private boolean individual;

	/**
	 * Constructor of this class.
	 * 
	 * @param cp1 the first panel of all the create project screens
	 */
	public CreateProjectController(CreateProjectPanel1 cp1) {
		this.cp1 = cp1;
		this.cp1C = new CP1Controller(this);
		this.app = Application.getApplication();
		this.frame = AppFrame.getAppFrame();
		this.creator = app.getCurrentUser();
	}
	
	
	/**
	 * Controller of the first panel to create project
	 *
	 */
	private class CP1Controller implements ActionListener {

		/**
		 * Reference to the container class
		 */
		private CreateProjectController cpC;
		
		/**
		 * Constructor of this class 
		 * 
		 * @param cpC Reference to the container class
		 */
		public CP1Controller(CreateProjectController cpC) {
			this.cpC = cpC;
		}
		
		/**
		 * Method that will be performed when the "next" button is pressed on the
		 * CreateProjectPanel1 screen.
		 * It collects all the fields from this screen and checks for errors
		 * 
		 * @param e the event caused by an action
		 */
		@Override
		public void actionPerformed(ActionEvent e) {

			if(cp1.getTitle().length()>25) {
				JOptionPane.showMessageDialog(cp1, "The title must be 25 characters max.", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			} else if(cp1.getTitle().length()==0) {
				JOptionPane.showMessageDialog(cp1, "Incomplete field: Title", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			if(cp1.getDescription().length()>500) {
				JOptionPane.showMessageDialog(cp1, "The description must be 500 characters max.", "Error", JOptionPane.ERROR_MESSAGE);
				System.out.println("Error 2");
				return;
			} else if(cp1.getDescription().length()==0) {
				JOptionPane.showMessageDialog(cp1, "Incomplete field: Description", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			try {
				if(cp1.getMoney().length()==0) {
					JOptionPane.showMessageDialog(cp1, "Incomplete field: Money", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				} else {
					amount = Double.parseDouble(cp1.getMoney());
				}
			} catch(NumberFormatException exception) {
				JOptionPane.showMessageDialog(cp1, "Money invalid value", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			title = cp1.getTitle();
			description = cp1.getDescription();
			
			cp1.setVisible(false);
			cp2C = new CP2Controller(cpC);
			cp2 = new CreateProjectPanel2(cpC, creator);
			frame.add(cp2);
			frame.remove(cp1);
			cp2.setVisible(true);
			
		}
	
	}
	
	/**
	 * Controller of the second panel to create project
	 *
	 */
	private class CP2Controller implements ActionListener {
		
		/**
		 * Reference to the container class
		 */
		private CreateProjectController cpC;
		
		/**
		 * Constructor of this class 
		 * 
		 * @param cpC Reference to the container class
		 */
		public CP2Controller(CreateProjectController cpC) {
			this.cpC = cpC;
		}

		/**
		 * Method that will be performed when the "next" button is pressed on the
		 * CreateProjectPanel2 screen.
		 * It collects all the fields from this screen and checks for errors
		 * 
		 * @param e the event caused by an action
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			
			if(cp2.getType()==null) {
				JOptionPane.showMessageDialog(cp2, "A project type must be selected", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			if(cp2.getIndOrGroup()==null) {
				JOptionPane.showMessageDialog(cp2, "A way to create the project must be selected", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			if(cp2.getIndOrGroup().equals("Individual")) {
				individual = true;
			} else if(cp2.getIndOrGroup().equals("Group")) {
				individual = false;
				for (Group g : creator.getCreatedGroups()) {
					if (g.getName().equals(cp2.getGroup())) {
						group = g;
					}
				}
			}
			
			if(cp2.getType().equals("Infrastructure")) {
				cp2.setVisible(false);
				cipC = new CIPController();
				cip = new CreateInfrastructureProjectPanel(cpC);
				frame.add(cip);
				frame.remove(cp2);
				cip.setVisible(true);
			} else if(cp2.getType().equals("Social")) {
				cp2.setVisible(false);
				cspC = new CSPController();
				csp = new CreateSocialProjectPanel(cpC);
				frame.add(csp);
				frame.remove(cp2);
				csp.setVisible(true);
			}			
		}
		
	}
	
	
	/**
	 * Controller of the panel to create infrastructure project
	 *
	 */
	private class CIPController implements ActionListener {
		
		/**
		 * Constructor of this class 
		 * 
		 * @param cpC Reference to the container class
		 */
		public CIPController(){}
		
		/**
		 * Method that will be performed when the "create" button is pressed on the
		 * CreateInfrastructreProjectPanel screen.
		 * It collects all the fields from this screen and checks for errors
		 * 
		 * @param e the event caused by an action
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			
			if(cip.getPath()==null) {
				JOptionPane.showMessageDialog(cip, "Incomplete field: Image", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			image = cip.getPath();
			
			District districts[] = District.values(); 
			for(District d : districts) {
				if(cip.getDistrict().equals(d.getDistrictName())) {
					district = d;
				}
			}
			
			if(individual) {
				InfrastructureProject iP = new InfrastructureProject(title, description, amount, creator, district, image);
				app.addProject(iP);
			} else {
				InfrastructureProject iP = new InfrastructureProject(title, description, amount, group, district, image);
				app.addProject(iP);
			}
			JOptionPane.showMessageDialog(cip, "Infrastructure Project " + title +  " has been created.", "Success", JOptionPane.INFORMATION_MESSAGE);
			JPanel p =  new UserFeedPanel(app.getCurrentUser().getUsername(),app.getCurrentUser().getNotificationsMessages(), app.getRegisteredUserGroups(app.getCurrentUser()),  app.getRegisteredUserVotes(app.getCurrentUser()));
			frame.add(p);
			cip.setVisible(false);
			frame.remove(cip);
			p.setVisible(true);
			
		}
		
	}
	
	
	/**
	 * Controller of the panel to create social project
	 *
	 */
	private class CSPController implements ActionListener {
		
		public CSPController(){}			
		
		/**
		 * Method that will be performed when the "next" button is pressed on the
		 * CreateSocialProjectPanel screen.
		 * It collects all the fields from this screen and checks for errors
		 * 
		 * @param e the event caused by an action
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			
			if(csp.getSocialGroup().length()==0) {
				JOptionPane.showMessageDialog(csp, "Incomplete field: Associated Social Group", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			if(csp.getNatOrInter()==null) {
				JOptionPane.showMessageDialog(csp, "National or international must be selected", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			if(csp.getDescription().length()>50) {
				JOptionPane.showMessageDialog(csp, "The description must be 50 characters max.", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			} else if (csp.getDescription().length()==0){
				JOptionPane.showMessageDialog(csp, "Incomplete field: Description", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			socialGroup = csp.getSocialGroup();
			
			if(csp.getNatOrInter().equals("National")) {
				national = true;
			} else if(csp.getNatOrInter().equals("International")) {
				national = false;
			}
			
			socialGroupDescription = csp.getDescription();
			
			if (individual) {
				SocialProject sP = new SocialProject(title, description, amount, creator, socialGroup, national, socialGroupDescription);
				app.addProject(sP);
			} else {
				SocialProject sP = new SocialProject(title, description, amount, group, socialGroup, national, socialGroupDescription);
				app.addProject(sP);
			}
			
			JOptionPane.showMessageDialog(csp, "Social Project " + title +  " has been created.", "Success", JOptionPane.INFORMATION_MESSAGE);
			JPanel p =  new UserFeedPanel(app.getCurrentUser().getUsername(),app.getCurrentUser().getNotificationsMessages(), app.getRegisteredUserGroups(app.getCurrentUser()),  app.getRegisteredUserVotes(app.getCurrentUser()));
			frame.add(p);
			csp.setVisible(false);
			frame.remove(csp);
			p.setVisible(true);
		}
		
	}
	
	/**
	 * Getter for the CreateInfrastructureProjectPanel
	 * 
	 * @return cip CreateInfrastructureProjectPanel
	 */
	public CreateInfrastructureProjectPanel getCip() {
		return cip;
	}

}






















