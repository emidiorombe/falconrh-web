package richard.falconrh.scheduler;

public enum StatusAgendamento {
	AGENDADO("Agendado"),
	EM_EXECUCAO("Em Execução"),
	INTERROMPIDO("Interrompido"),
	CANCELADO("Cancelado"),
	EXCLUIDO("Excluído");
	
	private String descricao;
	
	private StatusAgendamento(String descricao){
		this.descricao = descricao;
	}
	
	public String getDescricao(){
		return descricao;
	}
}
