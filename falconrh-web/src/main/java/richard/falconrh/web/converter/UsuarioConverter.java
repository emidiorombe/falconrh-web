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

import richard.falconrh.entity.pessoa.Usuario;
import richard.falconrh.exception.ServicesException;
import richard.falconrh.service.UsuarioServices;

/**
 * Classe para conversão de instancias de objetos Usuario em Strings para utilização em arquivos xhtml
 * @author Richard Mendes Madureira
 * @version $Revision: 1.0 $
 */
@FacesConverter(forClass=Usuario.class, value="usuarioConverter")
public class UsuarioConverter implements Converter {
	private static final Logger logger = Logger.getLogger(UsuarioConverter.class);

	/**
	 * Method getAsObject.
	 * @param context FacesContext
	 * @param component UIComponent
	 * @param value String
	 * @return Object * @see javax.faces.convert.Converter#getAsObject(FacesContext, UIComponent, String) */
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Long idUsuario = Long.valueOf(value);
		Usuario usuario = null;
		try {
			usuario = getUsuarioServices().obterPeloId(Usuario.class, idUsuario);
		} catch (ServicesException e) {
			logger.error("Erro ao converter a String para um objeto do tipo Usuario", e);
			FacesMessage facesMessage = new FacesMessage("Erro ao converter a String para um objeto do tipo Usuario");
			FacesContext.getCurrentInstance().addMessage(null, facesMessage);
		}
		return usuario;
	}

	/**
	 * Method getAsString.
	 * @param context FacesContext
	 * @param component UIComponent
	 * @param value Object
	 * @return String * @see javax.faces.convert.Converter#getAsString(FacesContext, UIComponent, Object) */
	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		String idUsuario = null;
		if (value != null && (value instanceof Usuario)) {
			idUsuario = String.valueOf(((Usuario) value).getId());
		}
		return idUsuario;
	}

	/**
	 * Method getUsuarioServices.
	 * @return UsuarioServices */
	private UsuarioServices getUsuarioServices() {
		try {
			Context context = new InitialContext();
			String name = "java:global/falconrh-web/ejb/UsuarioServices";
			UsuarioServices services = (UsuarioServices) context.lookup(name);
			return services;
		} catch (NamingException e) {
			e.printStackTrace();
		}
		return null;
	}

}
