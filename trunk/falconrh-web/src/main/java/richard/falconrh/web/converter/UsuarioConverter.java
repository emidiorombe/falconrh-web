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

import richard.falconrh.entity.pessoa.Usuario;
import richard.falconrh.exception.ServicesException;
import richard.falconrh.service.UsuarioServices;

/**
 * Classe para conversão de instâncias de objetos Usuario em Strings para utilização em arquivos xhtml
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
	 * @return Object * @see javax.faces.convert.Converter#getAsObject(FacesContext, UIComponent, String)
	 */
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Usuario usuario = null;
		if(StringUtils.isNotBlank(value) && !"--Selecione--".equals(value) && StringUtils.isNumeric(value)){
			try {
				Long idUsuario = Long.valueOf(value);
				usuario = getUsuarioServices().obterPeloId(Usuario.class, idUsuario);
				if(logger.isDebugEnabled()){
					logger.debug("Usuario encontrado: " + usuario.getId() + " - " + usuario.getNome());
				}
			} catch (ServicesException e) {
				logger.error("Erro ao converter a string para um objeto do tipo Usuario", e);
				FacesMessage facesMessage = new FacesMessage("Erro de Conversão: ", "Erro ao converter a String para um objeto do tipo Usuario");
				FacesContext.getCurrentInstance().addMessage(null, facesMessage);
			}
			return usuario;
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
		String idUsuario = null;
		if (value != null && (value instanceof Usuario)) {
			idUsuario = String.valueOf(((Usuario) value).getId());
		}
		if(logger.isDebugEnabled()){
			logger.debug("String convertida: " + idUsuario);
		}
		return idUsuario;
	}

	/**
	 * Method getUsuarioServices.
	 * @return UsuarioServices
	 */
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