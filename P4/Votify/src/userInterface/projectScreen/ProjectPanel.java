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

import application.group.Group;
import application.project.InfrastructureProject;
import application.project.Project;
import application.registeredUser.RegisteredUser;

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
	
	private ProjectController controller;
	
	public ProjectPanel(Project proj, RegisteredUser reg) {
		
		this.controller = new ProjectController(this, proj);
		
		// Set the component data
		this.projectName = new JLabel(proj.getTitle());
		if (proj instanceof InfrastructureProject) {
			projectType = new JLabel("(Infrastructure)");
		} else {
			projectType = new JLabel("(Social)");
		}
		
		moneyAsked = new JLabel("Asked: " + String.format("%.2f", proj.getAmount()) + "€");
		if (proj.getGrantedAmount() != -1) {
			moneyGranted = new JLabel("Granted: " + String.format("%.2f", proj.getGrantedAmount()) + "€");
		} else {
			moneyGranted = new JLabel();
		}
		
		
		if (reg.equals(proj.getCreator())) {
			notifButton = new JButton("You are the creator");
			notifButton.setEnabled(false);
		} else if (proj.getObservers().contains(reg)) {
			notifButton = new JButton("Stop Notifications");
		} else {
			notifButton = new JButton("Get Notifications");
		}
		
		this.status = new JLabel(proj.getState().toString());
		this.projectDescription = new JTextArea(proj.getDescription(), 6, 40);
		this.descrPanel = new JScrollPane(projectDescription);
		
		if (proj.hasVoted(reg)) {
			indivVote.setEnabled(false);
		} else {
			popularityReportButton.setEnabled(false);
		}
		
		radioGroup.add(indivVote);
		radioGroup.add(groupVote);

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
		
		
		projectName.setFont(new Font(projectName.getFont().getName(), Font.PLAIN, 30));
        projectType.setFont(new Font(projectType.getFont().getName(), Font.PLAIN, 18));
        projectDescription.setLineWrap(true);
        projectDescription.setWrapStyleWord(true);
        projectDescription.setEditable(false);
        projectDescription.setFont(projectDescription.getFont().deriveFont(16f));

		
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

        c.gridx = 1;
        c.gridy = 4; 
        c.gridwidth = 2;
        c.gridheight = 1;
        this.add(bottomPanel, c);
        
        voteButton.addActionListener(event -> controller.voteButtonPressed(event));
        popularityReportButton.addActionListener(event -> controller.createPopularityReport(event));
        notifButton.addActionListener(event -> controller.notifButtonPressed(event));
        
	}
	
	public Boolean getVoteType() {
		if (indivVote.isSelected()) {
			return true;
		} else if (groupVote.isSelected()) {
			return false;
		} else {
			return null;
		}
	}
	
	public String getSelectedGroup() {
		return (String)userGroups.getSelectedItem();
	}
	
	public void activatePopularityReportButton() {
		this.popularityReportButton.setEnabled(true);
	}
	
	public void setNotifButton(String s) {
		notifButton.setText(s);
	}

}
