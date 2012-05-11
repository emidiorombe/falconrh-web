package richard.falconrh.entity.banco;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import richard.falconrh.entity.Parent;
import richard.falconrh.util.FalconRHUtils;

/**
 * @author richard
 * @version $Revision: 1.0 $
 */
@Entity
@Table(name = "CONTAS_CORRENTES")
@GenericGenerator(name = "SEQ_GEN", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "SEQ_CONTAS_CORRENTES") })
public class ContaCorrente extends Parent {
	private static final long serialVersionUID = -3904815687730764510L;

	private Agencia agencia;
	private String numero;
	private String digitoVerificador;

	public ContaCorrente(){
	}
	
	/**
	 * Constructor for ContaCorrente.
	 * @param id Long
	 */
	public ContaCorrente(Long id){
		this.id = id;
	}
	
	/**
	 * Method getAgencia.
	
	 * @return Agencia */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ID_AGENCIA", nullable=false)
	public Agencia getAgencia() {
		return agencia;
	}
	
	/**
	 * Method getNumero.
	
	 * @return String */
	@Column(length=8, nullable=false)
	public String getNumero(){
		return numero;
	}

	/**
	 * Method getDigitoVerificador.
	
	 * @return String */
	@Column(length=2, nullable=false)
	public String getDigitoVerificador() {
		return digitoVerificador;
	}

	/**
	 * Method setAgencia.
	 * @param agencia Agencia
	 */
	public void setAgencia(Agencia agencia) {
		this.agencia = agencia;
	}
	
	/**
	 * Method setNumero.
	 * @param numero String
	 */
	public void setNumero(String numero){
		this.numero = numero;
	}

	/**
	 * Method setDigitoVerificador.
	 * @param digitoVerificador String
	 */
	public void setDigitoVerificador(String digitoVerificador) {
		this.digitoVerificador = digitoVerificador;
	}
	
	/**
	 * Method getDescricao.
	
	 * @return String */
	@Transient
	public String getDescricao(){
		if(StringUtils.isNotBlank(getDigitoVerificador())){
			return getNumero() + "-"+getDigitoVerificador();
		}
		return getNumero();
	}

	/**
	 * Method isValida.
	
	 * @return boolean */
	@Transient
	public boolean isValida() {
		String dv = FalconRHUtils.getDigitoVerificador(getNumero());
		return dv.equalsIgnoreCase(getDigitoVerificador());
		
	}

}
