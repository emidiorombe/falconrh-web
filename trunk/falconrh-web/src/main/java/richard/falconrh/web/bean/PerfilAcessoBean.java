package richard.falconrh.web.bean;

import java.util.ArrayList;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import richard.falconrh.entity.seguranca.PerfilAcesso;
import richard.falconrh.service.PerfilAcessoServices;

/**
 * ManagedBean JSF utilizado em arquivos xhtml
 * @author Richard Mendes Madureira
 * @version $Revision: 1.0 $
 */
@ManagedBean(name="perfilAcessoBean")
@ViewScoped
public class PerfilAcessoBean extends BaseBean<PerfilAcesso, PerfilAcessoServices>{
	private static final long serialVersionUID = 6994781837582750164L;

	@EJB(name="ejb/PerfilAcessoServices")
	private PerfilAcessoServices perfilAcessoServices;
	
	public PerfilAcessoBean(){
		super();
	}
	
	@Override
	public void inicializaEntity() {
		setEntity(new PerfilAcesso());
		setListaEntities(new ArrayList<PerfilAcesso>());
	}
	
	/**
	 * Method getServices.
	 * @return acaoServices 
	 */
	@Override
	public PerfilAcessoServices getServices() {
		return perfilAcessoServices;
	}

	@Override
	public void setMensagensInformativas() {
		SUCESSO_CADASTRO = "sucesso.cadastro.perfilAcesso";
		SUCESSO_ATUALIZACAO = "sucesso.atualizacao.perfilAcesso";
		SUCESSO_EXCLUSAO = "sucesso.exclusao.perfilAcesso";
		SUCESSO_PESQUISA = "sucesso.pesquisa.perfilAcesso";
		ERRO_CADASTRO = "erro.cadastro.perfilAcesso";
		ERRO_ATUALIZACAO = "erro.atualizacao.perfilAcesso";
		ERRO_EXCLUSAO = "erro.exclusao.perfilAcesso";
		ERRO_PESQUISA = "erro.pesquisa.perfilAcesso";
		PESQUISA_NAO_ENCONTRADA = "pesquisa.perfilAcesso.nao.encontrado";
	}
	
}