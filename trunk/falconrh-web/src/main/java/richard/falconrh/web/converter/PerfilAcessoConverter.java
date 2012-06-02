package richard.falconrh.web.converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.log4j.Logger;

import richard.falconrh.entity.seguranca.PerfilAcesso;
import richard.falconrh.exception.ServicesException;
import richard.falconrh.service.PerfilAcessoServices;

/**
 * Classe para conversão de instancias de objetos Acao em Strings para utilização em arquivos xhtml
 * @author Richard Mendes Madureira
 * @version $Revision: 1.0 $
 */
@FacesConverter(forClass=PerfilAcesso.class, value="perfilAcessoConverter")
public class PerfilAcessoConverter implements Converter {
	private static final Logger logger = Logger.getLogger(PerfilAcessoConverter.class);

	/**
	 * Method getAsObject.
	 * @param context FacesContext
	 * @param component UIComponent
	 * @param value String
	 * @return Object * @see javax.faces.convert.Converter#getAsObject(FacesContext, UIComponent, String)
	 */
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Long idPerfilAcesso = Long.valueOf(value);
		PerfilAcesso perfilAcesso = null;
		try {
			perfilAcesso = getPerfilAcessoServices().obterPeloId(PerfilAcesso.class, idPerfilAcesso);
		} catch (ServicesException e) {
			logger.error("Erro ao converter a String para um objeto do tipo PerfilAcesso", e);
			FacesMessage facesMessage = new FacesMessage("Erro ao converter a String para um objeto do tipo PerfilAcesso");
			FacesContext.getCurrentInstance().addMessage(null, facesMessage);
		}
		return perfilAcesso;
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
		String idPerfilAcesso = null;
		if (value != null && (value instanceof PerfilAcesso)) {
			idPerfilAcesso = String.valueOf(((PerfilAcesso) value).getId());
		}
		return idPerfilAcesso;
	}

	/**
	 * Method getPerfilAcessoServices.
	 * @return PerfilAcessoServices
	 */
	private PerfilAcessoServices getPerfilAcessoServices() {
		try {
			Context context = new InitialContext();
			String name = "java:global/falconrh-web/ejb/PerfilAcessoServices";
			PerfilAcessoServices services = (PerfilAcessoServices) context.lookup(name);
			return services;
		} catch (NamingException e) {
			e.printStackTrace();
		}
		return null;
	}

}
