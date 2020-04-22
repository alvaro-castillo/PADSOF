package userInterface.administrator;

import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.JPanel;

/**
* This class will create a combo box.
* This will be a common element for some administrator panels.
*
* @author Miguel Álvarez Valiente, Alejandro Benimeli Miranda, Álvaro Castillo García
*/
public class ComboBoxPanel <A extends ActionListener>extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private JComboBox<String> combo;
	
	/**
	 * Constructor of this class.
	 * 
	 * @param panel action listener panel
	 * @param v vector of strings that will be shown in the combo box
	 */
	public ComboBoxPanel(A panel,Vector<String> v) {
		this.combo = new JComboBox<String>(v);
		
		this.add(combo);
		
		combo.addActionListener(panel);
	}

}
