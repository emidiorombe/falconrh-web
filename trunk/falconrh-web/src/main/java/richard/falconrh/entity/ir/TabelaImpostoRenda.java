package richard.falconrh.entity.ir;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import richard.falconrh.entity.Parent;

/**
 * @author richard
 * @version $Revision: 1.0 $
 */
@Entity
@Table(name = "TAB_IR")
@GenericGenerator(name = "SEQ_GEN", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "SEQ_TABELA_IR") })
public class TabelaImpostoRenda extends Parent {
	private static final long serialVersionUID = 7231259256165330431L;

	private String descricao;
	private Integer anoBase;
	private Date dataInicioVigencia;
	private Date dataTerminoVigencia;
	private List<AliquotaImpostoRenda> listaAliquotasImpostoRenda;

	public TabelaImpostoRenda() {
	}
	
	/**
	 * Constructor for TabelaImpostoRenda.
	 * @param id Long
	 */
	public TabelaImpostoRenda(Long id) {
		this.id = id;
	}

	/**
	 * Method getDescricao.
	
	 * @return String */
	@Column(length=100, nullable=false)
	public String getDescricao(){
		return descricao;
	}
	
	/**
	 * Method getAnoBase.
	
	 * @return Integer */
	@Column(nullable=false)
	public Integer getAnoBase(){
		return anoBase;
	}
	
	/**
	 * Method getDataInicioVigencia.
	
	 * @return Date */
	@Temporal(value = TemporalType.DATE)
	@Column(nullable = false)
	public Date getDataInicioVigencia() {
		return dataInicioVigencia;
	}

	/**
	 * Method getDataTerminoVigencia.
	
	 * @return Date */
	@Temporal(value = TemporalType.DATE)
	public Date getDataTerminoVigencia() {
		return dataTerminoVigencia;
	}

	/**
	 * Method getListaAliquotasImpostoRenda.
	
	 * @return List<AliquotaImpostoRenda> */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tabelaImpostoRenda", cascade=CascadeType.ALL)
	public List<AliquotaImpostoRenda> getListaAliquotasImpostoRenda() {
		return listaAliquotasImpostoRenda;
	}

	/**
	 * Method setDescricao.
	 * @param descricao String
	 */
	public void setDescricao(String descricao){
		this.descricao = descricao;
	}
	
	/**
	 * Method setAnoBase.
	 * @param anoBase Integer
	 */
	public void setAnoBase(Integer anoBase){
		this.anoBase = anoBase;
	}
	
	/**
	 * Method setDataInicioVigencia.
	 * @param dataInicioVigencia Date
	 */
	public void setDataInicioVigencia(Date dataInicioVigencia) {
		this.dataInicioVigencia = dataInicioVigencia;
	}

	/**
	 * Method setDataTerminoVigencia.
	 * @param dataTerminoVigencia Date
	 */
	public void setDataTerminoVigencia(Date dataTerminoVigencia) {
		this.dataTerminoVigencia = dataTerminoVigencia;
	}

	/**
	 * Method setListaAliquotasImpostoRenda.
	 * @param listaAliquotasImpostoRenda List<AliquotaImpostoRenda>
	 */
	public void setListaAliquotasImpostoRenda(List<AliquotaImpostoRenda> listaAliquotasImpostoRenda) {
		this.listaAliquotasImpostoRenda = listaAliquotasImpostoRenda;
	}

	/**
	 * Method isVigente.
	 * @param dataReferencia Date
	
	 * @return boolean */
	@Transient
	public boolean isVigente(Date dataReferencia){
		if(getDataInicioVigencia().before(dataReferencia) || getDataInicioVigencia()==dataReferencia){
			if(getDataTerminoVigencia()==null || getDataTerminoVigencia().after(dataReferencia) || getDataTerminoVigencia() == dataReferencia){
				return true;
			}
		}
		return false;
	}
}
