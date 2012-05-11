package richard.falconrh.modelo.enums;

/**
 * @author richard
 * @version $Revision: 1.0 $
 */
public enum TipoTelefone {
	CELULAR("Telefone Celular"),
	RESIDENCIAL("Telefone Residencial"),
	CORPORATIVO("Telefone Corporativo");
	
	private String descricao;

	/**
	 * Constructor for TipoTelefone.
	 * @param descricao String
	 */
	private TipoTelefone(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * Method getDescricao.
	
	 * @return String */
	public String getDescricao() {
		return descricao;
	}
}
