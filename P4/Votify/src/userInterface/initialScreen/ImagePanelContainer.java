package userInterface.initialScreen;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;

public class ImagePanelContainer extends JPanel {
	

	private static final long serialVersionUID = 1L;
	private ImagePanel iPanel;
	private TextPanel tPanel;
	
	public ImagePanelContainer() {
		iPanel = new ImagePanel();
		tPanel = new TextPanel();
		
		GridBagLayout g = new GridBagLayout();
    	GridBagConstraints gbc = new GridBagConstraints();
    	this.setLayout(g);

	    
    	gbc.gridheight = 1;
    	gbc.gridwidth = 1;
    	gbc.gridx = 0;
    	gbc.gridy = 0;
    	gbc.weightx = 1.0;
    	gbc.weighty = 1.0;
    	gbc.fill = GridBagConstraints.BOTH;
    	
		this.add(iPanel, gbc);
		
		gbc.gridheight = 1;
    	gbc.gridwidth = 1;
    	gbc.gridx = 1;
    	gbc.gridy = 0;
		
		this.add(tPanel, gbc);
		iPanel.setVisible(true);
		tPanel.setVisible(true);
	 }
	
}
