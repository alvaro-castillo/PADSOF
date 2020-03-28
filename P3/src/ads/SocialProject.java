package ads;

/**
 * Subclass of Project. Represents a social one
 * 
 * @author Álvaro Castillo García
 * @author Alejandro Benimeli
 * @author Miguel Álvarez Valiente
 */
public class SocialProject extends Project {
	
	/**
	 * Association associated to the project
	 */
	private String association;
	
	/**
	 * Represents whether the association is national or not
	 */
	private boolean national;
	
	/**
	 * Description of the association
	 */
	private String associationDescription;
	
	/**
	 * Version for serialize objects
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor
	 * @param title Title of the project
	 * @param description Description of the project
	 * @param amount Amount requested
	 * @param creator Creator of the project
	 * @param association Association associated to the project
	 * @param national Is the association national?
	 * @param associationDescription Description of the association
	 */
	public SocialProject(String title, String description, double amount, 
			RegisteredUser creator, String association, boolean national, 
			String associationDescription) {
		super(title, description, amount, creator);
		this.association = association;
		this.national = national;
		this.associationDescription = associationDescription;
	}
	
	/**
	 * Getter for association
	 * @return association
	 */
	public String getAssociation() {
		return association;
	}
	
	/**
	 * Getter for national
	 * @return boolean telling you if the association is national
	 */
	public boolean getNational() {
		return national;
	}
	
	/**
	 * Getter for association description
	 * @return description of the association
	 */
	public String getAssociationDescription() {
		return associationDescription;
	}
	
	/**
	 * toString method override
	 */
	@Override
	public String toString() {
		return super.toString() + "Association: " + this.association + "   National: " + this.national
				+ "\nDescription of the Association: " + this.associationDescription;
	}

}