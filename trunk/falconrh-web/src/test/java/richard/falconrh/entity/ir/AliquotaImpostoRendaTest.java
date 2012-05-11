package richard.falconrh.entity.ir;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author richard
 * @version $Revision: 1.0 $
 */
public class AliquotaImpostoRendaTest {
	private AliquotaImpostoRenda aliquotaImpostoRenda;
	private Long id = 123L;
	private Long idTAbelaImpostoRenda = 555L;
	private Double aliquota = 52.6;
	private String descricao = "descricao";
	private TabelaImpostoRenda tabelaImpostoRenda = new TabelaImpostoRenda();
	private Double valorFinal = 8585d;
	private Double valorInicial = 121d;
	private Double valorParcelaADeduzir = 5d;
	
	
	@Before
	public void setUp(){
		tabelaImpostoRenda = new TabelaImpostoRenda();
		tabelaImpostoRenda.setId(idTAbelaImpostoRenda);
		aliquotaImpostoRenda = new AliquotaImpostoRenda();
		aliquotaImpostoRenda.setAliquota(aliquota);
		aliquotaImpostoRenda.setDescricao(descricao);
		aliquotaImpostoRenda.setId(id);
		aliquotaImpostoRenda.setTabelaImpostoRenda(tabelaImpostoRenda);
		aliquotaImpostoRenda.setValorFinal(valorFinal);
		aliquotaImpostoRenda.setValorInicial(valorInicial);
		aliquotaImpostoRenda.setValorParcelaADeduzir(valorParcelaADeduzir);
	}
	
	@After
	public void tearDown(){
		aliquotaImpostoRenda = null;
	}
	
	@Test
	public void testaGestESets(){
		assertEquals(id, aliquotaImpostoRenda.getId());
		assertEquals(descricao, aliquotaImpostoRenda.getDescricao());
		assertEquals(valorInicial, aliquotaImpostoRenda.getValorInicial());
		assertEquals(valorFinal, aliquotaImpostoRenda.getValorFinal());
		assertEquals(tabelaImpostoRenda, aliquotaImpostoRenda.getTabelaImpostoRenda());
		assertEquals(valorParcelaADeduzir, aliquotaImpostoRenda.getValorParcelaADeduzir());
		assertEquals(aliquota, aliquotaImpostoRenda.getAliquota());
	}
	
	@Test
	public void testaConstrutoraComParametroLong(){
		Long id = 9190L;
		aliquotaImpostoRenda = new AliquotaImpostoRenda(id);
		assertEquals(id, aliquotaImpostoRenda.getId());
	}
}
