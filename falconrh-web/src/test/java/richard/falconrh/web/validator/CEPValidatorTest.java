package richard.falconrh.web.validator;

import static org.junit.Assert.fail;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class CEPValidatorTest {
	private CEPValidator cepValidator;
	
	@Before
	public void setUp() throws Exception{
		cepValidator = new CEPValidator();
	}
	
	@After
	public void tearDown() throws Exception{
		cepValidator = null;
	}
	
	@Test
	public void validateDeveValidarCepValidoInteiro() {
		FacesContext ctx = Mockito.mock(FacesContext.class);
		UIComponent comp = Mockito.mock(UIComponent.class);
		Integer cep = 88036001;
		cepValidator.validate(ctx, comp, cep);
	}
	
	@Test
	public void validateDeveValidarCepValidoLong() {
		FacesContext ctx = Mockito.mock(FacesContext.class);
		UIComponent comp = Mockito.mock(UIComponent.class);
		Long cep = 88036001L;
		cepValidator.validate(ctx, comp, cep);
	}
	
	@Test
	public void validateDeveValidarCepValidoStringNumerica() {
		FacesContext ctx = Mockito.mock(FacesContext.class);
		UIComponent comp = Mockito.mock(UIComponent.class);
		String cep = "88036001";
		cepValidator.validate(ctx, comp, cep);
	}
	
	@Test(expected=ValidatorException.class)
	public void validateDeveLancarExceptionQuandoStringNaoNumerica() {
		FacesContext ctx = Mockito.mock(FacesContext.class);
		UIComponent comp = Mockito.mock(UIComponent.class);
		String cep = "abcd";
		cepValidator.validate(ctx, comp, cep);
		fail("Deveria ter lançado Exceção");
	}	
	
	@Test(expected=ValidatorException.class)
	public void validateDeveLancarExceptionParaCepLongInvalido(){
		FacesContext ctx = Mockito.mock(FacesContext.class);
		UIComponent comp = Mockito.mock(UIComponent.class);
		Long cep = 123456788987L;
		cepValidator.validate(ctx, comp, cep);
		fail("Deveria ter lançado Exceção");
	}

}
