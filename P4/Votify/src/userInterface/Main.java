package userInterface;

import java.io.IOException;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import application.Application;
import userInterface.initialScreen.InitialPanel;


public class Main {

	public static void main(String[] args) {
		JFrame m = AppFrame.getAppFrame();
		
		try {
			Application app = Application.loadApp("data.dat");
			
		}catch(ClassNotFoundException e) {
			
			System.out.println("Internal error: Class Application not found");
			JPanel warning = new JPanel();
			m.add(warning);
			JOptionPane.showMessageDialog(warning, "Internal Error. Class Application.java not found", "Error", JOptionPane.ERROR_MESSAGE);
			warning.setVisible(false);
			
		}catch(IOException e) {
			
			JPanel error = new JPanel();
			m.add(error);
			JOptionPane.showMessageDialog(error, "File data.dat not found. Creating a new Application object", "Warning", JOptionPane.WARNING_MESSAGE);
			error.setVisible(false);
			
		}
		
		JPanel initial = new InitialPanel();
		m.add(initial);
		initial.setVisible(true);
		m.revalidate();
			
		
	}

}
