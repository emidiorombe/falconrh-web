package richard.falconrh.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import richard.falconrh.entity.seguranca.Acao;
import richard.falconrh.modelo.BaseJPATest;

public class AcaoServicesImplTest  extends BaseJPATest{
	private AcaoServicesImpl acaoServices;
	
	static{
		nomeArquivoBase = "src/test/resources/acoes.xml";
		nomeArquivoAposInclusao = "src/test/resources/acoes-inclusao.xml";
		nomeArquivoAposAlteracao = "src/test/resources/acoes-alteracao.xml";
		nomeArquivoAposExclusao = "src/test/resources/acoes-exclusao.xml";
		nomeSequence = "SEQ_ACOES";
	}
	
	/**
	 * Method setUp.
	 * @throws Exception 
	 */
	@Before
	public void setUp() throws Exception{
		super.setUp();
		acaoServices = new AcaoServicesImpl();
		acaoServices.setEntityManager(getEntityManager());
	}
	
	/**
	 * Method cadastrarDeveCadastrarAcao.
	 * @throws Exception
	 */
	@Test
	public void cadastrarDeveCadastrarAcao() throws Exception {
		Acao acao = new Acao();
		acao.setNome("Cadastrar");
		acaoServices.getEntityManager().getTransaction().begin();
		acaoServices.cadastrar(acao);
		acaoServices.getEntityManager().getTransaction().commit();
		verificarEstruturaBancoAposInclusao("ACOES");
	}
	
	@Test
	public void alterarDeveAlterarAcao() throws Exception{
		Acao acao = getEntityManager().find(Acao.class, 2L);
		acao.setNome("Pesquisar");
		acaoServices.getEntityManager().getTransaction().begin();
		acaoServices.alterar(acao);
		acaoServices.getEntityManager().getTransaction().commit();
		verificarEstruturaBancoAposAlteracao("ACOES");
	}
	
	@Test
	public void excluirDeveAlterarAcao() throws Exception{
		Acao acao = getEntityManager().find(Acao.class, 2L);
		acaoServices.getEntityManager().getTransaction().begin();
		acaoServices.excluirPeloId(Acao.class, acao.getId());
		acaoServices.getEntityManager().getTransaction().commit();
		verificarEstruturaBancoAposExclusao("ACOES");
	}
	
	@Test
	public void pesquisarAcao() throws Exception{
		Acao acaoSample = new Acao();
		acaoSample.setNome("r");
		Set<Acao> lista = acaoServices.obterListaPeloExemplo(acaoSample);
		assertNotNull(lista);
		assertEquals(2, lista.size());
	}
	
}
