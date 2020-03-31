
package application.enums;

/**
 * These are the values which the District can take, the District enum. 
 * @author Miguel Álvarez Valiente, Alejandro Benimeli Miranda, Álvaro Castillo García
 */
public enum District {
	ARGANZUELA("Arganzuela"),
	BARAJAS("Barajas"),
	CARABANCHEL("Carabanchel"),
	CENTRO("Centro"),
	CHAMARTIN("Chamartín"),
	CHAMBERI("Chamberí"),
	CIUDAD_LINEAL("Ciudad Lineal"),
	FUENCARRAL_EL_PARDO("Fuencarral-El_Pardo"),
	HORTALEZA("Hortaleza"),
	LATINA("Latina"),
	MONCLOA_ARAVACA("Moncloa-Aravaca"),
	MORATALAZ("Moratalaz"),
	PUENTE_DE_VALLECAS("Puente de Vallecas"),
	RETIRO("Retiro"),
	SALAMANCA("Salamanca"),
	SAN_BLAS_CANILLEJAS("San Blas-Canillejas"),
	TETUAN("Tetuán"),
	USERA("Usera"),
	VICALVARO("Vicálvaro"),
	VILLA_DE_VALLECAS("Villa de Vallecas"),
	VILLAVERDE("Villaverde");
	
	private String districtName;
	
	/**
	 * Constructor of this enumeration.
	 *
	 * @param districtName
	 */
	private District(String districtName) {
		this.districtName = districtName;
	}

	/**
	 * districtName getter
	 * @return districtName
	 */
	public String getDistrictName() {
		return districtName;
	}
	
}
