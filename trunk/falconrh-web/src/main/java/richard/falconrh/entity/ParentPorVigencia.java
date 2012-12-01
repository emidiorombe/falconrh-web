package richard.falconrh.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Classe base para criação de entidades que possuem datas de inicio e termino de vigências
 * @author Richard Mendes Madureira
 */
@MappedSuperclass
public class ParentPorVigencia extends Parent {
	private static final long serialVersionUID = 1L;

	private Date dataInicioVigencia;
	private Date dataTerminoVigencia;

	@Temporal(TemporalType.DATE)
	@Column(name = "DT_INIC_VGNC", nullable = false)
	public Date getDataInicioVigencia() {
		return dataInicioVigencia;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DT_TERM_VGNC", nullable = false)
	public Date getDataTerminoVigencia() {
		return dataTerminoVigencia;
	}

	public void setDataInicioVigencia(Date dataInicioVigencia) {
		this.dataInicioVigencia = dataInicioVigencia;
	}

	public void setDataTerminoVigencia(Date dataTerminoVigencia) {
		this.dataTerminoVigencia = dataTerminoVigencia;
	}

}