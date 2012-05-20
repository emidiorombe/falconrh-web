package richard.falconrh.web.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value="simNaoConverter")
public class SimNaoConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if(value!=null){
			return (value.equalsIgnoreCase("true") || value.equalsIgnoreCase("Sim"))?Boolean.TRUE:Boolean.FALSE;
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if(value != null){
			if (value instanceof Boolean){
				Boolean bool = (Boolean)value;
				return bool?"Sim":"Não";
			}else if (value instanceof String){
				String s = (String) value;
				if(s.equalsIgnoreCase("Sim") || s.equalsIgnoreCase("true")) return "Sim";
				if(s.equalsIgnoreCase("Não") || s.equalsIgnoreCase("false")) return "Não";
			}
		}
		return null;
	}

}
