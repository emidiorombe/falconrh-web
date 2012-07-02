package richard.falconrh.web.validator;

import static org.junit.Assert.*;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import richard.falconrh.entity.documento.Documento;
import richard.falconrh.modelo.enums.TipoDocumento;

public class PISValidatorTest {
	private PISValidator pisValidator;

	@Before
	public void setUp() throws Exception {
		pisValidator = new PISValidator();
	}

	@After
	public void tearDown() throws Exception {
		pisValidator = null;
	}

	@Test
	public void validateDeveValidarContaCorrenteValida() {
		Documento documento = new Documento();
		documento.setTipoDocumento(TipoDocumento.PIS_PASEP);
		documento.setNumero("19037314492");
		
		FacesContext ctx = Mockito.mock(FacesContext.class);
		UIComponent comp = Mockito.mock(UIComponent.class);
		pisValidator.validate(ctx, comp, documento);
	}
	
	@Test(expected=ValidatorException.class)
	public void validateDeveLancarExceptionQuandoNaoDocumento() {
		Object object = new Object();
		FacesContext ctx = Mockito.mock(FacesContext.class);
		UIComponent comp = Mockito.mock(UIComponent.class);
		pisValidator.validate(ctx, comp, object);
		fail("Deveria ter lançado exceção ao validar objeto que não é instância de Documento");
	}
	
	@Test(expected=ValidatorException.class)
	public void validateDeveLancarExceptionQuandoDocumentoNaoforPIS_PASEP() {
		Documento documento = new Documento();
		documento.setTipoDocumento(TipoDocumento.TITULO_ELEITOR);
		documento.setNumero("19037314498");
		
		FacesContext ctx = Mockito.mock(FacesContext.class);
		UIComponent comp = Mockito.mock(UIComponent.class);
		pisValidator.validate(ctx, comp, documento);
		fail("Deveria ter lançado exception ao validar tipo de documento diferente de PIS/PASEP");
	}
	
	@Test(expected=ValidatorException.class)
	public void validateDeveLancarExceptionQuandoPIS_PASEPInvalido1() {
		Documento documento = new Documento();
		documento.setTipoDocumento(TipoDocumento.PIS_PASEP);
		documento.setNumero("11111111111");
		
		FacesContext ctx = Mockito.mock(FacesContext.class);
		UIComponent comp = Mockito.mock(UIComponent.class);
		pisValidator.validate(ctx, comp, documento);
		fail("Deveria ter lançado exception ao validar PIS/PASEP inválido");
	}
	
	@Test(expected=ValidatorException.class)
	public void validateDeveLancarExceptionQuandoCPFInvalido2() {
		Documento documento = new Documento();
		documento.setTipoDocumento(TipoDocumento.PIS_PASEP);
		documento.setNumero("123");
		
		FacesContext ctx = Mockito.mock(FacesContext.class);
		UIComponent comp = Mockito.mock(UIComponent.class);
		pisValidator.validate(ctx, comp, documento);
		fail("Deveria ter lançado exception ao validar cpf inválido");
	}

}
