package richard.falconrh.web.bean;

import java.util.ArrayList;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import richard.falconrh.entity.documento.Documento;
import richard.falconrh.entity.localizacao.Telefone;
import richard.falconrh.entity.pessoa.Pessoa;
import richard.falconrh.service.PessoaServices;


/**
 * ManagedBean JSF utilizado em arquivos xhtml
 * @author Richard Mendes Madureira
 * @version $Revision: 1.0 $
 */
@ManagedBean(name="pessoaBean")
@ViewScoped
public class PessoaBean extends BaseBean<Pessoa, PessoaServices>{
	private static final long serialVersionUID = -3829796433449749153L;

	@EJB(name="ejb/PessoaServices")
	private PessoaServices pessoaServices;
	
	private Telefone telefone;
	private Documento documento;
	
	public PessoaBean(){
		super();
	}
	
	@Override
	public void inicializaEntity() {
		setEntity(new Pessoa());
		getEntity().setListaTelefones(new ArrayList<Telefone>());
		getEntity().setListaDocumentos(new ArrayList<Documento>());
		setListaEntities(new ArrayList<Pessoa>());
		setTelefone(new Telefone());
		setDocumento(new Documento());
	}
	
	@Override
	public void beforeCadastrar() {
		atualizarObjetoPessoaDoTelefone();
		atualizarObjetoPessoaDoDocumento();
	}

	@Override
	public void beforeAtualizar() {
		atualizarObjetoPessoaDoTelefone();
		atualizarObjetoPessoaDoDocumento();
	}
	
	/**
	 * Method reinitAliqutoasImpostoRenda.
	 * @return String */
	public String reinitTelefones(){
		setTelefone(new Telefone());
		return null;
	}
	
	public String reinitDocumentos(){
		setDocumento(new Documento());
		return null;
	}
		
	private void atualizarObjetoPessoaDoTelefone(){
		for(Telefone telefone : getEntity().getListaTelefones()){
			if(telefone.getListaPessoas()!=null && !telefone.getListaPessoas().contains(telefone)){
				telefone.getListaPessoas().add(getEntity());
			}else if(telefone.getListaPessoas()==null){
				telefone.setListaPessoas(new ArrayList<Pessoa>());
				telefone.getListaPessoas().add(getEntity());
			}
		}
	}
	
	private void atualizarObjetoPessoaDoDocumento(){
		for(Documento documento : getEntity().getListaDocumentos()){
			if(documento.getPessoa()==null){
				documento.setPessoa(getEntity());
			}
		}
	}
	
	/**
	 * Method getTelefone.
	
	 * @return Telefone */
	public Telefone getTelefone() {
		return telefone;
	}

	/**
	 * Method setTelefone.
	 * @param telefone Telefone
	 */
	public void setTelefone(Telefone telefone) {
		this.telefone = telefone;
	}
	
	public Documento getDocumento(){
		return documento;
	}
	
	public void setDocumento(Documento documento){
		this.documento = documento;
	}
	
	/**
	 * Method getServices.
	 * @return PessoaServices */
	@Override
	public PessoaServices getServices() {
		return pessoaServices;
	}

	@Override
	public void setMensagensInformativas() {
		SUCESSO_CADASTRO = "sucesso.cadastro.pessoa";
		SUCESSO_ATUALIZACAO = "sucesso.atualizacao.pessoa";
		SUCESSO_EXCLUSAO = "sucesso.exclusao.pessoa";
		SUCESSO_PESQUISA = "sucesso.pesquisa.pessoa";
		ERRO_CADASTRO = "erro.cadastro.pessoa";
		ERRO_ATUALIZACAO = "erro.atualizacao.pessoa";
		ERRO_EXCLUSAO = "erro.exclusao.pessoa";
		ERRO_PESQUISA = "erro.pesquisa.pessoa";
		PESQUISA_NAO_ENCONTRADA = "pesquisa.pessoa.nao.encontrada";
	}
}
