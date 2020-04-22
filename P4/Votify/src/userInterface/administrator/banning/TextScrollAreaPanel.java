package userInterface.administrator.banning;

import java.awt.FlowLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
/**
* This class creates a area where the user can write and adds to it a scroll panel
* in case the text reaches the bounds of the text area.
* 
* @author Miguel Álvarez Valiente, Alejandro Benimeli Miranda, Álvaro Castillo García
*/
public class TextScrollAreaPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTextArea area;
	private JScrollPane scroll;
	
	/**
	 * Constructor of this class.
	 */
	public TextScrollAreaPanel() {
		this.area = new JTextArea(5,30);
		this.scroll = new JScrollPane(area);
	
		this.setLayout(new FlowLayout());

		this.add(scroll);
	}

}
