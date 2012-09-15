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

import richard.falconrh.entity.banco.Agencia;
import richard.falconrh.exception.ServicesException;
import richard.falconrh.service.AgenciaServices;

/**
 * Classe para conversão de instâncias de objetos Agência em Strings para utilização em arquivos xhtml
 * @author Richard Mendes Madureira
 * @version $Revision: 1.0 $
 */
@FacesConverter(forClass=Agencia.class, value="agenciaConverter")
public class AgenciaConverter implements Converter {
	private static final Logger logger = Logger.getLogger(AgenciaConverter.class);
	
	/**
	 * Method getAsObject.
	 * @param context FacesContext
	 * @param component UIComponent
	 * @param value String
	 * @return Object * @see javax.faces.convert.Converter#getAsObject(FacesContext, UIComponent, String)
	 */
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Agencia agencia = null;
		if(StringUtils.isNotBlank(value) && !"--Selecione--".equals(value) && StringUtils.isNumeric(value)){
			try {
				Long idAgencia = Long.valueOf(value);
				agencia = getAgenciaServices().obterPeloId(Agencia.class, idAgencia);
				if(logger.isDebugEnabled() && agencia!=null){
					logger.debug("Agência encontrada: " + agencia.getId() + " - " + agencia.getNome());
				}
			} catch (ServicesException e) {
				logger.error("Erro ao converter a string para um objeto do tipo Agencia", e);
				FacesMessage facesMessage = new FacesMessage("Erro de Conversão: ", "Erro ao converter a String para um objeto do tipo Agencia");
				FacesContext.getCurrentInstance().addMessage(null, facesMessage);
			}
			return agencia;
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
		String idAgencia = null;
		if (value != null && (value instanceof Agencia)) {
			idAgencia = String.valueOf(((Agencia) value).getId());
		}
		if(logger.isDebugEnabled()){
			logger.debug("String convertida: " + idAgencia);
		}
		return idAgencia;
	}

	/**
	 * Method getAgenciaServices.
	 * @return AgenciaServices
	 */
	private AgenciaServices getAgenciaServices(){
		try {
			Context context = new InitialContext();
			String name = "java:global/falconrh-web/ejb/AgenciaServices";
			AgenciaServices services = (AgenciaServices) context.lookup(name);
			return services;
		} catch (NamingException e) {
			logger.error("Erro ao tentar obter o EJB AgenciaServices");
			e.printStackTrace();
		}
		return null;
	}
}