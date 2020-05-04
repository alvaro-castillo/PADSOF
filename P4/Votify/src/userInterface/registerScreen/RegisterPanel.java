package userInterface.registerScreen;
import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
* This class will create the Register Panel.
* It includes three text fields and three labels explaining each field. In addition it includes
* a title and a button to create the new user.
*
* @author Miguel Álvarez Valiente, Alejandro Benimeli Miranda, Álvaro Castillo García
*/
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
    private JButton mainScreen = new JButton("Go to the Main Screen");
    private RegisterController controller;
    
    
    /**
	 * Constructor of this class.
     */
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
        
        c.gridy = yPos;
        yPos++;
        this.add(mainScreen, c);
                
        this.createButton.addActionListener(event -> controller.pressedRegister(event));
        this.mainScreen.addActionListener(event -> controller.goToMainScreen(event));
        passwordField.addKeyListener(controller);
        usernameField.addKeyListener(controller);
        nationalIDField.addKeyListener(controller);
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
     * Id introduced by the user getter.
     * 
     * @return id
     */
    public String getUserId() {
    	return this.nationalIDField.getText();
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

