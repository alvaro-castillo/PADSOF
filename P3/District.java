
package ads;

/**
 * Añadir al diagrama esta enumeración. 
 * These are the values which the District can take, the District enum. 
 * @author Miguel Álvarez Valiente, Alejandro Benimeli Miranda, Álvaro Castillo García
 *
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
	VICALVARO("Viáalvaro"),
	VILLA_DE_VALLECAS("Villa de Vallecas"),
	VILLAVERDE("Villaverde");
	
	private String districtName;

	private District(String districtName) {
		this.districtName = districtName;
	}

	public String getDistrictName() {
		return districtName;
	}
	
}
