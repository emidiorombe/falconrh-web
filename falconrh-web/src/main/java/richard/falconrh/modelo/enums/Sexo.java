package richard.falconrh.modelo.enums;

/**
 * @author richard
 * @version $Revision: 1.0 $
 */
public enum Sexo {
	MASCULINO("Masculino"), FEMININO("Feminino");

	private String descricao;

	/**
	 * Constructor for Sexo.
	 * @param descricao String
	 */
	private Sexo(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * Method getDescricao.
	
	 * @return String */
	public String getDescricao() {
		return descricao;
	}
}
