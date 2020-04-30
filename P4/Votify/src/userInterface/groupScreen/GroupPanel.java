package userInterface.groupScreen;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.*;

import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

import application.group.Group;
import application.registeredUser.RegisteredUser;
import userInterface.commonElements.FeedButtonPanel;

public class GroupPanel extends JPanel {
	

	private static final long serialVersionUID = 1L;
	private FeedButtonPanel feed;
	private JPanel mainPanel = new JPanel();
	private JLabel groupName;
	private JPanel namePanel = new JPanel();
	private JLabel parentLabel = new JLabel("Parent Group:");
	private JLabel parentGroup;
	private JLabel subg = new JLabel("Subgroups:");
	private JPanel parentPanel = new JPanel();
	private JList<String> subgroupList;
	private JScrollPane subgroupScroll;
	private JButton subgroup = new JButton("Create Subgroup");
	private JButton join = new JButton("Join the Group");
	private JButton leave = new JButton("Leave the Group");
	private JButton affinity = new JButton("Create Affinity Report");
	
	private GroupController controller;
	
	public GroupPanel(Group gr, RegisteredUser usr) {
		
		this.controller = new GroupController(this,gr);
		
		// Initialize components
		this.feed = new FeedButtonPanel(this);
		this.groupName = new JLabel(gr.getName());
		if (gr.getParent() == null) {
			this.parentGroup = new JLabel("None");
		} else {
			this.parentGroup = new JLabel(gr.getParent().getName());
		}
		
		if (!usr.equals(gr.getRepresentative())) {
			subgroup.setEnabled(false);
		}
		 
		
		Vector<String> subgroupNames = new Vector<>();
		
		for (Group g: gr.getSubgroups()) {
			subgroupNames.add(g.getName());
		}
		subgroupList = new JList<String>(subgroupNames);
		subgroupList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		subgroupList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		subgroupList.setVisibleRowCount(1);
		subgroupList.setFixedCellWidth(150);

		subgroupScroll = new JScrollPane(subgroupList);
		subgroupScroll.setPreferredSize(new Dimension(200,100));
		subgroupScroll.setBorder(new EmptyBorder(0,40,40,40));
		
		if (gr.getMembers().contains(usr)) {
			showLeave();
		} else {
			showJoin();
			disableAffinity();
		}

		
		mainPanel.setLayout(new GridBagLayout());
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
        c.gridheight = 1;
        groupName.setFont(new Font(namePanel.getFont().getName(), Font.PLAIN, 30));
        namePanel.add(groupName);
        mainPanel.add(namePanel, c);
        
        // Add the Parent group information panel
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        c.gridheight = 1;
        
        
        GroupLayout layout = new GroupLayout(parentPanel);
        parentPanel.setLayout(layout);
        
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        
        layout.setHorizontalGroup(
        		layout.createSequentialGroup()
        			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                			.addComponent(parentLabel)
        	        		.addComponent(subg))
        			.addComponent(parentGroup)
        		);
        
        layout.setVerticalGroup(
        		layout.createSequentialGroup()
        			.addComponent(parentLabel)
        			.addComponent(parentGroup)
        			.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED, 50, 50)
        			.addComponent(subg)
        		);

        
        mainPanel.add(parentPanel, c);
        

        // Add the subgroups scroll panel
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 3;
        c.gridheight = 1;
        mainPanel.add(subgroupScroll, c);
        
        
        c.fill = GridBagConstraints.BOTH;
        // Add the bottom buttons
        c.fill = GridBagConstraints.NONE;
        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 1;
        c.gridheight = 1;
        mainPanel.add(subgroup, c);
        
        
        c.gridx = 1;
        c.gridy = 3;
        c.gridwidth = 1;
        c.gridheight = 1;
        mainPanel.add(join, c);
        mainPanel.add(leave, c);
        
        
        c.gridx = 2;
        c.gridy = 3;
        c.gridwidth = 1;
        c.gridheight = 1;
        mainPanel.add(affinity, c);
	
		
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.add(feed);
		this.add(mainPanel);
		
		
		parentGroup.addMouseListener(new MouseListener () {

			@Override
			public void mouseClicked(MouseEvent e) {
				if (gr.getParent() == null) {
					return;
				}
				controller.changeToGroup(gr.getParent());
			}

			@Override
			public void mousePressed(MouseEvent e) {}

			@Override
			public void mouseReleased(MouseEvent e) {}

			@Override
			public void mouseEntered(MouseEvent e) {}

			@Override
			public void mouseExited(MouseEvent e) {}
			
		});
		join.addActionListener(event -> controller.joinButtonPressed(event));
		leave.addActionListener(event -> controller.leaveButtonPressed(event));
		subgroupList.addListSelectionListener(event -> controller.selectSubgroup(event, subgroupList));
		subgroup.addActionListener(event -> controller.createSubgroup(event));
		
	}
	
	public void showJoin() {
		this.join.setVisible(true);
		this.leave.setVisible(false);
	}
	
	public void showLeave() {
		this.leave.setVisible(true);
		this.join.setVisible(false);
	}
	
	public void enableAffinity() {
		affinity.setEnabled(true);
	}
	
	public void disableAffinity() {
		affinity.setEnabled(false);
	}

}
