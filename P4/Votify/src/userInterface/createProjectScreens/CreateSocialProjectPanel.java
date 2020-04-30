package userInterface.createProjectScreens;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import userInterface.commonElements.FeedButtonPanel;


public class CreateSocialProjectPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private FeedButtonPanel feed = new FeedButtonPanel(this);
	private JPanel mainPanel = new JPanel();
    private JLabel labelSocialProject = new JLabel("Social Project");
    private JLabel labelAssociatedSG = new JLabel("Associated Social Group:");
    private JLabel labelDescription = new JLabel("Description (50 chars):");
    private JTextField associatedSGField = new JTextField(30);
    private JTextArea descriptionArea = new JTextArea(3,30);
    private JScrollPane descriptionScroll = new JScrollPane(descriptionArea);
    private JRadioButton internationalRButton = new JRadioButton("International");
    private JRadioButton nationalRButton = new JRadioButton("National");
    private ButtonGroup natOrInterBG = new ButtonGroup();
    private JButton createButton = new JButton("Create");
    private JPanel secondPanel = new JPanel();
    private JPanel natOrInterPanel = new JPanel();
    	
    public CreateSocialProjectPanel(CreateProjectController cpC) {
    	
    	this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.add(feed);
		this.add(Box.createRigidArea(new Dimension(0, 60)));
    	
		mainPanel.setBorder(new EmptyBorder(20,20,20,20));
		mainPanel.setLayout(new GridBagLayout());
    	
    	GridBagConstraints c = new GridBagConstraints();
    	
    	c.fill = GridBagConstraints.NONE;
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.gridwidth = 1;
        c.gridheight = 1;
        
        labelSocialProject.setFont(new Font(labelSocialProject.getFont().getName(), Font.PLAIN, 30));
        
        int yPos = 0;
        
        c.ipady = 0;
        c.gridy = yPos;
        yPos++;
        mainPanel.add(labelSocialProject, c);
        
        c.gridy = yPos;
        yPos++;
        mainPanel.add(new JPanel(), c);
        
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);
        
        GroupLayout Glayout = new GroupLayout(secondPanel);
        secondPanel.setLayout(Glayout);
        
        Glayout.setAutoCreateGaps(true);
        Glayout.setAutoCreateContainerGaps(true);
        
        internationalRButton.setActionCommand("International");
        nationalRButton.setActionCommand("National");
        
        natOrInterBG.add(internationalRButton);
        natOrInterBG.add(nationalRButton);
        
        natOrInterPanel.setLayout(new GridLayout(0,2));
        natOrInterPanel.add(nationalRButton);
        natOrInterPanel.add(internationalRButton);
        
        Glayout.setHorizontalGroup(
        		   Glayout.createSequentialGroup()
        		      .addGroup(Glayout.createParallelGroup()
        		           .addComponent(labelAssociatedSG)
        		           .addComponent(associatedSGField)
        		           .addComponent(natOrInterPanel)
        		           .addComponent(labelDescription)
        		           .addComponent(descriptionScroll))
        );
        
        Glayout.setVerticalGroup(
        		  Glayout.createSequentialGroup()
        		   	.addGroup(Glayout.createSequentialGroup()
        		   		.addComponent(labelAssociatedSG)
        		   		.addComponent(associatedSGField)
        		   		.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED, 25, 25)
        		   		.addComponent(natOrInterPanel)
        		   		.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED, 25, 25)
        		   		.addComponent(labelDescription)
        		   		.addComponent(descriptionScroll))
       );
        
        
        c.gridy = yPos;
        yPos++;
        mainPanel.add(secondPanel, c);
        
        c.gridy = yPos;
        yPos++;
        mainPanel.add(new JPanel(), c);
        
        c.gridy = yPos;
        yPos++;
        mainPanel.add(createButton, c);
        
        this.createButton.addActionListener(cpC.cspC);
        
        this.add(mainPanel);
        
	}
    
    
    public String getSocialGroup() {
    	return associatedSGField.getText();
    }
    
    public String getNatOrInter() {
    	if(natOrInterBG.getSelection()==null) {
    		return null;
    	}
    	return natOrInterBG.getSelection().getActionCommand();
    }
    
    public String getDescription() {
    	return descriptionArea.getText();
    }

}





