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

import richard.falconrh.entity.banco.Banco;
import richard.falconrh.service.BancoServices;
import richard.falconrh.service.impl.BancoServicesImpl;

@RunWith(PowerMockRunner.class)
@PrepareForTest(BancoConverter.class)
public class BancoConverterTest {
	private BancoConverter bancoConverter;
	private final String nomeMetodoMockado = "getBancoServices";
	private BancoServices retornoEsperadoAoChamarMetodoMockado;
	
	@Before
	public void setUp() throws Exception{
		bancoConverter = spy(new BancoConverter());
		final Banco bancoEsperada = criarBanco();
		retornoEsperadoAoChamarMetodoMockado = Mockito.mock(BancoServicesImpl.class);
		Mockito.when(retornoEsperadoAoChamarMetodoMockado.obterPeloId(Banco.class, bancoEsperada.getId())).thenReturn(bancoEsperada);
		PowerMockito.when(bancoConverter, nomeMetodoMockado). thenReturn(retornoEsperadoAoChamarMetodoMockado);
	}
	
	@After
	public void tearDown() throws Exception{
		bancoConverter = null;
	}
	
	@Test
	public void getAsObjectDeveRetornarBanco() throws Exception{
		
		final String value = "1";
		final Banco bancoEsperada = criarBanco();

		FacesContext context = Mockito.mock(FacesContext.class);
		UIComponent component = Mockito.mock(UIComponent.class);
		Object object = bancoConverter.getAsObject(context, component, value);
		assertNotNull(object);
		assertTrue(object instanceof Banco);
		Banco bancoRetorno = (Banco)object;
		assertEquals(bancoEsperada, bancoRetorno);

		// Optionally verify that the private method was actually called
		verifyPrivate(bancoConverter).invoke(nomeMetodoMockado);
	}
	
	@Test
	public void getAsObjectDeveRetornarNullQuandoNaoEncontrarObjeto() throws Exception{
		final String value = "0";

		FacesContext context = Mockito.mock(FacesContext.class);
		UIComponent component = Mockito.mock(UIComponent.class);
		Object object = bancoConverter.getAsObject(context, component, value);
		assertNull(object);
		// Optionally verify that the private method was actually called
		verifyPrivate(bancoConverter).invoke(nomeMetodoMockado);
	}
	
	@Test
	public void getAsStringDeveRetornarString() throws Exception{
		final Banco bancoEsperada = criarBanco();
		final String idBanco = "1";
		
		FacesContext context = Mockito.mock(FacesContext.class);
		UIComponent component = Mockito.mock(UIComponent.class);
		String retorno = bancoConverter.getAsString(context, component, bancoEsperada);
		assertNotNull(retorno);
		assertTrue(retorno instanceof String);
		assertEquals(idBanco, retorno);
	}
	
	@Test
	public void getAsStringDeveRetornarNullQuandoNaoForinstanciaBanco() throws Exception{
		FacesContext context = Mockito.mock(FacesContext.class);
		UIComponent component = Mockito.mock(UIComponent.class);
		String retorno = bancoConverter.getAsString(context, component, new Object());
		assertNull(retorno);
	}

	protected Banco criarBanco() {
		final Long id = 1L;
		final String nome = "Banco do Brasil";
		Banco banco = new Banco(id);
		String codigoFebraban = "001";
		banco.setId(id);
		banco.setNome(nome);
		banco.setCodigoFebraban(codigoFebraban);
		return banco;
	}
}
