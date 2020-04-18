package userInterface.initialScreen;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import userInterface.AppFrame;
import userInterface.commonElements.TwoButtonsPanel;
import userInterface.loginScreen.LoginPanel;
import userInterface.registerScreen.RegisterPanel;

public class ButtonsContainer extends JPanel implements ActionListener{

	private TwoButtonsPanel<ButtonsContainer> buttons;
	private static final long serialVersionUID = 1L;
	private InitialPanel initial_panel;
	public ButtonsContainer(InitialPanel p) {
		this.buttons = new TwoButtonsPanel<ButtonsContainer>(this, "Register", "Log in");
		this.initial_panel = p;
		this.add(buttons);
	}

	@Override
	public void actionPerformed(ActionEvent e){
    	Object o = e.getSource();
    	if(o instanceof JButton) {
    		JButton b = (JButton) o;
    		JPanel j=null;
    		if(b.getText().contains("Register")) {
    			j = new RegisterPanel();
    			
    		}else if(b.getText().contains("Log in")) {
    			j = new LoginPanel();
    			
    		}else {
    			return;
    		}
    		initial_panel.setVisible(false);
    		JFrame frame = AppFrame.getAppFrame();
    		
			AppFrame.getAppFrame().add(j);
			j.setVisible(true);
			frame.remove(initial_panel);
			
    	}else {
    		return;
    	}
	}
	
}
