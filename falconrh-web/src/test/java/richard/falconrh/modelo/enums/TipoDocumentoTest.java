package richard.falconrh.modelo.enums;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * @author richard
 * @version $Revision: 1.0 $
 */
public class TipoDocumentoTest {

	@Test
	public void test() {
		String descricao = "CPF";
		TipoDocumento tipoDocumento = TipoDocumento.CPF;
		assertEquals(descricao, tipoDocumento.getDescricao());
	}

}
