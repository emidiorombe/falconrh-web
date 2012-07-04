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

import richard.falconrh.entity.banco.Agencia;
import richard.falconrh.service.AgenciaServices;
import richard.falconrh.service.impl.AgenciaServicesImpl;

@RunWith(PowerMockRunner.class)
@PrepareForTest(AgenciaConverter.class)
public class AgenciaConverterTest {
	private AgenciaConverter agenciaConverter;
	private final String nomeMetodoMockado = "getAgenciaServices";
	private AgenciaServices retornoEsperadoAoChamarMetodoMockado;
	
	@Before
	public void setUp() throws Exception{
		agenciaConverter = spy(new AgenciaConverter());
		final Agencia agenciaEsperada = criarAgencia();
		retornoEsperadoAoChamarMetodoMockado = Mockito.mock(AgenciaServicesImpl.class);
		Mockito.when(retornoEsperadoAoChamarMetodoMockado.obterPeloId(Agencia.class, agenciaEsperada.getId())).thenReturn(agenciaEsperada);
		PowerMockito.when(agenciaConverter, nomeMetodoMockado). thenReturn(retornoEsperadoAoChamarMetodoMockado);
	}
	
	@After
	public void tearDown() throws Exception{
		agenciaConverter = null;
	}
	
	@Test
	public void getAsObjectDeveRetornarAgencia() throws Exception{
		
		final String value = "1";
		final Agencia agenciaEsperada = criarAgencia();

		FacesContext context = Mockito.mock(FacesContext.class);
		UIComponent component = Mockito.mock(UIComponent.class);
		Object object = agenciaConverter.getAsObject(context, component, value);
		assertNotNull(object);
		assertTrue(object instanceof Agencia);
		Agencia agenciaRetorno = (Agencia)object;
		assertEquals(agenciaEsperada, agenciaRetorno);

		// Optionally verify that the private method was actually called
		verifyPrivate(agenciaConverter).invoke(nomeMetodoMockado);
	}
	
	@Test
	public void getAsObjectDeveRetornarNullQuandoNaoEncontrarObjeto() throws Exception{
		final String value = "0";

		FacesContext context = Mockito.mock(FacesContext.class);
		UIComponent component = Mockito.mock(UIComponent.class);
		Object object = agenciaConverter.getAsObject(context, component, value);
		assertNull(object);
		// Optionally verify that the private method was actually called
		verifyPrivate(agenciaConverter).invoke(nomeMetodoMockado);
	}
	
	@Test
	public void getAsStringDeveRetornarString() throws Exception{
		final Agencia agenciaEsperada = criarAgencia();
		
		FacesContext context = Mockito.mock(FacesContext.class);
		UIComponent component = Mockito.mock(UIComponent.class);
		String retorno = agenciaConverter.getAsString(context, component, agenciaEsperada);
		assertNotNull(retorno);
		assertTrue(retorno instanceof String);
		assertEquals(String.valueOf(agenciaEsperada.getId()), retorno);
	}
	
	@Test
	public void getAsStringDeveRetornarNullQuandoNaoForinstanciaAgencia() throws Exception{
		FacesContext context = Mockito.mock(FacesContext.class);
		UIComponent component = Mockito.mock(UIComponent.class);
		String retorno = agenciaConverter.getAsString(context, component, new Object());
		assertNull(retorno);
	}

	protected Agencia criarAgencia() {
		final Long idAgencia = 1L;
		final String nomeAgencia = "Praça XI de Novembro";
		Agencia agenciaRetorno = new Agencia();
		agenciaRetorno.setId(idAgencia);
		agenciaRetorno.setNome(nomeAgencia);
		return agenciaRetorno;
	}
}
