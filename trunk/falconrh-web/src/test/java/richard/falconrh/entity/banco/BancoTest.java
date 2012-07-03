package richard.falconrh.entity.banco;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author richard
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
}
