package richard.falconrh.web.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.log4j.Logger;
import org.primefaces.model.DualListModel;

import richard.falconrh.entity.seguranca.Acao;
import richard.falconrh.entity.seguranca.Funcionalidade;
import richard.falconrh.exception.ServicesException;
import richard.falconrh.service.AcaoServices;
import richard.falconrh.service.FuncionalidadeServices;

/**
 * ManagedBean JSF utilizado em arquivos xhtml
 * @author Richard Mendes Madureira
 * @version $Revision: 1.0 $
 */
@ManagedBean(name="funcionalidadeBean")
@ViewScoped
public class FuncionalidadeBean extends BaseBean<Funcionalidade, FuncionalidadeServices>{
	private static final long serialVersionUID = -7205911761531138512L;
	private static final Logger LOGGER = Logger.getLogger(FuncionalidadeBean.class);
	
	private Acao acao;
	private List<Funcionalidade> listaTodasFuncionalidades;
	
	private DualListModel<Acao> acoes;
	
	@EJB(name="ejb/FuncionalidadeServices")
	private FuncionalidadeServices funcionalidadeServices;
	
	@EJB(name="ejb/AcaoServices")
	private AcaoServices acaoServices;
	
	public FuncionalidadeBean(){
		super();
	}
	
	@Override
	public void inicializaEntity() {
		setEntity(new Funcionalidade());
		getEntity().setListaAcoes(new ArrayList<Acao>());
		setListaEntities(new ArrayList<Funcionalidade>());
		setAcao(new Acao());
		setAcoes(getListaAcaoInicial());
	}
	
	/**
	 * Method getServices.
	 * @return funcionalidadeServices
	 */
	@Override
	public FuncionalidadeServices getServices() {
		return funcionalidadeServices;
	}

	@Override
	public void setMensagensInformativas() {
		SUCESSO_CADASTRO = "sucesso.cadastro.funcionalidade";
		SUCESSO_ATUALIZACAO = "sucesso.atualizacao.funcionalidade";
		SUCESSO_EXCLUSAO = "sucesso.exclusao.funcionalidade";
		SUCESSO_PESQUISA = "sucesso.pesquisa.funcionalidade";
		ERRO_CADASTRO = "erro.cadastro.funcionalidade";
		ERRO_ATUALIZACAO = "erro.atualizacao.funcionalidade";
		ERRO_EXCLUSAO = "erro.exclusao.funcionalidade";
		ERRO_PESQUISA = "erro.pesquisa.funcionalidade";
		PESQUISA_NAO_ENCONTRADA = "pesquisa.funcionalidade.nao.encontrada";
	}
	
	/**
	 * Method reinitAcoes.
	 * @return String
	 */
	public String reinitAcoes(){
		setAcao(new Acao());
		return null;
	}
	
	public Acao getAcao() {
		return acao;
	}

	public void setAcao(Acao acao) {
		this.acao = acao;
	}

	public DualListModel<Acao> getAcoes() {
		return acoes;
	}

	public void setAcoes(DualListModel<Acao> acoes) {
		this.acoes = acoes;
	}
	
	private AcaoServices getAcaoServices() {
		try {
			Context context = new InitialContext();
			String name = "java:global/falconrh-web/ejb/AcaoServices";
			AcaoServices services = (AcaoServices) context.lookup(name);
			return services;
		} catch (NamingException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private DualListModel<Acao> getListaAcaoInicial(){
		Set<Acao> listaAcoes = new TreeSet<Acao>();
		try{
			if(acaoServices==null){
				acaoServices = getAcaoServices();
			}
			listaAcoes = acaoServices.obterListaTodasAcoes();
		}catch(ServicesException e){
			LOGGER.error("erro.obter.lista.acoes", e);
			adicionarMensagemErro("erro.obter.lista.acoes");
			return null;
		}
		List<Acao> source = new ArrayList<Acao>(listaAcoes);
		List<Acao> target = new ArrayList<Acao>(getEntity().getListaAcoes());
		source.removeAll(target);
		return  new DualListModel<Acao>(source, target);
	}
	
	public List<Funcionalidade> getListaTodasFuncionalidades(){
		if(listaTodasFuncionalidades==null){
			return new ArrayList<Funcionalidade>(getServices().obterListaTodasFuncionalidades());
		}
		return listaTodasFuncionalidades;
	}
	
	@Override
	public void cadastrar(ActionEvent event) {
		getEntity().setListaAcoes(getAcoes().getTarget());
		super.cadastrar(event);
	}
	
	@Override
	public void editar(ActionEvent event) {
		setAcoes(getListaAcaoInicial());
		super.editar(event);
	}
}