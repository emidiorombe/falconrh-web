package richard.falconrh.web.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import richard.falconrh.util.FalconRHUtils;

/**
 * @author richard
 * @version $Revision: 1.0 $
 */
@FacesValidator(value="pisValidator")
public class PISValidator implements Validator {

	/**
	 * Method validate.
	 * @param ctx FacesContext
	 * @param comp UIComponent
	 * @param obj Object
	
	
	 * @throws ValidatorException * @see javax.faces.validator.Validator#validate(FacesContext, UIComponent, Object) */
	@Override
	public void validate(FacesContext ctx, UIComponent comp, Object obj) throws ValidatorException {
		boolean isValido = false;
		if(obj instanceof String){
			isValido = FalconRHUtils.isPISValido((String)obj);
		}
		if(!isValido){
			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro de validação", "O valor informado para o PIS não é um valor válido");
			throw new ValidatorException(facesMessage);
		}
	}

}
