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
 * Classe para conversão de instancias de objetos Agencia em Strings para utilização em arquivos xhtml
 * @author Richard Mendes Madureira
 *
 * @version $Revision: 1.0 $
 */
@FacesConverter(forClass=Agencia.class, value="agenciaConverter")
public class AgenciaConverter implements Converter {
	private static final Logger logger = Logger.getLogger(AgenciaConverter.class);
	private AgenciaServices agenciaServices;
	
	public AgenciaConverter(){
		agenciaServices = getAgenciaServices();
	}
	
	/**
	 * Method setAgenciaServices.
	 * @param agenciaServices AgenciaServices
	 */
	public void setAgenciaServices(AgenciaServices agenciaServices){
		this.agenciaServices = agenciaServices;
	}
	
	/**
	 * Method getAsObject.
	 * @param context FacesContext
	 * @param component UIComponent
	 * @param value String
	
	
	 * @return Object * @see javax.faces.convert.Converter#getAsObject(FacesContext, UIComponent, String) */
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Agencia agencia = null;
		if(StringUtils.isNotBlank(value) && !"--Selecione--".equals(value)){
			try {
				Long idAgencia = Long.valueOf(value);
				agencia = agenciaServices.obterPeloId(Agencia.class, idAgencia);
			} catch (ServicesException e) {
				logger.error("Erro ao converter a string para um objeto do tipo agencia", e);
				FacesMessage facesMessage = new FacesMessage("Erro ao converter a String para um objeto do tipo Agencia");
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
	
	
	 * @return String * @see javax.faces.convert.Converter#getAsString(FacesContext, UIComponent, Object) */
	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		String idAgencia = null;
		if (value != null && (value instanceof Agencia)) {
			idAgencia = String.valueOf(((Agencia) value).getId());
		}
		return idAgencia;
	}

	/**
	 * Method getAgenciaServices.
	
	 * @return AgenciaServices */
	private AgenciaServices getAgenciaServices() {
		try {
			Context context = new InitialContext();
			String name = "java:global/falconrh-web/ejb/AgenciaServices";
			AgenciaServices services = (AgenciaServices) context.lookup(name);
			return services;
		} catch (NamingException e) {
			e.printStackTrace();
		}
		return null;
	}

}
