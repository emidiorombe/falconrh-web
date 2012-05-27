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

import richard.falconrh.entity.seguranca.Acao;
import richard.falconrh.exception.ServicesException;
import richard.falconrh.service.AcaoServices;

/**
 * Classe para conversão de instancias de objetos Acao em Strings para utilização em arquivos xhtml
 * @author Richard Mendes Madureira
 * @version $Revision: 1.0 $
 */
@FacesConverter(forClass=Acao.class, value="acaoConverter")
public class AcaoConverter implements Converter {
	private static final Logger logger = Logger.getLogger(AcaoConverter.class);

	/**
	 * Method getAsObject.
	 * @param context FacesContext
	 * @param component UIComponent
	 * @param value String
	 * @return Object * @see javax.faces.convert.Converter#getAsObject(FacesContext, UIComponent, String) */
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Long idAcao = Long.valueOf(value);
		Acao acao = null;
		try {
			acao = getAcaoServices().obterPeloId(Acao.class, idAcao);
		} catch (ServicesException e) {
			logger.error("Erro ao converter a String para um objeto do tipo Acao", e);
			FacesMessage facesMessage = new FacesMessage("Erro ao converter a String para um objeto do tipo Acao");
			FacesContext.getCurrentInstance().addMessage(null, facesMessage);
		}
		return acao;
	}

	/**
	 * Method getAsString.
	 * @param context FacesContext
	 * @param component UIComponent
	 * @param value Object
	 * @return String * @see javax.faces.convert.Converter#getAsString(FacesContext, UIComponent, Object) */
	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		String idAcao = null;
		if (value != null && (value instanceof Acao)) {
			idAcao = String.valueOf(((Acao) value).getId());
		}
		return idAcao;
	}

	/**
	 * Method getAcaoServices.
	 * @return AcaoServices */
	private AcaoServices getAcaoServices() {
		try {
			Context context = new InitialContext();
			String name = "java:global/falconrh-web/ejb/AcaoServices";
			AcaoServices services = (AcaoServices) context.lookup(name);
			return services;
		} catch (NamingException e) {
			e.printStackTrace();
		}
		return null;
	}

}
