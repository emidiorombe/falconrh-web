package richard.falconrh.modelo.enums;

public enum NivelAcesso {
	NACIONAL("Nacional"),
	ESTADUAL("Estadual"),
	MUNICIPIAL("Municipal"),
	LOCAL("Local"),
	FUNCIONAL("Funcional");
	
	private String nome;
	
	private NivelAcesso(String nome){
		this.nome = nome;
	}
	
	public String getNome(){
		return nome;
	}
}
