package richard.falconrh.web.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import richard.falconrh.entity.localizacao.Municipio;
import richard.falconrh.exception.ServicesException;
import richard.falconrh.service.MunicipioServices;

/**
 * Classe para conversão de instancias de objetos Municipio em Strings para utilização em arquivos xhtml
 * @author Richard Mendes Madureira
 *
 * @version $Revision: 1.0 $
 */
@FacesConverter(forClass=Municipio.class, value="municipioConverter")
public class MunicipioConverter implements Converter {
	
	/**
	 * Method getAsObject.
	 * @param ctx FacesContext
	 * @param component UIComponent
	 * @param value String
	 * @return Object * @see javax.faces.convert.Converter#getAsObject(FacesContext, UIComponent, String)
	 */
	@Override
	public Object getAsObject(FacesContext ctx, UIComponent component, String value) {
		if(value==null || "".equals(value) || value.equals("--Selecione--")){
			return null;
		}
		Long id =  Long.parseLong(value);
		try {
			return getMunicipioServices().obterPeloId(Municipio.class, id);
		} catch (ServicesException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Method getAsString.
	 * @param ctx FacesContext
	 * @param component UIComponent
	 * @param object Object
	 * @return String
	 * @see javax.faces.convert.Converter#getAsString(FacesContext, UIComponent, Object)
	 */
	@Override
	public String getAsString(FacesContext ctx, UIComponent component, Object object) {
		if(object instanceof Municipio){
			return String.valueOf(((Municipio) object).getId());
		}
		return null;
	}
	
	/**
	 * Method getMunicipioServices.
	 * @return MunicipioServices
	 */
	public MunicipioServices getMunicipioServices(){
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