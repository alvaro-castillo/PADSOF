package userInterface.commonElements;
import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
* This class will create a panel with two buttons align in the x axis
* and separated by a transparent rigid box. It is done in a general way.
*
* @author Miguel Álvarez Valiente, Alejandro Benimeli Miranda, Álvaro Castillo García
*/
public class TwoButtonsPanel<A extends ActionListener> extends JPanel {

	private static final long serialVersionUID = 1L;
	private JButton button1;
    private JButton button2;
    
    /**
     * Constructor of this class.
     * 
     * @param panel action listener of the buttons
     * @param s1 string that will be shown in the first button
     * @param s2 string that will be shown in the second button
     */
    public TwoButtonsPanel(A panel, String s1, String s2) {
    	
        this.button1 = new JButton(s1);
        this.button2 = new JButton(s2);
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.add(button1); 
		this.add(Box.createRigidArea(new Dimension(120, 0)));
        this.add(button2);
        this.add(Box.createRigidArea(new Dimension(10, 0)));
        button1.addActionListener(panel);
        button2.addActionListener(panel);
    }
	
	
}
