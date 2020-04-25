package application.enums;

/**
* These are the values which the Project Status can take, the ProjectSatus enum. 
*
* @author Miguel Ã�lvarez Valiente, Alejandro Benimeli Miranda, Ã�lvaro Castillo GarcÃ­a
* */
public enum ProjectStatus {
	WAITING_ACCEPTANCE("Waiting Admin Acceptance"),
	ADMIN_ACCEPTED("Accepted by Admin"),
	ADMIN_REJECTED("Rejected by Admin"),
	PENDING("Pending"),
	EXPIRED("Expired"),
	REJECTED("Rejected"),
	APPROVED("Approved");
	
	/**
	 * Description of each enumeration member
	 */
	private String descr;
	
	/**
	 * Constructor of ProjectStatus
	 * @param descr description of the enum member
	 */
	private ProjectStatus(String descr) {
		this.descr = descr;
	}
	
	/**
	 * toString override
	 */
	@Override
	public String toString() {
		return descr;
	}
	
}
