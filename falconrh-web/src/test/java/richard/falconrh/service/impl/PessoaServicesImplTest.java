package richard.falconrh.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Set;
import java.util.TimeZone;

import org.junit.Before;
import org.junit.Test;

import richard.falconrh.entity.pessoa.Pessoa;
import richard.falconrh.exception.ServicesException;
import richard.falconrh.modelo.BaseJPATest;
import richard.falconrh.modelo.enums.EstadoCivil;
import richard.falconrh.modelo.enums.Etnia;
import richard.falconrh.modelo.enums.Nacionalidade;
import richard.falconrh.modelo.enums.Sexo;

/**
 * @author richard
 * @version $Revision: 1.0 $
 */
public class PessoaServicesImplTest extends BaseJPATest{
	private PessoaServicesImpl pessoaServices;
	
	static{
		nomeArquivoBase = "src/test/resources/pessoas.xml";
		nomeArquivoAposInclusao = "src/test/resources/pessoas-inclusao.xml";
		nomeArquivoAposAlteracao = "src/test/resources/pessoas-alteracao.xml";
		nomeArquivoAposExclusao = "src/test/resources/pessoas-exclusao.xml";
		nomeSequence = "SEQ_PESSOAS";
	}
	
	/**
	 * Method setUp.
	
	 * @throws Exception */
	@Before
	public void setUp() throws Exception{
		super.setUp();
		pessoaServices = new PessoaServicesImpl();
		pessoaServices.setEntityManager(getEntityManager());
	}
	
	/**
	 * Method testObterPessoaPeloId.
	
	 * @throws Exception */
	@Test
	public void testObterPessoaPeloId() throws Exception{
		Long id = 2L;
		Pessoa pessoa = pessoaServices.obterPeloId(Pessoa.class, id);
		assertNotNull(pessoa);
		assertEquals(id, pessoa.getId());
	}
	
	/**
	 * Method obterPessoaPeloIdDeveLancarExceptionQuandoOcorrerQualquerErro.
	
	 * @throws Exception */
	@Test(expected=ServicesException.class)
	public void obterPessoaPeloIdDeveLancarExceptionQuandoOcorrerQualquerErro() throws Exception{
		Long id = 2L;
		pessoaServices.setEntityManager(null);
		pessoaServices.obterPeloId(Pessoa.class, id);
	}
	
	/**
	 * Method cadastrarPessoaDeveCadastrarPessoa.
	
	 * @throws Exception */
	@Test
	public void cadastrarPessoaDeveCadastrarPessoa() throws Exception{
		String nome = "Juciara Mendes Madureira";
		Calendar calendar = Calendar.getInstance(new Locale("pt", "BR"));
		calendar.setTimeZone(TimeZone.getTimeZone("America/Sao_Paulo"));
		
		calendar.clear();
		calendar.set(1996, Calendar.NOVEMBER, 22);
		Date dataNascimento = calendar.getTime();
		EstadoCivil estadoCivil = EstadoCivil.SOLTEIRO;
		Etnia etnia = Etnia.NEGRA;
		Nacionalidade nacionalidade = Nacionalidade.BRASILEIRO;
		Sexo sexo = Sexo.FEMININO;
		Boolean deficienteFisico = Boolean.FALSE;
		String email = "neguinhamm@hotmail.com";
		
		Pessoa pessoa = new Pessoa();
		pessoa.setNome(nome);
		pessoa.setDataNascimento(dataNascimento);
		pessoa.setEstadoCivil(estadoCivil);
		pessoa.setEtnia(etnia);
		pessoa.setNacionalidade(nacionalidade);
		pessoa.setSexo(sexo);
		pessoa.setDeficienteFisico(deficienteFisico);
		pessoa.setEmail(email);
		
		pessoaServices.getEntityManager().getTransaction().begin();
		pessoaServices.cadastrar(pessoa);
		pessoaServices.getEntityManager().getTransaction().commit();
		
		verificarEstruturaBancoAposInclusao("PESSOAS");
	}
	
	/**
	 * Method cadastrarPessoaDeveLancarExceptionQuandoOcorrerQualquerErro.
	
	 * @throws ServicesException */
	@Test(expected=ServicesException.class)
	public void cadastrarPessoaDeveLancarExceptionQuandoOcorrerQualquerErro() throws ServicesException{
		String nome = "Juciara Mendes Madureira";
		Calendar calendar = Calendar.getInstance(new Locale("pt", "BR"));
		calendar.setTimeZone(TimeZone.getTimeZone("America/Sao_Paulo"));
		
		calendar.clear();
		calendar.set(1996, Calendar.NOVEMBER, 22);
		Date dataNascimento = calendar.getTime();
		EstadoCivil estadoCivil = EstadoCivil.SOLTEIRO;
		Etnia etnia = Etnia.NEGRA;
		Nacionalidade nacionalidade = Nacionalidade.BRASILEIRO;
		Sexo sexo = Sexo.FEMININO;
		Boolean deficienteFisico = Boolean.FALSE;
		String email = "neguinhamm@hotmail.com";
		
		Pessoa pessoa = new Pessoa();
		pessoa.setNome(nome);
		pessoa.setDataNascimento(dataNascimento);
		pessoa.setEstadoCivil(estadoCivil);
		pessoa.setEtnia(etnia);
		pessoa.setNacionalidade(nacionalidade);
		pessoa.setSexo(sexo);
		pessoa.setDeficienteFisico(deficienteFisico);
		pessoa.setEmail(email);
		
		pessoaServices.getEntityManager().getTransaction().begin();
		pessoaServices.setEntityManager(null);
		pessoaServices.cadastrar(pessoa);
		pessoaServices.getEntityManager().getTransaction().commit();
		
	}
	
	/**
	 * Method alterarPessoaDeveAlterarPessoa.
	
	 * @throws Exception */
	@Test
	public void alterarPessoaDeveAlterarPessoa() throws Exception{
		Pessoa pessoa = pessoaServices.getEntityManager().find(Pessoa.class, 2L);
		String email = "richard.madureira@gmail.com";
		pessoa.setEmail(email);
		pessoaServices.getEntityManager().getTransaction().begin();
		pessoaServices.alterar(pessoa);
		pessoaServices.getEntityManager().getTransaction().commit();

		verificarEstruturaBancoAposAlteracao("PESSOAS");
	}
	
	/**
	 * Method alterarPessoaDeveLancarExceptionQuandoOcorrerQualquerErro.
	
	 * @throws Exception */
	@Test(expected=ServicesException.class)
	public void alterarPessoaDeveLancarExceptionQuandoOcorrerQualquerErro() throws Exception{
		Pessoa pessoa = pessoaServices.getEntityManager().find(Pessoa.class, 2L);
		String email = "richard.madureira@gmail.com";
		pessoa.setEmail(email);
		pessoaServices.getEntityManager().getTransaction().begin();
		pessoaServices.setEntityManager(null);
		pessoaServices.alterar(pessoa);
		pessoaServices.getEntityManager().getTransaction().commit();
	}
	
	/**
	 * Method excluirPessoaDeveExcluir.
	
	 * @throws Exception */
	@Test
	public void excluirPessoaDeveExcluir() throws Exception{
		Long id = 2L;
		pessoaServices.getEntityManager().getTransaction().begin();
		pessoaServices.excluirPeloId(Pessoa.class, id);
		pessoaServices.getEntityManager().getTransaction().commit();
		
		verificarEstruturaBancoAposExclusao("PESSOAS");
	}
	
	/**
	 * Method excluirPessoaDeveLancarExceptionQuandoOcorrerQualquerErro.
	
	 * @throws ServicesException */
	@Test(expected=ServicesException.class)
	public void excluirPessoaDeveLancarExceptionQuandoOcorrerQualquerErro() throws ServicesException{
		Long id = 2L;
		pessoaServices.getEntityManager().getTransaction().begin();
		pessoaServices.setEntityManager(null);
		pessoaServices.excluirPeloId(Pessoa.class, id);
		pessoaServices.getEntityManager().getTransaction().commit();
	}

	/**
	 * Method obterListaPeloExemploDeveRetornarListaDeObjetosEncontrados.
	
	 * @throws ServicesException */
	@Test
	public void obterListaPeloExemploDeveRetornarListaDeObjetosEncontrados() throws ServicesException{
		Pessoa pessoa = new Pessoa();
		pessoa.setNome("Madureira");
		pessoa.setEstadoCivil(EstadoCivil.CASADO);
		Set<Pessoa> listaPessoas = pessoaServices.obterListaPeloExemplo(pessoa);
		assertNotNull(listaPessoas);
		assertEquals(1, listaPessoas.size());
	}
	
	/**
	 * Method obterListaPeloExemploDeveRetornarListaDeObjetosEncontrados1.
	
	 * @throws ServicesException */
	@Test
	public void obterListaPeloExemploDeveRetornarListaDeObjetosEncontrados1() throws ServicesException{
		Pessoa pessoa = new Pessoa();
		pessoa.setNome("Richard Mendes Madureira");
		pessoa.setEstadoCivil(EstadoCivil.SOLTEIRO);
		pessoa.setEmail("richard.madureira@gmail.com");
		pessoa.setNacionalidade(Nacionalidade.BRASILEIRO);
		pessoa.setEtnia(Etnia.NEGRA);
		pessoa.setDeficienteFisico(Boolean.FALSE);
		pessoa.setNacionalidade(Nacionalidade.BRASILEIRO);
		pessoa.setSexo(Sexo.MASCULINO);
		
		Calendar c = Calendar.getInstance(new Locale("pt", "BR"));
		c.clear();
		c.set(Calendar.YEAR, 1980);
		c.set(Calendar.DAY_OF_MONTH, 10);
		c.set(Calendar.MONTH, Calendar.JULY);
		pessoa.setDataNascimento(c.getTime());
		Set<Pessoa> listaPessoas = pessoaServices.obterListaPeloExemplo(pessoa);

		assertNotNull(listaPessoas);
		assertEquals(1, listaPessoas.size());
	}
	
	/**
	 * Method obterListaPeloExemploDeveRetornarListaVaziaQuandoNaoEncontrarNada.
	
	 * @throws ServicesException */
	@Test
	public void obterListaPeloExemploDeveRetornarListaVaziaQuandoNaoEncontrarNada() throws ServicesException{
		Pessoa pessoa = new Pessoa();
		pessoa.setNome("XXXXXXXXXXX");
		Set<Pessoa> listaPessoas = pessoaServices.obterListaPeloExemplo(pessoa);
		assertNotNull(listaPessoas);
		assertTrue(listaPessoas.isEmpty());
	}
	
	/**
	 * Method obterListaPeloExemploDeveLancarExceptionQuandoOcorrerQualquerErro.
	
	 * @throws ServicesException */
	@Test(expected=ServicesException.class)
	public void obterListaPeloExemploDeveLancarExceptionQuandoOcorrerQualquerErro() throws ServicesException{
		Pessoa pessoa = new Pessoa();
		pessoa.setNome("Madureira");
		pessoa.setEstadoCivil(EstadoCivil.CASADO);
		pessoaServices.setEntityManager(null);
		pessoaServices.obterListaPeloExemplo(pessoa);
	}
}