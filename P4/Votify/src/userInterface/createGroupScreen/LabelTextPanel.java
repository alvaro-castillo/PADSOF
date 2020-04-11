package userInterface.createGroupScreen;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class LabelTextPanel extends JPanel {
    private JTextField textName;
	private JLabel label;
	private static final long serialVersionUID = 1L;
	
	public LabelTextPanel(CreateGroupScreen p) {
		this.textName = new JTextField(25);
    	this.label = new JLabel("Introduce a group name: ");
		
    	this.add(label);
    	this.add(textName);
		textName.addActionListener(p);
	}

}
