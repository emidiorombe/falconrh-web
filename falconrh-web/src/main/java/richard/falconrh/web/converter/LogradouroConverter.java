package richard.falconrh.web.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.log4j.Logger;

import richard.falconrh.entity.localizacao.Logradouro;
import richard.falconrh.exception.ServicesException;
import richard.falconrh.service.LogradouroServices;

/**
 * Classe para conversão de instancias de objetos Logradouro em Strings para utilização em arquivos xhtml
 * @author Richard Mendes Madureira
 *
 * @version $Revision: 1.0 $
 */
@FacesConverter(forClass=Logradouro.class, value="logradouroConverter")
public class LogradouroConverter implements Converter {
	private static final Logger LOGGER = Logger.getLogger(LogradouroConverter.class);
	
	/**
	 * Method getAsObject.
	 * @param ctx FacesContext
	 * @param component UIComponent
	 * @param value String
	
	
	 * @return Object * @see javax.faces.convert.Converter#getAsObject(FacesContext, UIComponent, String) */
	@Override
	public Object getAsObject(FacesContext ctx, UIComponent component, String value) {
		LOGGER.debug("Convertendo A String logradouro em um objeto...");
		if(value==null || "".equals(value) || value.equals("--Selecione--")){
			return null;
		}
		Long id =  Long.parseLong(value);
		try{
			return getLogradouroServices().obterPeloId(Logradouro.class, id);
		}catch(ServicesException e){
			LOGGER.error("Erro ao converter o bairro", e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Method getAsString.
	 * @param ctx FacesContext
	 * @param component UIComponent
	 * @param object Object
	
	
	 * @return String * @see javax.faces.convert.Converter#getAsString(FacesContext, UIComponent, Object) */
	@Override
	public String getAsString(FacesContext ctx, UIComponent component, Object object) {
		LOGGER.debug("Convertendo o objeto bairro em String...");
		if(object instanceof Logradouro){
			return String.valueOf(((Logradouro) object).getId());
		}
		return null;
	}
	
	/**
	 * Method getLogradouroServices.
	
	 * @return LogradouroServices */
	public LogradouroServices getLogradouroServices(){
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
