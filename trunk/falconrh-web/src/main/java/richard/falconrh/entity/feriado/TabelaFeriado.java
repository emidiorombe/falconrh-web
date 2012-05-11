package richard.falconrh.entity.feriado;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import richard.falconrh.entity.Parent;

/**
 * @author richard
 * @version $Revision: 1.0 $
 */
@Entity
@Table(name = "TAB_FERIADOS")
@GenericGenerator(name = "SEQ_GEN", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "SEQ_TAB_FERIADOS") })
public class TabelaFeriado extends Parent {
	private static final long serialVersionUID = -4553698889509765314L;

	private String descricao;
	private Integer ano;
	private List<Feriado> listaFeriados;

	public TabelaFeriado(){
	}
	
	/**
	 * Constructor for TabelaFeriado.
	 * @param id Long
	 */
	public TabelaFeriado(Long id) {
		this.id = id;
	}

	/**
	 * Method getDescricao.
	
	 * @return String */
	@Column(length=255, nullable=false)
	public String getDescricao(){
		return descricao;
	}
	
	/**
	 * Method getAno.
	
	 * @return Integer */
	@Column(nullable=false, length=4)
	public Integer getAno() {
		return ano;
	}

	/**
	 * Method getListaFeriados.
	
	 * @return List<Feriado> */
	@OneToMany(fetch = FetchType.LAZY, mappedBy="tabelaFeriado", cascade=CascadeType.ALL)
	public List<Feriado> getListaFeriados() {
		return listaFeriados;
	}

	/**
	 * Method setDescricao.
	 * @param descricao String
	 */
	public void setDescricao(String descricao){
		this.descricao = descricao;
	}
	
	/**
	 * Method setAno.
	 * @param ano Integer
	 */
	public void setAno(Integer ano) {
		this.ano = ano;
	}

	/**
	 * Method setListaFeriados.
	 * @param listaFeriados List<Feriado>
	 */
	public void setListaFeriados(List<Feriado> listaFeriados) {
		this.listaFeriados = listaFeriados;
	}
}
