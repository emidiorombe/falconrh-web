package richard.falconrh.modelo.enums;

/**
 * @author richard
 * @version $Revision: 1.0 $
 */
public enum EstadoCivil {
	SOLTEIRO("Solteiro(a)"),
	CASADO("Casado(a)"),
	DESQUITADO("Desquitado(a)"),
	VIUVO("Viúvo(a)");
	
	private String descricao;

	/**
	 * Constructor for EstadoCivil.
	 * @param descricao String
	 */
	private EstadoCivil(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * Method getDescricao.
	
	 * @return String */
	public String getDescricao() {
		return descricao;
	}
}
