package userInterface.projectScreen;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.*;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import application.enums.ProjectStatus;
import application.group.Group;
import application.project.InfrastructureProject;
import application.project.Project;
import application.registeredUser.RegisteredUser;

/**
 * This JPanel has the common Project user interface elements. Will be used by all the social and infrastructure panels
 * 
 * @author Álvaro Castillo García
 * @author Alejandro Benimeli
 * @author Miguel Álvarez Valiente
 *
 */
public class ProjectPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private JLabel projectName;
	private JPanel namePanel = new JPanel();
	private JLabel projectType;
	private JLabel status;
	private JLabel moneyAsked;
	private JLabel moneyGranted;
	private JPanel typePanel = new JPanel();
	private JTextArea projectDescription;
	private JScrollPane descrPanel;
	private JPanel votePanel = new JPanel();
	private ButtonGroup radioGroup = new ButtonGroup();

	private JRadioButton indivVote = new JRadioButton("Vote for myself");
	private JRadioButton groupVote = new JRadioButton("Vote for my group");

	private List<Group> groups = new ArrayList<>();
	private JComboBox<String> userGroups;
	private JButton voteButton = new JButton("Vote");
	private JButton popularityReportButton = new JButton("Create Popularity Report");
	private JButton notifButton;
	private JPanel bottomPanel = new JPanel();
	private JPanel popularityAndNotif = new JPanel();
	private JPanel voteButtonPanel = new JPanel();
	private JButton sendButton = new JButton("Send Project");
	
	private ProjectController controller;
	
	/**
	 * Constructor
	 * 
	 * @param proj Project that is shown in the panel
	 * @param reg User registered currently in the application
	 */
	public ProjectPanel(Project proj, RegisteredUser reg) {
		
		// Sets the controller
		this.controller = new ProjectController(this, proj);
		
		// In this cases you cant vote any more
		if (proj.getState() == ProjectStatus.EXPIRED || proj.getState() == ProjectStatus.APPROVED || proj.getState() == ProjectStatus.APPROVED || proj.getState() == ProjectStatus.PENDING) {
			voteButton.setEnabled(false);
		}
		
		// Set the component data
		this.projectName = new JLabel(proj.getTitle());
		if (proj instanceof InfrastructureProject) {
			projectType = new JLabel("(Infrastructure)");
		} else {
			projectType = new JLabel("(Social)");
		}
		
		// Shows different funding information depending on the state of the project
		moneyAsked = new JLabel("Asked: " + String.format("%.2f", proj.getAmount()) + "euros");
		if (proj.getGrantedAmount() != -1) {
			moneyGranted = new JLabel("Granted: " + String.format("%.2f", proj.getGrantedAmount()) + "euros");
		} else {
			moneyGranted = new JLabel();
		}
		
		// Shows the get/dont get notifications depending on if the user already has them activated
		if (reg.equals(proj.getCreator())) {
			notifButton = new JButton("You are the creator");
			notifButton.setEnabled(false);
		} else if (proj.getObservers().contains(reg)) {
			notifButton = new JButton("Stop Notifications");
		} else {
			notifButton = new JButton("Get Notifications");
		}
		
		// Initialize other components
		this.status = new JLabel(proj.getState().toString());
		this.projectDescription = new JTextArea(proj.getDescription(), 6, 40);
		this.descrPanel = new JScrollPane(projectDescription);
		
		// Only show popularity report if you have voted and dont show individual vote if the user already individually voted
		if (proj.hasVoted(reg)) {
			indivVote.setEnabled(false);
		} else {
			popularityReportButton.setEnabled(false);
		}
		
		// Radio button group with individual vote and group vote
		radioGroup.add(indivVote);
		radioGroup.add(groupVote);

		// Combo box with groups you can vote for
		for (Group g: reg.getCreatedGroups()) {
			if (proj.hasVoted(g) == false) {
				groups.add(g);
			}
		}
		String groupsArray[] = new String[groups.size()];
		for (int i=0; i<groups.size(); i++) {
			groupsArray[i] = groups.get(i).getName();
		}
		this.userGroups = new JComboBox<String>(groupsArray);
		
		// Initialize more components
		projectName.setFont(new Font(projectName.getFont().getName(), Font.PLAIN, 30));
        projectType.setFont(new Font(projectType.getFont().getName(), Font.PLAIN, 18));
        projectDescription.setLineWrap(true);
        projectDescription.setWrapStyleWord(true);
        projectDescription.setEditable(false);
        projectDescription.setFont(projectDescription.getFont().deriveFont(16f));

		// Set the layout and build the panel
    	this.setLayout(new GridBagLayout());
    	
        GridBagConstraints c = new GridBagConstraints(); 
        
        c.fill = GridBagConstraints.BOTH;
        c.anchor = GridBagConstraints.CENTER;
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.gridwidth = 1;
        c.gridheight = 1;
        
        int xPos = 0;
        int yPos = 0;
        
        c.gridx = xPos;
        c.gridy = yPos;
        ++xPos;
        this.add(new JPanel(), c);
        
        c.gridx = xPos;
        c.gridy = yPos;
        ++xPos;
        namePanel.setLayout(new GridBagLayout());
        namePanel.add(projectName);
        this.add(namePanel, c);
        
        c.gridx = xPos;
        c.gridy = yPos;
        ++xPos;
        typePanel.setLayout(new BoxLayout(typePanel, BoxLayout.Y_AXIS));
        typePanel.add(projectType);
        typePanel.add(status);
        typePanel.add(moneyAsked);
        typePanel.add(moneyGranted);
        this.add(typePanel, c);
        
        c.gridx = xPos;
        c.gridy = yPos;
        ++xPos;
        this.add(new JPanel(), c);
        
        xPos = 0;
        yPos++;
        
        c.gridwidth = 4;
        c.gridheight = 3;
        c.gridx = xPos;
        c.gridy = yPos; 
        this.add(descrPanel, c);
        
        votePanel.setLayout(new GridLayout(2,2));
        votePanel.add(groupVote);
        votePanel.add(userGroups);
        votePanel.add(indivVote);
        votePanel.add(new JPanel());
        
        
        popularityAndNotif.setLayout(new GridLayout(1,2));
        popularityAndNotif.add(popularityReportButton);
        popularityAndNotif.add(notifButton);
        
        
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));
        bottomPanel.add(votePanel);
        
        voteButtonPanel.add(voteButton);
        bottomPanel.add(voteButtonPanel);
        
        bottomPanel.add(popularityAndNotif);
        
        if ( (proj.getState() == ProjectStatus.ADMIN_ACCEPTED) && (proj.getActualVotes() >= proj.getMinimumVotes()) && (proj.getRequestId() == null) && (proj.getCreator().equals(reg))) {
        	bottomPanel.add(sendButton);
        }

        c.gridx = 1;
        c.gridy = 4; 
        c.gridwidth = 2;
        c.gridheight = 1;
        this.add(bottomPanel, c);
        
        voteButton.addActionListener(event -> controller.voteButtonPressed(event));
        popularityReportButton.addActionListener(event -> controller.createPopularityReport(event));
        notifButton.addActionListener(event -> controller.notifButtonPressed(event));
        sendButton.addActionListener(event -> controller.sendButtonPressed(event));
        
	}
	
	/**
	 * Returns information about which radio button was selected
	 * @return true if indivVote is selected, false if groupVote and null if none is selected
	 */
	public Boolean getVoteType() {
		if (indivVote.isSelected()) {
			return true;
		} else if (groupVote.isSelected()) {
			return false;
		} else {
			return null;
		}
	}
	
	/**
	 * Gets the group name selected in the combo box
	 * @return group name
	 */
	public String getSelectedGroup() {
		return (String)userGroups.getSelectedItem();
	}
	
	/**
	 * Enables the popularity report button
	 */
	public void activatePopularityReportButton() {
		this.popularityReportButton.setEnabled(true);
	}
	
	/**
	 * Sets the notification button text
	 * @param s string that we change the button to
	 */
	public void setNotifButton(String s) {
		notifButton.setText(s);
	}
	
	/**
	 * Disables the sendButton
	 */
	public void disableSendButton() {
		sendButton.setEnabled(false);
	}

}
