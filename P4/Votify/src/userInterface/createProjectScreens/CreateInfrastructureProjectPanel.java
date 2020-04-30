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
import userInterface.initialScreen.ImagePanel;

public class CreateInfrastructureProjectPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private FeedButtonPanel feed = new FeedButtonPanel(this);
	private JPanel mainPanel = new JPanel();
	private JLabel labelInfrastructureProject = new JLabel("Infrastructure Project");
	private JLabel labelImage = new JLabel("Upload an image of the project: ");
	private JButton browseButton = new JButton("Browse");
	private JPanel browsePanel = new JPanel();
	private ImagePanel imgPanel = new ImagePanel("no-image-1.jpg");
	private JPanel contImgPanel = new JPanel();
	private String path = null;
	private JComboBox<String> districtCBox = new JComboBox<String>();
	private JButton createButton = new JButton("Create");
	
	public CreateInfrastructureProjectPanel(CreateProjectController cpC) {
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.add(feed);
		this.add(Box.createRigidArea(new Dimension(0, 60)));
		
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
	    
	    contImgPanel.setSize(new Dimension(100,100));
	    
	    contImgPanel.add(imgPanel);
	    
	    c.gridy = yPos;
	    yPos++;
	    mainPanel.add(contImgPanel, c);
	    
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
	    			imgPanel = new ImagePanel(path);
	    			contImgPanel.removeAll();
	    			contImgPanel.add(imgPanel);
	    			contImgPanel.revalidate();
	    			contImgPanel.repaint();
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
	
	public String getPath() {
		return this.path;
	}
	
	public String getDistrict() {
		return districtCBox.getSelectedItem().toString();
	}
	
}













