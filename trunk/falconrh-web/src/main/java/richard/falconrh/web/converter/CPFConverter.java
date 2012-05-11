package richard.falconrh.web.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.apache.commons.lang.StringUtils;

import richard.falconrh.util.FalconRHUtils;

/**
 * @author richard
 * @version $Revision: 1.0 $
 */
@FacesConverter(value="cpfConverter")
public class CPFConverter implements Converter {

	/**
	 * Method getAsObject.
	 * @param ctx FacesContext
	 * @param comp UIComponent
	 * @param value String
	
	
	 * @return Object * @see javax.faces.convert.Converter#getAsObject(FacesContext, UIComponent, String) */
	@Override
	public Object getAsObject(FacesContext ctx, UIComponent comp, String value) {
		Long lValue = null;
		if(StringUtils.isNotBlank(value)){
			lValue = Long.valueOf(FalconRHUtils.getSomenteNumeros(value));
		}
		return lValue;
	}

	/**
	 * Method getAsString.
	 * @param ctx FacesContext
	 * @param comp UIComponent
	 * @param object Object
	
	
	 * @return String * @see javax.faces.convert.Converter#getAsString(FacesContext, UIComponent, Object) */
	@Override
	public String getAsString(FacesContext ctx, UIComponent comp, Object object) {
		if(object != null){
			if(object instanceof Long){
				return FalconRHUtils.getCPFCormatado((Long)object);
			}
		}
		return null;
	}

}
