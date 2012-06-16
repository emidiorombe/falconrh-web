package richard.falconrh.web.bean;

import java.util.ArrayList;
import java.util.Set;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import richard.falconrh.entity.seguranca.Acao;
import richard.falconrh.exception.ServicesException;
import richard.falconrh.service.AcaoServices;

/**
 * ManagedBean JSF utilizado em arquivos xhtml
 * @author Richard Mendes Madureira
 * @version $Revision: 1.0 $
 */
@ManagedBean(name="acaoBean")
@ViewScoped
public class AcaoBean extends BaseBean<Acao, AcaoServices>{
	private static final long serialVersionUID = -7205911761531138512L;
	
	@EJB(name="ejb/AcaoServices")
	private AcaoServices acaoServices;
	
	public AcaoBean(){
		super();
	}
	
	@Override
	public void inicializaEntity() {
		setEntity(new Acao());
		setListaEntities(new ArrayList<Acao>());
	}
	
	/**
	 * Method getServices.
	 * @return acaoServices 
	 */
	@Override
	public AcaoServices getServices() {
		return acaoServices;
	}

	@Override
	public void setMensagensInformativas() {
		SUCESSO_CADASTRO = "sucesso.cadastro.acao";
		SUCESSO_ATUALIZACAO = "sucesso.atualizacao.acao";
		SUCESSO_EXCLUSAO = "sucesso.exclusao.acao";
		SUCESSO_PESQUISA = "sucesso.pesquisa.acao";
		ERRO_CADASTRO = "erro.cadastro.acao";
		ERRO_ATUALIZACAO = "erro.atualizacao.acao";
		ERRO_EXCLUSAO = "erro.exclusao.acao";
		ERRO_PESQUISA = "erro.pesquisa.acao";
		PESQUISA_NAO_ENCONTRADA = "pesquisa.acao.nao.encontrada";
	}
	
	@Override
	public void cadastrar(ActionEvent event) {
		try {
			Set<Acao> acaoTemp = getServices().obterListaPeloExemplo(getEntity());
			if(!acaoTemp.isEmpty()){
				adicionarMensagemErro("erro.registro.jah.existente");
				return;
			}
		} catch (ServicesException e) {
			adicionarMensagemErroFatal(ERRO_PESQUISA);
		}
		super.cadastrar(event);
	}
	
}