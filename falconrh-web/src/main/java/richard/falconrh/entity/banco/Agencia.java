package richard.falconrh.entity.banco;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import richard.falconrh.entity.Parent;
import richard.falconrh.util.FalconRHUtils;

/**
 * @author Richard Mendes Madureira
 * @version $Revision: 1.0 $
 */
@Entity
@Table(name = "AGENCIAS")
@GenericGenerator(name = "SEQ_GEN", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "SEQ_AGENCIAS") })
public class Agencia extends Parent implements Comparable<Agencia>{
	private static final long serialVersionUID = 1469865252733374207L;

	private String nome;
	private String numero;
	private String digitoVerificador;
	private Banco banco;
	private List<ContaCorrente> listaContasCorrentes;

	public Agencia(){
	}
	
	/**
	 * Constructor for Agencia.
	 * @param id Long
	 */
	public Agencia(Long id){
		this.id = id;
	}
	
	
	/**
	 * Method getNome.
	 * @return String
	 */
	@Column(length = 100, nullable = true)
	public String getNome() {
		return nome;
	}

	/**
	 * Method getNumero.
	 * @return String
	 */
	@Column(length = 5, nullable = false)
	public String getNumero() {
		return numero;
	}

	/**
	 * Method getDigitoVerificador.
	 * @return String
	 */
	@Column(length = 1, nullable = false)
	public String getDigitoVerificador() {
		return digitoVerificador;
	}
	
	/**
	 * Method getBanco.
	 * @return Banco
	 */
	@ManyToOne
	@JoinColumn(name="ID_BANCO", nullable=false)
	public Banco getBanco(){
		return banco;
	}
	
	/**
	 * Method getListaContasCorrentes.
	 * @return List<ContaCorrente>
	 */
	@OneToMany(targetEntity=ContaCorrente.class, cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	public List<ContaCorrente> getListaContasCorrentes(){
		return listaContasCorrentes;
	}
	
	/**
	 * Method setNome.
	 * @param nome String
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * Method setNumero.
	 * @param numero String
	 */
	public void setNumero(String numero) {
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
	 * Method setBanco.
	 * @param banco Banco
	 */
	public void setBanco(Banco banco){
		this.banco = banco;
	}
	
	/**
	 * Method setListaContasCorrentes.
	 * @param listaContasCorrentes List<ContaCorrente>
	 */
	public void setListaContasCorrentes(List<ContaCorrente> listaContasCorrentes){
		this.listaContasCorrentes = listaContasCorrentes;
	}
	
	/**
	 * Method getDescricao.
	 * @return String
	 */
	@Transient
	public String getNumeroFormatado(){
		if(StringUtils.isNotBlank(getDigitoVerificador())){
			return getNumero()+"-"+getDigitoVerificador() + "/" + getNome();
		}
		return getNumero()+ "/" + getNome();
	}
	
	/**
	 * Method isValida.
	 * @return boolean
	 */
	@Transient
	public boolean isValida(){
		String dv = FalconRHUtils.getDigitoVerificador(getNumero());
		return dv.equalsIgnoreCase(getDigitoVerificador());
	}

	/**
	 * Method compareTo.
	 * @param agencia2 Agencia
	 * @return int
	 */
	@Override
	public int compareTo(Agencia agencia2) {
		return this.getNumero().compareTo(agencia2.getNumero());
	}
	
}
