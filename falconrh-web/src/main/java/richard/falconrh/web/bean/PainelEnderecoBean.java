package richard.falconrh.web.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ValueChangeEvent;

import org.apache.log4j.Logger;

import richard.falconrh.entity.localizacao.Bairro;
import richard.falconrh.entity.localizacao.Logradouro;
import richard.falconrh.entity.localizacao.Municipio;
import richard.falconrh.exception.ServicesException;
import richard.falconrh.modelo.enums.UF;
import richard.falconrh.service.BairroServices;
import richard.falconrh.service.LogradouroServices;
import richard.falconrh.service.MunicipioServices;

@ManagedBean(name="painelEnderecoBean")
@ViewScoped
public class PainelEnderecoBean implements Serializable{
	private static final Logger logger = Logger.getLogger(PainelEnderecoBean.class);
	private static final long serialVersionUID = 1L;
	
	private UF ufSelecionada;
	private Municipio municipioSelecionado;
	private Bairro bairroSelecionado;
	private Logradouro logradouroSelecionado;
	
	private List<Municipio> listaMunicipios;
	private List<Bairro> listaBairros;
	private List<Logradouro> listaLogradouros;

	@EJB(name="ejb/MunicipioServices")
	private MunicipioServices municipioServices;
	@EJB(name="ejb/BairroSevices")
	private BairroServices bairroServices;
	@EJB(name="ejb/LogradouroServices")
	private LogradouroServices logradouroServices;
	
	public PainelEnderecoBean(){
		setMunicipioSelecionado(new Municipio());
		setBairroSelecionado(new Bairro());
		setLogradouroSelecionado(new Logradouro());
		
		setListaMunicipios(new ArrayList<Municipio>());
		setListaBairros(new ArrayList<Bairro>());
		setListaLogradouros(new ArrayList<Logradouro>());
	}

	public void obterListaMunicipios(ValueChangeEvent event) throws ServicesException{
		logger.info("Obtendo lista de Municípios");
		UF uf = (UF)event.getNewValue();
		setUfSelecionada(uf);
		Set<Municipio> listaMunicipiosEncontrados = municipioServices.obterListaMunicipiosPelaUF(getUfSelecionada());
		setListaMunicipios(new ArrayList<Municipio>(listaMunicipiosEncontrados));
		//a partir daqui, limpar os campos abaixo de municipios
		setBairroSelecionado(new Bairro());
	}
	
	public void obterListaBairros(ValueChangeEvent event) throws ServicesException{
		logger.info("Obtendo lista de Bairros");
		Municipio municipio = (Municipio)event.getNewValue();
		Set<Bairro> listaBairrosEncontrados = bairroServices.obterListaPeloIdMunicipio(municipio.getId());
		setListaBairros(new ArrayList<Bairro>(listaBairrosEncontrados));
	}
	
	public void obterListaLogradouros(ValueChangeEvent event) throws ServicesException{
		logger.info("Obtendo lista de logradouros");
		Bairro bairro = (Bairro)event.getNewValue();
		Set<Logradouro> listaLogradourosEncontrados = logradouroServices.obterListaPeloIdBairro(bairro.getId());
		setListaLogradouros(new ArrayList<Logradouro>(listaLogradourosEncontrados));
	}

	public UF getUfSelecionada() {
		return ufSelecionada;
	}

	public void setUfSelecionada(UF ufSelecionada) {
		this.ufSelecionada = ufSelecionada;
	}

	public List<Municipio> getListaMunicipios() {
		return listaMunicipios;
	}

	public void setListaMunicipios(List<Municipio> listaMunicipios) {
		this.listaMunicipios = listaMunicipios;
	}

	public Municipio getMunicipioSelecionado() {
		return municipioSelecionado;
	}

	public void setMunicipioSelecionado(Municipio municipioSelecionado) {
		this.municipioSelecionado = municipioSelecionado;
	}

	public Bairro getBairroSelecionado() {
		return bairroSelecionado;
	}

	public void setBairroSelecionado(Bairro bairroSelecionado) {
		this.bairroSelecionado = bairroSelecionado;
	}

	public List<Bairro> getListaBairros() {
		return listaBairros;
	}

	public void setListaBairros(List<Bairro> listaBairros) {
		this.listaBairros = listaBairros;
	}

	public List<Logradouro> getListaLogradouros() {
		return listaLogradouros;
	}

	public void setListaLogradouros(List<Logradouro> listaLogradouros) {
		this.listaLogradouros = listaLogradouros;
	}

	public Logradouro getLogradouroSelecionado() {
		return logradouroSelecionado;
	}

	public void setLogradouroSelecionado(Logradouro logradouroSelecionado) {
		this.logradouroSelecionado = logradouroSelecionado;
	}
}
