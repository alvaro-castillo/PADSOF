package userInterface.userFeed;

import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Spring;
import javax.swing.SpringLayout;

public class TopButtonsPanel<A extends ActionListener> extends JPanel {

	private static final long serialVersionUID = 1L;
	private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JLabel label;
    public TopButtonsPanel(A panel, String s1, String s2, String s3,String s4, String l) {
    	
        this.button1 = new JButton(s1);
        this.button2 = new JButton(s2);
        this.button3 = new JButton(s3);
        this.button4 = new JButton(s4);
        this.label = new JLabel(l);
        button4.setEnabled(false);
      
        SpringLayout s = new SpringLayout();
        this.setLayout(s);
        
        this.add(button1); 
        this.add(button2);
        //this.add(Box.createRigidArea(new Dimension(80, 0)));
        
        this.add(button3);
        //this.add(Box.createRigidArea(new Dimension(10, 0)));
        this.add(label);
        //this.add(Box.createRigidArea(new Dimension(10, 0)));
        this.add(button4);
        
        button1.addActionListener(panel);
        button2.addActionListener(panel);
        button3.addActionListener(panel);
        this.setPreferredSize(new Dimension(0,50));

        Spring sp1= Spring.constant(0, 10, 20);
        Spring sp2= Spring.constant(0, 10, 100);
        Spring sp3= Spring.constant(0, 10, 10);
        s.putConstraint(SpringLayout.WEST, button1, sp1, SpringLayout.WEST, this);
        s.putConstraint(SpringLayout.WEST, button2, sp3, SpringLayout.EAST, button1);
        s.putConstraint(SpringLayout.WEST, button3, sp2, SpringLayout.EAST, button2);
        s.putConstraint(SpringLayout.WEST, label, sp1, SpringLayout.EAST, button3);
        s.putConstraint(SpringLayout.WEST, button4, sp1, SpringLayout.EAST, label);
        s.putConstraint(SpringLayout.EAST, this, sp3, SpringLayout.EAST, button4);
        
        this.setVisible(true);
    }
	
	
}
