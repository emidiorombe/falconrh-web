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

import richard.falconrh.entity.localizacao.Logradouro;
import richard.falconrh.exception.ServicesException;
import richard.falconrh.service.LogradouroServices;

/**
 * Classe para conversão de instâncias de objetos Logradouro em Strings para utilização em arquivos xhtml
 * @author Richard Mendes Madureira
 * @version $Revision: 1.0 $
 */
@FacesConverter(forClass=Logradouro.class, value="logradouroConverter")
public class LogradouroConverter implements Converter {
	private static final Logger logger = Logger.getLogger(LogradouroConverter.class);
	
	/**
	 * Method getAsObject.
	 * @param context FacesContext
	 * @param component UIComponent
	 * @param value String
	 * @return Object * @see javax.faces.convert.Converter#getAsObject(FacesContext, UIComponent, String)
	 */
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Logradouro logradouro = null;
		if(StringUtils.isNotBlank(value) && !"--Selecione--".equals(value) && StringUtils.isNumeric(value)){
			try {
				Long idLogradouro = Long.valueOf(value);
				logradouro = getLogradouroServices().obterPeloId(Logradouro.class, idLogradouro);
				if(logger.isDebugEnabled() && logradouro!=null){
					logger.debug("Logradouro encontrada: " + logradouro.getId() + " - " + logradouro.getNome());
				}
			} catch (ServicesException e) {
				logger.error("Erro ao converter a string para um objeto do tipo Logradouro", e);
				FacesMessage facesMessage = new FacesMessage("Erro de Conversão: ", "Erro ao converter a String para um objeto do tipo Logradouro");
				FacesContext.getCurrentInstance().addMessage(null, facesMessage);
			}
			return logradouro;
		}
		return null;
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
		String idLogradouro = null;
		if (value != null && (value instanceof Logradouro)) {
			idLogradouro = String.valueOf(((Logradouro) value).getId());
		}
		if(logger.isDebugEnabled()){
			logger.debug("String convertida: " + idLogradouro);
		}
		return idLogradouro;
	}

	/**
	 * Method getLogradouroServices.
	 * @return LogradouroServices
	 */
	private LogradouroServices getLogradouroServices() {
		try {
			Context context = new InitialContext();
			String name = "java:global/falconrh-web/ejb/LogradouroServices";
			LogradouroServices services = (LogradouroServices) context.lookup(name);
			return services;
		} catch (NamingException e) {
			e.printStackTrace();
		}
		return null;
	}
}