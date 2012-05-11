package richard.falconrh.web.bean;

import java.util.ArrayList;
import java.util.HashSet;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import richard.falconrh.entity.banco.Agencia;
import richard.falconrh.entity.banco.Banco;
import richard.falconrh.service.BancoServices;

/**
 * ManagedBean JSF utilizado em arquivos xhtml
 * @author Richard Mendes Madureira
 *
 * @version $Revision: 1.0 $
 */
@ManagedBean(name="bancoBean")
@ViewScoped
public class BancoBean extends BaseBean<Banco, BancoServices>{
	private static final long serialVersionUID = -7205911761531138512L;
	
	@EJB(name="ejb/BancoServices")
	private BancoServices bancoServices;
	
	public BancoBean(){
		super();
	}
	
	@Override
	public void inicializaEntity() {
		setEntity(new Banco());
		getEntity().setListaAgencias(new HashSet<Agencia>());
		setListaEntities(new ArrayList<Banco>());
	}
	
	/**
	 * Method getServices.
	
	 * @return BancoServices */
	@Override
	public BancoServices getServices() {
		return bancoServices;
	}

	@Override
	public void setMensagensInformativas() {
		SUCESSO_CADASTRO = "sucesso.cadastro.banco";
		SUCESSO_ATUALIZACAO = "sucesso.atualizacao.banco";
		SUCESSO_EXCLUSAO = "sucesso.exclusao.banco";
		SUCESSO_PESQUISA = "sucesso.pesquisa.banco";
		ERRO_CADASTRO = "erro.cadastro.banco";
		ERRO_ATUALIZACAO = "erro.atualizacao.banco";
		ERRO_EXCLUSAO = "erro.exclusao.banco";
		ERRO_PESQUISA = "erro.pesquisa.banco";
		PESQUISA_NAO_ENCONTRADA = "pesquisa.banco.nao.encontrado";
	}
	
}
