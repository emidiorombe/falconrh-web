package richard.falconrh.modelo.enums;

/**
 * @author richard
 * @version $Revision: 1.0 $
 */
public enum Nacionalidade {
	BRASILEIRO("Brasileiro(a)"),
	AMERICANO("Americano(a)"),
	ESPANHOL("Espanhol(a)");
	
	private String descricao;
	
	/**
	 * Constructor for Nacionalidade.
	 * @param descricao String
	 */
	private Nacionalidade(String descricao){
		this.descricao = descricao;
	}
	
	/**
	 * Method getDescricao.
	
	 * @return String */
	public String getDescricao(){
		return descricao;
	}
}
