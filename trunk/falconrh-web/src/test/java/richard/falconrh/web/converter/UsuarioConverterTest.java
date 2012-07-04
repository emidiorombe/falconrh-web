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

import richard.falconrh.entity.pessoa.Usuario;
import richard.falconrh.service.UsuarioServices;
import richard.falconrh.service.impl.UsuarioServicesImpl;

@RunWith(PowerMockRunner.class)
@PrepareForTest(UsuarioConverter.class)
public class UsuarioConverterTest {
	private UsuarioConverter usuarioConverter;
	private final String nomeMetodoMockado = "getUsuarioServices";
	private UsuarioServices retornoEsperadoAoChamarMetodoMockado;
	
	@Before
	public void setUp() throws Exception{
		usuarioConverter = spy(new UsuarioConverter());
		final Usuario usuarioEsperada = criarUsuario();
		retornoEsperadoAoChamarMetodoMockado = Mockito.mock(UsuarioServicesImpl.class);
		Mockito.when(retornoEsperadoAoChamarMetodoMockado.obterPeloId(Usuario.class, usuarioEsperada.getId())).thenReturn(usuarioEsperada);
		PowerMockito.when(usuarioConverter, nomeMetodoMockado). thenReturn(retornoEsperadoAoChamarMetodoMockado);
	}
	
	@After
	public void tearDown() throws Exception{
		usuarioConverter = null;
	}
	
	@Test
	public void getAsObjectDeveRetornarUsuario() throws Exception{
		
		final String value = "1";
		final Usuario usuarioEsperada = criarUsuario();

		FacesContext context = Mockito.mock(FacesContext.class);
		UIComponent component = Mockito.mock(UIComponent.class);
		Object object = usuarioConverter.getAsObject(context, component, value);
		assertNotNull(object);
		assertTrue(object instanceof Usuario);
		Usuario usuarioRetorno = (Usuario)object;
		assertEquals(usuarioEsperada, usuarioRetorno);

		// Optionally verify that the private method was actually called
		verifyPrivate(usuarioConverter).invoke(nomeMetodoMockado);
	}
	
	@Test
	public void getAsObjectDeveRetornarNullQuandoNaoEncontrarObjeto() throws Exception{
		final String value = "0";

		FacesContext context = Mockito.mock(FacesContext.class);
		UIComponent component = Mockito.mock(UIComponent.class);
		Object object = usuarioConverter.getAsObject(context, component, value);
		assertNull(object);
		// Optionally verify that the private method was actually called
		verifyPrivate(usuarioConverter).invoke(nomeMetodoMockado);
	}
	
	@Test
	public void getAsStringDeveRetornarString() throws Exception{
		final Usuario usuarioEsperada = criarUsuario();
		final String idUsuario = "1";
		
		FacesContext context = Mockito.mock(FacesContext.class);
		UIComponent component = Mockito.mock(UIComponent.class);
		String retorno = usuarioConverter.getAsString(context, component, usuarioEsperada);
		assertNotNull(retorno);
		assertTrue(retorno instanceof String);
		assertEquals(idUsuario, retorno);
	}
	
	@Test
	public void getAsStringDeveRetornarNullQuandoNaoForinstanciaUsuario() throws Exception{
		FacesContext context = Mockito.mock(FacesContext.class);
		UIComponent component = Mockito.mock(UIComponent.class);
		String retorno = usuarioConverter.getAsString(context, component, new Object());
		assertNull(retorno);
	}

	protected Usuario criarUsuario() {
		final Long id = 1L;
		Usuario usuario = new Usuario();
		usuario.setId(id);
		//TODO incluir mais sets
		return usuario;
	}
}
