package userInterface.projectScreen;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import application.project.SocialProject;
import application.registeredUser.RegisteredUser;
import userInterface.commonElements.FeedButtonPanel;

/**
 * This JPanel shows Social Projects
 * 
 * @author Álvaro Castillo García
 * @author Alejandro Benimeli
 * @author Miguel Álvarez Valiente
 *
 */
public class SocialProjectPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private ProjectPanel proj;
	private JPanel sociProjPanel = new JPanel();
	private JLabel associationLabel;
	private JLabel nationalLabel;
	private JPanel associationPanel = new JPanel();
	private JTextArea associationDescr;
	private JScrollPane descrPanel;
	private FeedButtonPanel feed;
	
	/**
	 * Constructor
	 * 
	 * @param soci Social project that shows in the panel
	 * @param reg Registered user currently logged in the app
	 */
	public SocialProjectPanel(SocialProject soci, RegisteredUser reg) {
		
		// Initialize component content
		this.proj = new ProjectPanel(soci, reg);
		this.feed = new FeedButtonPanel(this);
		
		this.associationLabel = new JLabel(soci.getAssociation());
		if (soci.getNational()) {
			this.nationalLabel = new JLabel("National");
		} else {
			this.nationalLabel = new JLabel("Foreign");
		}
		this.associationDescr = new JTextArea(soci.getAssociationDescription(), 6, 15);
		this.descrPanel = new JScrollPane(associationDescr);
		
		this.associationDescr.setLineWrap(true);
		this.associationDescr.setWrapStyleWord(true);
		this.associationDescr.setEditable(false);
		

		sociProjPanel.setLayout(new GridBagLayout());
    	
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
        sociProjPanel.add(proj, c);
        
        c.gridx = 0;
        c.gridwidth = 1;
        c.gridheight = 1;
        
        int yPos = 0;
        
        c.gridy = yPos;
        ++yPos;
        associationPanel.setLayout(new BoxLayout(associationPanel, BoxLayout.Y_AXIS));
        associationPanel.add(associationLabel);
        associationPanel.add(nationalLabel);
        JPanel temp = new JPanel();
        temp.add(associationPanel);
        sociProjPanel.add(temp, c);
        
        
        c.gridheight = 2;
        
        c.gridy = yPos;
        ++yPos;
        sociProjPanel.add(descrPanel, c);
        
        
        
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        sociProjPanel.setBorder(new EmptyBorder(20,20,20,20));
        this.add(feed);
        this.add(sociProjPanel);
        
	}


}
