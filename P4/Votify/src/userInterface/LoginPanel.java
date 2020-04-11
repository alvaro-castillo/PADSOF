import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.*;
import javax.swing.border.EmptyBorder;


public class LoginPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private JLabel labelLogin = new JLabel("Login Screen");
    private JLabel labelUsername = new JLabel("Username:");
    private JLabel labelPassword = new JLabel("Password:");
    private JLabel labelRegister = new JLabel("Don't have an account?");
    private JTextField usernameField = new JTextField(30);
    private JPasswordField passwordField = new JPasswordField(30);
    private JButton loginButton = new JButton("Login");
    private JButton registerButton = new JButton("Register");

    public LoginPanel() {
    	
    	this.setBorder(new EmptyBorder(20,20,20,20));
    	this.setLayout(new GridBagLayout());
    	
        GridBagConstraints c = new GridBagConstraints(); 

        c.fill = GridBagConstraints.NONE;
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.gridwidth = 1;
        c.gridheight = 1;
        
        labelLogin.setFont(new Font(labelLogin.getFont().getName(), Font.PLAIN, 30));
        
        int yPos = 0;
        
        c.ipady = 0;
        c.gridy = yPos;
        yPos++;
        this.add(labelLogin, c);
        
        c.gridy = yPos;
        yPos++;
        this.add(new JSeparator(JSeparator.VERTICAL), c);
        
        c.gridy = yPos;
        yPos++;
        this.add(labelUsername, c);
        
        c.ipady = 10;
        c.gridy = yPos;
        yPos++;
        this.add(usernameField, c);

        c.ipady = 0;
        c.gridy = yPos;
        yPos++;
        this.add(new JSeparator(JSeparator.VERTICAL), c);
        
        c.gridy = yPos;
        yPos++;
        this.add(labelPassword, c);

        c.ipady = 10;
        c.gridy = yPos;
        yPos++;
        this.add(passwordField, c);
        
        c.ipady = 0;
        c.gridy = yPos;
        yPos++;
        this.add(loginButton, c);
        
        c.gridy = yPos;
        yPos++;
        this.add(new JSeparator(JSeparator.VERTICAL), c);
        
        c.gridy = yPos;
        yPos++;
        this.add(labelRegister, c);

        c.gridy = yPos;
        yPos++;
        this.add(registerButton, c);

    }

    

}