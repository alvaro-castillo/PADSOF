package userInterface.createProjectScreens;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import userInterface.commonElements.FeedButtonPanel;

/**
 * This class represents the first screen to create a project.
 * It contains three labels, three fields and one button. The fields and labels
 * are organized in pairs, one pair is for the title, another for the description,
 * and a last one for the money. The next button is used when all the fields are
 * filled in and shows the next screen
 * There is also a feed button, to be used when you want to quit the creation of a
 * project and return to the user feed screen.
 * 
 * @author Miguel Álvarez Valiente, Alejandro Benimeli Miranda, Álvaro Castillo García
 *
 */
public class CreateProjectPanel1 extends JPanel{
	
	private static final long serialVersionUID = 1L;
	private FeedButtonPanel feed = new FeedButtonPanel(this);
	private JPanel mainPanel = new JPanel();
	private JLabel labelCreateProject = new JLabel("Create a Project");
	private JLabel labelTitle = new JLabel("Title:");
	private JLabel labelDescription = new JLabel("Description:");
	private JLabel labelMoney = new JLabel("Money:");
	private JTextField titleField = new JTextField(30);
	private JTextArea descriptionArea = new JTextArea(3,30);
	private JScrollPane descriptionScroll = new JScrollPane(descriptionArea);
	private JTextField moneyField = new JTextField(30);
	private JButton nextButton = new JButton("Next");
	private JPanel secondPanel = new JPanel();
	private CreateProjectController controller = new CreateProjectController(this); 
	
	/**
	 * Constructor of this class
	 * 
	 */
	public CreateProjectPanel1() {
		
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
        
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);
        
        GroupLayout Glayout = new GroupLayout(secondPanel);
        secondPanel.setLayout(Glayout);
        
        Glayout.setAutoCreateGaps(true);
        Glayout.setAutoCreateContainerGaps(true);
        
        
        Glayout.setHorizontalGroup(
        		   Glayout.createSequentialGroup()
        		      .addGroup(Glayout.createParallelGroup()
        		           .addComponent(labelTitle)
        		           .addComponent(titleField)
        		           .addComponent(labelDescription)
        		           .addComponent(descriptionScroll)//
        		           .addComponent(labelMoney)
        		           .addComponent(moneyField))
        );
        
        Glayout.setVerticalGroup(
        		  Glayout.createSequentialGroup()
        		   	.addGroup(Glayout.createSequentialGroup()
        		   		.addComponent(labelTitle)
        		   		.addComponent(titleField)
        		   		.addComponent(labelDescription)
        		   		.addComponent(descriptionScroll)//
        		   		.addComponent(labelMoney)
        		   		.addComponent(moneyField))
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
        
        this.nextButton.addActionListener(controller.cp1C);
        
        this.add(mainPanel);
        
	}
	
	/**
	 * Title field getter. Used by the controller
	 * 
	 * @return String in the title field
	 */
	public String getTitle() {
		return this.titleField.getText();
	}
	
	/**
	 * Description field getter. Used by the controller
	 * 
	 * @return String in the descriptions field
	 */
	public String getDescription() {
		return this.descriptionArea.getText();
	}
	
	/**
	 * Money field getter. Used by the controller
	 * 
	 * @return String in the money field
	 */
	public String getMoney() {
		return moneyField.getText();
	}
	
}











