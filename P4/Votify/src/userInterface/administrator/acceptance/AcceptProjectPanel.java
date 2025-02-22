package userInterface.administrator.acceptance;

import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import application.project.Project;
import userInterface.commonElements.BigTextPanel;
import userInterface.commonElements.CenteredButtonPanel;
import userInterface.commonElements.FeedButtonPanel;
import userInterface.commonElements.LabelTextPanel;
/**
* This class will create a panel for accepting the creation of a project.
* It will include a big text indicating the purpose of the panel, a label text panel for 
* introducing the minimum number of votes, a centered buttons panel for
* selecting the acceptance of the creation and the go to feed button.
*
* @author Miguel Álvarez Valiente, Alejandro Benimeli Miranda, Álvaro Castillo García
*/
public class AcceptProjectPanel extends JPanel{
	private FeedButtonPanel feed;
	private BigTextPanel text;
	private LabelTextPanel<AcceptProjectController> label;
	private CenteredButtonPanel<AcceptProjectController> buttons;
	private AcceptProjectController controller;
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor of this class.
	 * 
	 * @param p the project that we will have to accept
	 */
	public AcceptProjectPanel(Project p) {
		this.controller = new AcceptProjectController(this, p);
		this.feed = new FeedButtonPanel(this);
		this.text = new BigTextPanel("Accept Project", 145, 10, 40);
		this.label = new LabelTextPanel<AcceptProjectController>(controller, "Introduce minimum number of votes: ", 10);
		this.buttons = new CenteredButtonPanel<AcceptProjectController>("Accept", controller);
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.add(feed);
		this.add(text);
		this.add(Box.createRigidArea(new Dimension(0, 20)));
		this.add(label);
		this.add(buttons);
		this.add(Box.createRigidArea(new Dimension(0, 60)));
	}
	
	/**
	 * Label text area panel getter. Used by the controller.
	 * @return area
	 */
	public LabelTextPanel<AcceptProjectController> getTextPanel() {
		return this.label;
	}

}
