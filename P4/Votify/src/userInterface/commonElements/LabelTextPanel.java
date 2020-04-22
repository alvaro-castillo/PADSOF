package userInterface.commonElements;

import java.awt.event.KeyAdapter;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
* This class will create a panel with a label and a text field.
*
* @author Miguel Álvarez Valiente, Alejandro Benimeli Miranda, Álvaro Castillo García
*/
public class LabelTextPanel<A extends KeyAdapter> extends JPanel {
    private JTextField textName;
	private JLabel label;
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor of this class.
	 * 
	 * @param controller the controller of the text field. It should extend KeyAdapter
	 * @param s string that will be shown in the label
	 * @param size size of the textField
	 */
	public LabelTextPanel(A controller,String s, int size) {
		this.textName = new JTextField(size);
    	this.label = new JLabel(s);

    	this.add(label);
    	this.add(textName);
    	textName.addKeyListener(controller);
	}
}
