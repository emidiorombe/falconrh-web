package richard.falconrh.web.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ValueChangeEvent;

import org.apache.log4j.Logger;

import richard.falconrh.entity.banco.Agencia;
import richard.falconrh.entity.banco.Banco;
import richard.falconrh.entity.banco.ContaCorrente;
import richard.falconrh.exception.ServicesException;
import richard.falconrh.service.AgenciaServices;
import richard.falconrh.service.ContaCorrenteServices;

/**
 * ManagedBean JSF utilizado em arquivos xhtml
 * @author Richard Mendes Madureira
 *
 * @version $Revision: 1.0 $
 */
@ManagedBean(name="contaCorrenteBean")
@ViewScoped
public class ContaCorrenteBean extends BaseBean<ContaCorrente, ContaCorrenteServices>{
	private static final long serialVersionUID = 4183628255081652371L;
	public static final Logger logger = Logger.getLogger(ContaCorrenteBean.class);

	@EJB(name="ejb/ContaCorrenteServices")
	private ContaCorrenteServices contaCorrenteServices;
	
	@EJB(name="ejb/AgenciaServices")
	private AgenciaServices agenciaServices;
	
	private List<Agencia> listaAgencias;
	
	public ContaCorrenteBean(){
		super();
	}
	
	@Override
	public void inicializaEntity() {
		setEntity(new ContaCorrente());
		getEntity().setAgencia(new Agencia());
		getEntity().getAgencia().setBanco(new Banco());
		setListaEntities(new ArrayList<ContaCorrente>());
		setListaAgencias(new ArrayList<Agencia>());
	}
	
	/**
	 * Method getServices.
	
	 * @return ContaCorrenteServices */
	@Override
	public ContaCorrenteServices getServices() {
		return contaCorrenteServices;
	}

	@Override
	public void setMensagensInformativas() {
		SUCESSO_CADASTRO = "sucesso.cadastro.contaCorrente";
		SUCESSO_ATUALIZACAO = "sucesso.atualizacao.contaCorrente";
		SUCESSO_EXCLUSAO = "sucesso.exclusao.contaCorrente";
		SUCESSO_PESQUISA = "sucesso.pesquisa.contaCorrente";
		ERRO_CADASTRO = "erro.cadastro.contaCorrente";
		ERRO_ATUALIZACAO = "erro.atualizacao.contaCorrente";
		ERRO_EXCLUSAO = "erro.exclusao.contaCorrente";
		ERRO_PESQUISA = "erro.pesquisa.contaCorrente";
		PESQUISA_NAO_ENCONTRADA = "pesquisa.contaCorrente.nao.encontrada";
	}
	
	/**
	 * Method obterListaAgencias.
	 * @param event ValueChangeEvent
	 */
	public void obterListaAgencias(ValueChangeEvent event){
		Banco banco = (Banco)event.getNewValue();
		if(banco!=null){
			try {
				Set<Agencia> listaAgencias = agenciaServices.obterListaPeloIdBanco(banco.getId());
				setListaAgencias(new ArrayList<Agencia>(listaAgencias));
				adicionarMensagemInformacao("sucesso.pesquisa.agencia");
			} catch (ServicesException e) {
				logger.error("erro.pesquisa.agencia", e);
				adicionarMensagemErro("erro.pesquisa.agencia");
			}
		}
	}

	/**
	 * Method getListaAgencias.
	
	 * @return List<Agencia> */
	public List<Agencia> getListaAgencias() {
		return listaAgencias;
	}

	/**
	 * Method setListaAgencias.
	 * @param listaAgencias List<Agencia>
	 */
	public void setListaAgencias(List<Agencia> listaAgencias) {
		this.listaAgencias = listaAgencias;
	}


}
