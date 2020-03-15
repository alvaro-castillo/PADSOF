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
	
	/**
	 * Version for serialize objects
	 */
	private static final long serialVersionUID = 1L;
	
	public SocialProject(String title, String description, double amount, 
			RegisteredUser creator, String association, boolean national, 
			String associationDescription) {
		super(title, description, amount, creator);
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