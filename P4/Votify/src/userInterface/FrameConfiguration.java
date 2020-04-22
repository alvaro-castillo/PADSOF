package userInterface;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import application.Application;

/**
* This class contains the method that will be executed when we close the frame.
*
* @author Miguel Álvarez Valiente, Alejandro Benimeli Miranda, Álvaro Castillo García
*/
public class FrameConfiguration extends WindowAdapter {
	
	/**
	 * This method will log out the user and save the data of the app in the data.dat file.
	 * It is called when the frame is closed.
	 *
	 * @param e the event caused by a window action
	 */
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
