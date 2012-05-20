package richard.falconrh.web.bean;

import java.util.ArrayList;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import richard.falconrh.entity.ir.AliquotaImpostoRenda;
import richard.falconrh.entity.ir.TabelaImpostoRenda;
import richard.falconrh.service.ImpostoRendaServices;

/**
 * ManagedBean JSF utilizado em arquivos xhtml
 * @author Richard Mendes Madureira
 * @version $Revision: 1.0 $
 */
@ManagedBean(name="tabelaImpostoRendaBean")
@ViewScoped
public class TabelaImpostoRendaBean extends BaseBean<TabelaImpostoRenda, ImpostoRendaServices>{
	private static final long serialVersionUID = -7205911761531138512L;
	
	@EJB(name="ejb/ImpostoRendaServices")
	private ImpostoRendaServices impostoRendaServices;
	
	private AliquotaImpostoRenda aliquotaImpostoRenda;
	
	public TabelaImpostoRendaBean(){
		super();
	}
	
	@Override
	public void inicializaEntity() {
		setEntity(new TabelaImpostoRenda());
		getEntity().setListaAliquotasImpostoRenda(new ArrayList<AliquotaImpostoRenda>());
		setListaEntities(new ArrayList<TabelaImpostoRenda>());
		setAliquotaImpostoRenda(new AliquotaImpostoRenda());
	}
	
	@Override
	public void beforeCadastrar() {
		atualizarAliquotaComATabelaIR();
	}

	@Override
	public void beforeAtualizar() {
		atualizarAliquotaComATabelaIR();
	}
	
	/**
	 * Method reinitAliqutoasImpostoRenda.
	 * @return String
	 */
	public String reinitAliqutoasImpostoRenda(){
		setAliquotaImpostoRenda(new AliquotaImpostoRenda());
		return null;
	}
	
	private void atualizarAliquotaComATabelaIR(){
		for(AliquotaImpostoRenda aliquotaIR : getEntity().getListaAliquotasImpostoRenda()){
			aliquotaIR.setTabelaImpostoRenda(getEntity());
		}
	}
	
	/**
	 * Method getAliquotaImpostoRenda.
	 * @return AliquotaImpostoRenda
	 */
	public AliquotaImpostoRenda getAliquotaImpostoRenda() {
		return aliquotaImpostoRenda;
	}

	/**
	 * Method setAliquotaImpostoRenda.
	 * @param aliquotaImpostoRenda AliquotaImpostoRenda
	 */
	public void setAliquotaImpostoRenda(AliquotaImpostoRenda aliquotaImpostoRenda) {
		this.aliquotaImpostoRenda = aliquotaImpostoRenda;
	}
	
	/**
	 * Method getServices.
	 * @return ImpostoRendaServices */
	@Override
	public ImpostoRendaServices getServices() {
		return impostoRendaServices;
	}

	@Override
	public void setMensagensInformativas() {
		SUCESSO_CADASTRO = "sucesso.cadastro.tabelaImpostoRenda";
		SUCESSO_ATUALIZACAO = "sucesso.atualizacao.tabelaImpostoRenda";
		SUCESSO_EXCLUSAO = "sucesso.exclusao.tabelaImpostoRenda";
		SUCESSO_PESQUISA = "sucesso.pesquisa.tabelaImpostoRenda";
		ERRO_CADASTRO = "erro.cadastro.tabelaImpostoRenda";
		ERRO_ATUALIZACAO = "erro.atualizacao.tabelaImpostoRenda";
		ERRO_EXCLUSAO = "erro.exclusao.tabelaImpostoRenda";
		ERRO_PESQUISA = "erro.pesquisa.tabelaImpostoRenda";
		PESQUISA_NAO_ENCONTRADA = "pesquisa.tabelaImpostoRenda.nao.encontrada";
	}

}
