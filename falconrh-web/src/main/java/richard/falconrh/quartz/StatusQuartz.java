package richard.falconrh.quartz;

public enum StatusQuartz {
	DESLIGADO("Desligado"),
	HIBERNADO("Hibernado"),
	INICIADO("Iniciado");
	
	private String descricao;
	
	private StatusQuartz(String descricao){
		this.descricao = descricao;
	}
	
	public String getDescricao(){
		return descricao;
	}
}
