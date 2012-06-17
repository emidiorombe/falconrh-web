package richard.falconrh.web.bean;

import java.util.ArrayList;
import java.util.Set;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import richard.falconrh.entity.folha.ConfiguracaoFolhaPagamento;
import richard.falconrh.exception.ServicesException;
import richard.falconrh.service.ConfiguracaoFolhaPagamentoServices;

/**
 * ManagedBean JSF utilizado em arquivos xhtml
 * @author Richard Mendes Madureira
 * @version $Revision: 1.0 $
 */
@ManagedBean(name="configuracaoFolhaPagamentoBean")
@ViewScoped
public class ConfiguracaoFolhaPagamentoBean extends BaseBean<ConfiguracaoFolhaPagamento, ConfiguracaoFolhaPagamentoServices>{
	private static final long serialVersionUID = -7205911761531138512L;
	
	@EJB(name="ejb/ConfiguracaoFolhaPagamentoServices")
	private ConfiguracaoFolhaPagamentoServices configuracaoFolhaPagamentoServices;
	
	public ConfiguracaoFolhaPagamentoBean(){
		super();
	}
	
	@Override
	public void inicializaEntity() {
		setEntity(new ConfiguracaoFolhaPagamento());
		setListaEntities(new ArrayList<ConfiguracaoFolhaPagamento>());
		getEntity().setDiaPagamentoFolhaAdiantamento13(1);
		getEntity().setDiaPagamentoFolhaAdiantamentoFerias(1);
		getEntity().setDiaPagamentoFolhaAdiantamentoFeriasManual(1);
		getEntity().setDiaPagamentoFolha13(1);
		getEntity().setDiaPagamentoFolhaNormal(1);
		getEntity().setDiaPagamentoFolhaNormalManual(1);
		getEntity().setMesCobrancaContribuicaoSindical(1);
		getEntity().setMesLimitePagamentoFolhaAdiantamento13(1);
		getEntity().setMesPagamentoFolhaAdiantamento13(1);
		getEntity().setForcarDiaPagamentoFolhaNormalManual(Boolean.TRUE);
	}
	
	/**
	 * Method getServices.
	 * @return configuracaoFolhaPagamentoServices 
	 */
	@Override
	public ConfiguracaoFolhaPagamentoServices getServices() {
		return configuracaoFolhaPagamentoServices;
	}

	@Override
	public void setMensagensInformativas() {
		SUCESSO_CADASTRO = "sucesso.cadastro.configuracaoFolhaPagamento";
		SUCESSO_ATUALIZACAO = "sucesso.atualizacao.configuracaoFolhaPagamento";
		SUCESSO_EXCLUSAO = "sucesso.exclusao.configuracaoFolhaPagamento";
		SUCESSO_PESQUISA = "sucesso.pesquisa.configuracaoFolhaPagamento";
		ERRO_CADASTRO = "erro.cadastro.configuracaoFolhaPagamento";
		ERRO_ATUALIZACAO = "erro.atualizacao.configuracaoFolhaPagamento";
		ERRO_EXCLUSAO = "erro.exclusao.configuracaoFolhaPagamento";
		ERRO_PESQUISA = "erro.pesquisa.configuracaoFolhaPagamento";
		PESQUISA_NAO_ENCONTRADA = "pesquisa.configuracaoFolhaPagamento.nao.encontrada";
	}
	
	@Override
	public void cadastrar(ActionEvent event) {
		try {
			Set<ConfiguracaoFolhaPagamento> configuracaoFolhaPagamentoTemp = getServices().obterListaPeloExemplo(getEntity());
			if(!configuracaoFolhaPagamentoTemp.isEmpty()){
				adicionarMensagemErro("erro.registro.jah.existente");
				return;
			}
		} catch (ServicesException e) {
			adicionarMensagemErroFatal(ERRO_PESQUISA);
		}
		super.cadastrar(event);
	}
	
	public void changeForcarDataFolhaNormal(){
		if(getEntity().getForcarDiaPagamentoFolhaNormalManual()){
			
		}
	}
}