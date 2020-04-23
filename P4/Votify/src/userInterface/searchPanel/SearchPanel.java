package userInterface.searchPanel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.Vector;

import javax.swing.JPanel;

import userInterface.commonElements.FeedButtonPanel;
import userInterface.userFeed.CenterPanel;
/**
* This class defines the search panel.
* It includes a button for returning to the feed, and a centered panel with
* two combo boxes, two titles and a text to query again.
*
* @author Miguel Álvarez Valiente, Alejandro Benimeli Miranda, Álvaro Castillo GarcÃ­a
*/
public class SearchPanel extends JPanel {
	
	private FeedButtonPanel feed;
	private CenterPanel<SearchController> center;
	private SearchController controller;
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of this class.
	 * 
	 * @param groups a vector with the name of the groups to show
	 * @param projects a vector with the name of the projects to show
	 */
	public SearchPanel(Vector<String> groups, Vector<String> projects) {
		this.controller = new SearchController(this);
		this.feed = new FeedButtonPanel(this);
		this.center = new CenterPanel<SearchController>(30, 65, groups, projects, new Dimension(150,150), controller);
		
		this.setLayout(new BorderLayout());
		this.add(feed, BorderLayout.NORTH);
		this.add(center,BorderLayout.CENTER);
	}


}
