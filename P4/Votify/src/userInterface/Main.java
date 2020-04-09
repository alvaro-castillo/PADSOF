package userInterface;
import javax.swing.JFrame;
import javax.swing.JPanel;

import userInterface.initialScreen.InitialPanel;

public class Main {

	public static void main(String[] args) {
		JFrame m = new AppFrame();
		JPanel initial = new InitialPanel();
		m.add(initial);
		initial.setVisible(true);
		m.revalidate();
	}

}
