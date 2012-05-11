package richard.falconrh.entity.localizacao;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import richard.falconrh.entity.Parent;

/**
 * @author richard
 * @version $Revision: 1.0 $
 */
@Entity
@Table(name = "BAIRROS")
@GenericGenerator(name = "SEQ_GEN", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "SEQ_BAIRROS") })
public class Bairro extends Parent implements Comparable<Bairro>{
	private static final long serialVersionUID = 6695327704299775614L;

	private String nome;
	private Municipio municipio;
	private Set<Logradouro> listaLogradouros;

	public Bairro(){
	}
	
	/**
	 * Constructor for Bairro.
	 * @param id Long
	 */
	public Bairro(Long id){
		this.id = id;
	}
	
	/**
	 * Method getNome.
	
	 * @return String */
	@Column(length=255, nullable=false)
	public String getNome() {
		return nome;
	}

	/**
	 * Method getMunicipio.
	
	 * @return Municipio */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ID_MUNICIPIO", nullable=false)
	public Municipio getMunicipio() {
		return municipio;
	}
	
	/**
	 * Method getListaLogradouros.
	
	 * @return Set<Logradouro> */
	@OneToMany(mappedBy="bairro", targetEntity=Logradouro.class, fetch=FetchType.LAZY)
	public Set<Logradouro> getListaLogradouros(){
		return listaLogradouros;
	}
	
	/**
	 * Method setNome.
	 * @param nome String
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * Method setMunicipio.
	 * @param municipio Municipio
	 */
	public void setMunicipio(Municipio municipio) {
		this.municipio = municipio;
	}
	
	/**
	 * Method setListaLogradouros.
	 * @param listaLogradouros Set<Logradouro>
	 */
	public void setListaLogradouros(Set<Logradouro> listaLogradouros){
		this.listaLogradouros = listaLogradouros;
	}
	
	/**
	 * Method compareTo.
	 * @param bairro Bairro
	
	 * @return int */
	@Override
	public int compareTo(Bairro bairro) {
		if(this.getId()!=null & bairro.getId()!=null){
			return this.getId().compareTo(bairro.getId());
		}
		if(StringUtils.isNotBlank(this.getNome()) && StringUtils.isNotBlank(bairro.getNome())){
			return this.getNome().compareTo(bairro.getNome());
		}
		return 0;
	}
}
