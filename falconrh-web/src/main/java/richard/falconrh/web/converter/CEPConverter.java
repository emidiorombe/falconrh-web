package richard.falconrh.web.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.apache.commons.lang.StringUtils;

import richard.falconrh.util.FalconRHUtils;

/**
 * Classe de Conversão de Strings e objetos que representam o CE
 * @author `Richard Mendes Madureira
 * @version $Revision: 1.0 $
 */
@FacesConverter(value="cepConverter")
public class CEPConverter implements Converter {

	/**
	 * Método que transforma a String em um cep com o pattern aplicado
	 * @param ctx FacesContext
	 * @param comp UIComponent
	 * @param value String
	 * @return Object * @see javax.faces.convert.Converter#getAsObject(FacesContext, UIComponent, String)
	 */
	@Override
	public Object getAsObject(FacesContext ctx, UIComponent comp, String value) {
		Long lValue = null;
		if(StringUtils.isNotBlank(value)){
			lValue = Long.valueOf(FalconRHUtils.getSomenteNumeros(value));
		}
		return lValue;
	}

	/**
	 * Método que transforma o cep formatado em uma String.
	 * @param ctx FacesContext
	 * @param comp UIComponent
	 * @param object Object
	 * @return String * @see javax.faces.convert.Converter#getAsString(FacesContext, UIComponent, Object)
	 */
	@Override
	public String getAsString(FacesContext ctx, UIComponent comp, Object object) {
		if(object != null){
			if(object instanceof Long){
				return FalconRHUtils.getCEPFormatado((Long)object);
			}else if(object instanceof Integer){
				return FalconRHUtils.getCEPFormatado((Integer)object);
			}
		}
		return null;
	}
}
