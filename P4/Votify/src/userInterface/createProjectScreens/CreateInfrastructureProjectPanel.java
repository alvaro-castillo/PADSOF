package userInterface.createProjectScreens;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import application.enums.District;
import userInterface.commonElements.FeedButtonPanel;

/**
 * This class represents the screen to create an infrastructure project.
 * It has a label that asks the user for an image, and a button to browse for that 
 * image, and an area where that image is shown (in the beginning a default image is
 * shown). It also contains a CBox in order to chose the district of the project and a
 * create button, which is used when all the fields are filled in, and leads you to 
 * the feed screen. At the end of this step the project has been created.
 * There is also a feed button, to be used when you want to quit the creation of a
 * project and return to the user feed screen.
 * 
 * @author Miguel Álvarez Valiente, Alejandro Benimeli Miranda, Álvaro Castillo García
 *
 */
public class CreateInfrastructureProjectPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private FeedButtonPanel feed = new FeedButtonPanel(this);
	private JPanel mainPanel = new JPanel();
	private JLabel labelInfrastructureProject = new JLabel("Infrastructure Project");
	private JLabel labelImage = new JLabel("Upload an image of the project: ");
	private JButton browseButton = new JButton("Browse");
	private JPanel browsePanel = new JPanel();
	private ImagePanel imgPanel = new ImagePanel("no-image-1.jpg");
	private String path = null;
	private JComboBox<String> districtCBox = new JComboBox<String>();
	private JButton createButton = new JButton("Create");
	
	/**
	 * Constructor for this class
	 * 
	 * @param cpC Reference to the class with all the controllers
	 */
	public CreateInfrastructureProjectPanel(CreateProjectController cpC) {
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.add(feed);
		this.add(Box.createRigidArea(new Dimension(0, 30)));
		
		mainPanel.setBorder(new EmptyBorder(20,20,20,20));
		mainPanel.setLayout(new GridBagLayout());
		
	    GridBagConstraints c = new GridBagConstraints(); 
	
	    c.fill = GridBagConstraints.NONE;
	    c.weightx = 1.0;
	    c.weighty = 1.0;
	    c.gridwidth = 1;
	    c.gridheight = 1;
	    c.weightx = 1.0;
	    c.weighty = 1.0;
	    
	    labelInfrastructureProject.setFont(new Font(labelInfrastructureProject.getFont().getName(), Font.PLAIN, 30));
	    
	    int yPos = 0;
	    
	    c.ipady = 0;
	    c.gridy = yPos;
	    yPos++;
	    mainPanel.add(labelInfrastructureProject, c);
	    
	    c.gridy = yPos;
	    yPos++;
	    mainPanel.add(new JSeparator(JSeparator.VERTICAL), c);
	    
	    browsePanel.setLayout(new GridLayout(0,2));
	    browsePanel.add(labelImage);
	    browsePanel.add(browseButton);
	    
	    c.gridy = yPos;
	    yPos++;
	    mainPanel.add(browsePanel, c);
	    
	    c.gridy = yPos;
	    int imgPos = yPos;
	    yPos++;
	    mainPanel.add(imgPanel, c);
	    
	    browseButton.addActionListener(new ActionListener() {
	    	
	    public void actionPerformed(ActionEvent e) {
	    	
	    	try {
	    		
		    	JFileChooser file = new JFileChooser();
	    		file.setCurrentDirectory(new File(System.getProperty("user.home")));
	    		FileNameExtensionFilter filter = new FileNameExtensionFilter("*.Images", "jpg", "png");
	    		file.addChoosableFileFilter(filter);
	    		int res = file.showSaveDialog(null);
	    		if (res == JFileChooser.APPROVE_OPTION) {
	    			File selectedFile = file.getSelectedFile();
	    			path = selectedFile.getAbsolutePath();
	    			c.gridy = imgPos;
	    			mainPanel.remove(imgPanel);
	    			imgPanel = new ImagePanel(path);
	    			c.gridy = imgPos;
	    		    mainPanel.add(imgPanel, c);
	    		    mainPanel.revalidate();
	    		    mainPanel.repaint();
	    		} else if (res == JFileChooser.CANCEL_OPTION) {
	    			JOptionPane.showMessageDialog(cpC.getCip(), "There was a problem with the selected image", "Error", JOptionPane.ERROR_MESSAGE);
	    		}
	    	} catch(Exception ex) {
    			JOptionPane.showMessageDialog(cpC.getCip(), "There was a problem with the selected image", "Error", JOptionPane.ERROR_MESSAGE);
    		}

    		
    	}
	    	
	    });
	    
	    District districts[] = District.values(); 
	    for (District d : districts) {
	    	districtCBox.addItem(d.getDistrictName());
	    }
	    
	    c.ipady = 10;
	    c.gridy = yPos;
	    yPos++;
	    mainPanel.add(districtCBox, c);
	
	    c.ipady = 0;
	    c.gridy = yPos;
	    yPos++;
	    mainPanel.add(new JSeparator(JSeparator.VERTICAL), c);
	    
	    c.gridy = yPos;
	    yPos++;
	    mainPanel.add(createButton, c);
	    
	    this.createButton.addActionListener(cpC.cipC);
	    
	    this.add(mainPanel);
		
	}
	
	/**
	 * Getter of the path of the chosen image. Used by the controller
	 * 
	 * @return String with the path of the image
	 */
	public String getPath() {
		return this.path;
	}
	
	/**
	 * Getter of the option of the district CBox selected. Used by the controller
	 * 
	 * @return String of the item of the district CBox
	 */
	public String getDistrict() {
		return districtCBox.getSelectedItem().toString();
	}
	
}













