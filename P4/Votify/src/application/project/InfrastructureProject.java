package application.project;

import application.registeredUser.*;
import application.enums.District;

/**
 * Subclass of Project. Represents a infrastructure one 
 * @author Álvaro Castillo García
 * @author Alejandro Benimeli
 * @author Miguel Álvarez Valiente
 *
 */
public class InfrastructureProject extends Project {
	
	/**
	 * Disctric of the project
	 */
	private District district;
	
	/**
	 * Image path
	 */
	private String image;
	
	/**
	 * Version for serialize objects
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor
	 * 
	 * @param title Title of the project
	 * @param description Description of the project
	 * @param amount Amount requested for the project
	 * @param creator Creator of the project
	 * @param district District where the project will be done
	 * @param image Path to the image
	 */
	public InfrastructureProject(String title, String description, double amount, 
			RegisteredUser creator, District district, String image) {
		super(title, description, amount, creator);
		this.district = district;
		this.image = image;
	}
	
	/**
	 * Getter for district
	 * @return district of the project
	 */
	public District getDistrict() {
		return district;
	}
	
	/**
	 * Getter for image
	 * @return path of the image
	 */
	public String getImage() {
		return image;
	}
	
	/**
	 * toString method override
	 */
	@Override
	public String toString() {
		return super.toString() + "     Disctrict: " + this.district + "\n     Image path: " + this.image;
	}

}
