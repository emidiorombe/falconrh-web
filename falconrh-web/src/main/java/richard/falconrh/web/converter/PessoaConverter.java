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

import richard.falconrh.entity.pessoa.Pessoa;
import richard.falconrh.exception.ServicesException;
import richard.falconrh.service.PessoaServices;

/**
 * Classe para conversão de instâncias de objetos Pessoa em Strings para utilização em arquivos xhtml
 * @author Richard Mendes Madureira
 * @version $Revision: 1.0 $
 */
@FacesConverter(forClass=Pessoa.class, value="pessoaConverter")
public class PessoaConverter implements Converter {
	private static final Logger logger = Logger.getLogger(PessoaConverter.class);
	
	/**
	 * Method getAsObject.
	 * @param context FacesContext
	 * @param component UIComponent
	 * @param value String
	 * @return Object * @see javax.faces.convert.Converter#getAsObject(FacesContext, UIComponent, String)
	 */
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Pessoa pessoa = null;
		if(StringUtils.isNotBlank(value) && !"--Selecione--".equals(value) && StringUtils.isNumeric(value)){
			try {
				Long idPessoa = Long.valueOf(value);
				pessoa = getPessoaServices().obterPeloId(Pessoa.class, idPessoa);
				if(logger.isDebugEnabled()){
					logger.debug("Pessoa encontrada: " + pessoa.getId() + " - " + pessoa.getNome());
				}
			} catch (ServicesException e) {
				logger.error("Erro ao converter a string para um objeto do tipo Pessoa", e);
				FacesMessage facesMessage = new FacesMessage("Erro de Conversão: ", "Erro ao converter a String para um objeto do tipo Pessoa");
				FacesContext.getCurrentInstance().addMessage(null, facesMessage);
			}
			return pessoa;
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
		String idPessoa = null;
		if (value != null && (value instanceof Pessoa)) {
			idPessoa = String.valueOf(((Pessoa) value).getId());
		}
		if(logger.isDebugEnabled()){
			logger.debug("String convertida: " + idPessoa);
		}
		return idPessoa;
	}

	/**
	 * Method getPessoaServices.
	 * @return PessoaServices
	 */
	private PessoaServices getPessoaServices() {
		try {
			Context context = new InitialContext();
			String name = "java:global/falconrh-web/ejb/PessoaServices";
			PessoaServices services = (PessoaServices) context.lookup(name);
			return services;
		} catch (NamingException e) {
			e.printStackTrace();
		}
		return null;
	}
}