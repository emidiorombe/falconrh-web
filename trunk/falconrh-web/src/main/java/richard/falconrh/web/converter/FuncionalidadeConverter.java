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

import richard.falconrh.entity.seguranca.Funcionalidade;
import richard.falconrh.exception.ServicesException;
import richard.falconrh.service.FuncionalidadeServices;

/**
 * Classe para conversão de instâncias de objetos Funcionalidade em Strings para utilização em arquivos xhtml
 * @author Richard Mendes Madureira
 * @version $Revision: 1.0 $
 */
@FacesConverter(forClass=Funcionalidade.class, value="funcionalidadeConverter")
public class FuncionalidadeConverter implements Converter {
	private static final Logger logger = Logger.getLogger(FuncionalidadeConverter.class);
	
	/**
	 * Method getAsObject.
	 * @param context FacesContext
	 * @param component UIComponent
	 * @param value String
	 * @return Object * @see javax.faces.convert.Converter#getAsObject(FacesContext, UIComponent, String)
	 */
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Funcionalidade funcionalidade = null;
		if(StringUtils.isNotBlank(value) && !"--Selecione--".equals(value) && StringUtils.isNumeric(value)){
			try {
				Long idFuncionalidade = Long.valueOf(value);
				funcionalidade = getFuncionalidadeServices().obterPeloId(Funcionalidade.class, idFuncionalidade);
				if(logger.isDebugEnabled()){
					logger.debug("Funcionalidade encontrada: " + funcionalidade.getId() + " - " + funcionalidade.getNome());
				}
			} catch (ServicesException e) {
				logger.error("Erro ao converter a string para um objeto do tipo Funcionalidade", e);
				FacesMessage facesMessage = new FacesMessage("Erro de Conversão: ", "Erro ao converter a String para um objeto do tipo Funcionalidade");
				FacesContext.getCurrentInstance().addMessage(null, facesMessage);
			}
			return funcionalidade;
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
		String idFuncionalidade = null;
		if (value != null && (value instanceof Funcionalidade)) {
			idFuncionalidade = String.valueOf(((Funcionalidade) value).getId());
		}
		if(logger.isDebugEnabled()){
			logger.debug("String convertida: " + idFuncionalidade);
		}
		return idFuncionalidade;
	}

	/**
	 * Method getFuncionalidadeServices.
	 * @return FuncionalidadeServices
	 */
	private FuncionalidadeServices getFuncionalidadeServices() {
		try {
			Context context = new InitialContext();
			String name = "java:global/falconrh-web/ejb/FuncionalidadeServices";
			FuncionalidadeServices services = (FuncionalidadeServices) context.lookup(name);
			return services;
		} catch (NamingException e) {
			e.printStackTrace();
		}
		return null;
	}
}