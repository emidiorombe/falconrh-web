package richard.falconrh.web.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.apache.log4j.Logger;

import richard.falconrh.entity.pessoa.Usuario;
import richard.falconrh.exception.ServicesException;
import richard.falconrh.scheduler.AgendamentoTarefa;
import richard.falconrh.scheduler.StatusAgendamento;
import richard.falconrh.service.AbstractServices;
import richard.falconrh.service.AgendamentoTarefaServices;

/**
 * ManagedBean JSF utilizado em arquivos xhtml
 * @author Richard Mendes Madureira
 * @version $Revision: 1.0 $
 */
@ManagedBean(name="agendamentoTarefaBean")
@ViewScoped
public class AgendamentoTarefaBean extends BaseBean<AgendamentoTarefa, AbstractServices<AgendamentoTarefa>> implements Serializable{
	private static final long serialVersionUID = 0L;
	private static final Logger logger = Logger.getLogger(AgendamentoTarefaBean.class);
	
	private static final String ERRO_AGENDAMENTO = "erro.agendamento.agendamentoTarefa";
	private static final String SUCESSO_AGENDAMENTO = "sucesso.agendamento.agendamentoTarefa";
	
	@EJB(name="ejb/AgendamentoTarefaServices")
	private AgendamentoTarefaServices agendamentoTarefaServices;
	
	public AgendamentoTarefaBean(){
		super();
	}
	
	@Override
	public void inicializaEntity() {
		super.inicializaEntity();
		setEntity(new AgendamentoTarefa());
		getEntity().setResponsavelAgendamento(new Usuario());
	}
	
	public void agendarTarefa(ActionEvent event){
		try{
			getEntity().setDataAgendamento(new Date());
			getEntity().setStatusAgendamento(StatusAgendamento.AGENDADO);
			agendamentoTarefaServices.agendarTarefa(getEntity());
			adicionarMensagemInformacao(SUCESSO_AGENDAMENTO);
		} catch(ServicesException e){
			adicionarMensagemErro(ERRO_AGENDAMENTO);
		}
	}
	
	public void pesquisarAgendamento(ActionEvent event){
		logger.debug("Iniciando a pesquisa de de entidade...");
		Set<AgendamentoTarefa> lista = new HashSet<AgendamentoTarefa>();
		try{
			ultimoSamplePesquisa = getEntity();
			lista = agendamentoTarefaServices.obterListaTarefasAgendadas();
			logger.debug("Sucesso a pesquisar entidade");
		}catch(Exception e){
			adicionarMensagemErro(ERRO_PESQUISA);
			logger.error("Erro ao pesquisar entidade", e);
		}
		if(lista.isEmpty()){
			adicionarMensagemErro(PESQUISA_NAO_ENCONTRADA);
			setModoOperacaoAnterior(getModoOperacao());
			setModoOperacao(MODO_PESQUISA);
			logger.info("Nao foi encontrada nenhum entidade na pesquisa");
			return;
		}
		setListaEntities(new ArrayList<AgendamentoTarefa>(lista));
		setModoOperacao(MODO_DETALHE_PESQUISA);
		logger.debug("Fim da pesquisa de entidade");
	}
	
	@Override
	public void voltarParaResultadoDaPesquisa(ActionEvent event){
		setEntity(ultimoSamplePesquisa);
		pesquisarAgendamento(event);
	}
	
	@Override
	public void excluir(ActionEvent event) {
		logger.debug("Inicializando a exclusao de tarefa agendada");
		try{
			agendamentoTarefaServices.excluirTarefaAgendada(getEntity());
			adicionarMensagemInformacao(SUCESSO_EXCLUSAO);
			setModoOperacaoAnterior(getModoOperacao());
			setModoOperacao(MODO_PESQUISA);
			inicializaEntity();
			logger.debug("Tarefa agendada excluida com sucesso");
		}catch(Exception e){
			adicionarMensagemErro(ERRO_EXCLUSAO);
			logger.error("Erro ao excluir tarefa agendada", e);
		}finally{
			logger.debug("Fim da exclusao de tarefa agendada");
		}
	}
	
	@Override
	public void setMensagensInformativas() {
		ERRO_PESQUISA = "erro.pesquisa.agendamentoTarefa";
		PESQUISA_NAO_ENCONTRADA = "pesquisa.agendamentoTarefa.nao.encontrado";
		SUCESSO_EXCLUSAO ="sucesso.exclusao.agendamentoTarefa";
		ERRO_EXCLUSAO = "erro.exclusao.agendamentoTarefa";
		SUCESSO_ATUALIZACAO = "sucesso.atualizacao.agendamentoTarefa";
		ERRO_ATUALIZACAO= "erro.atualizacao.agendamentoTarefa";
	}
	
}