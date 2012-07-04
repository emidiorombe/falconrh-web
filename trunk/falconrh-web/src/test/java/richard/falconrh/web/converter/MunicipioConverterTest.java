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

import richard.falconrh.entity.localizacao.Municipio;
import richard.falconrh.modelo.enums.UF;
import richard.falconrh.service.MunicipioServices;
import richard.falconrh.service.impl.MunicipioServicesImpl;

@RunWith(PowerMockRunner.class)
@PrepareForTest(MunicipioConverter.class)
public class MunicipioConverterTest {
	private MunicipioConverter municipioConverter;
	private final String nomeMetodoMockado = "getMunicipioServices";
	private MunicipioServices retornoEsperadoAoChamarMetodoMockado;
	
	@Before
	public void setUp() throws Exception{
		municipioConverter = spy(new MunicipioConverter());
		final Municipio municipioEsperada = criarMunicipio();
		retornoEsperadoAoChamarMetodoMockado = Mockito.mock(MunicipioServicesImpl.class);
		Mockito.when(retornoEsperadoAoChamarMetodoMockado.obterPeloId(Municipio.class, municipioEsperada.getId())).thenReturn(municipioEsperada);
		PowerMockito.when(municipioConverter, nomeMetodoMockado). thenReturn(retornoEsperadoAoChamarMetodoMockado);
	}
	
	@After
	public void tearDown() throws Exception{
		municipioConverter = null;
	}
	
	@Test
	public void getAsObjectDeveRetornarMunicipio() throws Exception{
		
		final String value = "1";
		final Municipio municipioEsperada = criarMunicipio();

		FacesContext context = Mockito.mock(FacesContext.class);
		UIComponent component = Mockito.mock(UIComponent.class);
		Object object = municipioConverter.getAsObject(context, component, value);
		assertNotNull(object);
		assertTrue(object instanceof Municipio);
		Municipio municipioRetorno = (Municipio)object;
		assertEquals(municipioEsperada, municipioRetorno);

		// Optionally verify that the private method was actually called
		verifyPrivate(municipioConverter).invoke(nomeMetodoMockado);
	}
	
	@Test
	public void getAsObjectDeveRetornarNullQuandoNaoEncontrarObjeto() throws Exception{
		final String value = "0";

		FacesContext context = Mockito.mock(FacesContext.class);
		UIComponent component = Mockito.mock(UIComponent.class);
		Object object = municipioConverter.getAsObject(context, component, value);
		assertNull(object);
		// Optionally verify that the private method was actually called
		verifyPrivate(municipioConverter).invoke(nomeMetodoMockado);
	}
	
	@Test
	public void getAsStringDeveRetornarString() throws Exception{
		final Municipio municipioEsperada = criarMunicipio();
		final String idMunicipio = "1";
		
		FacesContext context = Mockito.mock(FacesContext.class);
		UIComponent component = Mockito.mock(UIComponent.class);
		String retorno = municipioConverter.getAsString(context, component, municipioEsperada);
		assertNotNull(retorno);
		assertTrue(retorno instanceof String);
		assertEquals(idMunicipio, retorno);
	}
	
	@Test
	public void getAsStringDeveRetornarNullQuandoNaoForinstanciaMunicipio() throws Exception{
		FacesContext context = Mockito.mock(FacesContext.class);
		UIComponent component = Mockito.mock(UIComponent.class);
		String retorno = municipioConverter.getAsString(context, component, new Object());
		assertNull(retorno);
	}

	protected Municipio criarMunicipio() {
		final Long id = 1L;
		final String nome = "Florianópolis";
		UF uf = UF.SANTA_CATARINA;
		Municipio municipio = new Municipio();
		municipio.setId(id);
		municipio.setNome(nome);
		municipio.setUf(uf);
		return municipio;
	}
}
