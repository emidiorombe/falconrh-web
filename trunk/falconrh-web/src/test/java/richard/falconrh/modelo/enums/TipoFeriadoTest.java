package richard.falconrh.modelo.enums;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * @author richard
 * @version $Revision: 1.0 $
 */
public class TipoFeriadoTest {

	@Test
	public void test() {
		String descricao = "Feriado Nacional";
		TipoFeriado tipoFeriado = TipoFeriado.NACIONAL;
		assertEquals(descricao, tipoFeriado.getDescricao());
	}

}
