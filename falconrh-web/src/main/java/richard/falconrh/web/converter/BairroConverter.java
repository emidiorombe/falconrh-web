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

import richard.falconrh.entity.localizacao.Bairro;
import richard.falconrh.exception.ServicesException;
import richard.falconrh.service.BairroServices;

/**
 * Classe para conversão de instâncias de objetos Bairros em Strings para utilização em arquivos xhtml
 * @author Richard Mendes Madureira
 * @version $Revision: 1.0 $
 */
@FacesConverter(forClass=Bairro.class, value="bairroConverter")
public class BairroConverter implements Converter {
	private static final Logger logger = Logger.getLogger(BairroConverter.class);
	
	/**
	 * Method getAsObject.
	 * @param context FacesContext
	 * @param component UIComponent
	 * @param value String
	 * @return Object * @see javax.faces.convert.Converter#getAsObject(FacesContext, UIComponent, String)
	 */
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Bairro bairro = null;
		if(StringUtils.isNotBlank(value) && !"--Selecione--".equals(value) && StringUtils.isNumeric(value)){
			try {
				Long idBairro = Long.valueOf(value);
				bairro = getBairroServices().obterPeloId(Bairro.class, idBairro);
				if(logger.isDebugEnabled()){
					logger.debug("Bairro encontrado: " + bairro.getId() + " - " + bairro.getNome());
				}
			} catch (ServicesException e) {
				logger.error("Erro ao converter a string para um objeto do tipo Bairro", e);
				FacesMessage facesMessage = new FacesMessage("Erro de Conversão: ", "Erro ao converter a String para um objeto do tipo Bairro");
				FacesContext.getCurrentInstance().addMessage(null, facesMessage);
			}
			return bairro;
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
		String idBairro = null;
		if (value != null && (value instanceof Bairro)) {
			idBairro = String.valueOf(((Bairro) value).getId());
		}
		if(logger.isDebugEnabled()){
			logger.debug("String convertida: " + idBairro);
		}
		return idBairro;
	}

	/**
	 * Method getBairroServices.
	 * @return BairroServices
	 */
	private BairroServices getBairroServices() {
		try {
			Context context = new InitialContext();
			String name = "java:global/falconrh-web/ejb/BairroServices";
			BairroServices services = (BairroServices) context.lookup(name);
			return services;
		} catch (NamingException e) {
			e.printStackTrace();
		}
		return null;
	}
}