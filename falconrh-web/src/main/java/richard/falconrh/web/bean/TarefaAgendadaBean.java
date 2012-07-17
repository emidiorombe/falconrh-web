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
import richard.falconrh.scheduler.StatusAgendamento;
import richard.falconrh.scheduler.TarefaAgendada;
import richard.falconrh.service.AbstractServices;
import richard.falconrh.service.AgendadorTarefaServices;

/**
 * ManagedBean JSF utilizado em arquivos xhtml
 * @author Richard Mendes Madureira
 * @version $Revision: 1.0 $
 */
@ManagedBean(name="tarefaAgendadaBean")
@ViewScoped
public class TarefaAgendadaBean extends BaseBean<TarefaAgendada, AbstractServices<TarefaAgendada>> implements Serializable{
	private static final long serialVersionUID = 0L;
	private static final Logger logger = Logger.getLogger(TarefaAgendadaBean.class);
	
	private static final String ERRO_AGENDAMENTO = "erro.agendamento.tarefa";
	private static final String SUCESSO_AGENDAMENTO = "sucesso.agendamento.tarefa";
	
	@EJB(name="ejb/AgendadorTarefaServices")
	private AgendadorTarefaServices agendadorTarefaServices;
	
	public TarefaAgendadaBean(){
		super();
	}
	
	@Override
	public void inicializaEntity() {
		super.inicializaEntity();
		setEntity(new TarefaAgendada());
		getEntity().setResponsavelAgendamento(new Usuario());
	}
	
	public void agendarTarefa(ActionEvent event){
		try{
			getEntity().setDataAgendamento(new Date());
			getEntity().setAtiva(Boolean.TRUE);
			getEntity().setStatusAgendamento(StatusAgendamento.AGENDADO);
			agendadorTarefaServices.agendarTarefa(getEntity());
			adicionarMensagemInformacao(SUCESSO_AGENDAMENTO);
		} catch(ServicesException e){
			adicionarMensagemErro(ERRO_AGENDAMENTO);
		}
	}
	
	public void pesquisarAgendamento(ActionEvent event){
		logger.debug("Iniciando a pesquisa de de entidade...");
		Set<TarefaAgendada> lista = new HashSet<TarefaAgendada>();
		try{
			ultimoSamplePesquisa = getEntity();
			lista = agendadorTarefaServices.obterListaTarefasAgendadas();
			logger.debug("Sucesso a pesquisar entidade");
		}catch(Exception e){
			adicionarMensagemErro(ERRO_PESQUISA);
			logger.error("Erro ao pesquisar entidade", e);
		}
		if(lista.isEmpty()){
			adicionarMensagemErro(PESQUISA_NAO_ENCONTRADA);
			setModoOperacao(MODO_PESQUISA);
			logger.info("Nao foi encontrada nenhum entidade na pesquisa");
			return;
		}
		setListaEntities(new ArrayList<TarefaAgendada>(lista));
		setModoOperacao(MODO_DETALHE_PESQUISA);
		logger.debug("Fim da pesquisa de entidade");
	}
	
	@Override
	public void excluir(ActionEvent event) {
		logger.debug("Inicializando a exclusao de tarefa agendada");
		try{
			agendadorTarefaServices.excluirTarefaAgendada(getEntity());
			adicionarMensagemInformacao(SUCESSO_EXCLUSAO);
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
		ERRO_PESQUISA = "erro.pesquisa.tarefaAgendada";
		PESQUISA_NAO_ENCONTRADA = "pesquisa.tarefaAgendada.nao.encontrada";
		SUCESSO_EXCLUSAO ="sucesso.exclusao.tarefaAgendada";
		ERRO_EXCLUSAO = "erro.exclusao.tarefaAgendada";
		SUCESSO_ATUALIZACAO = "sucesso.atualizacao.tarefaAgendada";
		ERRO_ATUALIZACAO= "erro.atualizacao.tarefaAgendada";
	}
	
}