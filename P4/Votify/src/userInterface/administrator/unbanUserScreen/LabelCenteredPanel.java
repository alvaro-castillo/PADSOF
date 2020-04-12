package userInterface.administrator.unbanUserScreen;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class LabelCenteredPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel label;
	public LabelCenteredPanel(String s) {
		this.label = new JLabel(s);
		this.add(label);
	}
}
