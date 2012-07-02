package richard.falconrh.web.validator;

import static org.junit.Assert.fail;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import richard.falconrh.entity.banco.ContaCorrente;

public class ContaCorrenteValidatorTest {
	private ContaCorrenteValidator contaCorrenteValidator;

	@Before
	public void setUp() throws Exception {
		contaCorrenteValidator = new ContaCorrenteValidator();
	}

	@After
	public void tearDown() throws Exception {
		contaCorrenteValidator = null;
	}

	@Test
	public void validateDeveValidarContaCorrente() {
		ContaCorrente contaCorrente = new ContaCorrente();
		contaCorrente.setNumero("47554");
		contaCorrente.setDigitoVerificador("8");
		
		FacesContext facesContext = Mockito.mock(FacesContext.class);
		UIComponent component = Mockito.mock(UIComponent.class);
		contaCorrenteValidator.validate(facesContext, component, contaCorrente);
	}
	
	@Test(expected=ValidatorException.class)
	public void validateDeveLancarExceptionQuandoContaCorrenteInvalida(){
		ContaCorrente contaCorrente = new ContaCorrente();
		contaCorrente.setNumero("47553");
		contaCorrente.setDigitoVerificador("8");
		
		FacesContext facesContext = Mockito.mock(FacesContext.class);
		UIComponent component = Mockito.mock(UIComponent.class);
		contaCorrenteValidator.validate(facesContext, component, contaCorrente);
		fail("Deveria ter lançado exceção ao validar conta corrente inválida");
	}

}
