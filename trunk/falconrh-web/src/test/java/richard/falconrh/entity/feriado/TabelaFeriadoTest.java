package richard.falconrh.entity.feriado;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author richard
 * @version $Revision: 1.0 $
 */
public class TabelaFeriadoTest {
	private TabelaFeriado tabelaFeriado;
	
	@Before
	public void setUp(){
		tabelaFeriado = new TabelaFeriado();
	}
	
	@After
	public void tearDown(){
		tabelaFeriado = null;
	}
	
	@Test
	public void testaGetsESets(){
		Integer ano = 2011;
		String descricao = "Tabela de Feriados";
		Long id = 444L;
		Long idFeriado = 123456L;
		Feriado feriado = new Feriado(idFeriado);
		List<Feriado> listaFeriados = new ArrayList<Feriado>();
		listaFeriados.add(feriado);
		tabelaFeriado.setAno(ano);
		tabelaFeriado.setDescricao(descricao);
		tabelaFeriado.setId(id);
		tabelaFeriado.setListaFeriados(listaFeriados);
		
		assertEquals(ano, tabelaFeriado.getAno());
		assertEquals(descricao, tabelaFeriado.getDescricao());
		assertEquals(id, tabelaFeriado.getId());
		assertEquals(listaFeriados, tabelaFeriado.getListaFeriados());
	}
	
	@Test
	public void testaConstrutoraComParametroLong(){
		Long id = 222L;
		tabelaFeriado = new TabelaFeriado(id);
		assertEquals(id, tabelaFeriado.getId());
	}
}
