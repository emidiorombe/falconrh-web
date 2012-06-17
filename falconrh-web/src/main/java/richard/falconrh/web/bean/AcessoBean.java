package richard.falconrh.web.bean;

import java.util.ArrayList;
import java.util.Set;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import richard.falconrh.entity.seguranca.Acesso;
import richard.falconrh.exception.ServicesException;
import richard.falconrh.service.AcessoServices;

/**
 * ManagedBean JSF utilizado em arquivos xhtml
 * @author Richard Mendes Madureira
 * @version $Revision: 1.0 $
 */
@ManagedBean(name="acessoBean")
@ViewScoped
public class AcessoBean extends BaseBean<Acesso, AcessoServices>{
	private static final long serialVersionUID = -7205911761531138512L;
	
	@EJB(name="ejb/AcessoServices")
	private AcessoServices acessoServices;
	
	public AcessoBean(){
		super();
	}
	
	@Override
	public void inicializaEntity() {
		setEntity(new Acesso());
		setListaEntities(new ArrayList<Acesso>());
	}
	
	/**
	 * Method getServices.
	 * @return acessoServices 
	 */
	@Override
	public AcessoServices getServices() {
		return acessoServices;
	}

	@Override
	public void setMensagensInformativas() {
		SUCESSO_CADASTRO = "sucesso.cadastro.acesso";
		SUCESSO_ATUALIZACAO = "sucesso.atualizacao.acesso";
		SUCESSO_EXCLUSAO = "sucesso.exclusao.acesso";
		SUCESSO_PESQUISA = "sucesso.pesquisa.acesso";
		ERRO_CADASTRO = "erro.cadastro.acesso";
		ERRO_ATUALIZACAO = "erro.atualizacao.acesso";
		ERRO_EXCLUSAO = "erro.exclusao.acesso";
		ERRO_PESQUISA = "erro.pesquisa.acesso";
		PESQUISA_NAO_ENCONTRADA = "pesquisa.acesso.nao.encontrado";
	}
	
	@Override
	public void cadastrar(ActionEvent event) {
		try {
			Set<Acesso> acessoTemp = getServices().obterListaPeloExemplo(getEntity());
			if(!acessoTemp.isEmpty()){
				adicionarMensagemErro("erro.registro.jah.existente");
				return;
			}
		} catch (ServicesException e) {
			adicionarMensagemErroFatal(ERRO_PESQUISA);
		}
		super.cadastrar(event);
	}
	
}