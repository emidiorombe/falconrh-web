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

import richard.falconrh.entity.localizacao.Logradouro;
import richard.falconrh.modelo.enums.TipoLogradouro;
import richard.falconrh.service.LogradouroServices;
import richard.falconrh.service.impl.LogradouroServicesImpl;

@RunWith(PowerMockRunner.class)
@PrepareForTest(LogradouroConverter.class)
public class LogradouroConverterTest {
	private LogradouroConverter logradouroConverter;
	private final String nomeMetodoMockado = "getLogradouroServices";
	private LogradouroServices retornoEsperadoAoChamarMetodoMockado;
	
	@Before
	public void setUp() throws Exception{
		logradouroConverter = spy(new LogradouroConverter());
		final Logradouro logradouroEsperada = criarLogradouro();
		retornoEsperadoAoChamarMetodoMockado = Mockito.mock(LogradouroServicesImpl.class);
		Mockito.when(retornoEsperadoAoChamarMetodoMockado.obterPeloId(Logradouro.class, logradouroEsperada.getId())).thenReturn(logradouroEsperada);
		PowerMockito.when(logradouroConverter, nomeMetodoMockado). thenReturn(retornoEsperadoAoChamarMetodoMockado);
	}
	
	@After
	public void tearDown() throws Exception{
		logradouroConverter = null;
	}
	
	@Test
	public void getAsObjectDeveRetornarLogradouro() throws Exception{
		
		final String value = "1";
		final Logradouro logradouroEsperada = criarLogradouro();

		FacesContext context = Mockito.mock(FacesContext.class);
		UIComponent component = Mockito.mock(UIComponent.class);
		Object object = logradouroConverter.getAsObject(context, component, value);
		assertNotNull(object);
		assertTrue(object instanceof Logradouro);
		Logradouro logradouroRetorno = (Logradouro)object;
		assertEquals(logradouroEsperada, logradouroRetorno);

		// Optionally verify that the private method was actually called
		verifyPrivate(logradouroConverter).invoke(nomeMetodoMockado);
	}
	
	@Test
	public void getAsObjectDeveRetornarNullQuandoNaoEncontrarObjeto() throws Exception{
		final String value = "0";

		FacesContext context = Mockito.mock(FacesContext.class);
		UIComponent component = Mockito.mock(UIComponent.class);
		Object object = logradouroConverter.getAsObject(context, component, value);
		assertNull(object);
		// Optionally verify that the private method was actually called
		verifyPrivate(logradouroConverter).invoke(nomeMetodoMockado);
	}
	
	@Test
	public void getAsStringDeveRetornarString() throws Exception{
		final Logradouro logradouroEsperada = criarLogradouro();
		final String idLogradouro = "1";
		
		FacesContext context = Mockito.mock(FacesContext.class);
		UIComponent component = Mockito.mock(UIComponent.class);
		String retorno = logradouroConverter.getAsString(context, component, logradouroEsperada);
		assertNotNull(retorno);
		assertTrue(retorno instanceof String);
		assertEquals(idLogradouro, retorno);
	}
	
	@Test
	public void getAsStringDeveRetornarNullQuandoNaoForinstanciaLogradouro() throws Exception{
		FacesContext context = Mockito.mock(FacesContext.class);
		UIComponent component = Mockito.mock(UIComponent.class);
		String retorno = logradouroConverter.getAsString(context, component, new Object());
		assertNull(retorno);
	}

	protected Logradouro criarLogradouro() {
		final Long id = 1L;
		final String nome = "Prefeito Waldemar Vieira";
		Logradouro logradouro = new Logradouro();
		logradouro.setId(id);
		logradouro.setNome(nome);
		logradouro.setTipoLogradouro(TipoLogradouro.AVENIDA);
		return logradouro;
	}
}
