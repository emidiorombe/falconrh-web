package richard.falconrh.entity.localizacao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.ForeignKey;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import richard.falconrh.entity.Parent;
import richard.falconrh.modelo.enums.TipoLogradouro;

/**
 * @author Richard Mendes Madureira
 * @version 1.0
 */
@Entity
@Table(name="LOGRADOUROS")
@GenericGenerator(name = "SEQ_GEN", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "SEQ_LOGRADOUROS") })
public class Logradouro extends Parent implements Comparable<Logradouro>{
	private static final long serialVersionUID = -768435063368126425L;

	private TipoLogradouro tipoLogradouro;
	private String nome;
	private Bairro bairro;
	private Long cep;

	public Logradouro() {
	}
	
	/**
	 * Constructor for Logradouro.
	 * @param id Long
	 */
	public Logradouro(Long id){
		this.id = id;
	}

	/**
	 * Method getTipoLogradouro.
	 * @return TipoLogradouro
	 */
	@Enumerated(value=EnumType.STRING)
	@Column(nullable=false, length=30)
	public TipoLogradouro getTipoLogradouro() {
		return tipoLogradouro;
	}

	/**
	 * Method getNome.
	 * @return String 
	 */
	@Column(length=255, nullable=false)
	public String getNome() {
		return nome;
	}

	/**
	 * Method getBairro.
	 * @return Bairro
	 */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ID_BAIRRO", nullable=false)
	@ForeignKey(name="fk_bairro")
	public Bairro getBairro() {
		return bairro;
	}
	
	/**
	 * Method getCep.
	
	 * @return Long */
	@Column(length=8, nullable=false)
	public Long getCep(){
		return cep;
	}

	/**
	 * Method setTipoLogradouro.
	 * @param tipoLogradouro TipoLogradouro
	 */
	public void setTipoLogradouro(TipoLogradouro tipoLogradouro) {
		this.tipoLogradouro = tipoLogradouro;
	}

	/**
	 * Method setNome.
	 * @param descricao String
	 */
	public void setNome(String descricao) {
		this.nome = descricao;
	}

	/**
	 * Method setBairro.
	 * @param bairro Bairro
	 */
	public void setBairro(Bairro bairro) {
		this.bairro = bairro;
	}
	
	/**
	 * Method setCep.
	 * @param cep Long
	 */
	public void setCep(Long cep){
		this.cep = cep;
	}

	@Override
	public int compareTo(Logradouro o) {
		if(this.getId()!=null & bairro.getId()!=null){
			return this.getId().compareTo(bairro.getId());
		}
		if(StringUtils.isNotBlank(this.getNome()) && StringUtils.isNotBlank(bairro.getNome())){
			return this.getNome().compareTo(bairro.getNome());
		}
		return 0;
	}
}
