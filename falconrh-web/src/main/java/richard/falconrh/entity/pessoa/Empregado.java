package richard.falconrh.entity.pessoa;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.ForeignKey;

import richard.falconrh.modelo.enums.SituacaoFuncional;

/**
 * @author richard
 * @version $Revision: 1.0 $
 */
@Entity
@Table(name="EMPREGADOS")
@PrimaryKeyJoinColumn(name="ID_PESSOA", referencedColumnName="id")
@ForeignKey(name="fk_pessoa")
public class Empregado extends Pessoa{
	private static final long serialVersionUID = -5809485961666989193L;
	
	private Long matricula;
	private SituacaoFuncional situacaoFuncional;
	private Double salario;
	private Date dataContratacao;

	/**
	 * Method getMatricula.
	 * @return Long
	 */
	@Column(nullable=false)
	public Long getMatricula() {
		return matricula;
	}
	
	/**
	 * Method getsituacaoFuncional.
	 * @return SituacaoFuncional
	 */
	@Enumerated(value=EnumType.STRING)
	public SituacaoFuncional getSituacaoFuncional(){
		return situacaoFuncional;
	}
	
	@Column(nullable=false)
	public Double getSalario(){
		return salario;
	}
	
	@Temporal(value=TemporalType.DATE)
	public Date getDataContratacao(){
		return dataContratacao;
	}
	
	/**
	 * Method setMatricula.
	 * @param matricula Long
	 */
	public void setMatricula(Long matricula) {
		this.matricula = matricula;
	}
	
	/**
	 * Method setSituacaoFuncional.
	 * @param situacaoFuncional SituacaoFuncional
	 */
	public void setSituacaoFuncional(SituacaoFuncional situacaoFuncional){
		this.situacaoFuncional = situacaoFuncional;
	}
	
	public void setSalario(Double salario){
		this.salario = salario;
	}
	
	public void setDataContratacao(Date dataContratacao){
		this.dataContratacao = dataContratacao;
	}
	
}
