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

import richard.falconrh.entity.banco.Banco;
import richard.falconrh.exception.ServicesException;
import richard.falconrh.service.BancoServices;

/**
 * Classe para conversão de instâncias de objetos Banco em Strings para utilização em arquivos xhtml
 * @author Richard Mendes Madureira
 * @version $Revision: 1.0 $
 */
@FacesConverter(forClass=Banco.class, value="bancoConverter")
public class BancoConverter implements Converter {
	private static final Logger logger = Logger.getLogger(BancoConverter.class);
	
	/**
	 * Method getAsObject.
	 * @param context FacesContext
	 * @param component UIComponent
	 * @param value String
	 * @return Object * @see javax.faces.convert.Converter#getAsObject(FacesContext, UIComponent, String)
	 */
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Banco banco = null;
		if(StringUtils.isNotBlank(value) && !"--Selecione--".equals(value) && StringUtils.isNumeric(value)){
			try {
				Long idBanco = Long.valueOf(value);
				banco = getBancoServices().obterPeloId(Banco.class, idBanco);
				if(logger.isDebugEnabled()){
					logger.debug("Banco encontrado: " + banco.getId() + " - " + banco.getNome());
				}
			} catch (ServicesException e) {
				logger.error("Erro ao converter a string para um objeto do tipo Banco", e);
				FacesMessage facesMessage = new FacesMessage("Erro de Conversão: ", "Erro ao converter a String para um objeto do tipo Banco");
				FacesContext.getCurrentInstance().addMessage(null, facesMessage);
			}
		}
		return banco;
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
		String idBanco = null;
		if (value != null && (value instanceof Banco)) {
			idBanco = String.valueOf(((Banco) value).getId());
		}
		if(logger.isDebugEnabled()){
			logger.debug("String convertida: " + idBanco);
		}
		return idBanco;
	}

	/**
	 * Method getBancoServices.
	 * @return BancoServices
	 */
	private BancoServices getBancoServices() {
		try {
			Context context = new InitialContext();
			String name = "java:global/falconrh-web/ejb/BancoServices";
			BancoServices services = (BancoServices) context.lookup(name);
			return services;
		} catch (NamingException e) {
			e.printStackTrace();
		}
		return null;
	}
}