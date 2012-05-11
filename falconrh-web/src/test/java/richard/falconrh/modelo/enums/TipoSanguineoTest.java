package richard.falconrh.modelo.enums;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * @author richard
 * @version $Revision: 1.0 $
 */
public class TipoSanguineoTest {

	@Test
	public void test() {
		String descricao = "A-";
		TipoSanguineo tipoSanguineo = TipoSanguineo.A_NEGATIVO;
		assertEquals(descricao, tipoSanguineo.getTipo());
	}

}
