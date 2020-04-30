package userInterface.userFeed;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.Spring;
import javax.swing.SpringLayout;

import application.Application;
import userInterface.AppFrame;
import userInterface.createGroupScreen.CreateGroupScreen;
import userInterface.createProjectScreens.CreateProjectPanel1;
import userInterface.initialScreen.InitialPanel;

/**
* This class creates a panel that includes four buttons an a label on a scroll pane.
* It is used in the user and admin feed
*
* @author Miguel Ã�lvarez Valiente, Alejandro Benimeli Miranda, Ã�lvaro Castillo GarcÃ­a
*/
public class TopButtonsPanel<A extends JPanel> extends JPanel {

	private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JLabel label;
	private static final long serialVersionUID = 1L;
    
    /**
     * Constructor of this class.
     * 
     * @param panel the action listener for the buttons
     * @param s1 string that will be shown in the first button
     * @param s2 string that will be shown in the second button
     * @param s3 string that will be shown in the third button
     * @param s4 string that will be shown in the fourth button
     * @param l string shown in the label
     */
    public TopButtonsPanel(A panel, String s1, String s2, String s3,String s4, String l) {
    	
        this.button1 = new JButton(s1);
        this.button2 = new JButton(s2);
        this.button3 = new JButton(s3);
        this.button4 = new JButton(s4);
        this.label = new JLabel(l);
        button4.setEnabled(false);
      
        SpringLayout s = new SpringLayout();
        this.setLayout(s);
        JScrollPane scroll = new JScrollPane(label);
        scroll.setPreferredSize(new Dimension(l.length(),35));
        
        this.add(button1); 
        this.add(button2);        
        this.add(button3);
        this.add(scroll);
        this.add(button4);
        
        button1.addActionListener(e-> {
        	JFrame m = AppFrame.getAppFrame();
        	panel.setVisible(false);
        	m.remove(panel);
        	m.add(new CreateGroupScreen());
        });
        
        button2.addActionListener(e-> {
        	JFrame m = AppFrame.getAppFrame();
        	panel.setVisible(false);
        	m.remove(panel);
        	m.add(new CreateProjectPanel1());
        });
        button3.addActionListener(e-> {
        	JFrame m = AppFrame.getAppFrame();
        	panel.setVisible(false);
        	
        	Application.getApplication().logOut();
        	
        	m.remove(panel);
        	JPanel p = InitialPanel.getInitialPanel();
        	m.add(p);
        	p.setVisible(true);
        });
        this.setPreferredSize(new Dimension(0,50));

        Spring sp1= Spring.constant(0, 10, 20);
        Spring sp2= Spring.constant(0, 10, 100);
        Spring sp3= Spring.constant(0, 10, 10);
        s.putConstraint(SpringLayout.WEST, button1, sp1, SpringLayout.WEST, this);
        s.putConstraint(SpringLayout.WEST, button2, sp3, SpringLayout.EAST, button1);
        s.putConstraint(SpringLayout.WEST, button3, sp2, SpringLayout.EAST, button2);
        s.putConstraint(SpringLayout.WEST, scroll, sp2, SpringLayout.EAST, button3);
        s.putConstraint(SpringLayout.WEST, button4, sp1, SpringLayout.EAST, scroll);
        s.putConstraint(SpringLayout.EAST, this, sp3, SpringLayout.EAST, button4);
        
        this.setVisible(true);
    }
	
	
}
