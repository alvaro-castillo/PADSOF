package userInterface.createGroupScreen;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
public class CreateGroupScreen extends JPanel implements ActionListener{
	private static final long serialVersionUID = 1L;
	
	private CenteredButtonPanel<CreateGroupScreen> create;
	private LabelTextPanel<?> labelText; //TODO:Parametrize with the controller
    private BigTextPanel text;
	private FeedButtonPanel feed;
    
    /**
     * Constructor of this class.
     */
    public CreateGroupScreen() {
    	 
    	this.create = new CenteredButtonPanel<CreateGroupScreen>("Create", this);
    	this.labelText = new LabelTextPanel(this, "Introduce a group name: ", 25);
    	this.text = new BigTextPanel("Create a group", 170, 10, 40);
    	this.feed = new FeedButtonPanel();
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
    
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
