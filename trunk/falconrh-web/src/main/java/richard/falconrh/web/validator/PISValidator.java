package richard.falconrh.web.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import richard.falconrh.entity.documento.Documento;
import richard.falconrh.modelo.enums.TipoDocumento;
import richard.falconrh.util.FalconRHUtils;

/**
 * @author Richard Mendes Madureira
 * @version $Revision: 1.0 $
 */
@FacesValidator(value="pisValidator")
public class PISValidator implements Validator {

	/**
	 * Method validate.
	 * @param ctx FacesContext
	 * @param comp UIComponent
	 * @param obj Object
	 * @throws ValidatorException * @see javax.faces.validator.Validator#validate(FacesContext, UIComponent, Object)
	 */
	@Override
	public void validate(FacesContext ctx, UIComponent comp, Object obj) throws ValidatorException {
		boolean isValido = false;
		String msg = null;
		if(obj instanceof Documento){
			Documento documento = (Documento)obj;
			if(documento.getTipoDocumento().equals(TipoDocumento.PIS_PASEP)){
				isValido = FalconRHUtils.isPISValido(documento.getNumero());
			}else{
				isValido = false;
				msg = "Tipo de documento Inválido";
			}
		} else{
			msg = "O valor informado não é um PIS/PASEP válido";
			isValido = false;
		}
		if(!isValido){
			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro de validação", msg);
			throw new ValidatorException(facesMessage);
		}
	}

}
