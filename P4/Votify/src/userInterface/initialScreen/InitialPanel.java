package userInterface.initialScreen;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;


public class InitialPanel extends JPanel implements ActionListener{

    private static final long serialVersionUID = 1L;

    private JLabel label;
    private ImagePanelContainer image;
    private InitialButtonsPannel buttons;

    public InitialPanel(){
    	super();

    	GridBagLayout g = new GridBagLayout();
    	GridBagConstraints gbc = new GridBagConstraints();
    	   	
    	this.setLayout(g);
        this.label = new JLabel("You need an account to use the application", JLabel.CENTER );
        this.image = new ImagePanelContainer();
        this.buttons = new InitialButtonsPannel(this); 
        
    	gbc.gridheight = 1;
    	gbc.gridwidth = 1;
    	gbc.gridx = 0;
    	gbc.gridy = 0;
    	gbc.weightx = 1.0;
    	gbc.weighty = 1.0;
    	gbc.fill = GridBagConstraints.BOTH;
        
        this.add(image, gbc);
        
        gbc.gridheight = 1;
    	gbc.gridwidth = 1;
    	gbc.gridx = 0;
    	gbc.gridy = 2;
    	
    	
        this.add(label, gbc);
        
        gbc.gridheight = 1;
    	gbc.gridwidth = 1;
    	gbc.gridx = 0;
    	gbc.gridy = 3;

    	
        this.add(buttons, gbc);
        
        image.setVisible(true);
        buttons.setVisible(true);
        this.setVisible(true);
        
    }
    public void actionPerformed(ActionEvent e){

    }


}