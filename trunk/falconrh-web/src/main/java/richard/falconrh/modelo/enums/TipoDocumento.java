package richard.falconrh.modelo.enums;

/**
 * @author richard
 * @version $Revision: 1.0 $
 */
public enum TipoDocumento {
	CPF("CPF"),
	RG("Registro de Identidade"),
	TITULO_ELEITOR("Título de Eleitor"),
	CARTEIRA_DE_TRABALHO("Carteira de Trabalho"),
	CARTEIRA_DE_MOTORISTA("Carteira de Motorista"),
	RIC("Registro de Identidade Civil"),
	CERTIFICADO_RESERVISTA("Certificado de Reservista");
	
	private String descricao;

	/**
	 * Constructor for TipoDocumento.
	 * @param descricao String
	 */
	private TipoDocumento(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * Method getDescricao.
	
	 * @return String */
	public String getDescricao() {
		return descricao;
	}
}
