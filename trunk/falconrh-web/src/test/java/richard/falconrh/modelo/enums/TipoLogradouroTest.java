package richard.falconrh.modelo.enums;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * @author richard
 * @version $Revision: 1.0 $
 */
public class TipoLogradouroTest {

	@Test
	public void test() {
		String descricao = "Rua";
		TipoLogradouro tipoLogradouro = TipoLogradouro.RUA;
		assertEquals(descricao, tipoLogradouro.getNome());
	}

}
