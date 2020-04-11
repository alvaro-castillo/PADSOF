package userInterface.createGroupScreen;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import userInterface.FeedButtonPanel;

public class CreateGroupScreen extends JPanel implements ActionListener{
	private static final long serialVersionUID = 1L;
	private JButton create;
	private LabelTextPanel labelText;
    private TextPanel text;
	private FeedButtonPanel feed;
    
    public CreateGroupScreen() {
    	 
    	this.create = new JButton("Create");
    	this.labelText = new LabelTextPanel(this);
    	this.text = new TextPanel();
    	this.feed = new FeedButtonPanel();
    	this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    	this.add(feed);
    	this.add(text);
    	this.add(Box.createRigidArea(new Dimension(0, 60)));
    	this.add(labelText);
        this.add(create);
        this.add(Box.createRigidArea(new Dimension(0, 80)));
        feed.setVisible(true);
        text.setVisible(true);
        labelText.setVisible(true);
        create.addActionListener(this);
    }
    
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
