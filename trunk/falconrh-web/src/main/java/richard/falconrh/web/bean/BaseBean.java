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
	private String modoOperacaoAnterior;
	
	private T entity;
	private List<T> listaEntities;
	protected T ultimoSamplePesquisa;
	
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
			setModoOperacaoAnterior(getModoOperacao());
			setModoOperacao(modoOperacao);
		}
	}
	
	/**
	 * Método que adiciona uma mensagem de informação.
	 * @param key String que é a chave do arquivo de recursos que contém a mensagem a ser adicionada.
	 */
	public void adicionarMensagemInformacao(String key){
		String mensagem = getMensagem(key);
		FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, INFORMACAO, mensagem);
		getContext().addMessage(INFORMACAO, facesMessage);
	}
	
	/**
	 * Método que adiciona uma mensagem de alerta.
	 * @param key String que é a chave do arquivo de recursos que contém a mensagem a ser adicionada.
	 */
	public void adicionarMensagemAlerta(String key){
		String mensagem = getMensagem(key);
		FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_WARN, ATENCAO, mensagem);
		getContext().addMessage(ATENCAO, facesMessage);
	}
	
	/**
	 * Método que adiciona uma mensagem de erro.
	 * @param key String que é a chave do arquivo de recursos que contém a mensagem a ser adicionada.
	 */
	public void adicionarMensagemErro(String key){
		String mensagem = getMensagem(key);
		FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, ERRO, mensagem);
		getContext().addMessage(ERRO, facesMessage);
	}
	
	/**
	 * Método que adiciona uma mensagem de erro fatal.
	 * @param key String que é a chave do arquivo de recursos que contém a mensagem a ser adicionada.
	 */
	public void adicionarMensagemErroFatal(String key){
		String mensagem = getMensagem(key);
		FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_FATAL, ERRO_FATAL, mensagem);
		getContext().addMessage(ERRO_FATAL, facesMessage);
	}
	
	/**
	 * Método que retorna a mensagem que está no arquivo de resources a partir de sua chave.
	 * @param key String que é a chave do arquivod e resources.
	 * @return String que é a mensagem associada ao chave solicitada.
	 */
	public String getMensagem(String key){
		ResourceBundle resource = getContext().getApplication().getResourceBundle(getContext(), MSG);
		return resource.getString(key);
	}
	
	/**
	 * Método que retorna uma instancia do FacesContext.
	 * @return FacesContext
	 */
	public FacesContext getContext(){
		return FacesContext.getCurrentInstance();
	}
	
	/**
	 * Método que diz se o modo de operação atualmente sendo executado é o modo de inclusão.
	 * @return boolean, sendo <code>true</code> se for "modoInclusao", ou <code>false</code> caso seja outro modo de operação.
	 */
	public boolean isModoInclusao(){
		return MODO_INCLUSAO.equalsIgnoreCase(getModoOperacao());
	}
	
	/**
	 * Método que diz se o modo de operação atualmente sendo executado é o modo de de detalhe de informações.
	 * @return boolean, sendo <code>true</code> se for "modoDetalhe", ou <code>false</code> caso seja outro modo de operação.
	 */
	public boolean isModoDetalhe(){
		return MODO_DETALHE.equalsIgnoreCase(getModoOperacao());
	}
	
	/**
	 * Método que diz se o modo de operação atualmente sendo executado é o modo de Pesquisa.
	 * @return boolean, sendo <code>true</code> se for "modoPesquisa", ou <code>false</code> caso seja outro modo de operação.
	 */
	public boolean isModoPesquisa(){
		return MODO_PESQUISA.equalsIgnoreCase(getModoOperacao());
	}
	
	/**
	 * Método que diz se o modo de operação atualmente sendo executado é o modo de exclusão.
	 * @return boolean, sendo <code>true</code> se for "modoExclusao", ou <code>false</code> caso seja outro modo de operação.
	 */
	public boolean isModoExclusao(){
		return MODO_EXCLUSAO.equalsIgnoreCase(getModoOperacao());
	}
	
	/**
	 * Método que diz se o modo de operação atualmente sendo executado é o modo de edição.
	 * @return boolean, sendo <code>true</code> se for "modoEdicao", ou <code>false</code> caso seja outro modo de operação.
	 */
	public boolean isModoEdicao(){
		return MODO_EDICAO.equalsIgnoreCase(getModoOperacao());
	}
	
	/**
	 * Método que diz se o modo de operação atualmente sendo executado é o modo de detalhe da pesquisa.<br/>
	 * Normalmente este método é utilizado para visualizar a lista de dados obtidas a partir de uma pesquisa (um dataTable, por exemplo).
	 * @return boolean, sendo <code>true</code> se for "modoDetalhePequisa", ou <code>false</code> caso seja outro modo de operação.
	 */	
	public boolean isModoDetalhePesquisa(){
		return MODO_DETALHE_PESQUISA.equalsIgnoreCase(getModoOperacao());
	}
	
	/**
	 * Método que diz se o modo de operação anterior que foi executado estava no modo de inclusão.
	 * @return boolean, sendo <code>true</code> se for "modoInclusao", ou <code>false</code> caso seja outro modo de operação.
	 */
	public boolean isModoInclusaoAnterior(){
		return MODO_INCLUSAO.equalsIgnoreCase(getModoOperacao());
	}
	
	/**
	 * Método que diz se o modo de operação anterior que foi executado estava no modo de detalhe de informacoes.
	 * @return boolean, sendo <code>true</code> se for "modoDetalhe", ou <code>false</code> caso seja outro modo de operação.
	 */
	public boolean isModoDetalheAnterior(){
		return MODO_DETALHE.equalsIgnoreCase(getModoOperacao());
	}
	
	/**
	 * Método que diz se o modo de operação anterior que foi executado estava no modo de pesquisa de informações.
	 * @return boolean, sendo <code>true</code> se for "modoPesquisa", ou <code>false</code> caso seja outro modo de operação.
	 */
	public boolean isModoPesquisaAnterior(){
		return MODO_PESQUISA.equalsIgnoreCase(getModoOperacao());
	}
	
	/**
	 * Método que diz se o modo de operação anterior que foi executado estava no modo de exclusao.
	 * @return boolean, sendo <code>true</code> se for "modoExclusao", ou <code>false</code> caso seja outro modo de operação.
	 */
	public boolean isModoExclusaoAnterior(){
		return MODO_EXCLUSAO.equalsIgnoreCase(getModoOperacao());
	}
	
	/**
	 * Método que diz se o modo de operação anterior que foi executado estava no modo de edição.
	 * @return boolean, sendo <code>true</code> se for "modoEdicao", ou <code>false</code> caso seja outro modo de operação.
	 */
	public boolean isModoEdicaoAnterior(){
		return MODO_EDICAO.equalsIgnoreCase(getModoOperacao());
	}
	
	/**
	 * Método que diz se o modo de operação anterior que foi executado estava no modo de detalhe de pesquisa.
	 * @return boolean, sendo <code>true</code> se for "modoDetalhePequisa", ou <code>false</code> caso seja outro modo de operação.
	 */	
	public boolean isModoDetalhePesquisaAnterior(){
		return MODO_DETALHE_PESQUISA.equalsIgnoreCase(getModoOperacao());
	}
	
	/**
	 * Método que retorna a execução inicial do sistema.
	 * @return String
	 */
	public String sair(){
		setModoOperacao("");
		return "/home";
	}
	
	/**
	 * Método que retorna a instância da classe que extente AbstractServices que está sendo utilizada.
	 * @return U - a instância da classe Services utilizada.
	 */
	public U getServices(){return null;}
	
	/**
	 * Método que deve ser sobrescrito nas classes filhas para inicializar objetos básicos do ManagedBean.
	 */
	public void inicializaEntity(){}

	/**
	 * Método base que realiza o cadastro no banco de dados da entidade que está sendo manipulada no momento.
	 * @param event ActionEvent a instância do evento que foi disparado pelo usuário.
	 */
	public void cadastrar(ActionEvent event){
		logger.debug("Inicializando o cadastro de entidade");
		try{
			getServices().cadastrar(getEntity());
			adicionarMensagemInformacao(SUCESSO_CADASTRO);
			setModoOperacaoAnterior(getModoOperacao());
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
	 * Método base que realiza a atualização da entidade no banco de dados que está sendo manipulada no momento.
	 * @param event ActionEvent a instância do evento que foi disparado pelo usuário.
	 */
	public void atualizar(ActionEvent event){
		logger.debug("Inicializando a atualizacao da entidade");
		try{
			getServices().alterar(getEntity());
			adicionarMensagemInformacao(SUCESSO_ATUALIZACAO);
			setModoOperacaoAnterior(getModoOperacao());
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
	 * Método base que realiza a pesquisa de entidades no banco de dados.<br/>
	 * Este método irá popular o objeto <code>lista</code> com os dados encontrados (pode ser obtido através de <code>getLista()</code>.
	 * @param event ActionEvent a instância do evento que foi disparado pelo usuário.
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
			setModoOperacaoAnterior(getModoOperacao());
			setModoOperacao(MODO_PESQUISA);
			logger.info("Nao foi encontrada nenhum entidade na pesquisa");
			return;
		}
		setListaEntities(new ArrayList<T>(lista));
		setModoOperacao(MODO_DETALHE_PESQUISA);
		logger.debug("Fim da pesquisa de entidade");
	}
	
	/**
	 * Método base que realiza a exclusão de objetos do banco de dados.<br/>
	 * @param event ActionEvent a instância do evento que foi disparado pelo usuário.
	 */
	@SuppressWarnings("unchecked")
	public void excluir(ActionEvent event){
		logger.debug("Inicializando a exclusao de entidade");
		try{
			getServices().excluirPeloId((Class<T>) getEntity().getClass(), getEntity().getId());
			adicionarMensagemInformacao(SUCESSO_EXCLUSAO);
			setModoOperacaoAnterior(getModoOperacao());
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
	 * Método que muda o modo de operação em excução para o modo de edição de dados.<br/>
	 * Após a execução deste método, o modo de operação passa a ser "modoEdicao".
	 * @param event ActionEvent a instância do evento que foi disparado pelo usuário.
	 */
	public void editar(ActionEvent event){
		logger.debug("Iniciando edicao");
		setModoOperacaoAnterior(getModoOperacao());
		setModoOperacao(MODO_EDICAO);
	}
	
	/**
	 * Método que muda o modo de operação em excução para o modo de visualização de dados.<br/>
	 * Após a execução deste método, o modo de operação passa a ser "modoDetalhe".
	 * @param event ActionEvent a instância do evento que foi disparado pelo usuário.
	 */
	public void visualizar(ActionEvent event){
		logger.debug("Iniciando visualizacao");
		setModoOperacaoAnterior(getModoOperacao());
		setModoOperacao(MODO_DETALHE);
	}
	
	/**
	 * Método que muda o modo de operação em excução para o modo de exclusão de dados.<br/>
	 * Após a execução deste método, o modo de operação passa a ser "modoExclusao".
	 * @param event ActionEvent a instância do evento que foi disparado pelo usuário.
	 */
	public void iniciarExclusao(ActionEvent event){
		logger.debug("Iniciando exclusao");
		setModoOperacaoAnterior(getModoOperacao());
		setModoOperacao(MODO_EXCLUSAO);
	}
	
	/**
	 * Método que muda o modo de operação em excução para o modo de pesquisa de dados.<br/>
	 * Após a execução deste método, o modo de operação passa a ser "modoPesquisa".
	 * @param event ActionEvent a instância do evento que foi disparado pelo usuário.
	 */
	public void iniciarPesquisa(ActionEvent event){
		logger.debug("Iniciando exclusao");
		setModoOperacaoAnterior(getModoOperacao());
		setModoOperacao(MODO_PESQUISA);
		inicializaEntity();
	}
	
	/**
	 * Método que realiza novamente a pesquisa com os dados do último objeto pesquisado.
	 * @param event ActionEvent a instância do evento que foi disparado pelo usuário.
	 */
	public void voltarParaResultadoDaPesquisa(ActionEvent event){
		setEntity(ultimoSamplePesquisa);
		pesquisar(event);
	}
	
	/**
	 * Método que retorna o modo de operação atualmente sendo executado.
	 * @return String que representa o modo de operacao sendo executado ("modoInclusao", "modoEdicao", "modoDetalhe", "modoDetalhePesquisa", "modoPesquisa" e "modoExclusao").
	 */
	public String getModoOperacao() {
		return modoOperacao;
	}
	
	/**
	 * Método que altera o objeto que representa o modo de operação atual do sistema
	 * @param modoOperacao String que contém o modo de operação a ser executado ("modoInclusao", "modoEdicao", "modoDetalhe", "modoDetalhePesquisa", "modoPesquisa" e "modoExclusao").
	 */
	public void setModoOperacao(String modoOperacao) {
		this.modoOperacao = modoOperacao;
	}
	
	/**
	 * Método que retorna o modo de operação que foi executado anteriormente ao modo que está sendo atualmente executado.
	* @return String que representa o modo de operacao ("modoInclusao", "modoEdicao", "modoDetalhe", "modoDetalhePesquisa", "modoPesquisa" e "modoExclusao"). 
	 */
	public String getModoOperacaoAnterior(){
		return modoOperacaoAnterior;
	}
	
	/**
	 * Método que muda o modo de operacao anterior
	 * @param modoOperacaoAnterior
	 */
	public void setModoOperacaoAnterior(String modoOperacaoAnterior){
		this.modoOperacaoAnterior = modoOperacaoAnterior;
	}

	/**
	 * Método que retorna a entidade que está sendo manipulada no momento.
	 * @return T
	 */
	public T getEntity() {
		return entity;
	}

	/**
	 * Método que informa qual será a entidade a ser manipulada.
	 * @param entity T a instância da entidade que será manipulada.
	 */
	public void setEntity(T entity) {
		this.entity = entity;
	}

	/**
	 * Método que retorna a lista de entidades encontradas.
	 * @return List<T> a instancia do objeto que representa a lista de entidades.
	 */
	public List<T> getListaEntities() {
		return listaEntities;
	}

	/**
	 * Método que informa qual será a lista de entidades a ser manipulada.
	 * @param listaEntities List<T> a instância do objeto que representa a lista de entidades.
	 */
	public void setListaEntities(List<T> listaEntities) {
		this.listaEntities = listaEntities;
	}
}