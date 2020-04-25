package userInterface.administrator.feed;

import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Spring;
import javax.swing.SpringLayout;

/**
* This class will create the Administrator tools panel.
* It will include five buttons for:
* - Accepting/Rejecting a user
* - Accepting/Rejecting a group
* - Accepting/Rejecting a project
* - Ban user
* - Unban a user
* In addition it includes a text indicating that, that are the admin tools.
* 
* @author Miguel Álvarez Valiente, Alejandro Benimeli Miranda, Álvaro Castillo García
*/
public class AdminToolsPanel extends JPanel{

	private JButton registration;
	private JButton group;
	private JButton project;
	private JButton ban;
	private JButton unban;
	private JLabel text;

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of this class.
	 * 
	 * @param controller of this panel
	 */
	public AdminToolsPanel(AdminFeedController controller) {
		this.text = new JLabel("          Admin Tools");
		this.registration = new JButton("Accept Registration");
		this.group = new JButton("Accept Group");
		this.project = new JButton("Accept Project");
		this.ban = new JButton("Ban User");
		this.unban = new JButton("Unban User");
		
		Dimension d = registration.getPreferredSize();
		group.setPreferredSize(d);
		project.setPreferredSize(d);
		ban.setPreferredSize(d);
		unban.setPreferredSize(d);
		
		this.add(text);
		this.add(registration);
		this.add(group);
		this.add(project);
		this.add(ban);
		this.add(unban);
		
		SpringLayout s = new SpringLayout();
		this.setLayout(s);
		
		
		this.setPreferredSize(new Dimension(200,300));

        Spring sp1= Spring.constant(0, -50, 20);
        Spring sp2= Spring.constant(0, -40, 10);

        s.putConstraint(SpringLayout.NORTH, text, sp2, SpringLayout.NORTH, this);
        s.putConstraint(SpringLayout.SOUTH, registration, sp1, SpringLayout.NORTH, text);
        s.putConstraint(SpringLayout.SOUTH, group, sp1, SpringLayout.NORTH, registration);
        s.putConstraint(SpringLayout.SOUTH, project, sp1, SpringLayout.NORTH, group);
        s.putConstraint(SpringLayout.SOUTH, ban, sp1, SpringLayout.NORTH, project);
        s.putConstraint(SpringLayout.SOUTH, unban, sp1, SpringLayout.NORTH, ban);
        s.putConstraint(SpringLayout.SOUTH, this, sp1, SpringLayout.SOUTH, unban);
        
       this.setVisible(true);
       
       registration.addActionListener(e-> controller.actionPerformedRegistration(e));
       group.addActionListener(e-> controller.actionPerformedGroup(e));
       project.addActionListener(e-> controller.actionPerformedProject(e));
       ban.addActionListener(e-> controller.actionPerformedBan(e));
       unban.addActionListener(e-> controller.actionPerformedUnban(e));
	}
}
