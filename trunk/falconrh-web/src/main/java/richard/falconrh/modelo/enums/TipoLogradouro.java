package richard.falconrh.modelo.enums;

/**
 * @author richard
 * @version $Revision: 1.0 $
 */
public enum TipoLogradouro {
	OUTROS("Outros"),										
	AEROPORTO("Aeroporto"),
	ALAMEDA("Alameda"),
	AREA("Área"),
	AVENIDA("Avenida"),														
	CAMPO("Campo"),
	CHACARA("Chácara"),
	COLONIA("Colônia"),
	CONDOMINIO("Condomínio"),
	CONJUNTO("Conjunto"),
	DISTRITO("Distrito"),
	ESPLANADA("Esplanada"),
	ESTACAO("Estação"),
	ESTRADA("Estrada"),
	FAVELA("Favela"),
	FAZENDA("Fazenda"),
	FEIRA("Feira"),
	JARDIM("Jardim"),
	LADEIRA("Ladeira"),
	LAGO("Lago"),
	LAGOA("Lagoa"),
	LARGO("Largo"),
	LOTEAMENTO("Loteamento"),
	MORRO("Morro"),
	NUCLEO("Núcleo"),
	PARQUE("Parque"),
	PASSARELA("Passarela"),
	PATIO("Pátio"),
	PRACA("Praça"),
	QUADRA("Quadra"),
	RECANDO("Recanto"),
	RESIDENCIAL("Residencial"),
	RODOVIA("Rodovia"),
	RUA("Rua"),
	SETOR("Setor"),
	SITIO("Sítio"),
	TRAVESSA("Travessa"),
	TRECHO("Trecho"),
	TREVO("Trevo"),
	VALE("Vale"),
	VEREDA("Vereda"),
	VIA("Via"),
	VIADUTO("Viaduto"),
	VIELA("Viela"),
	VILA("Vila");
	
	private String nome;

	/**
	 * Constructor for TipoLogradouro.
	 * @param nome String
	 */
	private TipoLogradouro(String nome) {
		this.nome = nome;
	}

	/**
	 * Method getNome.
	
	 * @return String */
	public String getNome() {
		return nome;
	}
}
