package userInterface.createProjectScreens;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import application.group.Group;
import application.registeredUser.RegisteredUser;
import userInterface.commonElements.FeedButtonPanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 * This class represents the second screen to create a project
 * It contains two button groups (with two radio buttons each), each of them with the corresponding label, a
 * combo box that will be shown only if the corresponding radio button is selected.
 * Finally it also has the "next" button, which is used when all the required fields
 * are selected, and it shows the next screen after being selected.
 * There is also a feed button, to be used when you want to quit the creation of a
 * project and return to the user feed screen.
 * 
 * @author Miguel Álvarez Valiente, Alejandro Benimeli Miranda, Álvaro Castillo García
 *
 */
public class CreateProjectPanel2 extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private FeedButtonPanel feed = new FeedButtonPanel(this);
	private JPanel mainPanel = new JPanel();
	private JLabel labelCreateProject = new JLabel("Create a Project");
	private JLabel labelProjectType = new JLabel("Type of the project:");
	private JRadioButton infrastructureRButton = new JRadioButton("Infrastructure");
	private JRadioButton socialRButton = new JRadioButton("Social");
	private ButtonGroup projectTypeBG = new ButtonGroup();
	private JLabel labelIndividualOrGroup = new JLabel("Create as individual or group representative:");
	private JRadioButton individualRButton = new JRadioButton("Create as individual");
	private JRadioButton groupRButton = new JRadioButton("Create as a group rep   ");
	private JComboBox groupCBox = new JComboBox();
	private ButtonGroup individualOrGroupBG = new ButtonGroup();
	private JButton nextButton = new JButton("Next");
	private JPanel secondPanel = new JPanel();
	private JPanel projectTypePanel = new JPanel();
	private JPanel individualOrGroupPanel = new JPanel();
	
	/**
	 * Constructor of this class
	 * 
	 * @param cpC Reference to the class with all the controllers
	 * @param user Reference to the RegisteredUser who is creating the project/interacting with the app
	 */
	public CreateProjectPanel2(CreateProjectController cpC, RegisteredUser user) {
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.add(feed);
		this.add(Box.createRigidArea(new Dimension(0, 30)));
		
		mainPanel.setBorder(new EmptyBorder(20,20,20,20));
		mainPanel.setLayout(new GridBagLayout());
    	
    	GridBagConstraints c = new GridBagConstraints();
    	
    	c.fill = GridBagConstraints.NONE;
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.gridwidth = 1;
        c.gridheight = 1;
        
        labelCreateProject.setFont(new Font(labelCreateProject.getFont().getName(), Font.PLAIN, 30));
        
        int yPos = 0;
        
        c.ipady = 0;
        c.gridy = yPos;
        yPos++;
        mainPanel.add(labelCreateProject, c);
        
        c.gridy = yPos;
        yPos++;
        mainPanel.add(new JPanel(), c);
        
        infrastructureRButton.setActionCommand("Infrastructure");
        socialRButton.setActionCommand("Social");
        
        projectTypeBG.add(infrastructureRButton);
        projectTypeBG.add(socialRButton);
        
        individualRButton.setActionCommand("Individual");
        groupRButton.setActionCommand("Group");
        
        groupRButton.addChangeListener(new ChangeListener(){
        	
        	@Override
        	public void stateChanged(ChangeEvent arg0) {
        		if(groupRButton.isSelected()) {
        			groupCBox.setVisible(true);
        			individualOrGroupPanel.revalidate();
        			individualOrGroupPanel.repaint();
        		} else {
        			groupCBox.setVisible(false);
        			individualOrGroupPanel.revalidate();
        			individualOrGroupPanel.repaint();
        		}
        	}
        	
        });
        
        individualOrGroupBG.add(individualRButton);
        individualOrGroupBG.add(groupRButton);
        
        for (Group g : user.getCreatedGroups()) {
        	groupCBox.addItem(g.getName()); 
        }
        
        groupCBox.setVisible(false);
        
        projectTypePanel.setLayout(new GridLayout(2,0));
        projectTypePanel.add(infrastructureRButton);
        projectTypePanel.add(socialRButton);
        
        individualOrGroupPanel.setLayout(new GridLayout(2,2));
        individualOrGroupPanel.add(individualRButton);
        individualOrGroupPanel.add(new JPanel());
        individualOrGroupPanel.add(groupRButton);
        individualOrGroupPanel.add(groupCBox);
        
        GroupLayout Glayout = new GroupLayout(secondPanel);
        secondPanel.setLayout(Glayout);
        
        Glayout.setAutoCreateGaps(true);
        Glayout.setAutoCreateContainerGaps(true);
        
        
        Glayout.setHorizontalGroup(
        		   Glayout.createSequentialGroup()
        		      .addGroup(Glayout.createParallelGroup()
        		           .addComponent(labelProjectType)
        		           .addGroup(Glayout.createSequentialGroup()
        		        		   .addPreferredGap(labelProjectType,projectTypePanel,LayoutStyle.ComponentPlacement.INDENT)
        		        		   .addComponent(projectTypePanel))
        		           .addComponent(labelIndividualOrGroup)
        		           .addGroup(Glayout.createSequentialGroup()
        		        		   .addPreferredGap(labelIndividualOrGroup,individualOrGroupPanel,LayoutStyle.ComponentPlacement.INDENT)
        		        		   .addComponent(individualOrGroupPanel)))
        );
        
        Glayout.setVerticalGroup(
        		   Glayout.createSequentialGroup()
        		      .addComponent(labelProjectType)
        		      .addComponent(projectTypePanel)
        		      .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED, 50, 50)
        		      .addComponent(labelIndividualOrGroup)
        		      .addComponent(individualOrGroupPanel)
        		);
        
        
        c.gridy = yPos;
        yPos++;
        mainPanel.add(secondPanel, c);
        
        c.gridy = yPos;
        yPos++;
        mainPanel.add(new JPanel(), c);
        
        c.gridy = yPos;
        yPos++;
        mainPanel.add(nextButton, c);

        this.nextButton.addActionListener(cpC.cp2C);
        
        this.add(mainPanel);
       
	}
	
	/**
	 * Type getter. Used by the controller
	 * 
	 * @return String of the type of the project
	 */
	public String getType() {
		if(projectTypeBG.getSelection()==null) {
			return null;
		}
		return projectTypeBG.getSelection().getActionCommand();
	}
	
	/**
	 * Way of creating the projetc getter. Used by the controller
	 * 
	 * @return String of the way of creating the project
	 */
	public String getIndOrGroup() {
		if(individualOrGroupBG.getSelection()==null) {
			return null;
		}
		return individualOrGroupBG.getSelection().getActionCommand();
	}
	
	/**
	 * Getter of the option of the group CBox selected. Used by the controller
	 * 
	 * @return String of the item of the group CBox
	 */
	public String getGroup() {
		return groupCBox.getSelectedItem().toString();
	}
	
}























