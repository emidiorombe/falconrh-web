package richard.falconrh.entity.localizacao;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Index;
import org.hibernate.annotations.Parameter;

import richard.falconrh.entity.Parent;
import richard.falconrh.modelo.enums.UF;

/**
 * Classe que representa um Município
 * @author Richard Mendes Madureira
 * @version 1.0
 */
@Entity
@Table(name="MUNICIPIOS")
@GenericGenerator(name = "SEQ_GEN", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "SEQ_MUNICIPIOS") })
public class Municipio extends Parent implements Comparable<Municipio> {
	private static final long serialVersionUID = 6404540235172004648L;
	
	private String nome;
	private UF uf;
	private List<Bairro> listaBairros;

	public Municipio() {
	}
	
	/**
	 * Constructor for Municipio.
	 * @param id Long
	 */
	public Municipio(Long id){
		this.id = id;
	}

	/**
	 * Method getNome.
	
	 * @return String */
	@Column(length=255, nullable=false, name="NOME")
	@Index(name="IDX_MUNICIPIOS_NOME")
	public String getNome() {
		return nome;
	}

	/**
	 * Method getUf.
	
	 * @return UF */
	@Enumerated(value=EnumType.STRING)
	@Column(length=19, name="UF")
	public UF getUf() {
		return uf;
	}
	
	/**
	 * Method getListaBairros.
	
	 * @return List<Bairro> */
	@OneToMany(mappedBy="municipio", targetEntity=Bairro.class, fetch=FetchType.LAZY)
	public List<Bairro> getListaBairros(){
		return listaBairros;
	}

	/**
	 * Method setNome.
	 * @param nome String
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * Method setUf.
	 * @param uf UF
	 */
	public void setUf(UF uf) {
		this.uf = uf;
	}
	
	/**
	 * Method setListaBairros.
	 * @param listaBairros List<Bairro>
	 */
	public void setListaBairros(List<Bairro> listaBairros){
		this.listaBairros = listaBairros;
	}

	/**
	 * Method hashCode.
	
	 * @return int */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((uf == null) ? 0 : uf.hashCode());
		return result;
	}

	/**
	 * Method equals.
	 * @param obj Object
	
	 * @return boolean */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (!(obj instanceof Municipio))
			return false;
		Municipio other = (Municipio) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (uf != other.uf)
			return false;
		return true;
	}
	
	/**
	 * Method compareTo.
	 * @param municipio Municipio
	
	 * @return int */
	@Override
	public int compareTo(Municipio municipio) {
		System.out.println("Comparando município com " + municipio.getId() + "- " + municipio.getNome());
		if(this.getId()!=null && municipio.getId()!=null){
			return this.getId().compareTo(municipio.getId());
		}
		if(StringUtils.isNotBlank(this.getNome())&& StringUtils.isNotBlank(municipio.getNome())){
			return this.getNome().compareTo(municipio.getNome());
		}
		return 0;
	}
	
	
	
}