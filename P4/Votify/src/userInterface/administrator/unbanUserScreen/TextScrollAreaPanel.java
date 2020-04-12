package userInterface.administrator.unbanUserScreen;

import java.awt.FlowLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class TextScrollAreaPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTextArea area;
	private JScrollPane scroll;
	public TextScrollAreaPanel() {
		this.area = new JTextArea(5,30);
		this.scroll = new JScrollPane(area);
	
		this.setLayout(new FlowLayout());

		this.add(scroll);
	}

}
