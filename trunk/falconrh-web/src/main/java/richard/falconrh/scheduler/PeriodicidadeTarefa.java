package richard.falconrh.scheduler;

public enum PeriodicidadeTarefa {
	UNICA("Única"),
	HORARIA("Horária"),
	DIARIA("Diária"),
	SEMANAL("Semanal"),
	MENSAL("Mensal"),
	ANUAL("Anual");
	
	private String descricao;
	
	private PeriodicidadeTarefa(String descricao){
		this.descricao = descricao;
	}
	
	public String getDescricao(){
		return descricao;
	}
}
