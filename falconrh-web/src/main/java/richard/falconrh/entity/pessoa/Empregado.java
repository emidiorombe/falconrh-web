package richard.falconrh.entity.pessoa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import richard.falconrh.modelo.enums.SituacaoFuncional;

/**
 * @author richard
 * @version $Revision: 1.0 $
 */
@Entity
@Table(name="EMPREGADOS")
@PrimaryKeyJoinColumn(name="ID_PESSOA", referencedColumnName="id")  
public class Empregado extends Pessoa{
	private static final long serialVersionUID = -5809485961666989193L;
	
	private Long matricula;
	private SituacaoFuncional situacaoFuncional;

	/**
	 * Method getMatricula.
	
	 * @return Long */
	@Column(nullable=false)
	public Long getMatricula() {
		return matricula;
	}
	
	/**
	 * Method getsituacaoFuncional.
	
	 * @return SituacaoFuncional */
	public SituacaoFuncional getsituacaoFuncional(){
		return situacaoFuncional;
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
	
}
