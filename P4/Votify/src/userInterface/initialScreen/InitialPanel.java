package userInterface.initialScreen;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;



public class InitialPanel extends JPanel{

    private static final long serialVersionUID = 1L;

    private JLabel label;
    private ImagePanelContainer image;
    private ButtonsContainer buttons;
    JFrame frame;
    public InitialPanel(JFrame frame){

    	GridBagLayout g = new GridBagLayout();
    	GridBagConstraints gbc = new GridBagConstraints();
    	
        this.label = new JLabel("You need an account to use the application", JLabel.CENTER );
        this.image = new ImagePanelContainer();
        //this.buttons = new TwoButtonsPanel<InitialPanel>(this, "Register", "Log in", (frame.getWidth()/4)+10); 
        this.buttons = new ButtonsContainer(this, frame);
        this.setBorder(new EmptyBorder(20,20,20,20)); // Padding
        
        
        this.setLayout(g);
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
}