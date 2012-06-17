package richard.falconrh.entity.seguranca;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import richard.falconrh.entity.Parent;

@Entity
@Table(name = "PERF_FUNC_ACAO")
@GenericGenerator(name = "SEQ_GEN", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "SEQ_PERF_FUNC_ACAO") })
public class PerfilaAcessoFuncionalidadeAcao extends Parent {
	private static final long serialVersionUID = 3611296341359361798L;

	private PerfilAcesso perfilAcesso;
	private FuncionalidadeAcao funcionalidadeAcao;

	public PerfilaAcessoFuncionalidadeAcao() {
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_FUNC_ACAO", nullable = false)
	public FuncionalidadeAcao getFuncionalidadeAcao() {
		return funcionalidadeAcao;
	}

	public void setFuncionalidadeAcao(FuncionalidadeAcao funcionalidadeAcao) {
		this.funcionalidadeAcao = funcionalidadeAcao;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_PERF", nullable = false)
	public PerfilAcesso getPerfilAcesso() {
		return perfilAcesso;
	}

	public void setPerfilAcesso(PerfilAcesso perfilAcesso) {
		this.perfilAcesso = perfilAcesso;
	}
}