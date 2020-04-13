package userInterface.administrator;

import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.JPanel;


public class ComboBoxPanel <A extends ActionListener>extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private JComboBox<String> combo;
	
	public ComboBoxPanel(A panel,Vector<String> v) {
		this.combo = new JComboBox<String>(v);
		
		this.add(combo);
		
		combo.addActionListener(panel);
	}

}
