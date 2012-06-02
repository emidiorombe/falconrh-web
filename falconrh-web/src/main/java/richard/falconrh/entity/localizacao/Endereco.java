package richard.falconrh.entity.localizacao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import richard.falconrh.entity.Parent;

/**
 * @author richard
 * @version $Revision: 1.0 $
 */
@Entity
@Table(name="ENDERECOS")
@GenericGenerator(name = "SEQ_GEN", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "SEQ_ENDERECOS") })
public class Endereco extends Parent{
	private static final long serialVersionUID = -6245051799760450454L;

	private String numero;
	private String complemento;
	private Logradouro logradouro;
	
	public Endereco(){
	}
	
	/**
	 * Constructor for Endereco.
	 * @param id Long
	 */
	public Endereco(Long id){
		this.id = id;
	}
	
	/**
	 * Method getNumero.
	
	 * @return String */
	@Column(length=10, nullable=false)
	public String getNumero() {
		return numero;
	}

	/**
	 * Method getComplemento.
	
	 * @return String */
	@Column(length=255, nullable=true)
	public String getComplemento() {
		return complemento;
	}
	
	/**
	 * Method getLogradouro.
	
	 * @return Logradouro */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ID_LOGRADOURO", nullable=false)
	@ForeignKey(name="fk_logradouro")
	public Logradouro getLogradouro() {
		return logradouro;
	}

	/**
	 * Method setNumero.
	 * @param numero String
	 */
	public void setNumero(String numero) {
		this.numero = numero;
	}
	
	/**
	 * Method setComplemento.
	 * @param complemento String
	 */
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	/**
	 * Method setLogradouro.
	 * @param logradouro Logradouro
	 */
	public void setLogradouro(Logradouro logradouro) {
		this.logradouro = logradouro;
	}
}
