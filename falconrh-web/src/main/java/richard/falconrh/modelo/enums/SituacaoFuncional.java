package richard.falconrh.modelo.enums;

/**
 * @author richard
 * @version $Revision: 1.0 $
 */
public enum SituacaoFuncional {
	EM_ATIVIDADE(1, "Em Atividade"),
	CEDIDO_COM_ONUS(2, "Cedido com Ônus"),
	CEDIDO_SEM_ONUS(3, "Cedido sem Ônus"),
	CEDIDO_COM_RESSARCIMENTO(4, "Cedido com Ressarcimento"),
	LICENCA_GESTANTE(5, "Licença Gestante"),
	LICENCA_PATERNIDADE(6, "Licença Paternidade"),
	PRORROGACAO_LICENCA_GESTANTE(7, "Prorrogação da Licença Gestante"),
	PRORROGACAO_LICENTA_PATERNIDADE(8, "Prorrogação da Licença Paternidade"),
	EM_AFASTAMENTO(9, "Em Afastamento"),
	APOSENTADO_POR_TEMPO_DE_SERVICO(10, "Aposentado por Tempo de Serviço"),
	APOSENTADO_POR_INVALIDEZ(11, "Aposentado por Invalidez"),
	APOSENTADO_POR_IDADE(12, "Aposentado por Idade");
	
	private int codigo;
	private String descricao;
	
	/**
	 * Constructor for SituacaoFuncional.
	 * @param codigo int
	 * @param descricao String
	 */
	private SituacaoFuncional(int codigo, String descricao){
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
