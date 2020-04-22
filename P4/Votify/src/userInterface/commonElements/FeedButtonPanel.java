package userInterface.commonElements;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
* This class will create a panel with a button that will return to the feed panel.
*
* @author Miguel Álvarez Valiente, Alejandro Benimeli Miranda, Álvaro Castillo García
*/
public class FeedButtonPanel extends JPanel implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JButton button;
	
	/**
	 * Constructor of this class.
	 */
	public FeedButtonPanel() {
		this.button = new JButton("Go to Feed");
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		this.add(button);
		button.addActionListener(this);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		
	}
	

}
