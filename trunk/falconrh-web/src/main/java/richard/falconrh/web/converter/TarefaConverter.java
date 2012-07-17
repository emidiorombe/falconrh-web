package richard.falconrh.web.converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import richard.falconrh.exception.ServicesException;
import richard.falconrh.scheduler.Tarefa;
import richard.falconrh.service.TarefaServices;

/**
 * Classe para conversão de instancias de objetos Tarefa em Strings para utilização em arquivos xhtml
 * @author Richard Mendes Madureira
 * @version $Revision: 1.0 $
 */
@FacesConverter(forClass=Tarefa.class, value="tarefaConverter")
public class TarefaConverter implements Converter {
	private static final Logger logger = Logger.getLogger(TarefaConverter.class);

	/**
	 * Method getAsObject.
	 * @param context FacesContext
	 * @param component UIComponent
	 * @param value String
	 * @return Object * @see javax.faces.convert.Converter#getAsObject(FacesContext, UIComponent, String)
	 */
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Tarefa tarefa = null;
		if(StringUtils.isNotBlank(value) && !"--Selecione--".equals(value)){
			try {
				Long idTarefa = Long.valueOf(value);
				tarefa = getTarefaServices().obterPeloId(Tarefa.class, idTarefa);
			} catch (ServicesException e) {
				logger.error("Erro ao converter a string para um objeto do tipo Tarefa", e);
				FacesMessage facesMessage = new FacesMessage("Erro ao converter a String para um objeto do tipo Tarefa");
				FacesContext.getCurrentInstance().addMessage(null, facesMessage);
			}
		}
		return tarefa;
	}

	/**
	 * Method getAsString.
	 * @param context FacesContext
	 * @param component UIComponent
	 * @param value Object
	 * @return String * @see javax.faces.convert.Converter#getAsString(FacesContext, UIComponent, Object)
	 */
	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		String idTarefa = null;
		if (value != null && (value instanceof Tarefa)) {
			idTarefa = String.valueOf(((Tarefa) value).getId());
		}
		return idTarefa;
	}

	/**
	 * Method getTarefaServices.
	 * @return TarefaServices
	 */
	private TarefaServices getTarefaServices() {
		try {
			Context context = new InitialContext();
			String name = "java:global/falconrh-web/ejb/TarefaServices";
			TarefaServices services = (TarefaServices) context.lookup(name);
			return services;
		} catch (NamingException e) {
			logger.error("Erro ao obter servido TarefaServices");
			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Erro:", "Impossível obter servico Tarefa");
			FacesContext.getCurrentInstance().addMessage(null,  facesMessage );
		}
		return null;
	}

}
