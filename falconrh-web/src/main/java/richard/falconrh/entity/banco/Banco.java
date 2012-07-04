package richard.falconrh.entity.banco;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import richard.falconrh.entity.Parent;

/**
 * Entidade que representa um Banco no Sistema.
 * @author Richard Mendes Madureira
 * @version $Revision: 1.0 $
 */
@Entity
@Table(name = "BANCOS")
@GenericGenerator(name = "SEQ_GEN", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "SEQ_BANCOS") })
public class Banco extends Parent implements Comparable<Banco>{
	private static final long serialVersionUID = -449185111258269244L;

	private String codigoFebraban;
	private String nome;
	private Set<Agencia> listaAgencias;

	/**
	 * Construtor Padrão
	 */
	public Banco(){
	}
	
	/**
	 * Construtor para Banco.
	 * @param id Long - O id do banco
	 */
	public Banco(Long id){
		this.id = id;
	}
	
	/**
	 * Construtor para BAnco
	 * @param codigoFebraban - o código do banco, segundo a Febraban
	 * @param nome - o nome do banco
	 */
	public Banco(String codigoFebraban, String nome){
		this.codigoFebraban = codigoFebraban;
		this.nome = nome;
	}
	
	/**
	 * Método que retorna o código go banco
	 * @return String o codigo do banco, segundo a Febraban
	 */
	@Column(length=3, nullable=false)
	public String getCodigoFebraban() {
		return codigoFebraban;
	}
	
	/**
	 * Method getNome.
	 * @return String - o nome do banco
	 */
	@Column(length=100, nullable=false)
	public String getNome() {
		return nome;
	}
	
	/**
	 * Method getListaAgencias.
	 * @return Set<Agencia>
	 */
	@OneToMany(mappedBy="banco", cascade=CascadeType.ALL, orphanRemoval=true)
	public Set<Agencia> getListaAgencias() {
		return listaAgencias;
	}

	/**
	 * Method setCodigoFebraban.
	 * @param codigoFebraban String
	 */
	public void setCodigoFebraban(String codigoFebraban) {
		this.codigoFebraban = codigoFebraban;
	}
	
	/**
	 * Method setNome.
	 * @param nome String
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * Method setListaAgencias.
	 * @param listaAgencias Set<Agencia>
	 */
	public void setListaAgencias(Set<Agencia> listaAgencias) {
		this.listaAgencias = listaAgencias;
	}

	/**
	 * Method getDescricao.
	 * @return String
	 */
	@Transient
	public String getDescricao(){
		return getCodigoFebraban() + " - " + getNome();
	}

	/**
	 * Method compareTo.
	 * @param b2 Banco
	 * @return int
	 */
	@Override
	public int compareTo(Banco b2) {
		if(StringUtils.isNotBlank(this.getCodigoFebraban()) && StringUtils.isNotBlank(b2.getCodigoFebraban())){
			return this.getCodigoFebraban().compareTo(b2.getCodigoFebraban());
		} else if(StringUtils.isNotBlank(this.getNome()) && StringUtils.isNotBlank(b2.getNome())){
			return this.getNome().compareTo(b2.getNome());
		}
		return 0;
	}
}