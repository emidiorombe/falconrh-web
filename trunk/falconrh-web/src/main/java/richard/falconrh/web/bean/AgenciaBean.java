package richard.falconrh.web.bean;

import java.util.ArrayList;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import richard.falconrh.entity.banco.Agencia;
import richard.falconrh.entity.banco.ContaCorrente;
import richard.falconrh.service.AgenciaServices;

/**
 * ManagedBean JSF utilizado em arquivos xhtml
 * @author Richard Mendes Madureira
 * @version $Revision: 1.0 $
 */
@ManagedBean(name="agenciaBean")
@ViewScoped
public class AgenciaBean extends BaseBean<Agencia, AgenciaServices>{
	private static final long serialVersionUID = -7205911761531138512L;
	
	@EJB(name="ejb/AgenciaServices")
	private AgenciaServices agenciaServices;
	
	public AgenciaBean(){
		super();
	}
	
	public void inicializaEntity(){
		setEntity(new Agencia());
		getEntity().setListaContasCorrentes(new ArrayList<ContaCorrente>());
		setListaEntities(new ArrayList<Agencia>());
	}
	
	/**
	 * Method getServices.
	 * @return AgenciaServices
	 */
	@Override
	public AgenciaServices getServices() {
		return agenciaServices;
	}

	@Override
	public void setMensagensInformativas() {
		SUCESSO_CADASTRO = "sucesso.cadastro.agencia";
		SUCESSO_ATUALIZACAO = "sucesso.atualizacao.agencia";
		SUCESSO_EXCLUSAO = "sucesso.exclusao.agencia";
		SUCESSO_PESQUISA = "sucesso.pesquisa.agencia";
		ERRO_CADASTRO = "erro.cadastro.agencia";
		ERRO_ATUALIZACAO = "erro.atualizacao.agencia";
		ERRO_EXCLUSAO = "erro.exclusao.agencia";
		ERRO_PESQUISA = "erro.pesquisa.agencia";
		PESQUISA_NAO_ENCONTRADA = "pesquisa.agencia.nao.encontrada";		
	}
}
