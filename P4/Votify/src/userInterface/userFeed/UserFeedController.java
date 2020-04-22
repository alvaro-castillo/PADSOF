package userInterface.userFeed;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;

import application.Application;
import userInterface.AppFrame;

/**
* This is the controller of the User Feed Panel.
*
* @author Miguel Álvarez Valiente, Alejandro Benimeli Miranda, Álvaro Castillo García
*/
public class UserFeedController extends KeyAdapter{
	protected JPanel panel;
	protected Application app;
	protected JFrame frame;
	

	/**
	 * Constructor of this class.
	 * 
	 * @param panel the panel that is controlled by this class
	 */
	public UserFeedController(JPanel panel) {
		this.panel = panel;
		this.app = Application.getApplication();
		this.frame = AppFrame.getAppFrame();
	}
	
	public void valueChangedGroup(ListSelectionEvent e,  ListPanel paux) {
		panel.setVisible(false);
		String group = paux.getSelectedValue();
		//JPanel p = new Panel TODO: donde se vea información del grupo
		//frame.add(p);
		frame.remove(panel);
		//p.setVisible(true);
		
	}
	
	public void valueChangedProject(ListSelectionEvent e,  ListPanel paux) {
		panel.setVisible(false);
		String projectString = paux.getSelectedValue();
		//JPanel p = new Panel TODO:donde se vea información del proyecto
		//frame.add(p);
		frame.remove(panel);
		//p.setVisible(true);
		
	}

	public void valueChangedNotifications(ListSelectionEvent e,  ListPanel paux) {
		//TODO: Realizar acciones con las notificaciones
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()!=KeyEvent.VK_ENTER) {
			return;
		}
		
		Object o = e.getSource();
		
		if(!(o instanceof  JTextField) || o==null) {
			return;
		}
		JTextField aux = (JTextField) o;
		String s = aux.getText();
		
		//TODO: enseñar panel de búsqueda
	}
}
