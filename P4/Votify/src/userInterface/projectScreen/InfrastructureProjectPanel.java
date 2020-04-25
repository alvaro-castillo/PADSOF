package userInterface.projectScreen;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import application.project.InfrastructureProject;
import application.registeredUser.RegisteredUser;
import userInterface.commonElements.FeedButtonPanel;
import userInterface.initialScreen.ImagePanel;

public class InfrastructureProjectPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private ImagePanel img;
	private ProjectPanel proj;
	private JLabel districtLabel;
	private JPanel districtPanel = new JPanel();
	private JPanel infrProjPanel = new JPanel(); 
	private FeedButtonPanel feed;
	
	public InfrastructureProjectPanel(InfrastructureProject infr, RegisteredUser reg) {
		
		// Initialize component content
		this.proj = new ProjectPanel(infr, reg);
		this.img = new ImagePanel(infr.getImage());
		this.districtLabel = new JLabel(infr.getDistrict().toString());
		this.feed = new FeedButtonPanel(this);

		infrProjPanel.setLayout(new GridBagLayout());
    	
        GridBagConstraints c = new GridBagConstraints(); 
        
        c.fill = GridBagConstraints.BOTH;
        c.anchor = GridBagConstraints.CENTER;
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.ipadx = 0;
        c.ipady = 0;
        
        
        c.gridx = 1;
        c.gridy = 0;
        c.gridwidth = 1;
        c.gridheight = 3;
        infrProjPanel.add(proj, c);
        
        c.gridx = 0;
        c.gridwidth = 1;
        c.gridheight = 1;
        
        int yPos = 0;
 
        c.gridy = yPos;
        ++yPos;
        infrProjPanel.add(new JPanel(), c);
        
        c.gridy = yPos;
        ++yPos;
        infrProjPanel.add(img, c);
        
        c.gridy = yPos;
        ++yPos;
        districtPanel.setLayout(new GridBagLayout());
        districtPanel.add(districtLabel);
        infrProjPanel.add(districtPanel, c);
        
        
        
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        infrProjPanel.setBorder(new EmptyBorder(10,10,10,10));
        this.add(feed);
        this.add(infrProjPanel);
        
	}


}
