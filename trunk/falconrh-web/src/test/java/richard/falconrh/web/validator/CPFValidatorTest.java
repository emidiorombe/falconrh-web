package richard.falconrh.web.validator;

import static org.junit.Assert.fail;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import richard.falconrh.entity.documento.Documento;
import richard.falconrh.modelo.enums.TipoDocumento;

public class CPFValidatorTest {
	private CPFValidator cpfValidator;

	@Before
	public void setUp() throws Exception {
		cpfValidator = new CPFValidator();
	}

	@After
	public void tearDown() throws Exception {
		cpfValidator = null;
	}

	@Test
	public void validateDeveValidarContaCorrenteValida() {
		Documento documento = new Documento();
		documento.setTipoDocumento(TipoDocumento.CPF);
		documento.setNumero("03930263610");
		
		FacesContext ctx = Mockito.mock(FacesContext.class);
		UIComponent comp = Mockito.mock(UIComponent.class);
		cpfValidator.validate(ctx, comp, documento);
	}
	
	@Test(expected=ValidatorException.class)
	public void validateDeveLancarExceptionQuandoNaoDocumento() {
		Object object = new Object();
		FacesContext ctx = Mockito.mock(FacesContext.class);
		UIComponent comp = Mockito.mock(UIComponent.class);
		cpfValidator.validate(ctx, comp, object);
		fail("Deveria ter lançado exceção ao validar objeto que não é instância de Documento");
	}
	
	@Test(expected=ValidatorException.class)
	public void validateDeveLancarExceptionQuandoDocumentoNaoforCPF() {
		Documento documento = new Documento();
		documento.setTipoDocumento(TipoDocumento.TITULO_ELEITOR);
		documento.setNumero("03930263610");
		
		FacesContext ctx = Mockito.mock(FacesContext.class);
		UIComponent comp = Mockito.mock(UIComponent.class);
		cpfValidator.validate(ctx, comp, documento);
		fail("Deveria ter lançado exception ao validar tipo de documento diferente de cpf");
	}
	
	@Test(expected=ValidatorException.class)
	public void validateDeveLancarExceptionQuandoCPFInvalido1() {
		Documento documento = new Documento();
		documento.setTipoDocumento(TipoDocumento.CPF);
		documento.setNumero("11111111111");
		
		FacesContext ctx = Mockito.mock(FacesContext.class);
		UIComponent comp = Mockito.mock(UIComponent.class);
		cpfValidator.validate(ctx, comp, documento);
		fail("Deveria ter lançado exception ao validar cpf inválido");
	}
	
	@Test(expected=ValidatorException.class)
	public void validateDeveLancarExceptionQuandoCPFInvalido2() {
		Documento documento = new Documento();
		documento.setTipoDocumento(TipoDocumento.CPF);
		documento.setNumero("03930263611");
		
		FacesContext ctx = Mockito.mock(FacesContext.class);
		UIComponent comp = Mockito.mock(UIComponent.class);
		cpfValidator.validate(ctx, comp, documento);
		fail("Deveria ter lançado exception ao validar cpf inválido");
	}
}
