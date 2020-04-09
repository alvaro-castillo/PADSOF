package userInterface.initialScreen;
import javax.swing.JButton;
import javax.swing.JPanel;

public class InitialButtonsPannel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JButton register;
    private JButton login;
    public InitialButtonsPannel(InitialPanel p) {
    	
        this.register = new JButton("Register");
        this.login = new JButton("Log in");
   	
        this.add(register); 
 
        this.add(login);
        register.addActionListener(p);
        login.addActionListener(p);
    }
	
	
}
