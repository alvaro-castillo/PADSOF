package userInterface.createGroupScreen;

import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import userInterface.commonElements.BigTextPanel;
import userInterface.commonElements.CenteredButtonPanel;
import userInterface.commonElements.FeedButtonPanel;
import userInterface.commonElements.LabelTextPanel;

/**
* This class represents the create group screen.
* It contains a label text panel for introducing the group name,
* a button for pressing when the user has written a name,
* a big text and a button that if pressed will go back to the feed panel.
*
* @author Miguel Álvarez Valiente, Alejandro Benimeli Miranda, Álvaro Castillo García
*/
public class CreateGroupScreen extends JPanel{
	private static final long serialVersionUID = 1L;
	
	private CenteredButtonPanel<CreateGroupController> create;
	private LabelTextPanel<CreateGroupController> labelText;
    private BigTextPanel text;
	private FeedButtonPanel feed;
    private CreateGroupController controller;
    /**
     * Constructor of this class.
     */
    public CreateGroupScreen() {
    	this.controller = new CreateGroupController(this);
    	this.create = new CenteredButtonPanel<CreateGroupController>("Create", controller);
    	this.labelText = new LabelTextPanel<CreateGroupController>(controller, "Introduce a group name: ", 25);
    	this.text = new BigTextPanel("Create a group", 170, 10, 40);
    	this.feed = new FeedButtonPanel(this);
    	this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    	this.add(feed);
    	this.add(text);
    	this.add(Box.createRigidArea(new Dimension(0, 60)));
    	this.add(labelText);
        this.add(create);
        this.add(Box.createRigidArea(new Dimension(0, 80)));
        feed.setVisible(true);
        text.setVisible(true);
        labelText.setVisible(true);
    }
    
    /**
	 * Label Text panel getter. Used by the controller.
     * @return labelText
     */
    public LabelTextPanel<CreateGroupController> getLabelTextPanel() {
    	return this.labelText;
    }
}
