package richard.falconrh.web.bean;

import java.util.ArrayList;
import java.util.Set;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import richard.falconrh.entity.empresa.Empresa;
import richard.falconrh.exception.ServicesException;
import richard.falconrh.service.EmpresaServices;

/**
 * ManagedBean JSF utilizado em arquivos xhtml
 * @author Richard Mendes Madureira
 * @version $Revision: 1.0 $
 */
@ManagedBean(name="empresaBean")
@ViewScoped
public class EmpresaBean extends BaseBean<Empresa, EmpresaServices>{
	private static final long serialVersionUID = -7205911761531138512L;
	
	@EJB(name="ejb/EmpresaServices")
	private EmpresaServices empresaServices;
	
	public EmpresaBean(){
		super();
	}
	
	@Override
	public void inicializaEntity() {
		setEntity(new Empresa());
		setListaEntities(new ArrayList<Empresa>());
	}
	
	/**
	 * Method getServices.
	 * @return empresaServices 
	 */
	@Override
	public EmpresaServices getServices() {
		return empresaServices;
	}

	@Override
	public void setMensagensInformativas() {
		SUCESSO_CADASTRO = "sucesso.cadastro.empresa";
		SUCESSO_ATUALIZACAO = "sucesso.atualizacao.empresa";
		SUCESSO_EXCLUSAO = "sucesso.exclusao.empresa";
		SUCESSO_PESQUISA = "sucesso.pesquisa.empresa";
		ERRO_CADASTRO = "erro.cadastro.empresa";
		ERRO_ATUALIZACAO = "erro.atualizacao.empresa";
		ERRO_EXCLUSAO = "erro.exclusao.empresa";
		ERRO_PESQUISA = "erro.pesquisa.empresa";
		PESQUISA_NAO_ENCONTRADA = "pesquisa.empresa.nao.encontrada";
	}
	
	@Override
	public void cadastrar(ActionEvent event) {
		try {
			Set<Empresa> acaoTemp = getServices().obterListaPeloExemplo(getEntity());
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