package richard.falconrh.web.bean;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import richard.falconrh.entity.Parent;
import richard.falconrh.entity.banco.Agencia;
import richard.falconrh.entity.banco.Banco;
import richard.falconrh.entity.localizacao.Bairro;
import richard.falconrh.entity.localizacao.Endereco;
import richard.falconrh.entity.localizacao.Logradouro;
import richard.falconrh.entity.localizacao.Municipio;
import richard.falconrh.entity.pessoa.Pessoa;
import richard.falconrh.entity.seguranca.Acao;
import richard.falconrh.exception.ServicesException;
import richard.falconrh.modelo.enums.EstadoCivil;
import richard.falconrh.modelo.enums.Etnia;
import richard.falconrh.modelo.enums.Nacionalidade;
import richard.falconrh.modelo.enums.NivelAcesso;
import richard.falconrh.modelo.enums.Sexo;
import richard.falconrh.modelo.enums.TipoDocumento;
import richard.falconrh.modelo.enums.TipoFeriado;
import richard.falconrh.modelo.enums.TipoLogradouro;
import richard.falconrh.modelo.enums.TipoTelefone;
import richard.falconrh.modelo.enums.UF;
import richard.falconrh.service.AbstractServices;
import richard.falconrh.service.AcaoServices;
import richard.falconrh.service.BancoServices;
import richard.falconrh.service.PessoaServices;

/**
 * ManagedBean JSF utilizado em arquivos xhtml
 * @author Richard Mendes Madureira
 *
 * @version $Revision: 1.0 $
 */
@ManagedBean(name="utilBean")
@RequestScoped
public class UtilBean extends BaseBean<Parent, AbstractServices<Parent>> implements Serializable{
	private static final long serialVersionUID = 3751062097379238368L;
	private static final Logger LOGGER = Logger.getLogger(UtilBean.class);
	
	private Endereco endereco;
	
	public UtilBean(){
		setEndereco(new Endereco());
		getEndereco().setLogradouro(new Logradouro());
		getEndereco().getLogradouro().setBairro(new Bairro());
		getEndereco().getLogradouro().getBairro().setMunicipio(new Municipio());
	}

	@EJB(name="ejb/BancoServices")
	private BancoServices bancoServices;
	
	@EJB(name="ejb/PessoaServices")
	private PessoaServices pessoaServices;
	
	@EJB(name="ejb/AcaoServices")
	private AcaoServices acaoServices;
	
	/**
	 * Method getListaNacionalidades.
	 * @return SelectItem[]
	 */
	public SelectItem[] getListaNacionalidades(){
		SelectItem[] lista = new SelectItem[Nacionalidade.values().length];
		int cont = 0;
		SelectItem item = null;
		for(Nacionalidade nacionalidade : Nacionalidade.values()){
			item = new SelectItem(nacionalidade, nacionalidade.getDescricao());
			lista[cont++] = item;
		}
		return lista;
	}
	
	/**
	 * Method getListaSexos.
	 * @return SelectItem[]
	 */
	public SelectItem[] getListaSexos(){
		SelectItem[] lista = new SelectItem[Sexo.values().length];
		int cont = 0;
		SelectItem item = null;
		for(Sexo sexo : Sexo.values()){
			item = new SelectItem(sexo, sexo.getDescricao());
			lista[cont++] = item;
		}
		return lista;
	}

	/**
	 * Method getListaEstadosCivis.
	 * @return SelectItem[]
	 */
	public SelectItem[] getListaEstadosCivis(){
		SelectItem[] lista = new SelectItem[EstadoCivil.values().length];
		int cont = 0;
		SelectItem item = null;
		for(EstadoCivil estadoCivil: EstadoCivil.values()){
			item = new SelectItem(estadoCivil, estadoCivil.getDescricao());
			lista[cont++] = item;
		}
		return lista;
	}

	/**
	 * Method getListaTiposDocumentos.
	 * @return SelectItem[]
	 */
	public SelectItem[] getListaTiposDocumentos(){
		SelectItem[] lista = new SelectItem[TipoDocumento.values().length];
		int cont = 0;
		SelectItem item = null;
		for(TipoDocumento tipoDocumento : TipoDocumento.values()){
			item = new SelectItem(tipoDocumento, tipoDocumento.getDescricao());
			lista[cont++] = item;
		}
		return lista;
	}
	
	/**
	 * Method getListaTiposTelefones.
	 * @return SelectItem[]
	 */
	public SelectItem[] getListaTiposTelefones(){
		SelectItem[] lista = new SelectItem[TipoTelefone.values().length];
		int cont = 0;
		SelectItem item = null;
		for(TipoTelefone tipoTelefone: TipoTelefone.values()){
			item = new SelectItem(tipoTelefone, tipoTelefone.getDescricao());
			lista[cont++] = item;
		}
		return lista;
	}
	
	/**
	 * Method getListaNiveisAcessos.
	 * @return SelectItem[]
	 */
	public SelectItem[] getListaNiveisAcessos(){
		SelectItem[] lista = new SelectItem[NivelAcesso.values().length];
		int cont = 0;
		SelectItem item = null;
		for(NivelAcesso nivelAcesso: NivelAcesso.values()){
			item = new SelectItem(nivelAcesso, nivelAcesso.getNome());
			lista[cont++] = item;
		}
		return lista;
	}
	
	/**
	 * Method getListaEtnias.
	 * @return SelectItem[]
	 */
	public SelectItem[] getListaEtnias(){
		SelectItem[] lista = new SelectItem[Etnia.values().length];
		int cont = 0;
		SelectItem item = null;
		for(Etnia etnia: Etnia.values()){
			item = new SelectItem(etnia, etnia.getDescricao());
			lista[cont++] = item;
		}
		return lista;
	}
	
	/**
	 * Method getListaTiposLogradouros.
	 * @return SelectItem[]
	 */
	public SelectItem[] getListaTiposLogradouros(){
		SelectItem[] lista = new SelectItem[TipoLogradouro.values().length];
		int cont = 0;
		SelectItem item = null;
		for(TipoLogradouro tipo : TipoLogradouro.values()){
			item = new SelectItem(tipo, tipo.getNome());
			lista[cont++] = item;
		}
		return lista;
	}
	
	/**
	 * Method getListaUFs.
	 * @return SelectItem[]
	 */
	public SelectItem[] getListaUFs(){
		SelectItem[] lista = new SelectItem[UF.values().length];
		int cont = 0;
		SelectItem item = null;
		for(UF uf : UF.values()){
			item = new SelectItem(uf, uf.getNome());
			lista[cont++] = item;
		}
		return lista;
	}
	
	/**
	 * Method getListaTiposFeriados.
	 * @return SelectItem[]
	 */
	public SelectItem[] getListaTiposFeriados(){
		SelectItem[] lista = new SelectItem[TipoFeriado.values().length];
		int cont = 0;
		SelectItem item = null;
		for(TipoFeriado tipoFeriado : TipoFeriado.values()){
			item = new SelectItem(tipoFeriado, tipoFeriado.getDescricao());
			lista[cont++] = item;
		}
		return lista;
	}
	
	/**
	 * Method getListaBancos.
	 * @return SelectItem[]
	 */
	public SelectItem[] getListaBancos(){
		SortedSet<Banco> listaBancos = new TreeSet<Banco>();
		try{
			listaBancos = bancoServices.obterListaTodosBancos();
		}catch(ServicesException e){
			LOGGER.error("erro.obter.lista.bancos", e);
			adicionarMensagemErro("erro.obter.lista.bancos");
			return null;
		}
		SelectItem[] lista = new SelectItem[listaBancos.size()];
		int cont = 0;
		SelectItem item = null;
		for(Banco banco : listaBancos){
			item = new SelectItem(banco, banco.getDescricao());
			lista[cont++] = item;
		}
		return lista;
	}
	
	/**
	 * Method getListaAgencias.
	 * @return SelectItem[]
	 */
	public SelectItem[] getListaAgencias(){
		String sIdBanco = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idBanco");
		if(StringUtils.isNotBlank(sIdBanco)){
			Set<Agencia> listaAgencias = new HashSet<Agencia>();
			Long idBanco = Long.valueOf(sIdBanco);
			try{
				listaAgencias = bancoServices.obterListaAgenciasPeloIdBanco(idBanco);
			}catch(ServicesException e){
				LOGGER.error("erro.obter.lista.agencias", e);
				adicionarMensagemErro("erro.obter.lista.agencias");
				return null;
			}
			SelectItem[] lista = new SelectItem[listaAgencias.size()];
			int cont = 0;
			SelectItem item = null;
			for(Agencia agencia : listaAgencias){
				item = new SelectItem(agencia, agencia.getNumeroFormatado());
				lista[cont++] = item;
			}
			return lista;
		}
		return null;
	}
	
	public SelectItem[] getListaTodasAcoes(){
		Set<Acao> listaAcoes = new TreeSet<Acao>();
		try{
			listaAcoes = acaoServices.obterListaTodasAcoes();
		}catch(ServicesException e){
			LOGGER.error("erro.obter.lista.acoes", e);
			adicionarMensagemErro("erro.obter.lista.acoes");
			return null;
		}
		SelectItem[] lista = new SelectItem[listaAcoes.size()];
		int cont = 0;
		SelectItem item = null;
		for(Acao acao : listaAcoes){
			item = new SelectItem(acao, acao.getNome());
			lista[cont++] = item;
		}
		return lista;
	}
	
	/**
	 * Method getListaSimOuNao.
	 * @return SelectItem[]
	 */
	public SelectItem[] getListaSimOuNao(){
		SelectItem[] items = new SelectItem[2];
		items[0] = new SelectItem(Boolean.TRUE, "Sim");
		items[1] = new SelectItem(Boolean.FALSE, "Nao");
		return items;
	}
	
	/**
	 * Method getListaPessoas.
	 * @return SelectItem[]
	 */
	public SelectItem[] getListaPessoas(){
		LOGGER.info("Iniciando pesquisa de todoas as pessoas");
		Set<Pessoa> listaPessoas;
		try {
			listaPessoas = pessoaServices.obterListaTodasPessoas();
			LOGGER.info("Total de pessoas encontradas: " + listaPessoas.size());
		} catch (ServicesException e) {
			LOGGER.error("Erro ao pesquisar todas as pessoas", e);
			adicionarMensagemErroFatal("erro.pesquisa.pessoa");
			return null;
		}
		SelectItem[] listaSelectItems = new SelectItem[listaPessoas.size()];
		SelectItem item = null;
		int cont = 0;
		for(Pessoa pessoa : listaPessoas){
			item = new SelectItem(pessoa, pessoa.getNome() + " - " + new SimpleDateFormat("dd/MM/yyyy").format(pessoa.getDataNascimento()));
			listaSelectItems[cont++] = item;
		}
		return listaSelectItems;
	}
	
	/**
	 * Method getEndereco.
	 * @return Endereco
	 */
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

	@Override
	public void setMensagensInformativas() {
		// TODO Auto-generated method stub
		
	}
	
}