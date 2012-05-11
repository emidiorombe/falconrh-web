package richard.falconrh.entity.feriado;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import richard.falconrh.modelo.enums.TipoFeriado;


/**
 * @author richard
 * @version $Revision: 1.0 $
 */
public class FeriadoTest {
	private Feriado feriado;
	
	@Before
	public void setUp(){
		this.feriado = new Feriado();
	}
	
	@After
	public void tearDown(){
		this.feriado = null;
	}
	
	@Test
	public void testaGetsESets(){
		Date dataFeriado = new Date();
		String descricao = "Nossa Senhora Aparecida";
		Long id = 8787L;
		Long idTabelaFeriado = 999L;
		TabelaFeriado tabelaFeriados = new TabelaFeriado(idTabelaFeriado);
		TipoFeriado tipoFeriado = TipoFeriado.NACIONAL;
		
		feriado.setData(dataFeriado);
		feriado.setDescricao(descricao);
		feriado.setId(id);
		feriado.setTabelaFeriado(tabelaFeriados);
		feriado.setTipoFeriado(tipoFeriado);
		
		assertEquals(dataFeriado, feriado.getData());
		assertEquals(id, feriado.getId());
		assertEquals(descricao, feriado.getDescricao());
		assertEquals(tabelaFeriados, feriado.getTabelaFeriado());
		assertEquals(tipoFeriado, feriado.getTipoFeriado());
	}
	
	@Test
	public void testaConstrutoraComParametroLong(){
		Long id = 969L;
		feriado = new Feriado(id);
		assertEquals(id, feriado.getId());
	}
}
