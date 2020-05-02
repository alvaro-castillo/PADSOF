package userInterface.createProjectScreens;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

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
import userInterface.administrator.feed.AdminFeedPanel;
import userInterface.userFeed.UserFeedPanel;

public class CreateProjectController {
	
	public CP1Controller cp1C = null;
	public CP2Controller cp2C = null;
	public CIPController cipC = null;
	public CSPController cspC = null;
	
	private CreateProjectPanel1 cp1;
	private CreateProjectPanel2 cp2;
	private CreateInfrastructureProjectPanel cip;
	private CreateSocialProjectPanel csp;
	
	private Application app;
	private JFrame frame;
	
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
	 * @param cp1 the panel that is controlled by this class
	 */
	public CreateProjectController(CreateProjectPanel1 cp1) {
		this.cp1 = cp1;
		this.cp1C = new CP1Controller(this);
		this.app = Application.getApplication();
		this.frame = AppFrame.getAppFrame();
		this.creator = app.getCurrentUser();
	}
	
	private class CP1Controller implements ActionListener {

		private CreateProjectController cpC;
		
		public CP1Controller(CreateProjectController cpC) {
			this.cpC = cpC;
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {

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
	
	private class CP2Controller implements ActionListener {
		
		private CreateProjectController cpC;
		
		public CP2Controller(CreateProjectController cpC) {
			this.cpC = cpC;
		}


		@Override
		public void actionPerformed(ActionEvent arg0) {
			
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
	
	private class CIPController implements ActionListener {
		
		public CIPController() { }

		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			if(cip.getPath()==null) {
				JOptionPane.showMessageDialog(cip, "Incomplete field: Image", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			File originalFile = new File(cip.getPath());
		    String path = cip.getPath();
		    String newPath = path.substring(path.lastIndexOf('/') + 1);
		    File newFile = new File(newPath);
			originalFile.renameTo(newFile);
			image = newPath;
			
			District districts[] = District.values(); 
			for(District d : districts) {
				if(cip.getDistrict().equals(d.getDistrictName())) {
					district = d;
				}
			}
			
			cip.setVisible(false);
			frame.remove(cip);
			
			if(individual) {
				InfrastructureProject iP = new InfrastructureProject(title, description, amount, creator, district, image); 
				app.addProject(iP);
				creator.addProject(iP);
			} else {
				InfrastructureProject iP = new InfrastructureProject(title, description, amount, group, district, image);
				app.addProject(iP);
				group.addProject(iP);
			}
			
			JPanel feed;
			
			if(creator.equals(app.getAdmin())) {
				feed = new AdminFeedPanel(creator.getUsername(),creator.getNotificationsMessages(), app.getRegisteredUserGroups(creator), app.getRegisteredUserVotes(creator));
			}else {
				feed = new UserFeedPanel(creator.getUsername(),creator.getNotificationsMessages(), app.getRegisteredUserGroups(creator),  app.getRegisteredUserVotes(creator));
			}
			
			frame.add(feed);
		}
		
	}
	
	private class CSPController implements ActionListener {
		
		public CSPController() { }			

		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			if(csp.getSocialGroup().length()==0) {
				JOptionPane.showMessageDialog(csp, "Incomplete field: Associated Social Group", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			if(csp.getNatOrInter()==null) {
				JOptionPane.showMessageDialog(csp, "National or international must be selected", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			if(csp.getDescription().length()>50) {
				JOptionPane.showMessageDialog(csp, "The description must be 500 characters max.", "Error", JOptionPane.ERROR_MESSAGE);
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
			
			csp.setVisible(false);
			frame.remove(csp);
			
			if (individual) {
				SocialProject sP = new SocialProject(title, description, amount, creator, socialGroup, national, socialGroupDescription);
				app.addProject(sP);
				creator.addProject(sP);
			} else {
				SocialProject sP = new SocialProject(title, description, amount, group, socialGroup, national, socialGroupDescription);
				app.addProject(sP);
				group.addProject(sP);
			}
			
			
			JPanel feed;
			
			if(creator.equals(app.getAdmin())) {
				feed = new AdminFeedPanel(creator.getUsername(),creator.getNotificationsMessages(), app.getRegisteredUserGroups(creator), app.getRegisteredUserVotes(creator));
			}else {
				feed = new UserFeedPanel(creator.getUsername(),creator.getNotificationsMessages(), app.getRegisteredUserGroups(creator),  app.getRegisteredUserVotes(creator));
			}
			
			frame.add(feed);
			
		}
		
	}
	
	public CreateInfrastructureProjectPanel getCip() {
		return cip;
	}

}






















