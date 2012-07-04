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

import richard.falconrh.entity.seguranca.Funcionalidade;
import richard.falconrh.service.FuncionalidadeServices;
import richard.falconrh.service.impl.FuncionalidadeServicesImpl;

@RunWith(PowerMockRunner.class)
@PrepareForTest(FuncionalidadeConverter.class)
public class FuncionalidadeConverterTest {
	private FuncionalidadeConverter funcionalidadeConverter;
	private final String nomeMetodoMockado = "getFuncionalidadeServices";
	private FuncionalidadeServices retornoEsperadoAoChamarMetodoMockado;
	
	@Before
	public void setUp() throws Exception{
		funcionalidadeConverter = spy(new FuncionalidadeConverter());
		final Funcionalidade funcionalidadeEsperada = criarFuncionalidade();
		retornoEsperadoAoChamarMetodoMockado = Mockito.mock(FuncionalidadeServicesImpl.class);
		Mockito.when(retornoEsperadoAoChamarMetodoMockado.obterPeloId(Funcionalidade.class, funcionalidadeEsperada.getId())).thenReturn(funcionalidadeEsperada);
		PowerMockito.when(funcionalidadeConverter, nomeMetodoMockado). thenReturn(retornoEsperadoAoChamarMetodoMockado);
	}
	
	@After
	public void tearDown() throws Exception{
		funcionalidadeConverter = null;
	}
	
	@Test
	public void getAsObjectDeveRetornarFuncionalidade() throws Exception{
		
		final String value = "1";
		final Funcionalidade funcionalidadeEsperada = criarFuncionalidade();

		FacesContext context = Mockito.mock(FacesContext.class);
		UIComponent component = Mockito.mock(UIComponent.class);
		Object object = funcionalidadeConverter.getAsObject(context, component, value);
		assertNotNull(object);
		assertTrue(object instanceof Funcionalidade);
		Funcionalidade funcionalidadeRetorno = (Funcionalidade)object;
		assertEquals(funcionalidadeEsperada, funcionalidadeRetorno);

		// Optionally verify that the private method was actually called
		verifyPrivate(funcionalidadeConverter).invoke(nomeMetodoMockado);
	}
	
	@Test
	public void getAsObjectDeveRetornarNullQuandoNaoEncontrarObjeto() throws Exception{
		final String value = "0";

		FacesContext context = Mockito.mock(FacesContext.class);
		UIComponent component = Mockito.mock(UIComponent.class);
		Object object = funcionalidadeConverter.getAsObject(context, component, value);
		assertNull(object);
		// Optionally verify that the private method was actually called
		verifyPrivate(funcionalidadeConverter).invoke(nomeMetodoMockado);
	}
	
	@Test
	public void getAsStringDeveRetornarString() throws Exception{
		final Funcionalidade funcionalidadeEsperada = criarFuncionalidade();
		final String idFuncionalidade = "1";
		
		FacesContext context = Mockito.mock(FacesContext.class);
		UIComponent component = Mockito.mock(UIComponent.class);
		String retorno = funcionalidadeConverter.getAsString(context, component, funcionalidadeEsperada);
		assertNotNull(retorno);
		assertTrue(retorno instanceof String);
		assertEquals(idFuncionalidade, retorno);
	}
	
	@Test
	public void getAsStringDeveRetornarNullQuandoNaoForinstanciaFuncionalidade() throws Exception{
		FacesContext context = Mockito.mock(FacesContext.class);
		UIComponent component = Mockito.mock(UIComponent.class);
		String retorno = funcionalidadeConverter.getAsString(context, component, new Object());
		assertNull(retorno);
	}

	protected Funcionalidade criarFuncionalidade() {
		final Long id = 1L;
		final String nome = "Gestão de Pessoas";
		Funcionalidade funcionalidade = new Funcionalidade();
		funcionalidade.setId(id);
		funcionalidade.setNome(nome);
		return funcionalidade;
	}
}
