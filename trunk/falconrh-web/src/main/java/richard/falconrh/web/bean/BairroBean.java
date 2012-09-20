package richard.falconrh.web.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ValueChangeEvent;

import richard.falconrh.entity.localizacao.Bairro;
import richard.falconrh.entity.localizacao.Municipio;
import richard.falconrh.exception.ServicesException;
import richard.falconrh.modelo.enums.UF;
import richard.falconrh.service.BairroServices;
import richard.falconrh.service.MunicipioServices;

/**
 * ManagedBean JSF utilizado em arquivos xhtml
 * @author Richard Mendes Madureira
 * @version $Revision: 1.0 $
 */
@ManagedBean(name="bairroBean")
@ViewScoped
public class BairroBean extends BaseBean<Bairro, BairroServices>{
	private static final long serialVersionUID = -7205911761531138512L;
	
	@EJB(name="ejb/BairroServices")
	private BairroServices bairroServices;
	
	@EJB(name="ejb/MUnicipioServices")
	private MunicipioServices municipioServices;
	
	private List<Municipio> listaMunicipios;
	
	public BairroBean(){
		super();
	}
	
	@Override
	public void inicializaEntity() {
		setEntity(new Bairro());
		getEntity().setMunicipio(new Municipio());
		getEntity().getMunicipio().setUf(UF.ACRE);
		setListaEntities(new ArrayList<Bairro>());
	}
	
	/**
	 * Method getServices.
	 * @return BairroServices
	 */
	@Override
	public BairroServices getServices() {
		return bairroServices;
	}

	@Override
	public void setMensagensInformativas() {
		SUCESSO_CADASTRO = "sucesso.cadastro.bairro";
		SUCESSO_ATUALIZACAO = "sucesso.atualizacao.bairro";
		SUCESSO_EXCLUSAO = "sucesso.exclusao.bairro";
		SUCESSO_PESQUISA = "sucesso.pesquisa.bairro";
		ERRO_CADASTRO = "erro.cadastro.bairro";
		ERRO_ATUALIZACAO = "erro.atualizacao.bairro";
		ERRO_EXCLUSAO = "erro.exclusao.bairro";
		ERRO_PESQUISA = "erro.pesquisa.bairro";
		PESQUISA_NAO_ENCONTRADA = "pesquisa.bairro.nao.encontrado";
	}
	
	/**
	 * Method obterListaMunicipios.
	 * @param event ValueChangeEvent
	 * @throws ServicesException
	 */
	public void obterListaMunicipios(ValueChangeEvent event) throws ServicesException{
		UF uf = (UF)event.getNewValue();
		getEntity().getMunicipio().setUf(uf);
		Set<Municipio> municipiosObtidos = municipioServices.obterListaMunicipiosPelaUF(uf);
		setListaMunicipios(new ArrayList<Municipio>(municipiosObtidos));
	}

	/**
	 * Method getListaMunicipios.
	 * @return List<Municipio>
	 */
	public List<Municipio> getListaMunicipios() {
		return listaMunicipios;
	}

	/**
	 * Method setListaMunicipios.
	 * @param listaMunicipios List<Municipio>
	 */
	public void setListaMunicipios(List<Municipio> listaMunicipios) {
		this.listaMunicipios = listaMunicipios;
	}
	
}
