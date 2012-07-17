package richard.falconrh.web.bean;

import java.util.ArrayList;
import java.util.Set;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import richard.falconrh.exception.ServicesException;
import richard.falconrh.scheduler.Tarefa;
import richard.falconrh.service.TarefaServices;

/**
 * ManagedBean JSF utilizado em arquivos xhtml
 * @author Richard Mendes Madureira
 * @version $Revision: 1.0 $
 */
@ManagedBean(name="tarefaBean")
@ViewScoped
public class TarefaBean extends BaseBean<Tarefa, TarefaServices>{
	private static final long serialVersionUID = -7205911761531138512L;
	
	@EJB(name="ejb/TarefaServices")
	private TarefaServices tarefaServices;
	
	public TarefaBean(){
		super();
	}
	
	@Override
	public void inicializaEntity() {
		setEntity(new Tarefa());
		setListaEntities(new ArrayList<Tarefa>());
	}
	
	/**
	 * Method getServices.
	 * @return tarefaServices 
	 */
	@Override
	public TarefaServices getServices() {
		return tarefaServices;
	}

	@Override
	public void setMensagensInformativas() {
		SUCESSO_CADASTRO = "sucesso.cadastro.tarefa";
		SUCESSO_ATUALIZACAO = "sucesso.atualizacao.tarefa";
		SUCESSO_EXCLUSAO = "sucesso.exclusao.tarefa";
		SUCESSO_PESQUISA = "sucesso.pesquisa.tarefa";
		ERRO_CADASTRO = "erro.cadastro.tarefa";
		ERRO_ATUALIZACAO = "erro.atualizacao.tarefa";
		ERRO_EXCLUSAO = "erro.exclusao.tarefa";
		ERRO_PESQUISA = "erro.pesquisa.tarefa";
		PESQUISA_NAO_ENCONTRADA = "pesquisa.tarefa.nao.encontrada";
	}
	
	@Override
	public void cadastrar(ActionEvent event) {
		try {
			Set<Tarefa> tarefaTemp = getServices().obterListaPeloExemplo(getEntity());
			if(!tarefaTemp.isEmpty()){
				adicionarMensagemErro("erro.registro.jah.existente");
				return;
			}
		} catch (ServicesException e) {
			adicionarMensagemErroFatal(ERRO_PESQUISA);
		}
		super.cadastrar(event);
	}
	
}