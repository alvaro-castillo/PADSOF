package userInterface.registerScreen;
import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;


public class RegisterPanel extends JPanel{

    private static final long serialVersionUID=1L;
    private JLabel labelRegister = new JLabel("Register Screen");
    private JLabel labelUsername = new JLabel("Username:");
    private JLabel labelNationalID = new JLabel("National ID:");
    private JLabel labelPassword = new JLabel("Password:");
    private JTextField usernameField = new JTextField(30);
    private JTextField nationalIDField = new JTextField(30);
    private JPasswordField passwordField = new JPasswordField(30);
    private JButton createButton = new JButton("Create Account");
    private RegisterController controller;
    
    
    public RegisterPanel(){
    	this.controller = new RegisterController(this);
    	
    	this.setBorder(new EmptyBorder(20,20,20,20));
    	this.setLayout(new GridBagLayout());
    	
    	GridBagConstraints c = new GridBagConstraints();
    	
    	c.fill = GridBagConstraints.NONE;
    	c.weightx = 1.0;
        c.weighty = 1.0;
        c.gridwidth = 1;
        c.gridheight = 1;
        
        labelRegister.setFont(new Font(labelRegister.getFont().getName(), Font.PLAIN, 30));
        
        int yPos = 0;
        
        c.ipady = 0;
        c.gridy = yPos;
        yPos++;
        this.add(labelRegister, c);
        
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
        this.add(labelNationalID, c);
        
        c.ipady = 10;
        c.gridy = yPos;
        yPos++;
        this.add(nationalIDField, c);
        
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
        this.add(new JSeparator(JSeparator.VERTICAL), c);
        
        c.gridy = yPos;
        yPos++;
        this.add(createButton, c);
                
        this.createButton.addActionListener(controller);
        passwordField.addKeyListener(controller);
        usernameField.addKeyListener(controller);
        nationalIDField.addKeyListener(controller);
    }
    
    public String getUsername() {
    	return this.usernameField.getText();
    }
    
    public String getUserId() {
    	return this.nationalIDField.getText();
    }
    
    public String getUserPassword() {
    	return new String(this.passwordField.getPassword());
    }
}

