package userInterface;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import application.Application;

public class FrameConfiguration extends WindowAdapter {
	
	@Override
	public void windowClosing(WindowEvent e) {
		Application app = Application.getApplication();
		if(app.getCurrentUser() != null) {
			app.logOut();
		}
		try {
			app.saveApp("data.dat");
		}catch(IOException ex) {
			JFrame m = AppFrame.getAppFrame();
			JPanel error = new JPanel();
			m.add(error);
			JOptionPane.showMessageDialog(error, "Application can not be saved in data.dat ", "Error", JOptionPane.ERROR_MESSAGE);
			error.setVisible(false);
		}

	}
}
