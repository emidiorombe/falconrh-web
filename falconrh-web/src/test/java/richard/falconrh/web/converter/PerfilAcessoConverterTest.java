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

import richard.falconrh.entity.seguranca.PerfilAcesso;
import richard.falconrh.modelo.enums.NivelAcesso;
import richard.falconrh.service.PerfilAcessoServices;
import richard.falconrh.service.impl.PerfilAcessoServicesImpl;

@RunWith(PowerMockRunner.class)
@PrepareForTest(PerfilAcessoConverter.class)
public class PerfilAcessoConverterTest {
	private PerfilAcessoConverter perfilAcessoConverter;
	private final String nomeMetodoMockado = "getPerfilAcessoServices";
	private PerfilAcessoServices retornoEsperadoAoChamarMetodoMockado;
	
	@Before
	public void setUp() throws Exception{
		perfilAcessoConverter = spy(new PerfilAcessoConverter());
		final PerfilAcesso perfilAcessoEsperada = criarPerfilAcesso();
		retornoEsperadoAoChamarMetodoMockado = Mockito.mock(PerfilAcessoServicesImpl.class);
		Mockito.when(retornoEsperadoAoChamarMetodoMockado.obterPeloId(PerfilAcesso.class, perfilAcessoEsperada.getId())).thenReturn(perfilAcessoEsperada);
		PowerMockito.when(perfilAcessoConverter, nomeMetodoMockado). thenReturn(retornoEsperadoAoChamarMetodoMockado);
	}
	
	@After
	public void tearDown() throws Exception{
		perfilAcessoConverter = null;
	}
	
	@Test
	public void getAsObjectDeveRetornarPerfilAcesso() throws Exception{
		
		final String value = "1";
		final PerfilAcesso perfilAcessoEsperada = criarPerfilAcesso();

		FacesContext context = Mockito.mock(FacesContext.class);
		UIComponent component = Mockito.mock(UIComponent.class);
		Object object = perfilAcessoConverter.getAsObject(context, component, value);
		assertNotNull(object);
		assertTrue(object instanceof PerfilAcesso);
		PerfilAcesso perfilAcessoRetorno = (PerfilAcesso)object;
		assertEquals(perfilAcessoEsperada, perfilAcessoRetorno);

		// Optionally verify that the private method was actually called
		verifyPrivate(perfilAcessoConverter).invoke(nomeMetodoMockado);
	}
	
	@Test
	public void getAsObjectDeveRetornarNullQuandoNaoEncontrarObjeto() throws Exception{
		final String value = "0";

		FacesContext context = Mockito.mock(FacesContext.class);
		UIComponent component = Mockito.mock(UIComponent.class);
		Object object = perfilAcessoConverter.getAsObject(context, component, value);
		assertNull(object);
		// Optionally verify that the private method was actually called
		verifyPrivate(perfilAcessoConverter).invoke(nomeMetodoMockado);
	}
	
	@Test
	public void getAsStringDeveRetornarString() throws Exception{
		final PerfilAcesso perfilAcessoEsperada = criarPerfilAcesso();
		final String idPerfilAcesso = "1";
		
		FacesContext context = Mockito.mock(FacesContext.class);
		UIComponent component = Mockito.mock(UIComponent.class);
		String retorno = perfilAcessoConverter.getAsString(context, component, perfilAcessoEsperada);
		assertNotNull(retorno);
		assertTrue(retorno instanceof String);
		assertEquals(idPerfilAcesso, retorno);
	}
	
	@Test
	public void getAsStringDeveRetornarNullQuandoNaoForinstanciaPerfilAcesso() throws Exception{
		FacesContext context = Mockito.mock(FacesContext.class);
		UIComponent component = Mockito.mock(UIComponent.class);
		String retorno = perfilAcessoConverter.getAsString(context, component, new Object());
		assertNull(retorno);
	}

	protected PerfilAcesso criarPerfilAcesso() {
		final Long id = 1L;
		final String nome = "Praça XI de Novembro";
		PerfilAcesso perfilAcesso = new PerfilAcesso();
		perfilAcesso.setId(id);
		perfilAcesso.setNome(nome);
		perfilAcesso.setNivelAcesso(NivelAcesso.NACIONAL);
		perfilAcesso.setAtivo(Boolean.TRUE);
		return perfilAcesso;
	}
}
