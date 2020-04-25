package userInterface.administrator.acceptance;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import application.Application;
import application.project.Project;
import userInterface.AppFrame;

/**
* This is the controller of the Accept or Deny Project Panel.
*
* @author Miguel Álvarez Valiente, Alejandro Benimeli Miranda, Álvaro Castillo García
*/
public class AcceptOrDenyProjectController implements ActionListener {

	private AcceptOrDenyProjectPanel panel;
	private Application app;
	private JFrame frame;
	
	/**
	 * Constructor of this class.
	 * 
	 * @param panel the panel that is controlled by this class
	 */
	public AcceptOrDenyProjectController(AcceptOrDenyProjectPanel panel) {
		this.panel = panel;
		this.app = Application.getApplication();
		this.frame = AppFrame.getAppFrame();
	}

	/**
	 * This method will perform the operations for accepting or rejecting a user.
	 *
	 * @param e the event caused by an action
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
    	if(o instanceof JButton) {
    		JButton b = (JButton) o;
    		
    		String projectString = panel.getCombo().getSelectedObject();
    		if(projectString==null) {
    			return;
    		}
    		
    		String[] partsOfStr = projectString.split(" ");
    		
    		int id = 0;
    		
    		try {
    			id = Integer.parseInt(partsOfStr[partsOfStr.length-1]);
    		} catch (Exception exc) {
    			//error
    			return;
    		}
    		
    		Project project = app.searchProject(id);
    		if (project == null) {
    			// error
    			return;
    		}

    		JPanel p;
    		if(b.getText().equals("Accept")) {
    			p = new AcceptProjectPanel(project);
    		}else if(b.getText().equals("Deny")) {
    			p = new DenyProjectPanel(project);
    		}else {
    			return;
    		}
    		
    		panel.setVisible(false);
    		frame.remove(panel);
    		frame.add(p);
    		p.setVisible(true);
    	}else {
    		return;
    	}
	}
}