package richard.falconrh.web.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ValueChangeEvent;

import richard.falconrh.entity.localizacao.Bairro;
import richard.falconrh.entity.localizacao.Logradouro;
import richard.falconrh.entity.localizacao.Municipio;
import richard.falconrh.exception.ServicesException;
import richard.falconrh.modelo.enums.UF;
import richard.falconrh.service.BairroServices;
import richard.falconrh.service.LogradouroServices;
import richard.falconrh.service.MunicipioServices;

/**
 * ManagedBean JSF utilizado em arquivos xhtml
 * @author Richard Mendes Madureira
 *
 * @version $Revision: 1.0 $
 */
@ManagedBean(name="logradouroBean")
@ViewScoped
public class LogradouroBean extends BaseBean<Logradouro, LogradouroServices>{
	private static final long serialVersionUID = -7205911761531138512L;
	
	@EJB(name="ejb/LogradouroServices")
	private LogradouroServices logradouroServices;
	@EJB(name="ejb/MunicipioServices")
	private MunicipioServices municipioServices;
	@EJB(name="ejb/BairroServices")
	private BairroServices bairroServices;
	
	private List<Bairro> listaBairros;
	private List<Municipio> listaMunicipios;
	
	public LogradouroBean(){
		super();
	}
	
	@Override
	public void inicializaEntity() {
		setEntity(new Logradouro());
		getEntity().setBairro(new Bairro());
		getEntity().getBairro().setMunicipio(new Municipio());
		setListaEntities(new ArrayList<Logradouro>());
		setListaBairros(new ArrayList<Bairro>());
		setListaMunicipios(new ArrayList<Municipio>());
	}
	
	/**
	 * Method getServices.
	
	 * @return LogradouroServices */
	@Override
	public LogradouroServices getServices() {
		return logradouroServices;
	}

	@Override
	public void setMensagensInformativas() {
		SUCESSO_CADASTRO = "sucesso.cadastro.logradouro";
		SUCESSO_ATUALIZACAO = "sucesso.atualizacao.logradouro";
		SUCESSO_EXCLUSAO = "sucesso.exclusao.logradouro";
		SUCESSO_PESQUISA = "sucesso.pesquisa.logradouro";
		ERRO_CADASTRO = "erro.cadastro.logradouro";
		ERRO_ATUALIZACAO = "erro.atualizacao.logradouro";
		ERRO_EXCLUSAO = "erro.exclusao.logradouro";
		ERRO_PESQUISA = "erro.pesquisa.logradouro";
		PESQUISA_NAO_ENCONTRADA = "pesquisa.logradouro.nao.encontrado";
	}

	/**
	 * Method getListaBairros.
	
	 * @return List<Bairro> */
	public List<Bairro> getListaBairros() {
		return listaBairros;
	}

	/**
	 * Method setListaBairros.
	 * @param listaBairros List<Bairro>
	 */
	public void setListaBairros(List<Bairro> listaBairros) {
		this.listaBairros = listaBairros;
	}

	/**
	 * Method getListaMunicipios.
	
	 * @return List<Municipio> */
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
	
	/**
	 * Method obterListaMunicipios.
	 * @param event ValueChangeEvent
	
	 * @throws ServicesException */
	public void obterListaMunicipios(ValueChangeEvent event) throws ServicesException{
		UF uf = (UF)event.getNewValue();
		Set<Municipio> municipiosObtidos = municipioServices.obterListaMunicipiosPelaUF(uf);
		setListaMunicipios(new ArrayList<Municipio>(municipiosObtidos));
		setListaBairros(new ArrayList<Bairro>());
	}
	
	/**
	 * Method obterListaBairros.
	 * @param event ValueChangeEvent
	
	 * @throws ServicesException */
	public void obterListaBairros(ValueChangeEvent event) throws ServicesException{
		Municipio municipio = (Municipio)event.getNewValue();
		Set<Bairro> listaBairros = bairroServices.obterListaPeloIdMunicipio(municipio.getId());
		setListaBairros(new ArrayList<Bairro>(listaBairros));
	}
	
}
