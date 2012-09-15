package richard.falconrh.entity.competencia;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import richard.falconrh.entity.Parent;

@Entity
@Table(name = "COMPETENCIAS")
@GenericGenerator(name = "SEQ_GEN", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "SEQ_COMPETENCIAS") })
public class Competencia extends Parent {
	private static final long serialVersionUID = 2471659975768002337L;

	private Date dataAbertura;
	private Date dataFechamento;
	private String descricao;

	public Competencia() {
	}

	public Competencia(String descricao) {
		this.descricao = descricao;
	}

	public Competencia(String descricao, Date dataAbertura, Date dataFechamento) {
		this.descricao = descricao;
		this.dataAbertura = dataAbertura;
		this.dataFechamento = dataFechamento;
	}

	public Date getDataAbertura() {
		return dataAbertura;
	}

	public Date getDataFechamento() {
		return dataFechamento;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDataAbertura(Date dataAbertura) {
		this.dataAbertura = dataAbertura;
	}

	public void setDataFechamento(Date dataFechamento) {
		this.dataFechamento = dataFechamento;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (!(obj instanceof Competencia)) {
			return false;
		}
		Competencia other = (Competencia) obj;
		if (descricao == null) {
			if (other.descricao != null) {
				return false;
			}
		} else if (!descricao.equals(other.descricao)) {
			return false;
		}
		return true;
	}

	@Transient
	public boolean isAberta(){
		return (dataFechamento==null || dataFechamento.after(new Date()));
	}
}
