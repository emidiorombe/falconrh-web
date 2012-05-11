package richard.falconrh.entity.inss;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author richard
 * @version $Revision: 1.0 $
 */
public class AliquotaINSSTest {
	private AliquotaINSS aliquotaINSS;
	
	@Before
	public void setUp(){
		aliquotaINSS = new AliquotaINSS();
	}
	
	@After
	public void tearDown(){
		aliquotaINSS = null;
	}
	
	@Test
	public void testarGetsESets(){
		Double aliquota = 123.d;
		String descricao = "descricao";
		Long id = 123L;
		TabelaINSS tabelaINSS = new TabelaINSS();
		Long idTabelaINSS = 321L;
		tabelaINSS.setId(idTabelaINSS);
		Double valorFinal = 9999d;
		Double valorInicial = 1111d;
		aliquotaINSS.setAliquota(aliquota);
		aliquotaINSS.setDescricao(descricao);
		aliquotaINSS.setId(id);
		aliquotaINSS.setTabelaINSS(tabelaINSS);
		aliquotaINSS.setValorFinal(valorFinal);
		aliquotaINSS.setValorInicial(valorInicial);
		
		assertEquals(aliquota, aliquotaINSS.getAliquota());
		assertEquals(descricao, aliquotaINSS.getDescricao());
		assertEquals(id, aliquotaINSS.getId());
		assertEquals(tabelaINSS, aliquotaINSS.getTabelaINSS());
		assertEquals(valorFinal, aliquotaINSS.getValorFinal());
		assertEquals(valorInicial, aliquotaINSS.getValorInicial());
	}
	
	@Test
	public void testaConstrutoraComParametroLong(){
		Long id = 5454L;
		aliquotaINSS = new AliquotaINSS(id);
		assertEquals(id, aliquotaINSS.getId());
	}
}
