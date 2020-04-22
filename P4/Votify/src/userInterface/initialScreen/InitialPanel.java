package userInterface.initialScreen;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
* This class represents the initial panel of the Application Votify.
* It contains a panel with two buttons(login, register), a label 
* with the name of the app and an image with the logo of the app.
*
* @author Miguel Álvarez Valiente, Alejandro Benimeli Miranda, Álvaro Castillo García
*/
public class InitialPanel extends JPanel{

    private static final long serialVersionUID = 1L;

    private JLabel label;
    private ImagePanelContainer image;
    private ButtonsContainer buttons;
    private static InitialPanel INSTANCE; 
    
    /**
     * Constructor of this class.
     * It is private to follow the singleton pattern.
     */
    private InitialPanel(){

    	GridBagLayout g = new GridBagLayout();
    	GridBagConstraints gbc = new GridBagConstraints();
    	
        this.label = new JLabel("You need an account to use the application", JLabel.CENTER );
        this.image = new ImagePanelContainer();
        this.buttons = new ButtonsContainer(this);
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
    
    /**
	 * InitialPanel getter. It follows the singleton pattern.
	 * @return INSTANCE .
	 */
	public static InitialPanel getInitialPanel() {
		if(INSTANCE == null) {
			INSTANCE= new InitialPanel();
		}
		return INSTANCE;
	}
}