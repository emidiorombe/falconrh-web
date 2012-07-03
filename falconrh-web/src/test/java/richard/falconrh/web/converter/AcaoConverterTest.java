package richard.falconrh.web.converter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.powermock.api.mockito.PowerMockito.spy;
import static org.powermock.api.mockito.PowerMockito.verifyPrivate;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import richard.falconrh.entity.seguranca.Acao;
import richard.falconrh.service.AcaoServices;
import richard.falconrh.service.impl.AcaoServicesImpl;

@RunWith(PowerMockRunner.class)
@PrepareForTest(AcaoConverter.class)
public class AcaoConverterTest {
	private AcaoConverter acaoConverter;
	private final String nomeMetodoMockado = "getAcaoServices";
	private AcaoServices retornoEsperadoAoChamarMetodoMockado;
	
	@Before
	public void setUp() throws Exception{
		acaoConverter = spy(new AcaoConverter());
		final Acao acaoEsperada = criarAcao();
		retornoEsperadoAoChamarMetodoMockado = Mockito.mock(AcaoServicesImpl.class);
		Mockito.when(retornoEsperadoAoChamarMetodoMockado.obterPeloId(Acao.class, acaoEsperada.getId())).thenReturn(acaoEsperada);
		PowerMockito.when(acaoConverter, nomeMetodoMockado). thenReturn(retornoEsperadoAoChamarMetodoMockado);
	}
	
	@After
	public void tearDown() throws Exception{
		acaoConverter = null;
	}
	
	@Test
	public void getAsObjectDeveRetornarAcao() throws Exception{
		
		final String value = "1";
		final Acao acaoEsperada = criarAcao();

		FacesContext context = Mockito.mock(FacesContext.class);
		UIComponent component = Mockito.mock(UIComponent.class);
		Object object = acaoConverter.getAsObject(context, component, value);
		assertNotNull(object);
		assertTrue(object instanceof Acao);
		Acao acaoRetorno = (Acao)object;
		assertEquals(acaoEsperada, acaoRetorno);

		// Optionally verify that the private method was actually called
		verifyPrivate(acaoConverter).invoke(nomeMetodoMockado);
	}
	
	@Test
	public void getAsObjectDeveRetornarNullQuandoNaoEncontrarObjeto() throws Exception{
		final String value = "0";

		FacesContext context = Mockito.mock(FacesContext.class);
		UIComponent component = Mockito.mock(UIComponent.class);
		Object object = acaoConverter.getAsObject(context, component, value);
		assertNull(object);
		// Optionally verify that the private method was actually called
		verifyPrivate(acaoConverter).invoke(nomeMetodoMockado);
	}
	
	@Test
	public void getAsStringDeveRetornarString() throws Exception{
		final Acao acaoEsperada = criarAcao();
		final String idAcao = "1";
		
		FacesContext context = Mockito.mock(FacesContext.class);
		UIComponent component = Mockito.mock(UIComponent.class);
		String retorno = acaoConverter.getAsString(context, component, acaoEsperada);
		assertNotNull(retorno);
		assertTrue(retorno instanceof String);
		assertEquals(idAcao, retorno);
	}
	
	@Test
	public void getAsStringDeveRetornarNullQuandoNaoForinstanciaAcao() throws Exception{
		FacesContext context = Mockito.mock(FacesContext.class);
		UIComponent component = Mockito.mock(UIComponent.class);
		String retorno = acaoConverter.getAsString(context, component, new Object());
		assertNull(retorno);
	}

	protected Acao criarAcao() {
		final Long idAcao = 1L;
		final String nomeAcao = "Cadastrar";
		Acao acaoRetorno = new Acao();
		acaoRetorno.setId(idAcao);
		acaoRetorno.setNome(nomeAcao);
		return acaoRetorno;
	}
}
