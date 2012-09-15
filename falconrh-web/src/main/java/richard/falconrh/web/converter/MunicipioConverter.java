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

import richard.falconrh.entity.localizacao.Municipio;
import richard.falconrh.exception.ServicesException;
import richard.falconrh.service.MunicipioServices;

/**
 * Classe para conversão de instâncias de objetos Municipio em Strings para utilização em arquivos xhtml
 * @author Richard Mendes Madureira
 * @version $Revision: 1.0 $
 */
@FacesConverter(forClass=Municipio.class, value="municipioConverter")
public class MunicipioConverter implements Converter {
	private static final Logger logger = Logger.getLogger(MunicipioConverter.class);
	
	/**
	 * Method getAsObject.
	 * @param context FacesContext
	 * @param component UIComponent
	 * @param value String
	 * @return Object * @see javax.faces.convert.Converter#getAsObject(FacesContext, UIComponent, String)
	 */
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Municipio municipio = null;
		if(StringUtils.isNotBlank(value) && !"--Selecione--".equals(value) && StringUtils.isNumeric(value)){
			try {
				Long idMunicipio = Long.valueOf(value);
				municipio = getMunicipioServices().obterPeloId(Municipio.class, idMunicipio);
				if(logger.isDebugEnabled() && municipio!=null){
					logger.debug("Municipio encontrado: " + municipio.getId() + " - " + municipio.getNome());
				}
			} catch (ServicesException e) {
				logger.error("Erro ao converter a string para um objeto do tipo Municipio", e);
				FacesMessage facesMessage = new FacesMessage("Erro de Conversão: ", "Erro ao converter a String para um objeto do tipo Municipio");
				FacesContext.getCurrentInstance().addMessage(null, facesMessage);
			}
			return municipio;
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
		String idMunicipio = null;
		if (value != null && (value instanceof Municipio)) {
			idMunicipio = String.valueOf(((Municipio) value).getId());
		}
		if(logger.isDebugEnabled()){
			logger.debug("String convertida: " + idMunicipio);
		}
		return idMunicipio;
	}

	/**
	 * Method getMunicipioServices.
	 * @return MunicipioServices
	 */
	private MunicipioServices getMunicipioServices() {
		try {
			Context context = new InitialContext();
			String name = "java:global/falconrh-web/ejb/MunicipioServices";
			MunicipioServices services = (MunicipioServices) context.lookup(name);
			return services;
		} catch (NamingException e) {
			e.printStackTrace();
		}
		return null;
	}
}