package userInterface.userFeed;

import java.awt.Dimension;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

/**
* This class creates a panel that includes a list added into a sccroll pane.
*
* @author Miguel Álvarez Valiente, Alejandro Benimeli Miranda, Álvaro Castillo García
*/
public class ListPanel extends JPanel {
	private JList<String> list;
	private JScrollPane scroll;
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor of this class.
	 * 
	 * @param v vector that contains notifications in form of string
	 * @param d dimension used for the scroll pane size
	 * @param controller the controller of the panel
	 */
	public ListPanel(Vector<String> v, Dimension d, UserFeedController controller, int type) {
		this.list = new JList<String>(v);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		this.scroll = new JScrollPane(list);
		
		this.add(Box.createRigidArea(new Dimension(0, 50)));
		scroll.setPreferredSize(d);
		this.add(scroll);

		if(type==-1 ) {
			list.addListSelectionListener(e -> {controller.valueChangedProject(e, ListPanel.this);});
		}else if(type==1) {
			list.addListSelectionListener(e -> {controller.valueChangedGroup(e, ListPanel.this);});
		}else if(type==0) {
			list.addListSelectionListener(e -> {controller.valueChangedNotifications(e, ListPanel.this);});
		}
		
		
	} 
	
	public String getSelectedValue() {
		return list.getSelectedValue();
	}
}
