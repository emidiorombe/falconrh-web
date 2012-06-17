package richard.falconrh.entity.seguranca;

import java.security.Principal;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import richard.falconrh.entity.Parent;

@Entity
@Table(name="FUNCIONALIDADES_ACOES")
@GenericGenerator(name = "SEQ_GEN", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "SEQ_FUNCIONALIDADES_ACOES") })
public class FuncionalidadeAcao extends Parent implements Principal{
	private static final long serialVersionUID = 1L;

	private Funcionalidade funcionalidade;
	private Acao acao;
		
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="ID_ACAO",nullable=false)
	public Acao getAcao() {
		return acao;
	}
	public void setAcao(Acao acao) {
		this.acao = acao;
	}
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="ID_FUNC",nullable=false)
	public Funcionalidade getFuncionalidade() {
		return funcionalidade;
	}
	public void setFuncionalidade(Funcionalidade funcionalidade) {
		this.funcionalidade = funcionalidade;
	}
	
	@Override
	@Transient
	public String getName() {
		return funcionalidade.getNome()+"_"+acao.getNome();
	}
	
}