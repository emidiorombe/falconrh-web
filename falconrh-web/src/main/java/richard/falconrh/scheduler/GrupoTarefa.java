package richard.falconrh.scheduler;

public enum GrupoTarefa {
	RELATORIOS("Geração de Relatorios"),
	GERACAO_ARQUIVOS("Geração de Arquivos"),
	PROCESSAMENTO("Processamento automáticos"),
	OUTROS("Outros");
	
	private String descricao;
	
	private GrupoTarefa(String descricao){
		this.descricao = descricao;
	}
	
	public String getDescricao(){
		return descricao;
	}
}
