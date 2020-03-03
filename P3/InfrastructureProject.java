package app;

import java.time.LocalDate;

/**
 * 
 * @author Alejandro Benimeli
 *
 */
public class InfrastructureProject extends Project {
	
	private String district;
	private String image;
	
	public InfrastructureProject(String title, String description, double amount, 
			String district, String image) {
		super(title, description, amount);
		this.district = district;
		this.image = image;
	}
	
	public String getDistrict() {
		return district;
	}
	
	public String getImage() {
		return image;
	}

}
