package userInterface;

import java.io.IOException;
//import java.util.*;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import application.Application;
//import userInterface.administrator.feed.AdminFeedPanel;
import userInterface.initialScreen.InitialPanel;
//import userInterface.userFeed.UserFeedPanel;

/**
* This is the main class program that will be executed to start the application.
*
* @author Miguel Álvarez Valiente, Alejandro Benimeli Miranda, Álvaro Castillo García
*/
public class Main {
	/**
	* This is the main program.
	**/
	public static void main(String[] args) {
		JFrame m = AppFrame.getAppFrame();
		/*Vector <String> v = new Vector<String>();
	
		v.add("AAAAAA");
		v.add("BBBBBB");
		v.add("CCCCCC");
		v.add("DDDDD");
		v.add("..........");
		v.add("EEEEEEEE");
		v.add("EEEEEEEE");
		v.add("EEEEEEEE");
		v.add("EEEEEEEEEEEEEEEEEEEEEEEEEEE");
		v.add("EEEEEEEE");

		JPanel initial = new UserFeedPanel("User1", v, v,v);*/
		try {
			Application.loadApp("data.dat");
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
		
		
		JPanel initial = InitialPanel.getInitialPanel();
		m.add(initial);
		initial.setVisible(true);
		m.revalidate();
			
		
	}

}
