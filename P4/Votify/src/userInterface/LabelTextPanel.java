package userInterface;

import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class LabelTextPanel<A extends ActionListener> extends JPanel {
    private JTextField textName;
	private JLabel label;
	private static final long serialVersionUID = 1L;
	
	public LabelTextPanel(A panel, String s, int size) {
		this.textName = new JTextField(size);
    	this.label = new JLabel(s);

    	this.add(label);
    	this.add(textName);
		textName.addActionListener(panel);
	}

}
