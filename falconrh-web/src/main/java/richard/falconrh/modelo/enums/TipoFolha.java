package richard.falconrh.modelo.enums;

/**
 * @author richard
 * @version $Revision: 1.0 $
 */
public enum TipoFolha {
	NORMAL("Folha Normal"),
	ADIANTAMENTO_FERIAS("Folha de Adiantamento de Férias"),
	ADIANTAMENTO_DECIMO_TERCEIRO("Folha de Adiantamento de 13°"),
	DECIMO_TERCEIRO(" Folha de Décimo Tercerio"),
	SUPLEMENTAR("Folha Suplementar"),
	EXTRAFOLHA("Folha Extra");
	
	private String descricao;
	
	/**
	 * Constructor for TipoFolha.
	 * @param descricao String
	 */
	private TipoFolha(String descricao){
		this.descricao = descricao;
	}

	/**
	 * Method getDescricao.
	
	 * @return String */
	public String getDescricao() {
		return descricao;
	}
}