package userInterface.administrator;

import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.JPanel;

/**
* This class will create a combo box.
* This will be a common element for some administrator panels.
*
* @author Miguel Álvarez Valiente, Alejandro Benimeli Miranda, Álvaro Castillo García
*/
public class ComboBoxPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private JComboBox<String> combo;
	
	/**
	 * Constructor of this class.
	 * 
	 * @param v vector of strings that will be shown in the combo box
	 */
	public ComboBoxPanel(Vector<String> v) {
		this.combo = new JComboBox<String>(v);
		
		this.add(combo);
		
	}
	
	/**
	 * This method returns the object string that is selected on the combo box
	 * 
	 * @return string of the object
	 */
	public String getSelectedObject() {
		Object o = combo.getSelectedItem();
				
		if(o==null) {
			return null;
		}
		return o.toString();
	}

}
