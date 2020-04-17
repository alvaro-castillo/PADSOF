package userInterface.userFeed;

import java.awt.Dimension;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


public class ListPanel extends JPanel implements ListSelectionListener {
	private JList<String> list;
	private JScrollPane scroll;

	private static final long serialVersionUID = 1L;
	public ListPanel(Vector<String> v) {
		this.list = new JList<String>(v);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.scroll = new JScrollPane(list);
		scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		this.add(Box.createRigidArea(new Dimension(0, 50)));
		scroll.setPreferredSize(new Dimension(120,150));
		this.add(scroll);

	} 

	@Override
	public void valueChanged(ListSelectionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
