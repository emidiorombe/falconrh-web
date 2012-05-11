package richard.falconrh.modelo.enums;

/**
 * @author richard
 * @version $Revision: 1.0 $
 */
public enum TipoFeriado {
	NACIONAL(1, "Feriado Nacional"),
	NACIONAL_FACULTATIVO(2, "Feriado Nacional Facultativo"),
	ESTADUAL(3, "Feriado Estadual"),
	ESTADUAL_FACULTATIVO(4, "Feriado Estadual Facultativo"),
	MUNICIPIAL(5, "Feriado Municipal"),
	MUNICIPAL_FACULTATIVO(6, "Feriado Municipial Facultativo"),
	EXCEPCIONAL(7, "Feriado de Situacao Excepcional"),
	EXCEPCIONAL_FACULTATIVO(85, "Feriado de Situacao Excepcional Facultativo");
	
	private int codigo;
	private String descricao;
	
	/**
	 * Constructor for TipoFeriado.
	 * @param codigo int
	 * @param descricao String
	 */
	private TipoFeriado(int codigo, String descricao){
		this.codigo = codigo;
		this.descricao = descricao;
	}
	
	/**
	 * Method getCodigo.
	
	 * @return int */
	public int getCodigo(){
		return codigo;
	}
	
	/**
	 * Method getDescricao.
	
	 * @return String */
	public String getDescricao(){
		return descricao;
	}
}
