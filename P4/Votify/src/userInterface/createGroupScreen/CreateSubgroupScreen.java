package userInterface.createGroupScreen;

import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import application.group.Group;
import userInterface.commonElements.BigTextPanel;
import userInterface.commonElements.CenteredButtonPanel;
import userInterface.commonElements.FeedButtonPanel;
import userInterface.commonElements.LabelTextPanel;

public class CreateSubgroupScreen extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	private CenteredButtonPanel<CreateSubgroupController> create;
	private LabelTextPanel<CreateSubgroupController> labelText;
	private BigTextPanel text;
	private FeedButtonPanel feed;
	private CreateSubgroupController controller;
    /**
     * Constructor of this class.
     */
    public CreateSubgroupScreen(Group parent) {
    	this.controller = new CreateSubgroupController(this, parent);
    	this.create = new CenteredButtonPanel<CreateSubgroupController>("Create", controller);
    	this.labelText = new LabelTextPanel<CreateSubgroupController>(controller, "Introduce a group name: ", 25);
    	this.text = new BigTextPanel("Create a group", 170, 10, 40);
    	this.feed = new FeedButtonPanel(this);
    	this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    	this.add(feed);
    	this.add(text);
    	this.add(Box.createRigidArea(new Dimension(0, 60)));
    	this.add(labelText);
        this.add(create);
        this.add(Box.createRigidArea(new Dimension(0, 50)));
        feed.setVisible(true);
        text.setVisible(true);
        labelText.setVisible(true);
    }
    
    /**
	 * Label Text panel getter. Used by the controller.
     * @return labelText
     */
    public LabelTextPanel<CreateSubgroupController> getLabelTextPanel() {
    	return this.labelText;
    }
}
