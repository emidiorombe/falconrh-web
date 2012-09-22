package richard.falconrh.web.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import richard.falconrh.modelo.enums.UF;

@FacesConverter(forClass=UF.class, value="ufConverter")
public class UFConverter implements Converter{
	private static final Logger logger = Logger.getLogger(UFConverter.class);
	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
		UF uf = null;
		if(StringUtils.isNotBlank(value) && !"--Selecione--".equals(value)){
			uf = UF.valueOf(value);
			if(logger.isDebugEnabled() && uf!=null){
				logger.debug("UF: " + uf.getNome());
			}
		}
		return uf;
	}

	@Override
	public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value) {
		String retorno = null;
		if (value != null && (value instanceof UF)) {
			UF uf = (UF)value;
			retorno = uf.name();
		}
		if(logger.isDebugEnabled()){
			logger.debug("String convertida: " + retorno);
		}
		return retorno;
	}

}
