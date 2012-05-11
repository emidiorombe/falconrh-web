package richard.falconrh.entity.inss;

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
@Table(name = "TAB_INSS")
@GenericGenerator(name = "SEQ_GEN", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "SEQ_TABELA_INSS") })
public class TabelaINSS extends Parent {
	private static final long serialVersionUID = 7231259256165330431L;

	private String descricao;
	private Date dataInicioVigencia;
	private Date dataTerminoVigencia;
	private List<AliquotaINSS> listaAliquotasINSS;

	public TabelaINSS() {
	}
	
	/**
	 * Constructor for TabelaINSS.
	 * @param id Long
	 */
	public TabelaINSS(Long id) {
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
	 * Method getListaAliquotasINSS.
	
	 * @return List<AliquotaINSS> */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tabelaINSS", cascade=CascadeType.ALL)
	public List<AliquotaINSS> getListaAliquotasINSS() {
		return listaAliquotasINSS;
	}

	/**
	 * Method setDescricao.
	 * @param descricao String
	 */
	public void setDescricao(String descricao){
		this.descricao = descricao;
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
	 * Method setListaAliquotasINSS.
	 * @param listaAliquotasINSS List<AliquotaINSS>
	 */
	public void setListaAliquotasINSS(List<AliquotaINSS> listaAliquotasINSS) {
		this.listaAliquotasINSS = listaAliquotasINSS;
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
