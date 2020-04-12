package userInterface;


import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class CenteredButtonPanel <A extends ActionListener> extends JPanel {

	private static final long serialVersionUID = 1L;
	private JButton button;
	
	public CenteredButtonPanel(String text, A actionListener) {
		this.button = new JButton(text);
		
		button.addActionListener(actionListener);
		this.add(button);
	}

}
