package richard.falconrh.web.converter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.powermock.api.mockito.PowerMockito.spy;
import static org.powermock.api.mockito.PowerMockito.verifyPrivate;

import java.util.Date;

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

import richard.falconrh.entity.pessoa.Pessoa;
import richard.falconrh.modelo.enums.EstadoCivil;
import richard.falconrh.modelo.enums.Etnia;
import richard.falconrh.modelo.enums.Sexo;
import richard.falconrh.service.PessoaServices;
import richard.falconrh.service.impl.PessoaServicesImpl;

@RunWith(PowerMockRunner.class)
@PrepareForTest(PessoaConverter.class)
public class PessoaConverterTest {
	private PessoaConverter pessoaConverter;
	private final String nomeMetodoMockado = "getPessoaServices";
	private PessoaServices retornoEsperadoAoChamarMetodoMockado;
	
	@Before
	public void setUp() throws Exception{
		pessoaConverter = spy(new PessoaConverter());
		final Pessoa pessoaEsperada = criarPessoa();
		retornoEsperadoAoChamarMetodoMockado = Mockito.mock(PessoaServicesImpl.class);
		Mockito.when(retornoEsperadoAoChamarMetodoMockado.obterPeloId(Pessoa.class, pessoaEsperada.getId())).thenReturn(pessoaEsperada);
		PowerMockito.when(pessoaConverter, nomeMetodoMockado). thenReturn(retornoEsperadoAoChamarMetodoMockado);
	}
	
	@After
	public void tearDown() throws Exception{
		pessoaConverter = null;
	}
	
	@Test
	public void getAsObjectDeveRetornarPessoa() throws Exception{
		final String value = "1";
		final Pessoa pessoaEsperada = criarPessoa();

		FacesContext context = Mockito.mock(FacesContext.class);
		UIComponent component = Mockito.mock(UIComponent.class);
		Object object = pessoaConverter.getAsObject(context, component, value);
		assertNotNull(object);
		assertTrue(object instanceof Pessoa);
		Pessoa pessoaRetorno = (Pessoa)object;
		assertEquals(pessoaEsperada.getId(), pessoaRetorno.getId());
		assertEquals(pessoaEsperada.getNome(), pessoaRetorno.getNome());

		// Optionally verify that the private method was actually called
		verifyPrivate(pessoaConverter).invoke(nomeMetodoMockado);
	}
	
	@Test
	public void getAsObjectDeveRetornarNullQuandoNaoEncontrarObjeto() throws Exception{
		final String value = "0";

		FacesContext context = Mockito.mock(FacesContext.class);
		UIComponent component = Mockito.mock(UIComponent.class);
		Object object = pessoaConverter.getAsObject(context, component, value);
		assertNull(object);
		// Optionally verify that the private method was actually called
		verifyPrivate(pessoaConverter).invoke(nomeMetodoMockado);
	}
	
	@Test
	public void getAsStringDeveRetornarString() throws Exception{
		final Pessoa pessoaEsperada = criarPessoa();
		final String idPessoa = "1";
		
		FacesContext context = Mockito.mock(FacesContext.class);
		UIComponent component = Mockito.mock(UIComponent.class);
		String retorno = pessoaConverter.getAsString(context, component, pessoaEsperada);
		assertNotNull(retorno);
		assertTrue(retorno instanceof String);
		assertEquals(idPessoa, retorno);
	}
	
	@Test
	public void getAsStringDeveRetornarNullQuandoNaoForinstanciaPessoa() throws Exception{
		FacesContext context = Mockito.mock(FacesContext.class);
		UIComponent component = Mockito.mock(UIComponent.class);
		String retorno = pessoaConverter.getAsString(context, component, new Object());
		assertNull(retorno);
	}

	protected Pessoa criarPessoa() {
		final Long id = 1L;
		final String nome = "José Aparecido da Silva";
		Pessoa pessoa = new Pessoa();
		pessoa.setId(id);
		pessoa.setNome(nome);
		pessoa.setSexo(Sexo.MASCULINO);
		pessoa.setEstadoCivil(EstadoCivil.SOLTEIRO);
		pessoa.setDeficienteFisico(Boolean.TRUE);
		pessoa.setDataNascimento(new Date());
		pessoa.setEmail("jose.aparecido@gmail.com");
		pessoa.setEtnia(Etnia.NEGRA);
		return pessoa;
	}
}
