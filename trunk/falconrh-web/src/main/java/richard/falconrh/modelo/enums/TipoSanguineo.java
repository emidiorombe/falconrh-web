package richard.falconrh.modelo.enums;

/**
 * @author richard
 * @version $Revision: 1.0 $
 */
public enum TipoSanguineo{
	A_POSITIVO("A+"),
	B_POSITIVO("B+"),
	AB_POSITIVO("AB+"),
	O_POSITIVO("O+"),
	A_NEGATIVO("A-"),
	B_NEGATIVO("B-"),
	AB_NEGATIVO("AB-"),
	O_NEGATIVO("O-");
	
	private String tipo;
	
	/**
	 * Constructor for TipoSanguineo.
	 * @param tipo String
	 */
	private TipoSanguineo(String tipo){
		this.tipo = tipo;
	}
	
	/**
	 * Method getTipo.
	
	 * @return String */
	public String getTipo(){
		return tipo;
	}
}
