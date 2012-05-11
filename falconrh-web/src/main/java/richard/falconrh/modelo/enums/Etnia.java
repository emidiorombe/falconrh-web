package richard.falconrh.modelo.enums;

/**
 * @author richard
 * @version $Revision: 1.0 $
 */
public enum Etnia {
	BRANCA("Branco(a)"),
	AMARELA("Amarelo(a)"),
	PARDA("Pardo(a)"),
	NEGRA("Negro(a)");
	
	private String descricao;

	/**
	 * Constructor for Etnia.
	 * @param descricao String
	 */
	private Etnia(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * Method getDescricao.
	
	 * @return String */
	public String getDescricao() {
		return descricao;
	}
}
