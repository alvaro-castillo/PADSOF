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
* This class will create a panel for rejecting the creation of a project.
* It will include a big text indicating the purpose of the panel, a label text panel for 
* introducing the reason of the rejection if needed, a centered buttons panel for
* selecting the rejection of the creation and the go to feed button.
*
* @author Miguel Álvarez Valiente, Alejandro Benimeli Miranda, Álvaro Castillo García
*/
public class DenyProjectPanel extends JPanel{
	private FeedButtonPanel feed;
	private BigTextPanel text;
	private LabelTextPanel<DenyProjectController> label;
	private CenteredButtonPanel<DenyProjectController> buttons;
	private DenyProjectController controller;
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor of this class.
	 * 
	 * @param p the project that we will have to deny
	 */
	public DenyProjectPanel(Project p) {
		this.controller = new DenyProjectController(this, p);
		this.feed = new FeedButtonPanel(this);
		this.text = new BigTextPanel("Deny Project", 145, 10, 40);
		this.label = new LabelTextPanel<DenyProjectController>(controller, "Introduce a reason for the rejection: ", 50);
		this.buttons = new CenteredButtonPanel<DenyProjectController>("Accept", controller);
		
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
	 * */
	public LabelTextPanel<DenyProjectController> getTextPanel() {
		return this.label;
	}

}
