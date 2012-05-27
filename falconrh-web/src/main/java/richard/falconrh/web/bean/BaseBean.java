package richard.falconrh.web.bean;

import static richard.falconrh.util.FalconRHConstants.ATENCAO;
import static richard.falconrh.util.FalconRHConstants.ERRO;
import static richard.falconrh.util.FalconRHConstants.ERRO_FATAL;
import static richard.falconrh.util.FalconRHConstants.INFORMACAO;
import static richard.falconrh.util.FalconRHConstants.MSG;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.apache.log4j.Logger;

import richard.falconrh.entity.Parent;
import richard.falconrh.service.AbstractServices;

/**
 * @author richard
 * @version $Revision: 1.0 $
 */
public abstract class BaseBean<T extends Parent, U extends AbstractServices<T>> implements Serializable{
	private static final long serialVersionUID = 1106883964284901680L;
	
	private static final Logger logger = Logger.getLogger(BaseBean.class);
	
	public static final String MODO_OPERACAO = "modoOperacao";
	public static final String MODO_INCLUSAO = "modoInclusao";
	public static final String MODO_DETALHE = "modoDetalhe";
	public static final String MODO_DETALHE_PESQUISA = "modoDetalhePesquisa";
	public static final String MODO_PESQUISA = "modoPesquisa";
	public static final String MODO_EDICAO = "modoEdicao";
	public static final String MODO_EXCLUSAO = "modoExclusao";
	
	public String SUCESSO_CADASTRO = "sucesso.cadastro.entidade";
	public String ERRO_CADASTRO = "erro.cadastro.entidade";
	public String SUCESSO_ATUALIZACAO = "sucesso.atualizacao.entidade";
	public String ERRO_ATUALIZACAO = "erro.atualizacao.entidade";
	public String SUCESSO_EXCLUSAO = "sucesso.exclusao.entidade";
	public String ERRO_EXCLUSAO = "erro.exclusao.entidade";
	public String SUCESSO_PESQUISA = "sucesso.pesquisa.entidade";
	public String ERRO_PESQUISA = "erro.pesquisa.entidade";
	public String PESQUISA_NAO_ENCONTRADA = "erro.pesquisa.nao.encontrado.entidade";
	
	private String modoOperacao;
	
	private T entity;
	private List<T> listaEntities;
	private T ultimoSamplePesquisa;
	
	public BaseBean(){
		inicializaEntity();
		setMensagensInformativas();
	}
	
	public abstract void setMensagensInformativas();
	
	@PostConstruct
	public void initModoOperacao(){
		logger.debug("obtendo o Modo de Operacao");
		String modoOperacao = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get(MODO_OPERACAO);
		if(getModoOperacao()==null || "".equals(getModoOperacao())){
			setModoOperacao(modoOperacao);
		}
	}
	
	/**
	 * Method adicionarMensagemInformacao.
	 * @param key String
	 */
	public void adicionarMensagemInformacao(String key){
		String mensagem = getMensagem(key);
		FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, INFORMACAO, mensagem);
		getContext().addMessage(INFORMACAO, facesMessage);
	}
	
	/**
	 * Method adicionarMensagemAlerta.
	 * @param key String
	 */
	public void adicionarMensagemAlerta(String key){
		String mensagem = getMensagem(key);
		FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_WARN, ATENCAO, mensagem);
		getContext().addMessage(ATENCAO, facesMessage);
	}
	
	/**
	 * Method adicionarMensagemErro.
	 * @param key String
	 */
	public void adicionarMensagemErro(String key){
		String mensagem = getMensagem(key);
		FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, ERRO, mensagem);
		getContext().addMessage(ERRO, facesMessage);
	}
	
	/**
	 * Method adicionarMensagemErroFatal.
	 * @param key String
	 */
	public void adicionarMensagemErroFatal(String key){
		String mensagem = getMensagem(key);
		FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_FATAL, ERRO_FATAL, mensagem);
		getContext().addMessage(ERRO_FATAL, facesMessage);
	}
	
	/**
	 * Method getMensagem.
	 * @param key String
	
	 * @return String */
	public String getMensagem(String key){
		ResourceBundle resource = getContext().getApplication().getResourceBundle(getContext(), MSG);
		return resource.getString(key);
	}
	
	/**
	 * Method getContext.
	
	 * @return FacesContext */
	public FacesContext getContext(){
		return FacesContext.getCurrentInstance();
	}
	
	/**
	 * Method isModoInclusao.
	
	 * @return boolean */
	public boolean isModoInclusao(){
		return MODO_INCLUSAO.equalsIgnoreCase(getModoOperacao());
	}
	
	/**
	 * Method isModoDetalhe.
	
	 * @return boolean */
	public boolean isModoDetalhe(){
		return MODO_DETALHE.equalsIgnoreCase(getModoOperacao());
	}
	
	/**
	 * Method isModoPesquisa.
	
	 * @return boolean */
	public boolean isModoPesquisa(){
		return MODO_PESQUISA.equalsIgnoreCase(getModoOperacao());
	}
	
	/**
	 * Method isModoExclusao.
	
	 * @return boolean */
	public boolean isModoExclusao(){
		return MODO_EXCLUSAO.equalsIgnoreCase(getModoOperacao());
	}
	
	/**
	 * Method isModoEdicao.
	
	 * @return boolean */
	public boolean isModoEdicao(){
		return MODO_EDICAO.equalsIgnoreCase(getModoOperacao());
	}
	
	/**
	 * Method isModoDetalhePesquisa.
	
	 * @return boolean */
	public boolean isModoDetalhePesquisa(){
		return MODO_DETALHE_PESQUISA.equalsIgnoreCase(getModoOperacao());
	}
	
	/**
	 * Method sair.
	
	 * @return String */
	public String sair(){
		setModoOperacao("");
		return "/home";
	}
	
	/**
	 * Method getServices.
	
	 * @return U */
	public U getServices(){return null;}
	public void inicializaEntity(){}
	


	/**
	 * Method cadastrar.
	 * @param event ActionEvent
	 */
	public void cadastrar(ActionEvent event){
		logger.debug("Inicializando o cadastro de entidade");
		try{
			getServices().cadastrar(getEntity());
			adicionarMensagemInformacao(SUCESSO_CADASTRO);
			setModoOperacao(MODO_DETALHE);
			logger.debug("Entidade cadastrada com sucesso");
		}catch(Exception e){
			adicionarMensagemErro(ERRO_CADASTRO);
			logger.error("Erro ao cadastrar entidade", e);
		}finally{
			logger.debug("Fim do cadastro de entidade");
		}
	}

	/**
	 * Method atualizar.
	 * @param event ActionEvent
	 */
	public void atualizar(ActionEvent event){
		logger.debug("Inicializando a atualizacao da entidade");
		try{
			getServices().alterar(getEntity());
			adicionarMensagemInformacao(SUCESSO_ATUALIZACAO);
			setModoOperacao(MODO_DETALHE);
			logger.debug("Entidade atualizada com sucesso");
		}catch(Exception e){
			adicionarMensagemErro(ERRO_ATUALIZACAO);
			logger.error("Erro ao atualizar entidade", e);
		}finally{
			logger.debug("Fim da atualizacao de entidade");
		}
	}
	
	/**
	 * Method pesquisar.
	 * @param event ActionEvent
	 */
	public void pesquisar(ActionEvent event){
		logger.debug("Iniciando a pesquisa de de entidade...");
		Set<T> lista = new HashSet<T>();
		try{
			ultimoSamplePesquisa = getEntity();
			lista = getServices().obterListaPeloExemplo(getEntity());
			logger.debug("SUcesso a pesquisar entidade");
		}catch(Exception e){
			e.printStackTrace();
			adicionarMensagemErro(ERRO_PESQUISA);
			logger.error("Erro ao pesquisar entidade", e);
		}
		if(lista.isEmpty()){
			adicionarMensagemErro(PESQUISA_NAO_ENCONTRADA);
			setModoOperacao(MODO_PESQUISA);
			logger.info("Nao foi encontrada nenhum entidade na pesquisa");
			return;
		}
		setListaEntities(new ArrayList<T>(lista));
		setModoOperacao(MODO_DETALHE_PESQUISA);
		logger.debug("Fim da pesquisa de entidade");
	}
	
	/**
	 * Method excluir.
	 * @param event ActionEvent
	 */
	@SuppressWarnings("unchecked")
	public void excluir(ActionEvent event){
		logger.debug("Inicializando a exclusao de entidade");
		try{
			getServices().excluirPeloId((Class<T>) getEntity().getClass(), getEntity().getId());
			adicionarMensagemInformacao(SUCESSO_EXCLUSAO);
			setModoOperacao(MODO_PESQUISA);
			inicializaEntity();
			logger.debug("Entidade excluida com sucesso");
		}catch(Exception e){
			adicionarMensagemErro(ERRO_EXCLUSAO);
			logger.error("Erro ao excluir entidade", e);
		}finally{
			logger.debug("Fim da exclusao de entidade");
		}
	}
	
	
	/**
	 * Method editar.
	 * @param event ActionEvent
	 */
	public void editar(ActionEvent event){
		logger.debug("Iniciando edicao");
		setModoOperacao(MODO_EDICAO);
	}
	
	/**
	 * Method visualizar.
	 * @param event ActionEvent
	 */
	public void visualizar(ActionEvent event){
		logger.debug("Iniciando visualizacao");
		setModoOperacao(MODO_DETALHE);
	}
	
	/**
	 * Method iniciarExclusao.
	 * @param event ActionEvent
	 */
	public void iniciarExclusao(ActionEvent event){
		logger.debug("Iniciando exclusao");
		setModoOperacao(MODO_EXCLUSAO);
	}
	
	/**
	 * Method iniciarPesquisa.
	 * @param event ActionEvent
	 */
	public void iniciarPesquisa(ActionEvent event){
		logger.debug("Iniciando exclusao");
		setModoOperacao(MODO_PESQUISA);
		inicializaEntity();
	}
	
	/**
	 * Method voltarParaResultadoDaPesquisa.
	 * @param event ActionEvent
	 */
	public void voltarParaResultadoDaPesquisa(ActionEvent event){
		setEntity(ultimoSamplePesquisa);
		pesquisar(event);
	}
	
	/**
	 * Method getModoOperacao.
	 * @return String
	 */
	public String getModoOperacao() {
		return modoOperacao;
	}
	
	/**
	 * Method setModoOperacao.
	 * @param modoOperacao String
	 */
	public void setModoOperacao(String modoOperacao) {
		this.modoOperacao = modoOperacao;
	}

	/**
	 * Method getEntity.
	 * @return T
	 */
	public T getEntity() {
		return entity;
	}

	/**
	 * Method setEntity.
	 * @param entity T
	 */
	public void setEntity(T entity) {
		this.entity = entity;
	}

	/**
	 * Method getListaEntities.
	 * @return List<T> */
	public List<T> getListaEntities() {
		return listaEntities;
	}

	/**
	 * Method setListaEntities.
	 * @param listaEntities List<T>
	 */
	public void setListaEntities(List<T> listaEntities) {
		this.listaEntities = listaEntities;
	}
}