package richard.falconrh.web.bean;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.apache.log4j.Logger;

import richard.falconrh.exception.ServicesException;
import richard.falconrh.quartz.StatusQuartz;
import richard.falconrh.service.QuartzServices;

@SuppressWarnings("rawtypes")
@ManagedBean(name="quartzBean")
@ViewScoped
public class QuartzBean extends BaseBean{
	private static final long serialVersionUID = 1L;

	private static final Logger logger = Logger.getLogger(QuartzBean.class);
	
	private static final String SUCESSO_INICIALIZACAO_QUARTZ = "sucesso.inicializacao.quartz";
	private static final String ERRO_INICIALIZACAO_QUARTZ = "erro.inicializacao.quartz";
	private static final String SUCESSO_HIBERNACAO_QUARTZ = "sucesso.hibernacao.quartz";
	private static final String ERRO_HIBERNACAO_QUARTZ = "erro.hibernacao.quartz";
	private static final String SUCESSO_DESLIGAMENTO_QUARTZ = "sucesso.desligamento.quartz";
	private static final String ERRO_DESLIGAMENTO_QUARTZ = "erro.desligamento.quartz";
	private static final String ERRO_STATUS_QUARTZ = "erro.status.quartz";
	//------------------------------------------------------------------------------
	private StatusQuartz statusQuartz;
	
	@EJB(name="ejb/QuartzServices")
	private QuartzServices quartzServices;
	
	@PostConstruct
	public void obterStatusDoQuartz(){
		obterStatusQuartz(null);
	}
	
	@Override
	public void setMensagensInformativas() {
		// TODO Auto-generated method stub
	}
	
	public void iniciarQuartz(ActionEvent event){
		try {
			setStatusQuartz(quartzServices.iniciarQuartz());
			logger.info("Quartz iniciado com sucesso");
			adicionarMensagemInformacao(SUCESSO_INICIALIZACAO_QUARTZ);
		} catch (ServicesException e) {
			logger.error("Erro ao inicializar o quartz");
			adicionarMensagemErro(ERRO_INICIALIZACAO_QUARTZ);
		}
	}
	
	public void desligarQuartz(ActionEvent event){
		try {
			setStatusQuartz(quartzServices.desligarQuartz());
			logger.info("Quartz desligado com sucesso");
			adicionarMensagemInformacao(SUCESSO_DESLIGAMENTO_QUARTZ);
		} catch (ServicesException e) {
			logger.error("Erro ao desligar o quartz");
			adicionarMensagemErro(ERRO_DESLIGAMENTO_QUARTZ);
		}
	}
	
	public void hibernarQuartz(ActionEvent event){
		try {
			setStatusQuartz(quartzServices.hibernarQuartz());
			logger.info("Quartz hibernado com sucesso");
			adicionarMensagemInformacao(SUCESSO_HIBERNACAO_QUARTZ);
		} catch (ServicesException e) {
			logger.error("Erro ao hibernar o quartz");
			adicionarMensagemErro(ERRO_HIBERNACAO_QUARTZ);
		}
	}
	
	public void obterStatusQuartz(ActionEvent event){
		try{
			setStatusQuartz(quartzServices.obterStatusQuartz());
			logger.info("Status do Quartz obtido: " + getStatusQuartz());
		}catch(ServicesException e){
			logger.error("Erro ao obter status do Quartz");
			adicionarMensagemErro(ERRO_STATUS_QUARTZ);
		}
	}

	public StatusQuartz getStatusQuartz() {
		return statusQuartz;
	}

	public void setStatusQuartz(StatusQuartz statusQuartz) {
		this.statusQuartz = statusQuartz;
	}
}
