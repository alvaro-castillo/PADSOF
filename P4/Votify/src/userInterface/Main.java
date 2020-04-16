package userInterface;

import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;

//import userInterface.initialScreen.InitialPanel;
import userInterface.userFeed.UserFeedPanel;


public class Main {

	public static void main(String[] args) {
		JFrame m = new AppFrame();
		Vector <String> v = new Vector<String>();
	
		v.add("AAAAAA");
		v.add("BBBBBB");
		v.add("CCCCCC");
		v.add("DDDDD");
		v.add("..........");
		v.add("EEEEEEEE");
		v.add("EEEEEEEE");
		v.add("EEEEEEEE");
		v.add("EEEEEEEE");
		v.add("EEEEEEEE");
		JPanel initial = new UserFeedPanel("User1", v);
		//JPanel initial = new InitialPanel(m);
		m.add(initial);
		initial.setVisible(true);
		m.revalidate();
	}

}
