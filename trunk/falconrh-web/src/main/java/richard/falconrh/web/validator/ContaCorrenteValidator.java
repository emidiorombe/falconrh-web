package richard.falconrh.web.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import richard.falconrh.entity.banco.ContaCorrente;

/**
 * Classe de validação de números de contas correntes bancárias
 * @author Richard Mendes Madureira
 *
 * @version $Revision: 1.0 $
 */
@FacesValidator(value="contaCorrenteValidator")
public class ContaCorrenteValidator implements Validator{

	/**
	 * Method validate.
	 * @param facesContext FacesContext
	 * @param component UIComponent
	 * @param object Object
	
	
	 * @throws ValidatorException * @see javax.faces.validator.Validator#validate(FacesContext, UIComponent, Object) */
	@Override
	public void validate(FacesContext facesContext, UIComponent component, Object object) throws ValidatorException {
		ContaCorrente contaCorrente = (ContaCorrente)object;
		if(!contaCorrente.isValida()){
			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro de validação", "O valor informado para o número da conta corrente não é um valor válido");
			throw new ValidatorException(facesMessage);
		}
	}

}
