package richard.falconrh.modelo.enums;

public enum SituacaoFolha {
	DEFINITIVA("Situação Definitiva"),
	PREVIA("Situação Prévia");
	
	private String descricao;
	
	private SituacaoFolha(String descricao){
		this.descricao = descricao;
	}
	
	public String getDescricao(){
		return descricao;
	}
}
