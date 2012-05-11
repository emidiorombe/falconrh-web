package richard.falconrh.modelo.enums;

/**
 * @author richard
 * @version $Revision: 1.0 $
 */
public enum TipoVinculoEmpresa {
	EMPREGADO("Empregado(a)"),
	ESTAGIARIO("Estagiário(a)"),
	JOVEM_APRENDIZ("Jovem Aprendiz"),
	REQUISITADO("Requisitado(a)"),
	PENSAO_ALIMENTICIA("Pensão Alimentícia"),
	PENSAO_MORTE("Pensão por Morte"),
	EXTRA_QUADRO("Extra Quadro"),
	CONSELHEIRO("Conselheiro");
	
	private String descricao;
	
	/**
	 * Constructor for TipoVinculoEmpresa.
	 * @param descricao String
	 */
	private TipoVinculoEmpresa(String descricao){
		this.descricao = descricao;
	}
	
	/**
	 * Method getDescricao.
	
	 * @return String */
	public String getDescricao(){
		return descricao;
	}
}
