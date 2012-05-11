package richard.falconrh.modelo.enums;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * @author richard
 * @version $Revision: 1.0 $
 */
public class TipoTelefoneTest {

	@Test
	public void test() {
		String descricao = "Telefone Residencial";
		TipoTelefone tipoTelefone = TipoTelefone.RESIDENCIAL;
		assertEquals(descricao, tipoTelefone.getDescricao());
	}

}
