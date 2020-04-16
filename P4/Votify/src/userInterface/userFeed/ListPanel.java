package userInterface.userFeed;

import java.awt.Dimension;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


public class ListPanel extends JPanel implements ListSelectionListener {
	private JList<String> list;
	private JScrollPane scroll;

	private static final long serialVersionUID = 1L;
	public ListPanel(Vector<String> v) {
		this.list = new JList<String>(v);
		//list.setFixedCellWidth(80);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.scroll = new JScrollPane(list);
		this.add(Box.createRigidArea(new Dimension(0, 200)));
		this.add(scroll);
	} 

	@Override
	public void valueChanged(ListSelectionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
