package userInterface.loginScreen;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import userInterface.AppFrame;
import userInterface.registerScreen.RegisterPanel;

/**
* This class will create the Log In Panel.
* It includes two text fields and two labels explaining each field. In addition it includes
* a title and a button to log in.
* There is also a label and a button to go to the register panel.
*
* @author Miguel Álvarez Valiente, Alejandro Benimeli Miranda, Álvaro Castillo García
*/
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
    private LoginController controller;
    
    /**
   	 * Constructor of this class.
        */
    public LoginPanel() {
    
    	this.controller = new LoginController(this);
    	
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
        
        this.loginButton.addActionListener(controller);
        passwordField.addKeyListener(controller);
        usernameField.addKeyListener(controller);
        
        this.registerButton.addActionListener(new ActionListener(){
        		public void actionPerformed(ActionEvent e){
        			AppFrame.getAppFrame().add(new RegisterPanel());
        			LoginPanel.this.setVisible(false);
        		}
        	}
        );

    }

    /**
     * Username introduced by the user getter.
     * 
     * @return username
     */
    public String getUsername() {
    	return this.usernameField.getText();
    }
    
    /**
     * Password introduced by the user getter.
     * 
     * @return password introduced
     */
    public String getUserPassword() {
    	return new String(this.passwordField.getPassword());
    } 

}