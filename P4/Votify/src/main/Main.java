package main;

import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import application.Application;
import userInterface.AppFrame;
import userInterface.initialScreen.InitialPanel;

/**
* This is the main class program that will be executed to start the application with the GUI.
*
* @author Miguel Álvarez Valiente, Alejandro Benimeli Miranda, Álvaro Castillo García
*/
public class Main {
	/**
	* This is the main program.
	**/
	public static void main(String[] args) {
		JFrame m = AppFrame.getAppFrame();
		try {
			Application.loadApp("data.dat");
		}catch(ClassNotFoundException e) {
			
			System.out.println("Internal error: Class Application not found");
			JPanel error = new JPanel();
			m.add(error);
			JOptionPane.showMessageDialog(error, "Internal Error. Class Application.java not found", "Error", JOptionPane.ERROR_MESSAGE);
			error.setVisible(false);
			
		}catch(IOException e) {
			
			JPanel warning = new JPanel();
			m.add(warning);
			JOptionPane.showMessageDialog(warning, "File data.dat not found. Creating a new Application object", "Warning", JOptionPane.WARNING_MESSAGE);
			warning.setVisible(false);
			
		}
		
		
		JPanel initial = InitialPanel.getInitialPanel();
		m.add(initial);
		initial.setVisible(true);
		m.revalidate();
			
		
	}

}
