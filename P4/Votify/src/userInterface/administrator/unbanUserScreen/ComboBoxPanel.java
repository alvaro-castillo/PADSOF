package userInterface.administrator.unbanUserScreen;

import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.JPanel;


public class ComboBoxPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private JComboBox<String> combo;
	
	public ComboBoxPanel(UnbanUserPanel p,Vector<String> v) {
		this.combo = new JComboBox<String>(v);
		
		this.add(combo);
		
		combo.addActionListener(p);
	}

}
