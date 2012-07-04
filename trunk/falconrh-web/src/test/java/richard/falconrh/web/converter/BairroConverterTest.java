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

import richard.falconrh.entity.localizacao.Bairro;
import richard.falconrh.service.BairroServices;
import richard.falconrh.service.impl.BairroServicesImpl;

@RunWith(PowerMockRunner.class)
@PrepareForTest(BairroConverter.class)
public class BairroConverterTest {
	private BairroConverter bairroConverter;
	private final String nomeMetodoMockado = "getBairroServices";
	private BairroServices retornoEsperadoAoChamarMetodoMockado;
	
	@Before
	public void setUp() throws Exception{
		bairroConverter = spy(new BairroConverter());
		final Bairro bairroEsperada = criarBairro();
		retornoEsperadoAoChamarMetodoMockado = Mockito.mock(BairroServicesImpl.class);
		Mockito.when(retornoEsperadoAoChamarMetodoMockado.obterPeloId(Bairro.class, bairroEsperada.getId())).thenReturn(bairroEsperada);
		PowerMockito.when(bairroConverter, nomeMetodoMockado). thenReturn(retornoEsperadoAoChamarMetodoMockado);
	}
	
	@After
	public void tearDown() throws Exception{
		bairroConverter = null;
	}
	
	@Test
	public void getAsObjectDeveRetornarBairro() throws Exception{
		
		final String value = "1";
		final Bairro bairroEsperada = criarBairro();

		FacesContext context = Mockito.mock(FacesContext.class);
		UIComponent component = Mockito.mock(UIComponent.class);
		Object object = bairroConverter.getAsObject(context, component, value);
		assertNotNull(object);
		assertTrue(object instanceof Bairro);
		Bairro bairroRetorno = (Bairro)object;
		assertEquals(bairroEsperada, bairroRetorno);

		// Optionally verify that the private method was actually called
		verifyPrivate(bairroConverter).invoke(nomeMetodoMockado);
	}
	
	@Test
	public void getAsObjectDeveRetornarNullQuandoNaoEncontrarObjeto() throws Exception{
		final String value = "0";

		FacesContext context = Mockito.mock(FacesContext.class);
		UIComponent component = Mockito.mock(UIComponent.class);
		Object object = bairroConverter.getAsObject(context, component, value);
		assertNull(object);
		// Optionally verify that the private method was actually called
		verifyPrivate(bairroConverter).invoke(nomeMetodoMockado);
	}
	
	@Test
	public void getAsStringDeveRetornarString() throws Exception{
		final Bairro bairroEsperada = criarBairro();
		final String idBairro = "1";
		
		FacesContext context = Mockito.mock(FacesContext.class);
		UIComponent component = Mockito.mock(UIComponent.class);
		String retorno = bairroConverter.getAsString(context, component, bairroEsperada);
		assertNotNull(retorno);
		assertTrue(retorno instanceof String);
		assertEquals(idBairro, retorno);
	}
	
	@Test
	public void getAsStringDeveRetornarNullQuandoNaoForinstanciaBairro() throws Exception{
		FacesContext context = Mockito.mock(FacesContext.class);
		UIComponent component = Mockito.mock(UIComponent.class);
		String retorno = bairroConverter.getAsString(context, component, new Object());
		assertNull(retorno);
	}

	protected Bairro criarBairro() {
		final Long id = 1L;
		final String nome = "Dona Joaquina";
		Bairro bairro = new Bairro();
		bairro.setId(id);
		bairro.setNome(nome);
		return bairro;
	}
}
