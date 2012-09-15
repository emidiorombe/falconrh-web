package richard.falconrh.entity.banco;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Richard Mendes Madureira
 * @version $Revision: 1.0 $
 */
public class BancoTest {

	private Banco banco;
	
	@Before
	public void setUp(){
		this.banco = new Banco();
	}
	
	@After
	public void tearDown(){
		this.banco = null;
	}
	
	@Test
	public void testGetsESets(){
		Long id = 1L;
		String nome = "Nome do Banco";
		String codigoFebraban = "999";
		Long idAgencia = 9L;
		Set<Agencia> listaAgencias = new HashSet<Agencia>();
		listaAgencias.add(new Agencia(idAgencia));

		banco.setCodigoFebraban(codigoFebraban);
		banco.setId(id);
		banco.setListaAgencias(listaAgencias);
		banco.setNome(nome);
		
		assertEquals(id, banco.getId());
		assertEquals(nome, banco.getNome());
		assertEquals(codigoFebraban, banco.getCodigoFebraban());
		assertArrayEquals(listaAgencias.toArray(), banco.getListaAgencias().toArray());
	}
	
	@Test
	public void testarConstrutoraComParametroLong(){
		Long idBanco = 1L;
		banco = new Banco(idBanco);
		assertEquals(idBanco, banco.getId());
	}
	
	@Test
	public void testarConstrutoraComParametroStringEString(){
		String codigoFebraban = "001";
		String nome = "Banco do Brasil";
		banco = new Banco(codigoFebraban, nome);
		assertEquals(codigoFebraban, banco.getCodigoFebraban());
		assertEquals(nome, banco.getNome());
	}
	
	@Test
	public void getDescricaoDeveRetornarCodigoFebrabanENomeDoBanco(){
		final String descricaoEsperada = "001 - Banco do Brasil S/A";
		banco = new Banco();
		banco.setCodigoFebraban("001");
		banco.setNome("Banco do Brasil S/A");
		assertEquals(descricaoEsperada, banco.getDescricao());
	}
	
	@Test
	public void compareToDeveCompararBancosPeloCodigoFebraban(){
		Banco banco = new Banco();
		banco.setCodigoFebraban("001");
		Banco banco2 = new Banco();
		banco2.setCodigoFebraban("399");
		int a = banco.compareTo(banco2);
		assertTrue(a<0);
		a = banco2.compareTo(banco);
		assertTrue(a>0);
	}
	
	@Test
	public void compareToDeveCompararBancosPeloNomeQuandoNaoHouverCodigo(){
		Banco banco = new Banco();
		banco.setNome("Banco do Brasil");
		Banco banco2 = new Banco();
		banco2.setNome("Banco do Bradesco");
		int a = banco.compareTo(banco2);
		assertTrue(a>0);
		a = banco2.compareTo(banco);
		assertTrue(a<0);
	}
	
	@Test
	public void compareToDeveRetornar0quandoUmdosObjetosNaoPossuirCodigoFebraban(){
		Banco banco2 = new Banco();
		banco2.setCodigoFebraban("399");

		assertEquals (0, banco.compareTo(banco2));
		assertEquals (0, banco2.compareTo(banco));
	}
	
	@Test
	public void compareToDeveRetornar0quandoUmdosObjetosNaoPossuirNome(){
		Banco banco2 = new Banco();
		banco2.setNome("Banco do Bradesco");
		
		assertEquals (0, banco.compareTo(banco2));
		assertEquals (0, banco2.compareTo(banco));
	}
	
	@Test
	public void compareToDeveRetornar0QuandoNaoHouverNemcodigoNemNome(){
		Banco banco2 = new Banco();
		assertEquals(0, banco.compareTo(banco2));
	}
}
