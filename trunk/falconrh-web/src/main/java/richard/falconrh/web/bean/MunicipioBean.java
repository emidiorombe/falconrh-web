package richard.falconrh.web.bean;

import java.util.ArrayList;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import richard.falconrh.entity.localizacao.Municipio;
import richard.falconrh.service.MunicipioServices;

/**
 * ManagedBean JSF utilizado em arquivos xhtml
 * @author Richard Mendes Madureira
 *
 * @version $Revision: 1.0 $
 */
@ManagedBean(name="municipioBean")
@ViewScoped
public class MunicipioBean extends BaseBean<Municipio, MunicipioServices>{
	private static final long serialVersionUID = -7205911761531138512L;
	
	@EJB(name="ejb/MunicipioServices")
	private MunicipioServices municipioServices;
	
	public MunicipioBean(){
		super();
	}
	
	@Override
	public void inicializaEntity() {
		setEntity(new Municipio());
		setListaEntities(new ArrayList<Municipio>());
	}
	
	/**
	 * Method getServices.
	
	 * @return MunicipioServices */
	@Override
	public MunicipioServices getServices() {
		return municipioServices;
	}

	@Override
	public void setMensagensInformativas() {
		SUCESSO_CADASTRO = "sucesso.cadastro.municipio";
		SUCESSO_ATUALIZACAO = "sucesso.atualizacao.municipio";
		SUCESSO_EXCLUSAO = "sucesso.exclusao.municipio";
		SUCESSO_PESQUISA = "sucesso.pesquisa.municipio";
		ERRO_CADASTRO = "erro.cadastro.municipio";
		ERRO_ATUALIZACAO = "erro.atualizacao.municipio";
		ERRO_EXCLUSAO = "erro.exclusao.municipio";
		ERRO_PESQUISA = "erro.pesquisa.municipio";
		PESQUISA_NAO_ENCONTRADA = "pesquisa.municipio.nao.encontrado";
	}
	
}
