package ads;

import java.time.LocalDate;

/**
 * Subclass of Project. Represents a type 
 * 
 * @author Álvaro Castillo García
 * @author Alejandro Benimeli
 * @author Miguel Álvarez Valiente
 *
 */
public class InfrastructureProject extends Project {
	
	private District district;
	private String image;
	
	public InfrastructureProject(String title, String description, double amount, 
			RegisteredUser creator, District district, String image) {
		super(title, description, amount, creator);
		this.district = district;
		this.image = image;
	}
	
	public District getDistrict() {
		return district;
	}
	
	public String getImage() {
		return image;
	}

}
