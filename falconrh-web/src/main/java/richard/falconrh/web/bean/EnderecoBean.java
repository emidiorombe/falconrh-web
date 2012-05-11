package richard.falconrh.web.bean;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ValueChangeEvent;

import richard.falconrh.entity.localizacao.Bairro;
import richard.falconrh.entity.localizacao.Endereco;
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
@ManagedBean(name="enderecoBean")
@ViewScoped
public class EnderecoBean implements Serializable{
	private static final long serialVersionUID = 2937065706096773504L;

	private UF uf;
	private Municipio municipio;
	private Bairro bairro;
	private Endereco endereco;
	
	private Set<Municipio> listaMunicipios;
	private Set<Bairro> listaBairros;
	private Set<Logradouro> listaLogradouros;
	
	@EJB(name="ejb/MunicipioServices")
	private MunicipioServices municipioServices;
	@EJB(name="ejb/BairroServices")
	private BairroServices bairroServices;
	@EJB(name="ejb/LogradouroServices")
	private LogradouroServices logradouroServices;
	
	public EnderecoBean(){
		setMunicipio(new Municipio());
		setBairro(new Bairro());
		getBairro().setMunicipio(new Municipio());
		setListaMunicipios(new HashSet<Municipio>());
		setListaBairros(new HashSet<Bairro>());
		setListaLogradouros(new HashSet<Logradouro>());
		endereco = new Endereco();
		endereco.setLogradouro(new Logradouro());
		endereco.getLogradouro().setBairro(new Bairro());
		endereco.getLogradouro().getBairro().setMunicipio(new Municipio());
	}
	
	/**
	 * Method obterListaMunicipios.
	 * @param event ValueChangeEvent
	
	 * @throws ServicesException */
	public void obterListaMunicipios(ValueChangeEvent event) throws ServicesException{
		setUf((UF)event.getNewValue());
		Set<Municipio> municipiosObtidos = municipioServices.obterListaMunicipiosPelaUF(getUf());
		setListaMunicipios(municipiosObtidos);
		setListaBairros(new HashSet<Bairro>());
	}
	
	/**
	 * Method obterListaBairros.
	 * @param event ValueChangeEvent
	
	 * @throws ServicesException */
	public void obterListaBairros(ValueChangeEvent event) throws ServicesException{
		municipio = (Municipio)event.getNewValue();
		Set<Bairro> listaBairros = bairroServices.obterListaPeloIdMunicipio(municipio.getId());
		setListaBairros(listaBairros);
	}
	
	/**
	 * Method obterListaLogradouros.
	 * @param event ValueChangeEvent
	
	 * @throws ServicesException */
	public void obterListaLogradouros(ValueChangeEvent event) throws ServicesException{
		Bairro bairro = (Bairro) event.getNewValue();
		Set<Logradouro> listaLogradouros = logradouroServices.obterLogradourosPeloIdBairro(bairro.getId());
		setListaLogradouros(listaLogradouros);
	}
	
	/**
	 * Method atualizarEnderecoLogradouro.
	 * @param event ValueChangeEvent
	 */
	public void atualizarEnderecoLogradouro(ValueChangeEvent event){
		
	}

	/**
	 * Method setMunicipio.
	 * @param municipio Municipio
	 */
	public void setMunicipio(Municipio municipio) {
		this.municipio = municipio;
	}

	/**
	 * Method getMunicipio.
	
	 * @return Municipio */
	public Municipio getMunicipio() {
		return municipio;
	}

	/**
	 * Method setListaMunicipios.
	 * @param listaMunicipios Set<Municipio>
	 */
	public void setListaMunicipios(Set<Municipio> listaMunicipios) {
		this.listaMunicipios = listaMunicipios;
	}

	/**
	 * Method getListaMunicipios.
	
	 * @return Set<Municipio> */
	public Set<Municipio> getListaMunicipios() {
		return listaMunicipios;
	}

	/**
	 * Method setListaBairros.
	 * @param listaBairros Set<Bairro>
	 */
	public void setListaBairros(Set<Bairro> listaBairros) {
		this.listaBairros = listaBairros;
	}

	/**
	 * Method getListaBairros.
	
	 * @return Set<Bairro> */
	public Set<Bairro> getListaBairros() {
		return listaBairros;
	}

	/**
	 * Method setUf.
	 * @param uf UF
	 */
	public void setUf(UF uf) {
		this.uf = uf;
	}

	/**
	 * Method getUf.
	
	 * @return UF */
	public UF getUf() {
		return uf;
	}

	/**
	 * Method setBairro.
	 * @param bairro Bairro
	 */
	public void setBairro(Bairro bairro) {
		this.bairro = bairro;
	}

	/**
	 * Method getBairro.
	
	 * @return Bairro */
	public Bairro getBairro() {
		return bairro;
	}

	/**
	 * Method getEndereco.
	
	 * @return Endereco */
	public Endereco getEndereco() {
		return endereco;
	}

	/**
	 * Method setEndereco.
	 * @param endereco Endereco
	 */
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	/**
	 * Method getListaLogradouros.
	
	 * @return Set<Logradouro> */
	public Set<Logradouro> getListaLogradouros() {
		return listaLogradouros;
	}

	/**
	 * Method setListaLogradouros.
	 * @param listaLogradouros Set<Logradouro>
	 */
	public void setListaLogradouros(Set<Logradouro> listaLogradouros) {
		this.listaLogradouros = listaLogradouros;
	}
}
