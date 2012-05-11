package richard.falconrh.entity.feriado;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import richard.falconrh.entity.Parent;
import richard.falconrh.modelo.enums.TipoFeriado;

/**
 * @author richard
 * @version $Revision: 1.0 $
 */
@Entity
@Table(name = "FERIADOS")
@GenericGenerator(name = "SEQ_GEN", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "SEQ_FERIADOS") })
public class Feriado extends Parent {
	private static final long serialVersionUID = 2568175855535442512L;

	private String descricao;
	private TipoFeriado tipoFeriado;
	private TabelaFeriado tabelaFeriado;
	private Date data;

	public Feriado(){
	}
	
	/**
	 * Constructor for Feriado.
	 * @param id Long
	 */
	public Feriado(Long id){
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
	 * Method getTipoFeriado.
	
	 * @return TipoFeriado */
	@Enumerated(value=EnumType.STRING)
	@Column(nullable=false)
	public TipoFeriado getTipoFeriado() {
		return tipoFeriado;
	}

	/**
	 * Method getTabelaFeriado.
	
	 * @return TabelaFeriado */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ID_TAB_FERIADO", nullable=false)
	public TabelaFeriado getTabelaFeriado() {
		return tabelaFeriado;
	}

	/**
	 * Method getData.
	
	 * @return Date */
	@Temporal(value=TemporalType.DATE)
	@Column(nullable=false)
	public Date getData() {
		return data;
	}

	/**
	 * Method setDescricao.
	 * @param descricao String
	 */
	public void setDescricao(String descricao){
		this.descricao = descricao;
	}
	
	/**
	 * Method setTipoFeriado.
	 * @param tipoFeriado TipoFeriado
	 */
	public void setTipoFeriado(TipoFeriado tipoFeriado) {
		this.tipoFeriado = tipoFeriado;
	}

	/**
	 * Method setTabelaFeriado.
	 * @param tabelaFeriados TabelaFeriado
	 */
	public void setTabelaFeriado(TabelaFeriado tabelaFeriados) {
		this.tabelaFeriado = tabelaFeriados;
	}

	/**
	 * Method setData.
	 * @param dataFeriado Date
	 */
	public void setData(Date dataFeriado) {
		this.data = dataFeriado;
	}

	/**
	 * Method hashCode.
	
	 * @return int */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result
				+ ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result
				+ ((tabelaFeriado == null) ? 0 : tabelaFeriado.hashCode());
		result = prime * result
				+ ((tipoFeriado == null) ? 0 : tipoFeriado.hashCode());
		return result;
	}

	/**
	 * Method equals.
	 * @param obj Object
	
	 * @return boolean */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (!(obj instanceof Feriado)) {
			return false;
		}
		Feriado other = (Feriado) obj;
		if (data == null) {
			if (other.data != null) {
				return false;
			}
		} else if (!data.equals(other.data)) {
			return false;
		}
		if (descricao == null) {
			if (other.descricao != null) {
				return false;
			}
		} else if (!descricao.equals(other.descricao)) {
			return false;
		}
		if (tabelaFeriado == null) {
			if (other.tabelaFeriado != null) {
				return false;
			}
		} else if (!tabelaFeriado.equals(other.tabelaFeriado)) {
			return false;
		}
		if (tipoFeriado != other.tipoFeriado) {
			return false;
		}
		return true;
	}

	
}