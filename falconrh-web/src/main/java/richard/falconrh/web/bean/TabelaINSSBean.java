package richard.falconrh.web.bean;

import java.util.ArrayList;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import richard.falconrh.entity.inss.AliquotaINSS;
import richard.falconrh.entity.inss.TabelaINSS;
import richard.falconrh.service.TabelaINSSServices;

/**
 * ManagedBean JSF utilizado em arquivos xhtml
 * @author Richard Mendes Madureira
 * @version $Revision: 1.0 $
 */
@ManagedBean(name="tabelaINSSBean")
@ViewScoped
public class TabelaINSSBean extends BaseBean<TabelaINSS, TabelaINSSServices>{
	private static final long serialVersionUID = 3252063837702084394L;

	private AliquotaINSS aliquotaINSS;
	
	@EJB(name="ejb/TabelaINSSServices")
	TabelaINSSServices tabelaINSSServices;
	
	public TabelaINSSBean(){
		super();
	}
	
	public void inicializaEntity() {
		setEntity(new TabelaINSS());
		getEntity().setListaAliquotasINSS(new ArrayList<AliquotaINSS>());
		setAliquotaINSS(new AliquotaINSS());
	}
	
	/**
	 * Method reinitAliquotasINSS.
	 * @return String
	 */
	public String reinitAliquotasINSS(){
		setAliquotaINSS(new AliquotaINSS());
		return null;
	}

	@Override
	public void beforeCadastrar() {
		atualizarObjetoAntes();
	}
	
	@Override
	public void beforeAtualizar() {
		atualizarObjetoAntes();
	}
	private void atualizarObjetoAntes() {
		for(AliquotaINSS a :  getEntity().getListaAliquotasINSS()){
			a.setTabelaINSS(getEntity());
		}
	}

	/**
	 * Method getAliquotaINSS.
	
	 * @return AliquotaINSS */
	public AliquotaINSS getAliquotaINSS() {
		return aliquotaINSS;
	}

	/**
	 * Method setAliquotaINSS.
	 * @param aliquotaINSS AliquotaINSS
	 */
	public void setAliquotaINSS(AliquotaINSS aliquotaINSS) {
		this.aliquotaINSS = aliquotaINSS;
	}

	/**
	 * Method getServices.
	
	 * @return TabelaINSSServices */
	@Override
	public TabelaINSSServices getServices() {
		return tabelaINSSServices;
	}

	@Override
	public void setMensagensInformativas() {
		SUCESSO_CADASTRO = "sucesso.cadastro.tabelaINSS";
		SUCESSO_ATUALIZACAO = "sucesso.atualizacao.tabelaINSS";
		SUCESSO_EXCLUSAO = "sucesso.exclusao.tabelaINSS";
		SUCESSO_PESQUISA = "sucesso.pesquisa.tabelaINSS";
		ERRO_CADASTRO = "erro.cadastro.tabelaINSS";
		ERRO_ATUALIZACAO = "erro.atualizacao.tabelaINSS";
		ERRO_EXCLUSAO = "erro.exclusao.tabelaINSS";
		ERRO_PESQUISA = "erro.pesquisa.tabelaINSS";
		PESQUISA_NAO_ENCONTRADA = "pesquisa.tabelaINSS.nao.encontrada";
	}
}