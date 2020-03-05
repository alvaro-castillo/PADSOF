package ads;

import java.time.LocalDate;

/**
 * 
 * @author Alejandro Benimeli
 *
 */
public class SocialProject extends Project {
	
	private String association;
	private boolean national;
	private String associationDescription;
	
	public SocialProject(String title, String description, double amount, 
			String association, boolean national, String associationDescription) {
		super(title, description, amount);
		this.association = association;
		this.national = national;
		this.associationDescription = associationDescription;
	}
	
	public String getAssociation() {
		return association;
	}
	
	public boolean getNational() {
		return national;
	}
	
	public String getAssociationDescription() {
		return associationDescription;
	}

}